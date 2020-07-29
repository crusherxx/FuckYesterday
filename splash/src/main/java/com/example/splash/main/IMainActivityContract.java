package com.example.splash.main;

import androidx.fragment.app.Fragment;

import com.example.mvp2.mvp.ILifeCircle;
import com.example.mvp2.mvp.IMvpView;
import com.example.mvp2.mvp.MvpControler;

public interface IMainActivityContract {
    interface Iview extends IMvpView {

        void addFragment(Fragment mFragment);

        void showFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);

    }
    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedIndex();

        void replaceFragment(int mCurrentFragmentIndex);

        int getCurrentCheckedId();

        int getTopPosition();

        int getBottomPosition();
    }
    Iview emptyView = new Iview() {

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
