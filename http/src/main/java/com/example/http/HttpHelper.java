package com.example.http;

import com.example.http.okhttp.OkHttpScheduler;
import com.example.http.request.IRequest;
import com.example.http.request.call.ICall;
import com.example.http.result.IResult;

import java.util.Map;

public class HttpHelper {

    private volatile static HttpScheduler httpScheduler;
    public static HttpScheduler getHttpScheduler(){
        if (httpScheduler == null){
            synchronized (HttpHelper.class){
                if (httpScheduler == null){
                    httpScheduler = new OkHttpScheduler ();
                }
            }
        }
        return httpScheduler;
    }
    public static <T> IResult<T> execute(IRequest request, Map<String, Object> params) {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler ().execute (call);
    }
}
