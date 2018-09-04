package com.tourism.strategy.tourism_strategy;

import android.app.Application;

public class BaseApplication extends Application{

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
