package com.example.splash.main;

import androidx.fragment.app.Fragment;

import com.example.splash.R;
import com.example.splash.main.beijing.BeiJingFragment;
import com.example.splash.main.hangzhou.HangZhouFragment;
import com.example.splash.main.shanghai.ShangHaiFragment;
import com.example.splash.main.shenzhen.ShenZhenFragment;
import com.example.splash.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter{

    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    public int mCurrentCheckId;
    private int mTopPosition;
    private int mBottomPosition;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }


    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckId;
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPosition;
    }

    @Override
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i <mFragments.length ; i++) {
            if (mCurrentFragmentIndex != i){
                if (mFragments[i] != null){
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null){
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        }else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked (mCurrentFragmentIndex);
        }
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckId = R.id.rb_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckId = R.id.rb_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckId = R.id.rb_main_beijing;
                mBottomPosition = 2;
                break;
            case 3:
                mCurrentCheckId = R.id.rb_main_shenzhen;
                mBottomPosition =3;
                break;
        }
    }

    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangZhouFragment ();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else {
            getView().addFragment(mFragment);
        }
    }

    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }
}
