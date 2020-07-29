package com.example.splash.main.shanghai.module;

import com.example.http.request.IRequest;
import com.example.http.annotation.RequestMethod;
import com.example.splash.base.JHRequest;
import com.example.splash.main.shanghai.dto.ShangHaiDetailBean;
import com.example.splash.main.shanghai.dto.ShanghaiBean;

public interface ShanghaiDetailRequest {

    IRequest xiaohuaRequest = JHRequest.sendHttp ("/joke/content/list.php", RequestMethod.Get, ShangHaiDetailBean.class);
}
