package com.example.http.request.call;

import com.example.http.request.IRequest;
import com.example.http.response.IResponse;
import com.example.http.result.IResult;

import okhttp3.Response;

public interface ICall {
    IResponse execute();

    IRequest getRequest();

}
