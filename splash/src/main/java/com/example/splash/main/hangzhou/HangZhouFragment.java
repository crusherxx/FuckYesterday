package com.example.splash.main.hangzhou;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.splash.R;
import com.example.splash.base.BaseFragment;
import com.example.splash.base.ViewInject;
import com.example.splash.main.hangzhou.adpter.HangzhouViewPagerAdapter;
import com.example.splash.main.hangzhou.view.ZhiHuFragment;
import com.example.splash.main.shenzhen.ShenZhenFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_hangzhou)
public class HangZhouFragment extends BaseFragment {
    @BindView(R.id.tl_tablayout)
    TabLayout tbTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView() {
        tbTablayout.setupWithViewPager (vpViewpager);
        vpViewpager.setAdapter (new HangzhouViewPagerAdapter (getChildFragmentManager ()));


    }
}
