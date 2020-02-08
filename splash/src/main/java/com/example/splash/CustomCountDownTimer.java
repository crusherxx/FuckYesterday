package com.example.splash;

import android.os.Handler;

import java.util.logging.LogRecord;

public class CustomCountDownTimer implements Runnable{
    private int time;
    private final ICountDownHandler countDownHandler;
    private boolean isRun;
    private int countDownTime;
    private final Handler handler;


    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        handler = new Handler();
        this.time = time;
        this.countDownHandler = countDownHandler;
        this.countDownTime = time;
    }

    @Override
    public void run() {
        if (isRun){
            if (countDownHandler!=null){
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0){
                cancel();
                if (countDownHandler!=null){
                    countDownHandler.onFinish();
                }
            }else{
                countDownTime = time--;
                handler.postDelayed(this,1000);
            }
        }
    }
    public void start(){
        isRun = true;
        handler.post(this);
    }
    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }


    protected interface ICountDownHandler {
        void onTicker(int time);

        void onFinish();
    }
}
