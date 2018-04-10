package com.zjdt.dtsjwb.App;

import android.app.Application;

/**
 * Created by 71568 on 2018/4/3.
 * 为了全局获取context
 */

public class AppApplication extends Application {
    private static AppApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
    public static AppApplication getApp(){
        return application;
    }
}
