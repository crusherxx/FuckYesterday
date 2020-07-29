package com.example.splash.main;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.MotionEvent;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.splash.R;
import com.example.splash.base.BaseActivity;
import com.example.splash.base.ViewInject;
import com.example.splash.main.tools.MainConstantTool;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {
    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.face_main_home)
    FloatingActionButton faceMainHome;
    @BindView(R.id.rb_main_shanghai)
    LottieAnimationView rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    LottieAnimationView rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    LinearLayout rgMainTop;
    @BindView(R.id.rb_main_beijing)
    RadioButton rbMainBeijing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rbMainShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    private boolean isChangeTopOrBottom;


    @Override
    public void afterBindView() {
        changeAnima(rgMainBottom, rgMainTop);
        initHomeFragment();
        initCheckListener();
    }

    private void initCheckListener() {
        rbMainShanghai.playAnimation ();
        rbMainShanghai.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (rbMainShanghai.getId ()==mPresenter.getCurrentCheckedId ()){
                    return;
                }
                mPresenter.replaceFragment (MainConstantTool.SHANGHAI);
                rbMainShanghai.playAnimation ();
                rbMainHangzhou.reverseAnimationSpeed ();
            }
        });
        rbMainHangzhou.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (rbMainHangzhou.getId ()==mPresenter.getCurrentCheckedId ()){
                    return;
                }
                rbMainHangzhou.playAnimation ();
                mPresenter.replaceFragment (MainConstantTool.HANGZHOU);
                rbMainShanghai.reverseAnimationSpeed ();
            }
        });

        rgMainBottom.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mPresenter.getCurrentCheckedId ()){
                    return;
                }
                switch (i){
                    case R.id.rb_main_beijing:
                        mPresenter.replaceFragment (MainConstantTool.BEIJING);
                        break;
                    case R.id.rb_main_shenzhen:
                        mPresenter.replaceFragment (MainConstantTool.SHENZHEN);
                        break;
                }
            }
        });
    }

    //初始化Fragment
    private void initHomeFragment(){
        mPresenter.initHomeFragment();
    }


    @OnClick(R.id.face_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.face_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnima(rgMainTop, rgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnima(rgMainBottom, rgMainTop);
                    handleBottomPosition();
                }
                break;
        }
    }

    private void handleBottomPosition() {
        if (mPresenter.getTopPosition ()!=1){
            mPresenter.replaceFragment(0);
            rbMainShanghai.pauseAnimation ();
        } else {
            mPresenter.replaceFragment(1);
            rbMainHangzhou.playAnimation ();
        }
    }

    private void handleTopPosition() {
        if (mPresenter.getBottomPosition ()!=3){
            mPresenter.replaceFragment(2);
            rbMainBeijing.setChecked(true);
        } else {
            mPresenter.replaceFragment(3);
            rbMainShenzhen.setChecked(true);
        }
    }

    private void changeAnima(ViewGroup gone, ViewGroup show) {
        //消失的动画
        gone.clearAnimation(); //清楚自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
