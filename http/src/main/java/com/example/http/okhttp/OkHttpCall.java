package com.example.http.okhttp;

import com.example.http.request.call.ICall;
import com.example.http.request.IRequest;
import com.example.http.response.IResponse;


import okhttp3.Call;
import okhttp3.Response;

class OkHttpCall implements ICall {
    private IRequest request;
    private Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.request = request;

    }


    @Override
    public IResponse execute() {
        Response response = null;
        try {
            response = call.execute ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        OkHttpResponse okHttpResponse = new OkHttpResponse (response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}
