package com.example.splash.main.shanghai;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash.R;
import com.example.splash.base.BaseFragment;
import com.example.splash.base.ViewInject;
import com.example.splash.main.shanghai.adapter.ShanghaiAdapter;
import com.example.splash.main.shanghai.dto.ShanghaiDataManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_shanghai)
public class ShangHaiFragment extends BaseFragment {
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView shanghaiRecyclerview;
    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtollbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtollbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout shanghaiAppBarlayout;

    @Override
    public void afterBindView() {
        initListener ();
        initRecyclerView ();
    }

    private void initRecyclerView() {
        shanghaiRecyclerview.setLayoutManager (new LinearLayoutManager (mContext));
        shanghaiRecyclerview.setAdapter (new ShanghaiAdapter (ShanghaiDataManager.getData(),getActivity (),false));
    }

    private void initListener() {
        shanghaiAppBarlayout.addOnOffsetChangedListener (new AppBarLayout.OnOffsetChangedListener () {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset < appBarLayout.getMeasuredHeight () / 2) {
                    tvShanghaiWelcome.setVisibility (View.INVISIBLE);
                } else {
                    tvShanghaiWelcome.setVisibility (View.VISIBLE);
                }
            }
        });
    }
}
