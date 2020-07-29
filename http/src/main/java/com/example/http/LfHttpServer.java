package com.example.http;

import com.example.http.request.IRequest;
import com.example.http.result.IResult;

import java.util.Map;

public class LfHttpServer {
    protected <T>IResult<T> execute(IRequest request, Map<String,Object> params) {
        return HttpHelper.execute(request,params);
    }
}
