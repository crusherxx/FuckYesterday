package com.example.splash.base;

import com.example.mvp2.mvp.IMvpView;
import com.example.mvp2.mvp.base.BaseMvpPresenter;
import com.example.task.LfTask;
import com.example.task.TaskHelper;

public class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {
    public BasePresenter(T view) {
        super (view);
    }

    public void submitTask(LfTask task){
        TaskHelper.submitTask(task,task);
    }

    @Override
    protected T getEmptyView() {
        GenericsUtils.getSuperClassGenricType (this.getClass (),0);
        return null;
    }
}
