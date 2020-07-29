package com.example.splash.main.hangzhou.adpter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash.R;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;
import com.example.splash.main.shanghai.dto.ShanghaiBean;
import com.example.splash.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

public class ZhiHuAdapter extends RecyclerView.Adapter {

    private final ArrayList<ShangHaiDetailBean.XiaoHuaBean> mData;

    public ZhiHuAdapter(ArrayList<ShangHaiDetailBean.XiaoHuaBean> data) {
        this.mData = data;
    }

    //  创建View 然后进行缓存
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }

    // 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShangHaiDetailBean.XiaoHuaBean xiaoHuaBean = mData.get(position);
        ((ShanghaiViewHolder) holder).mTv.setText(xiaoHuaBean.content);
        ((ShanghaiViewHolder) holder).mIv.setVisibility(View.GONE);

    }

    // 条目的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 缓存View  内存友好设计
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public TextView mTv;
        public ImageView mIv;

        public ShanghaiViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIv = itemView.findViewById(R.id.item_shanghai_iv);
        }
    }

}

