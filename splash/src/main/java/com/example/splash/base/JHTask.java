package com.example.splash.base;

import android.util.Log;

import com.example.http.result.IResult;
import com.example.http.result.IResultCallBack;
import com.example.http.result.Result;
import com.example.task.LfTask;
import com.google.gson.Gson;

public abstract class JHTask<T> extends LfTask<IResult<T>> implements IResultCallBack<T> {


    @Override
    public void onComplete(IResult<T> iResult) {
        if (iResult != null) {
            if (iResult.isSuccess()) {
                onSuccess(iResult);
            } else {
                onFailed(Result.failed(Result.CODE_505)); // 1次失败
            }
        } else {
            onFailed(Result.failed(Result.CODE_404)); //2次失败
        }
    }

    @Override
    public void onFailed(IResult t) {
        switch (t.getCode()) {
            // 可以做成统一错误码的处理
            case Result.CODE_404:
                break;
            case Result.CODE_504:
                break;
        }
    }

    @Override
    public void onException(Throwable throwable) {
        onFailed(Result.failed(Result.CODE_504)); //3次失败
    }
}
