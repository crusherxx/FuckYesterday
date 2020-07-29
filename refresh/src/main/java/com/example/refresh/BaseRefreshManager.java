package com.example.refresh;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;

public abstract class BaseRefreshManager {
    public  LayoutInflater mLayoutInflater;
    public BaseRefreshManager(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public abstract View getHeaderView();

    public abstract void downRefresh();

    public abstract void releaseRefresh();

    public abstract void iddleRefresh();

    public abstract void refreshing();

    public abstract void downRefreshPercet(float percent);
}
