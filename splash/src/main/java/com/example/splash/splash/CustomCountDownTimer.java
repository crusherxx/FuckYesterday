package com.example.splash.splash;

import android.os.Handler;

import java.util.logging.LogRecord;

public class CustomCountDownTimer implements Runnable{
    private int time;
    private ICountDownHandler countDownHandler;
    private int countDownTime;
    private Handler handler;
    private boolean isRun;

    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        this.time = time;
        this.countDownHandler = countDownHandler;
        this.countDownTime = time;
        handler = new Handler();
    }

    public interface ICountDownHandler{
        void onTicker(int time);
        void onFinish();
    }
    public void start(){
        isRun = true;
        handler.post(this);
    }
    public void cancel(){
        isRun = false;
        handler.postDelayed(this,1000);
    }
    @Override
    public void run() {
        if (isRun){
            if (countDownHandler!=null){
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime ==0){
                cancel();
                if (countDownHandler!=null){
                    countDownHandler.onFinish();
                }
            }else {
                countDownTime = time--;
                handler.postDelayed(this,1000);
            }
        }
    }
}
