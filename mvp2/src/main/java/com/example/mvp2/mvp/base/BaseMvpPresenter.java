package com.example.mvp2.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvp2.mvp.IMvpView;
import com.example.mvp2.mvp.presenter.LifeCircleMvpPresenter;


public abstract  class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {
    public BaseMvpPresenter(T view){
        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

}
