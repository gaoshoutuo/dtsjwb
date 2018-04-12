package com.zjdt.dtsjwb.NetUtil;

import android.os.Handler;

import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.zjdt.dtsjwb.Activity.MenuActivity;
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

         static OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build();



   static OkhttpUtil okhttpUtil=new OkhttpUtil();
    public static OkhttpUtil getInstance(){
        return okhttpUtil;
    }

    //线程跳过了 想 原来是没开通网络权限啊  也有可能是没网络
    public static void getUrl(final String url){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Request request=new Request.Builder().url(url).build();
                    try {

                        Response response=okHttpClient.newCall(request).execute();
                        String json= response.body().string().toString();
                    /*    Message msg=new Message();
                        //Message msg = handle.obtainMessage()
                        msg.what=HandlerFinal.ROOL_MSG;msg.obj=json;
                        Looper.prepare();
                        HandlerUtil.handler.sendMessage(msg);*/
                    if(json.contains("doctor_pic"))
                        HandlerFinal.msg=json;
                    if(json.contains("username"))
                        HandlerFinal.json=json;
                        //if (json!=null)  Log.e("json",json);
                        Log.e("jsonArr",json);
                            //Can't create handler inside thread that has not called Looper.prepare()
                          //直接用handler是一个null值吧  烦了初始化的错误 封装一定要注意类的初始化问题

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    /**
     * 最终我妥协了恢复了丑陋又重复的设计
     */


/*    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 110:
                    HandlerFinal.msg=msg;
                    break;

                    default:break;

            }
        }
    };*/
}
