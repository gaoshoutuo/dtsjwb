package com.zjdt.dtsjwb.Util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.JsonObject;
import com.zjdt.dtsjwb.Activity.BeforeActivity;
import com.zjdt.dtsjwb.Activity.InfoActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.AsertFormActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.WatchAssetActivity;
import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.AssertBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.IdcBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public static void doHandler(Message msg){
        handler.sendMessage(msg);
    }

    public static Handler handler=new Handler(AppApplication.getApp().getMainLooper()){
        int check_in_step=0;
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

                    ToastUtil.ggg(AppApplication.getApp(),"成功注册，快去登录吧");

                    break;

                case HandlerFinal.DTSJ_INS_FIX:
                    ToastUtil.ggg(AppApplication.getApp(),msg.obj.toString());
                    break;

                case HandlerFinal.MESSAGE_CUS:
                    Intent cusIntent=new Intent(AppApplication.getApp(), PdfLoaderActivity.class);
                    cusIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    cusIntent.putExtra("cus",(String)msg.obj);
                    AppApplication.getApp().startActivity(cusIntent);
                    //ToastUtil.ggg(AppApplication.getApp(),msg.obj.toString());
                    break;

                case HandlerFinal.MESSAGE_ENG:
                    Intent engIntent=new Intent(AppApplication.getApp(), PdfLoaderActivity.class);
                    engIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    engIntent.putExtra("cus",(String)msg.obj);
                    AppApplication.getApp().startActivity(engIntent);
                   // ToastUtil.ggg(AppApplication.getApp(),msg.obj.toString());
                    break;

                case HandlerFinal.FIX_UPS_REQUEST://获得服务器回执之后  似乎又没有必要

                    break;

                case HandlerFinal.TEST_UPS_REQUEST:

                    break;

                case HandlerFinal.INS_UPS_REQUEST:

                    break;

                case HandlerFinal.INS_AIR_REQUEST:

                    break;

                case HandlerFinal.SERVICE_REQUEST:

                    break;

                case HandlerFinal.INSTALL_REQUEST:

                    break;
                case HandlerFinal.MSG_CREATE_IDC:
                    Toast.makeText(AppApplication.getApp(),"创建机房成功",Toast.LENGTH_SHORT).show();
                    break;

                case HandlerFinal.IDC_QUERY_REPLY:
                   String iqrJson=(String) msg.obj;
                    try {
                        //这里要加锁
                        if (check_in_step==0) {
                            ArrayList<IdcBean> iqrList = JsonUtil.getInstance().iqrJsonParse(new JSONObject(iqrJson));
                            if (iqrList.size() > 0) {
                                Intent intent = new Intent(AppApplication.getApp(), AsertFormActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("key", iqrList);
                                intent.putExtras(bundle);
                                AppApplication.getApp().startActivity(intent);
                            }else{
                                Toast.makeText(AppApplication.getApp(),"还未存在机房",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case HandlerFinal.QUERY_SUB_REPLY:
                   Log.e("ttt1", (String)msg.obj);
                   // arg1Switch(msg.arg1,(String)msg.obj);
                    if(((String)msg.obj).length()>2){
                        arg1Switch(WatchAssetActivity.list,msg.arg1,(String)msg.obj);
                        WatchAssetActivity.sInstance.setView();
                        WatchAssetActivity.sInstance.changedList();
                    }else {
                        WatchAssetActivity.list.clear();
                        WatchAssetActivity.sInstance.setNoAsset();
                       // WatchAssetActivity.sInstance.changedList();
                    }

                    break;

                case HandlerFinal.INFO_MSG:

                    Intent infoIntent=new Intent(AppApplication.getApp(), InfoActivity.class);
                    infoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppApplication.getApp().startActivity(infoIntent);

                    break;

                //主入口跳转
                case HandlerFinal.MAIN_LOOP:
                    BeforeActivity.mainLoopActivity.mainLoop();
                    break;

                    default:break;
            }
        }

    };

    private static ArrayList<AssertBean> arg1Switch( ArrayList<AssertBean> list,int arg1,String json){
        //ArrayList<AssertBean> list=new ArrayList<>();
        list.clear();
        try {
        switch (arg1){
            case 0:
                JSONObject upsJson=new JSONObject(json);
                //AssertBean upsBean=new AssertBean("",null,"");
                //Log.e("ttt2", (String)msg.obj);
                ArrayList<AssertBean> list1= JsonUtil.getInstance().parseSubtitle(upsJson,HandlerFinal.SUB_UPS);
                list.addAll(list1);
                break;

            case 1:
                JSONObject airJson=new JSONObject(json);
                ArrayList<AssertBean> list2= JsonUtil.getInstance().parseSubtitle(airJson,HandlerFinal.SUB_AIR);
                list.addAll(list2);
                break;

            case 2:
                JSONObject emiJson=new JSONObject(json);
                ArrayList<AssertBean> list3= JsonUtil.getInstance().parseSubtitle(emiJson,HandlerFinal.SUB_EMI);
                list.addAll(list3);
                break;

            case 3:
                JSONObject msJson=new JSONObject(json);
                ArrayList<AssertBean> list4= JsonUtil.getInstance().parseSubtitle(msJson,HandlerFinal.SUB_MS);
                list.addAll(list4);
                break;

            case 4:
                JSONObject miJson=new JSONObject(json);
                ArrayList<AssertBean> list5= JsonUtil.getInstance().parseSubtitle(miJson,HandlerFinal.SUB_MI);
                list.addAll(list5);
                break;

            case 5:
                JSONObject mhJson=new JSONObject(json);
                ArrayList<AssertBean> list6= JsonUtil.getInstance().parseSubtitle(mhJson,HandlerFinal.SUB_MH);
                list.addAll(list6);
                break;

            case 6:
                JSONObject macJson=new JSONObject(json);
                ArrayList<AssertBean> list7= JsonUtil.getInstance().parseSubtitle(macJson,HandlerFinal.SUB_MAC);
                list.addAll(list7);
                break;

            case 7:
                JSONObject mvJson=new JSONObject(json);
                ArrayList<AssertBean> list8= JsonUtil.getInstance().parseSubtitle(mvJson,HandlerFinal.SUB_MV);
                list.addAll(list8);
                break;

            case 8:
                JSONObject cabJson=new JSONObject(json);
                ArrayList<AssertBean> list9= JsonUtil.getInstance().parseSubtitle(cabJson,HandlerFinal.SUB_CAB);
                list.addAll(list9);
                break;
            default:break;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
