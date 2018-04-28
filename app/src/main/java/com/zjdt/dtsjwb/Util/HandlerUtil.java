package com.zjdt.dtsjwb.Util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;

/**
 * 线程中的数据需要handler来调度
 * Created by 71568 on 2018/4/8.
 */

public  class HandlerUtil {

    /**
     * 加工msg  难道handler难免到处都是了
     * @param data
     * @param what
     */



    public void handlerWithData(String data,int what){
        //Message msg=Message.obtain();我也觉得为啥这种方式不行呢
        Looper.prepare();
        Message msg=new Message();
        msg.what=what;
        msg.obj=data;
        //是不是因为 hanglerUtil一直为
       // if(HandlerUtil.handler==null)
        Log.e("english",msg.obj.toString());

        //handler.sendMessage(msg);
        Looper.loop();
    }

    /**
     * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
     * 为啥handler取不到呢 因为handler属性在里面 外部类如果不一样的话 handler也不一样
     */


    //对付 static 限制 最好的办法就是传递形参，不错形参似乎也有final的生命周期限制
    public static Object handlerLife(Object obj){
        return null;
    }

    public static Handler handler=new Handler(AppApplication.getApp().getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HandlerFinal.ROOL_MSG:
                   // MenuActivity.msg=(String)msg.obj;所谓的结论就是handler 不适合置值 而适合做一些ui操作
                   // MenuActivity.test.setText((String)msg.obj);
                   HandlerFinal.getHf().msg=(String)msg.obj;
                    Log.e("msg",msg.obj+"");
                    break;//很奇怪
                case HandlerFinal.AU_REGISTER_MSG:
                    ToastUtil.ggg(AppApplication.getApp(),msg.obj.toString());
                    break;

            }
        }

    };

}
