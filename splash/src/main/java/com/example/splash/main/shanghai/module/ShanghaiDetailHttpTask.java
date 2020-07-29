package com.example.splash.main.shanghai.module;

import com.example.http.LfHttpServer;
import com.example.http.result.IResult;

import java.util.HashMap;

public class ShanghaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaohuaList(String sort, String page, String pagesize){
        HashMap<String, Object> params = new HashMap<> ();
        params.put ("sort",sort);
        params.put ("page",page);
        params.put ("pagesize",pagesize);
        params.put ("time",""+System.currentTimeMillis ()/1000);
        params.put ("key","952eec0125454eb3e74ffa47676f49e0");
        return super.execute(ShanghaiDetailRequest.xiaohuaRequest,params);
    }
}