package com.example.splash.main.shanghai.presenter;

import android.util.Log;

import com.example.http.result.IResult;
import com.example.mvp2.mvp.base.BaseMvpPresenter;
import com.example.splash.base.BasePresenter;
import com.example.splash.base.JHTask;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;
import com.example.splash.main.shanghai.dto.ShanghaiBean;
import com.example.splash.main.shanghai.lf.IShanghaiDetailContract;
import com.example.splash.main.shanghai.module.ShanghaiDetailHttpTask;
import com.example.splash.splash.ISplashActivityContract;
import com.example.task.LfTask;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.Iview> implements IShanghaiDetailContract.IPresenter {

    public ShanghaiDetailPresenter(IShanghaiDetailContract.Iview view) {
        super (view);
    }

    @Override
    protected IShanghaiDetailContract.Iview getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(int pagesize) {
//        submitTask(new LfTask () {
////            @Override
////            public void onSuccess(Object o) {
////
////                Log.e ("getNetData1","onSuccess"+Thread.currentThread ().getName ());
////                Log.e("getData",o.toString ());
////
////            }
////
////            @Override
////            public void onException(Throwable throwable) {
////                Log.e ("getNetData1",throwable.toString ());
////            }
////
////            @Override
////            public Object onBackground() {
////                Response desc = (Response) new ShanghaiDetailHttpTask ().getXiaohuaList ("desc","1","1");
////                String strResponse = null;
////                try {
////                    strResponse = desc.body ().string ();
////                } catch (IOException e) {
////                    e.printStackTrace ();
////                }
////                return strResponse;
////            }
////        });
       submitTask (new JHTask<ShangHaiDetailBean> () {
           @Override
           public IResult<ShangHaiDetailBean> onBackground() {
               return new ShanghaiDetailHttpTask<ShangHaiDetailBean> ().getXiaohuaList ("desc", "1", pagesize+"");
           }

           @Override
           public void onSuccess(IResult<ShangHaiDetailBean> t) {
               ShangHaiDetailBean data = t.data ();
//               Gson gson = new Gson ();
//               String s = gson.toJson (data);
//               Log.e ("shanghai",s);
               getView ().showData(data);
           }
       });

    }

}
