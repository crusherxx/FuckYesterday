package com.example.splash.main.hangzhou.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.refresh.BaseRefreshManager;
import com.example.splash.R;

public class MeiTuanRefreshManager extends BaseRefreshManager {
    private ImageView imageView;
    public MeiTuanRefreshManager(Context context) {
        super (context);
    }

    @Override
    public View getHeaderView() {
        View inflate = mLayoutInflater.inflate (R.layout.meituan_header_refresh_layout, null, false);
        imageView = inflate.findViewById (R.id.loading);
        return inflate;
    }

    @Override
    public void downRefresh() {

    }

    @Override
    public void releaseRefresh() {
        imageView.setImageResource (R.drawable.mei_tuan_loading_pre);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getDrawable ();
        animationDrawable.start ();
    }

    @Override
    public void iddleRefresh() {
        imageView.clearAnimation ();
        imageView.setImageResource (R.mipmap.pull_image);
        imageView.setScaleX (0);
        imageView.setScaleY (0);

    }

    @Override
    public void refreshing() {
        imageView.setImageResource (R.drawable.mei_tuan_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getDrawable ();
        animationDrawable.start ();
    }

    @Override
    public void downRefreshPercet(float percent) {
        imageView.setScaleX (percent);
        imageView.setScaleY (percent);
    }
}
