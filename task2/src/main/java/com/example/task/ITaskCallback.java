package com.example.task;

import javax.xml.transform.Result;

interface ITaskCallback<Result> {

    void onComplete(Result o);
    void onException(Throwable throwable);
}
