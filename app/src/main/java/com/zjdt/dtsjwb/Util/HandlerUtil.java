package com.zjdt.dtsjwb.Util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.zjdt.dtsjwb.App.AppApplication;

/**
 * 线程中的数据需要handler来调度
 * Created by 71568 on 2018/4/8.
 */

public class HandlerUtil {

    /**
     * 加工msg  难道handler难免到处都是了
     * @param data
     * @param what
     */
    public static HandlerUtil handlerUtil=new HandlerUtil();
    public static Message object;
    public static synchronized HandlerUtil getInstance(){
            return handlerUtil;
    }

    public void handlerWithData(String data,int what){
        //Message msg=Message.obtain();我也觉得为啥这种方式不行呢
        Looper.prepare();
        Message msg=new Message();
        msg.what=what;
        msg.obj=data;
        HandlerUtil.handler.sendMessage(msg);
        Looper.loop();
    }

    /**
     * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
     * 为啥handler取不到呢 因为handler属性在里面 外部类如果不一样的话 handler也不一样
     */

    public static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
          //  super.handleMessage(msg);这个super为啥会害我
            switch (msg.what){
                case 11:
                    //最好是回调吧 还没想好  此处解析json
                    Log.e("handlerJson",msg.obj.toString());
                   HandlerUtil.object=msg;
                    break;
                default:break;
            }
        }
    };
    //对付 static 限制 最好的办法就是传递形参，不错形参似乎也有final的生命周期限制
    public static Object handlerLife(Object obj){
        return null;
    }

}
