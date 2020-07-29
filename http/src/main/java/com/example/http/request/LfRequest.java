package com.example.http.request;

import com.example.http.Paser.IParser;
import com.example.http.annotation.RequestMethod;
import com.example.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

public class LfRequest implements IRequest {
    protected IHost host;
    @RequestMethod
    protected int requestMethod;
    protected Map<String, Object> params;
    protected String path;
    protected Type type;
    protected IParser resultParser;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }

}
