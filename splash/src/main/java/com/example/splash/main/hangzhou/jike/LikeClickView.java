package com.example.splash.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.splash.R;
import com.example.splash.main.tools.SystemUtil;

public class LikeClickView extends View {

    private boolean isLike;
    private Bitmap likeBitmap;
    private Bitmap unLikeBitmap;
    private Bitmap shiningBitmap;
    private Paint paint;
    private int left;
    private int top;
    private float handScale = 1.0f;
    private int centerX;
    private int centerY;

    public LikeClickView(Context context) {
        super (context,null,0);
        init();
    }


    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        super (context, null,0);
        init ();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes (attrs, R.styleable.JiKeLikeView);
        isLike = typedArray.getBoolean (R.styleable.JiKeLikeView_is_like, false);
        typedArray.recycle ();
        init ();
    }

    private void init() {
        Resources resources = getResources ();
        likeBitmap = BitmapFactory.decodeResource (resources, R.mipmap.ic_message_like);
        unLikeBitmap = BitmapFactory.decodeResource (resources, R.mipmap.ic_message_unlike);
        shiningBitmap = BitmapFactory.decodeResource (resources, R.mipmap.ic_message_like_shining);
        paint = new Paint ();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;
        int maxHeight = likeBitmap.getHeight ()+ SystemUtil.dp2px (getContext (),20);
        int maxWidth = likeBitmap.getHeight ()+ SystemUtil.dp2px (getContext (),30);
        int mode = MeasureSpec.getMode (widthMeasureSpec);
        int widthSize = MeasureSpec.getSize (widthMeasureSpec);
        int heightSize = MeasureSpec.getSize (heightMeasureSpec);

        if (mode !=MeasureSpec.EXACTLY){
            int suggestedMinimumHeight = getSuggestedMinimumHeight ();
            int suggestedMinimumWidth = getSuggestedMinimumWidth ();
            if (suggestedMinimumWidth == 0){
                measureWidth = maxWidth;
            }else {
                measureWidth = Math.min(suggestedMinimumWidth,maxWidth);
            }
            if (suggestedMinimumHeight == 0){
                measureHeight = maxHeight;
            }else {
                measureHeight = Math.min(suggestedMinimumHeight,maxHeight);
            }
        }else {
            measureWidth = Math.min(maxWidth,widthSize);
            measureHeight = Math.min(maxHeight,heightSize);
        }
        setMeasuredDimension (measureWidth,measureHeight);
        getPading(measureWidth,measureHeight);
    }

    private void getPading(int measureWidth, int measureHeight) {
        int bitmapWidth = likeBitmap.getWidth ();
        int bitmapHeight = likeBitmap.getHeight ();
        left = (measureWidth-bitmapWidth)/2;
        top = (measureHeight-bitmapHeight)/2;
        int width = getMeasuredWidth ();
        int height = getMeasuredHeight ();
        centerX = width/2;
        centerY = height/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw (canvas);
        Bitmap handBitmap = isLike?likeBitmap:unLikeBitmap;
        canvas.save ();
        canvas.scale (handScale,handScale,centerX,centerY);
        canvas.drawBitmap (handBitmap,left,top,paint);
        canvas.restore ();
        if (isLike){
            canvas.drawBitmap (shiningBitmap,left+10,top,paint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow ();
        likeBitmap.recycle ();
        unLikeBitmap.recycle ();
        shiningBitmap.recycle ();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction ()){
            case MotionEvent.ACTION_DOWN:
                onClick();
                break;
            default:break;
        }
        return super.onTouchEvent (event);
    }

    private void onClick() {
        isLike = !isLike;
//        ObjectAnimator handScale = ObjectAnimator.ofFloat (this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration (250);
//        handScale.start ();
        invalidate ();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat (1.0f, 0.5f, 1.0f);
        valueAnimator.start ();
        valueAnimator.addUpdateListener (new ValueAnimator.AnimatorUpdateListener () {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue ();
                handScale = animatedValue;
                invalidate ();
            }
        });
    }

    public void setHandScale(float value){
        this.handScale = value;
        invalidate ();
    }
}
