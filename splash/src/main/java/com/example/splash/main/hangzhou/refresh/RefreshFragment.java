package com.example.splash.main.hangzhou.refresh;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refresh.GodRefreshLayout;
import com.example.splash.R;
import com.example.splash.base.BaseFragment;
import com.example.splash.base.ViewInject;
import com.example.splash.main.hangzhou.adpter.ZhiHuAdapter;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;
import com.example.splash.main.shanghai.lf.IShanghaiDetailContract;
import com.example.splash.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShanghaiDetailContract.Iview {
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter (this);
    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;
    @BindView(R.id.refresh_recyclerview)
    RecyclerView refreshRecyclerview;

    @Override
    public void afterBindView() {
        initRefreshLayout ();
        initRecyclerView ();
    }

    private void initRefreshLayout() {
        godRefresh.setRefreshManager (new MeiTuanRefreshManager (mContext));
        godRefresh.setRefreshListener (new GodRefreshLayout.RefreshingListener () {
            @Override
            public void onRefreshing() {
                mPresenter.getNetData (20);
//                godRefresh.postDelayed (new Runnable () {
//                    @Override
//                    public void run() {
//                        godRefresh.refreshOver ();
//                    }
//                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
        refreshRecyclerview.setLayoutManager (new LinearLayoutManager (mContext));
        mPresenter.getNetData (20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (refreshRecyclerview.getAdapter () == null){
            ZhiHuAdapter adapter = new ZhiHuAdapter (data.result.data);
            refreshRecyclerview.setAdapter (adapter);
        }
        godRefresh.refreshOver ();
    }
}
