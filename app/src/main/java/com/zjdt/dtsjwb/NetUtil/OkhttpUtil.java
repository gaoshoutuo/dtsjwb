package com.zjdt.dtsjwb.NetUtil;

import android.os.Message;
import android.util.Log;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Util.HandlerUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 71568 on 2018/4/4.
 */

public class OkhttpUtil {

    private static OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.SECONDS)
            .readTimeout(10000, TimeUnit.SECONDS)
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build();
    public OkHttpClient getInstance(){
        return okHttpClient;
    }
    private HandlerUtil handlerUtil;
    //线程跳过了 想 原来是没开通网络权限啊  也有可能是没网络
    public void getUrl(final String url){
        handlerUtil=  HandlerUtil.getInstance();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Request request=new Request.Builder().url(url).build();
                    try {
                        Response response=okHttpClient.newCall(request).execute();
                        if (response.isSuccessful()){
                           String json= response.body().string().toString();
                            Log.e("json",json);
                            //Can't create handler inside thread that has not called Looper.prepare()
                            Message msg=new Message();
                            msg.what=11;
                            msg.obj=json;
                            HandlerUtil.handler.sendMessage(msg);

                        }else{

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }
}
