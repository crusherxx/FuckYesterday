package com.example.splash.main.hangzhou.adpter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.splash.main.hangzhou.refresh.RefreshFragment;
import com.example.splash.main.hangzhou.view.JiKeFragment;
import com.example.splash.main.hangzhou.view.ZhiHuFragment;

import java.util.ArrayList;

public class HangzhouViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> titleList = new ArrayList<> ();
    public HangzhouViewPagerAdapter(@NonNull FragmentManager fm) {
        super (fm);
        titleList.add("知乎");
        titleList.add ("即刻");
        titleList.add("下拉刷新");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new ZhiHuFragment ();
            case 1:return new JiKeFragment ();
            case 2:return new RefreshFragment ();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return titleList.size ();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get (position);
    }
}
