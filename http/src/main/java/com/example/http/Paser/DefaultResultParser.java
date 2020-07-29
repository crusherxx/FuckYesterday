package com.example.http.Paser;

import android.util.Log;

import com.example.http.request.IRequest;
import com.example.http.response.IResponse;
import com.example.http.result.IResult;
import com.example.http.result.Result;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class DefaultResultParser implements IParser{

    private static DefaultResultParser instance;
    private final Gson gson;

    public DefaultResultParser() {
        gson = new Gson ();
    }

    public static IParser getInstance() {
        if (instance == null){
            instance = new DefaultResultParser ();
        }
        return instance;
    }

    @Override
    public IResult parserResponse(IRequest request, IResponse response) {
        Type type = request.getType ();
        Log.e ("type1",type.toString ());
        String bodyStr = response.getBodyStr ();
        Log.e ("parser1",bodyStr);
        Object object = gson.fromJson (bodyStr,type);
        return Result.success (object);
    }
}
