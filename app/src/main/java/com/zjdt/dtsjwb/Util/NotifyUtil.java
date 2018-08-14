package com.zjdt.dtsjwb.Util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

public class NotifyUtil {

    public static void notifySer(JSONObject reply,Class t) {
        try {

            if (reply.getString("cus_id").equals(HandlerFinal.userId)) {//逻辑就是cus_id 就是我客户自己的 所以 就我可以一人打开服务
                //架构概念 数据在系统内怎么流通  有序 有效
                String titleNotify = reply.getString("title");
                String contentNotify = reply.getString("content");

                Intent intent = new Intent(AppApplication.getApp(), t);
                if (reply.getString("instruction").equals(HandlerFinal.NOTIFY_ADDED_BASE)){//这俩属于客户
                    intent.putExtra("nofity",HandlerFinal.NOTIFY_ADDED_BASE);
                    intent.putExtra("title",titleNotify);
                    intent.putExtra("content",contentNotify);
                }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_ADDED_IT)){
                    intent.putExtra("nofity",HandlerFinal.NOTIFY_ADDED_IT);
                    intent.putExtra("title",titleNotify);
                    intent.putExtra("content",contentNotify);
                }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_AGREE_BASE)){//这俩属于工程师
                    intent.putExtra("nofity",HandlerFinal.NOTIFY_AGREE_BASE);
                    intent.putExtra("title",titleNotify);
                    intent.putExtra("content",contentNotify);
                }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_AGREE_IT)){
                    intent.putExtra("nofity",HandlerFinal.NOTIFY_AGREE_IT);
                    intent.putExtra("title",titleNotify);
                    intent.putExtra("content",contentNotify);
                }
                PendingIntent pi = PendingIntent.getActivity(AppApplication.getApp(), 0, intent, 0);
                NotificationManager nm = (NotificationManager) AppApplication.getApp().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(AppApplication.getApp())
                        .setContentTitle(titleNotify)
                        .setContentText(contentNotify)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(AppApplication.getApp().getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setPriority(PRIORITY_MAX)//android.support.v7.app.NotificationCompat.PRIORITY_MAX  改了会不会出事 因为新增一个gradle
                        .setSound(/*Uri.parse("android:resource://com.android.zhgf.zhgf/"+R.raw.sound1)*/
                                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                        )
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setColor(Color.GREEN)
                        .build();
                      /*  notification.ledOffMS=1000;
                        notification.ledOnMS=1000;
                        notification.flags = 1;*/
                nm.notify(2, notification);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}