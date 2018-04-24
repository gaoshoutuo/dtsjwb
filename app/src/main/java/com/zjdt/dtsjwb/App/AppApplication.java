package com.zjdt.dtsjwb.App;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by 71568 on 2018/4/3.
 * 为了全局获取context
 */

public class AppApplication extends Application {
    private static AppApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        initX5WebView();
        application=this;
    }
    public static AppApplication getApp(){
        return application;
    }

    /**
     * 修改 webview 成为tencent de x5webview
     */

    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
