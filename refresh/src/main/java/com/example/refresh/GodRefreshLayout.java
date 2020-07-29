package com.example.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class GodRefreshLayout extends LinearLayout {

    private BaseRefreshManager mRefreshManager;
    private Context mContext;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private int downY;
    private int moveY;
    private int minHeadViewHight;
    private int maxHeadViewHieght;
    private RefreshingListener mRefreshListener;
    private int interceptDownY;
    private int interceptDownX;
    private RecyclerView recyclerView;
    private ScrollView scrollView;

    public GodRefreshLayout(Context context) {
        super (context);
        initView (context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
        initView (context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        initView (context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    public void setRefreshManager() {
        mRefreshManager = new DefaultRefreshManager (mContext);
        initHeaderView ();

    }

    public void setRefreshManager(BaseRefreshManager manager) {
        mRefreshManager = manager;
        initHeaderView ();
    }

    public void refreshOver() {
        hideHeadView (getHeadViewLayoutParams ());
    }

    public interface RefreshingListener {
        void onRefreshing();
    }

    public void setRefreshListener(RefreshingListener refreshListener) {
        this.mRefreshListener = refreshListener;
    }

    private void initHeaderView() {

        setOrientation (VERTICAL);
        mHeaderView = mRefreshManager.getHeaderView ();
        mHeaderView.measure (0, 0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight ();
        minHeadViewHight = -mHeaderViewHeight;
        maxHeadViewHieght = (int) (mHeaderViewHeight * 0.3f);
        LayoutParams params = new LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, mHeaderViewHeight);
        params.topMargin = minHeadViewHight;
        addView (mHeaderView, 0, params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction ()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY ();
                return true;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) event.getY ();
                if (downY == 0){
                    downY = interceptDownY;
                }
                int dy = moveY - downY;
                if (dy > 0) {
                    LayoutParams layoutParams = getHeadViewLayoutParams ();
                    int topMargin = (int) Math.min (dy / 1.8f + minHeadViewHight, maxHeadViewHieght);
                    if(topMargin <=0){
                        float percet = (Math.abs (minHeadViewHight)-Math.abs (topMargin))*1.0f/Math.abs (minHeadViewHight);
                        mRefreshManager.downRefreshPercet(percet);
                    }
                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNREFRESH) {
                        mCurrentRefreshState = RefreshState.DOWNREFRESH;
                        handleRefreshState (mCurrentRefreshState);
                    } else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH) {
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        handleRefreshState (mCurrentRefreshState);
                    }
                    layoutParams.topMargin = topMargin;
                    mHeaderView.setLayoutParams (layoutParams);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handleEventUp ()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent (event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction ()) {
            case MotionEvent.ACTION_DOWN:
                interceptDownY = (int) ev.getY ();
                interceptDownX = (int) ev.getX ();
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = (int) (ev.getY ()-interceptDownY);
                int dx = (int) (ev.getX ()-interceptDownX);
                if (Math.abs (dy)>Math.abs (dx) && handleChildViewIsTop()){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent (ev);
    }

    private boolean handleChildViewIsTop() {
        if (recyclerView != null){
            return RefreshScrollingUtil.isRecyclerViewToTop (recyclerView);
        }
        if (scrollView != null){
            return RefreshScrollingUtil.isScrollViewOrWebViewToTop (scrollView);
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate ();
        View childAt = getChildAt (0);
        if (childAt instanceof RecyclerView){
            recyclerView = (RecyclerView) childAt;
        }
    }

    private boolean handleEventUp() {
        final LayoutParams layoutParams = getHeadViewLayoutParams ();
        if (mCurrentRefreshState == RefreshState.DOWNREFRESH) {
            hideHeadView (layoutParams);
        } else if (mCurrentRefreshState == RefreshState.RELEASEREFRESH) {
            layoutParams.topMargin = 0;
            mHeaderView.setLayoutParams (layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState (mCurrentRefreshState);
            if (mRefreshListener != null) {
                mRefreshListener.onRefreshing ();
            }
        }
        return layoutParams.topMargin > minHeadViewHight;
    }

    private void hideHeadView(final LayoutParams layoutParams) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt (layoutParams.topMargin, minHeadViewHight);
        valueAnimator.addUpdateListener (new ValueAnimator.AnimatorUpdateListener () {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue ();
                layoutParams.topMargin = animatedValue;
                mHeaderView.setLayoutParams (layoutParams);
            }
        });
        valueAnimator.addListener (new AnimatorListenerAdapter () {
            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentRefreshState = RefreshState.IDDLE;
                handleRefreshState (mCurrentRefreshState);
            }
        });
        valueAnimator.setDuration (500);
        valueAnimator.start ();
    }

    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeaderView.getLayoutParams ();
    }

    private void handleRefreshState(RefreshState mCurrentRefreshState) {
        switch (mCurrentRefreshState) {
            case REFRESHING:
                mRefreshManager.refreshing ();
                break;
            case IDDLE:
                mRefreshManager.iddleRefresh ();
                break;
            case DOWNREFRESH:
                mRefreshManager.downRefresh ();
                break;
            case RELEASEREFRESH:
                mRefreshManager.releaseRefresh ();
                break;
            default:
                break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDDLE;

    private enum RefreshState {
        IDDLE, DOWNREFRESH, RELEASEREFRESH, REFRESHING, REFRESHOVER
    }
}
