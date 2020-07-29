package com.example.mvp2.mvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

import com.example.mvp2.mvp.presenter.LifeCircleMvpPresenter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MvpControler implements ILifeCircle {
    private Set<ILifeCircle> lifeCircles = new HashSet<>();
    public void savePresenter(ILifeCircle lifeCircle){
        this.lifeCircles.add(lifeCircle);
    }
    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator<ILifeCircle> iterator = this.lifeCircles.iterator();
        while (iterator.hasNext()){
            ILifeCircle lifeCircle = iterator.next();
            if (intent == null){
                intent = new Intent();
            }
            if (getArguments == null){
                getArguments = new Bundle();
            }
            lifeCircle.onCreate(savedInstanceState,intent,getArguments);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        Iterator var = this.lifeCircles.iterator();
        while (var.hasNext()){
            ILifeCircle presenter = (ILifeCircle) var.next();
            if (intent ==null){
                intent = new Intent();
            }
            if (getArguments == null){
                getArguments = new Bundle();
            }
            presenter.onActivityCreated(savedInstanceState,intent,getArguments);
        }
    }

    @Override
    public void onStart() {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()){
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ILifeCircle> var2 = this.lifeCircles.iterator();
        while (var2.hasNext()){
            ILifeCircle presnter = var2.next();
            presnter.onResume();
        }
    }

    @Override
    public void onPause() {
        Iterator<ILifeCircle> var3 = this.lifeCircles.iterator();
        while (var3.hasNext()){
            ILifeCircle presenter = var3.next();
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ILifeCircle> var4 = this.lifeCircles.iterator();
        while (var4.hasNext()){
            ILifeCircle presenter = var4.next();
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ILifeCircle> var5 = this.lifeCircles.iterator();
        while(var5.hasNext()) {
            ILifeCircle presenter = var5.next();
            presenter.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        Iterator<ILifeCircle> var6 = this.lifeCircles.iterator();
        while (var6.hasNext()){
            ILifeCircle presenter = var6.next();
            presenter.destroyView();
        }
    }

    @Override
    public void onViewDestroy() {
        Iterator<ILifeCircle> var7 = this.lifeCircles.iterator();
        while (var7.hasNext()){
            ILifeCircle prsenter = var7.next();
            prsenter.onViewDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        Iterator<ILifeCircle> var8 = this.lifeCircles.iterator();
        while (var8.hasNext()){
            if (intent == null){
                intent = new Intent();
            }
            ILifeCircle presenter = var8.next();
            presenter.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Iterator var4 = this.lifeCircles.iterator();
        while (var4.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var4.next();
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle bundle) {
        Iterator var2 = this.lifeCircles.iterator();
        while (var2.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var2.next();
            presenter.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        Iterator var1 = this.lifeCircles.iterator();
        while (var1.hasNext()) {
            ILifeCircle presenter = (ILifeCircle) var1.next();
            presenter.attachView(iMvpView);
        }
    }


}
