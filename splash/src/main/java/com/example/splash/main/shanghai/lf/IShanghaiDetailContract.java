package com.example.splash.main.shanghai.lf;

import androidx.fragment.app.Fragment;

import com.example.mvp2.mvp.ILifeCircle;
import com.example.mvp2.mvp.IMvpView;
import com.example.mvp2.mvp.MvpControler;
import com.example.splash.main.IMainActivityContract;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;

public interface IShanghaiDetailContract {
    interface Iview extends IMvpView {

        void showData(ShangHaiDetailBean data);
    }
    interface IPresenter extends ILifeCircle {


        void getNetData(int pagesize);
    }
    IShanghaiDetailContract.Iview emptyView = new IShanghaiDetailContract.Iview () {


        @Override
        public void showData(ShangHaiDetailBean data) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
