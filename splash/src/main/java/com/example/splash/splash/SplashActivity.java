package com.example.splash.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.example.splash.main.MainActivity;
import com.example.splash.R;
import com.example.splash.base.ViewInject;
import com.example.splash.base.BaseActivity;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview{

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
    //    @BindView(R.id.splash_vs)
//    ViewStub mViewStub;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;
    private ISplashActivityContract.IPresenter timerPresenter;

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initListener();
        initVideo();
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }


    private void initVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                Test.save (SplashActivity.this);
                finish();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

    }


    @Override
    public void setTvTimer(String s) {
        mTvTimer.setText(s);
    }

}
