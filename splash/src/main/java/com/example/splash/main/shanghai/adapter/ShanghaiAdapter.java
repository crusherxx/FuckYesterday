package com.example.splash.main.shanghai.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash.R;
import com.example.splash.main.shanghai.dto.ShanghaiBean;
import com.example.splash.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

public class ShanghaiAdapter extends RecyclerView.Adapter {
    private ArrayList<ShanghaiBean> mData;
    private Activity mContext;
    private final boolean mIsHor;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public ShanghaiAdapter(ArrayList<ShanghaiBean> data, Activity context, boolean isHor) {
        recycledViewPool = new RecyclerView.RecycledViewPool ();
        mData = data;
        mContext = context;
        mIsHor = isHor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL){
            View inflate = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_shanghai_fragment, parent,false);
            RecyclerView.ViewHolder holder = new ShanghaiViewHolder (inflate);
            return holder;
        } else if (viewType == ShanghaiBean.IShanghaiItemType.HORIZANTAL){
            View inflate = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_shanghai_fragment_rv, null);
            RecyclerView.ViewHolder holder = new ShanghaiViewHolderRv (inflate);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {
        ShanghaiBean shanghaiBean = mData.get (position);
        if (holder instanceof ShanghaiViewHolder){
            ((ShanghaiViewHolder)holder).mTv.setText (shanghaiBean.getmDec ());
            ((ShanghaiViewHolder)holder).imageView.setVisibility (shanghaiBean.isShowImg()?View.VISIBLE:View.GONE);
            ((ShanghaiViewHolder)holder).itemView.setTag (position);
        } else if (holder instanceof ShanghaiViewHolderRv){

            ((ShanghaiViewHolderRv)holder).rv.setAdapter (new ShanghaiAdapter (shanghaiBean.getData(), mContext ,true));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size ();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get (position).getmItemType ();
    }



    public class ShanghaiViewHolder extends RecyclerView.ViewHolder{

        public TextView mTv;
        public ImageView imageView;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super (itemView);
            mTv = itemView.findViewById (R.id.item_shanghai_tv);
            imageView = itemView.findViewById (R.id.item_shanghai_iv);
            this.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    ShanghaiDetailActivity.start_5_0 (mContext,imageView);
                }
            });
        }
    }

    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder{

        public RecyclerView rv;

        public ShanghaiViewHolderRv(@NonNull View itemView) {
            super (itemView);
            rv = itemView.findViewById (R.id.item_shanghai_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager (mContext,LinearLayoutManager.HORIZONTAL,false);
            linearLayoutManager.setRecycleChildrenOnDetach (true);
            rv.setLayoutManager (linearLayoutManager);
            rv.setRecycledViewPool (recycledViewPool);

        }
    }
}
