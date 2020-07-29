package com.example.splash.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.example.splash.R;
import com.example.splash.base.BaseActivity;
import com.example.splash.base.ViewInject;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;
import com.example.splash.main.shanghai.lf.IShanghaiDetailContract;
import com.example.splash.main.shanghai.manager.GetXiaoHuaList;
import com.example.splash.main.shanghai.module.ShanghaiDetailHttpTask;
import com.example.splash.main.shanghai.presenter.ShanghaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.task.TaskHelper.submitTask;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.Iview {
    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter (this);
    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima ();
        initGetNetData();
//        initPostNetData();
    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient ();
        FormBody.Builder builder = new FormBody.Builder ();
        builder.add ("key","f3de53348fffc06ac27c1dc1a4e87c19");
        Request request = new Request.Builder ().url ("http://apis.juhe.cn/lottery/types").post (builder.build ()).build ();
        Call call = client.newCall (request);
        call.enqueue (new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    private void initGetNetData() {
//        mPresenter.getNetData(pagesize);

//        GetXiaoHuaList task = new GetXiaoHuaList ();
//        task.execute ("desc", "1", "1");
//        Object desc = new ShanghaiDetailHttpTask ().getXiaohuaList ("desc", "1", "1");
//        if (desc instanceof Response){
//            Response response = (Response)desc;
//            Log.e ("initGetNetData:",response.body ().toString () );
//        }
//        OkHttpClient client = new OkHttpClient ();
//        HttpUrl.Builder builder = HttpUrl.parse ("http://v.juhe.cn/joke/content/list.php").newBuilder ();
//        builder.addQueryParameter ("\tsort","desc");
//        builder.addQueryParameter ("page","1");
//        builder.addQueryParameter ("pagesize","2");
//        builder.addQueryParameter ("time",""+System.currentTimeMillis ()/1000);
//        builder.addQueryParameter ("key","952eec0125454eb3e74ffa47676f49e0");
//        Request request = new Request.Builder ().url (builder.build ()).get ().build ();
//        Call call = client.newCall (request);
//        call.enqueue (new Callback () {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//            }
//        });
    }

    private void initAnima(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName (ivShanghaiDetail,mActivityOptionsCompat);
            startPostponedEnterTransition ();
        }
    }


    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent (activity, ShanghaiDetailActivity.class);
            Pair pair = new Pair (view, mActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation (activity, pair);
            ActivityCompat.startActivity (activity, intent, optionsCompat.toBundle ());
        }
    }


    @Override
    public void showData(ShangHaiDetailBean data) {

    }
}
