package com.zjdt.dtsjwb.NetUtil;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.zjdt.dtsjwb.Activity.CAssetsActivity;
import com.zjdt.dtsjwb.Activity.HistoryActivity;
import com.zjdt.dtsjwb.Activity.InfoActivity;
import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.HandlerUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketUtil {
    /**
     * int port=3333;
     * 想写成一个服务
     */

    public static String sendMessageAdd(String ip,int port,String json){
        StringBuffer sf=null;
        try {
            Socket socket=new Socket(ip,port);
           OutputStream os= socket.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            socket.shutdownOutput();
            InputStream is=socket.getInputStream();
            byte []bytes=new byte[1024];
            int len;
            sf=new StringBuffer();
            while ((len=is.read(bytes))!=-1){
                sf.append(new String(bytes,0,len,"UTF-8"));
            }
            JSONObject reply=new JSONObject(sf.toString());


                switch (reply.getString("reply")){
                    case "register_reply":
                        Message message=new Message();
                        message.what= HandlerFinal.AU_REGISTER_MSG;
                        message.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(message);
                        break;
                    case "notify_reply"://数据库消息返回语 json

                        Log.e("9875",reply.toString());


                        if (reply.getString("cus_id").equals(HandlerFinal.userId)){
                            //架构概念 数据在系统内怎么流通  有序 有效
                            String titleNotify= reply.getString("title");
                            String contentNotify=reply.getString("content");

                            //Intent intent = new Intent(AppApplication.getApp(), SignActivity.class);
                            Intent intent = new Intent(AppApplication.getApp(), PdfLoaderActivity.class);
                            //intent.putExtra("pdffile",reply.getString("filename"));
                            HandlerFinal.pdfFIle=reply.getString("filename");
                            Log.e("9999",reply.getString("filename"));
                            intent.putExtra("title", titleNotify);
                            intent.putExtra("content", contentNotify);


                            PendingIntent pi = PendingIntent.getActivity(AppApplication.getApp(), 0, intent, 0);
                            NotificationManager nm = (NotificationManager) AppApplication.getApp().getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notification = new NotificationCompat.Builder(AppApplication.getApp())
                                    .setContentTitle(titleNotify)
                                    .setContentText(contentNotify)
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(AppApplication.getApp().getResources(),R.mipmap.ic_launcher))
                                    .setContentIntent(pi)
                                    .setAutoCancel(true)
                                    .setPriority(android.support.v7.app.NotificationCompat.PRIORITY_MAX)
                                    .setSound(/*Uri.parse("android:resource://com.android.zhgf.zhgf/"+R.raw.sound1)*/
                                            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                                    )
                                    .setVibrate(new long[]{ 0, 1000, 1000, 1000 })
                                    .setColor(Color.GREEN)
                                    .build();
                      /*  notification.ledOffMS=1000;
                        notification.ledOnMS=1000;
                        notification.flags = 1;*/
                            nm.notify(2,notification);

                        }
                        break;

                    case "ins_fix_i_s":
                        Message messageMIX=new Message();
                        messageMIX.what= HandlerFinal.DTSJ_INS_FIX;
                        messageMIX.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(messageMIX);
                        break;

                    case "tis_history_eng":
                        JSONArray engArray=reply.getJSONArray("array");
                        HistoryActivity.sInstance.list=JsonUtil.parseHistory(engArray.toString());
                        break;

                    case "tis_history_cus":
                        JSONArray cusArray=reply.getJSONArray("array");
                        HistoryActivity.sInstance.list=JsonUtil.parseHistory(cusArray.toString());
                        break;

                    case "battery_y":

                        break;
                    case "ups_fix_request":
                        Message upsfixRequest= HandlerUtil.handler.obtainMessage();
                        upsfixRequest.what=HandlerFinal.FIX_UPS_REQUEST;
                        break;
                    case "notify_reply_":



                        break;

                    case "login_reply"://其实这里的request
                        HandlerFinal.userId=  reply.getString("userid");
                        HandlerFinal.indentity=reply.getString("identity");
                        HandlerFinal.userName=reply.getString("name");
                        HandlerFinal.userLocation=reply.getString("location");
                        HandlerFinal.company=reply.getString("company");
                        Log.e("login_reply",HandlerFinal.userName+"45544545454");
                        //大概来回数据时延是1s 左右  应该不到一点

                        /**
                         *      loginReply.put("reply","login_reply");
                         loginReply.put("identity",llll.getIdentity());
                         loginReply.put("name",llll.getName());
                         loginReply.put("userid",llll.getUserId());
                         loginReply.put("location",llll.getIdentity());
                         loginReply.put("company",llll.getCompany());
                         */

                        break;

                    case "battery_reply":
                        HandlerFinal.upsBatteryNum=reply.getInt("number");

                        break;

                    case "info_reply":
                        InfoActivity.list=JsonUtil.parseInfo(reply.toString());
                        break;

                    case "assert_reply":
                        //CAssetsActivity.sInstance.getList()=JsonUtil.parseAssert(reply);  函数不能给函数吗  这只是传递
                        //ArrayList list=CAssetsActivity.sInstance.getList();
                        Log.e("122222",reply.toString());
                        CAssetsActivity.sInstance.setList(JsonUtil.template(HandlerFinal.HUNDRED_STR,reply));
                        break;

                        default:break;
                }


            Log.e("SocketUtil",sf.toString());
            os.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sf.toString();
    }
}
