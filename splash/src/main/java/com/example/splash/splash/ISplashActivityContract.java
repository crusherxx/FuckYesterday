package com.example.splash.splash;

import com.example.annotation.MvpEmptyViewFactory;
import com.example.mvp2.mvp.ILifeCircle;
import com.example.mvp2.mvp.IMvpView;
import com.example.mvp2.mvp.MvpControler;

public interface ISplashActivityContract {
    @MvpEmptyViewFactory
    interface Iview extends IMvpView {
        void setTvTimer(String timer);

    }
    interface IPresenter extends ILifeCircle {
        void initTimer();
    }
    Iview emptyView = new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
