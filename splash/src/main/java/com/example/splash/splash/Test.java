package com.example.splash.splash;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


public class Test extends Application {
    private static SplashActivity haha;

    public static void save(SplashActivity splashActivity){
        haha = splashActivity;
    }

//    @Override
//    public void onCreate() {
//        super.onCreate ();
//        if (LeakCanary.isInAnalyzerProcess (this)){
//            return;
//        }
//        LeakCanary.install (this);
//
//    }
}
