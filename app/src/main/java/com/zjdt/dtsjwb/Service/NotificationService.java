package com.zjdt.dtsjwb.Service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class NotificationService extends Service{
    public NotificationService() {
    }

    private Notify notify=new Notify();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return notify;
    }

    @Override
    public void onCreate() {
        notify.listen();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notify.listen();
        return super.onStartCommand(intent, flags, startId);
    }

    public class Notify extends Binder {//socketio 的长连接
        public void listen(){
            try {
                final Socket socket= IO.socket("http://218.108.146.98:3222");
                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");
                        socket.emit("foo","你好 已经连接");

                    }
                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");

                    }
                }).on(HandlerFinal.UPS_1, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {

                        Toast.makeText(AppApplication.getApp(),args.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).on(HandlerFinal.UPS_2, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {

                        Toast.makeText(AppApplication.getApp(),args.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).on(HandlerFinal.UPS_3, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {


                    }
                }).on(HandlerFinal.UPS_4, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {


                    }
                }).on(HandlerFinal.NOTIFY_K, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {

                            JSONObject jsonObject=new JSONObject(args[0].toString());
                            if (jsonObject.getString("au")==HandlerFinal.userId){
                                //架构概念 数据在系统内怎么流通  有序 有效
                                String titleNotify= jsonObject.getString("title");
                                String contentNotify=jsonObject.getString("content");

                                Intent intent = new Intent(AppApplication.getApp(), SignActivity.class);
                                intent.putExtra("title", titleNotify);
                                intent.putExtra("content", contentNotify);


                                PendingIntent pi = PendingIntent.getActivity(AppApplication.getApp(), 0, intent, 0);
                                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                Notification notification = new NotificationCompat.Builder(AppApplication.getApp())
                                        .setContentTitle(titleNotify)
                                        .setContentText(contentNotify)
                                        .setWhen(System.currentTimeMillis())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
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

                        }  catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                socket.connect();


            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        public void listen2(int port){ //java 的socket 长连接  server 就是一直等待后台的到来  太费电了  推送  socket 两种 一直是直接 ip port  另一种通过server获取
            /**
             *  这种方法不行 因为socket的服务端启动时滞后的。看来 只有nodejs socketio
             */
            StringBuilder sb=new StringBuilder();
            try {

                ServerSocket serverSocket=new ServerSocket(port);
                java.net.Socket socket=serverSocket.accept();
                InputStream is=socket.getInputStream();
                byte []bytes=new byte[1024];
                int len=0;
                while ((len=is.read(bytes))!=-1){
                    sb.append(new String(bytes,0,len));
                }
                JSONObject jsonObject=new JSONObject(sb.toString());
                if (jsonObject.getString("au")==HandlerFinal.userId){
                    //架构概念 数据在系统内怎么流通  有序 有效
                    String titleNotify= jsonObject.getString("title");
                    String contentNotify=jsonObject.getString("content");

                    Intent intent = new Intent(AppApplication.getApp(), SignActivity.class);
                    intent.putExtra("title", titleNotify);
                    intent.putExtra("content", contentNotify);


                    PendingIntent pi = PendingIntent.getActivity(AppApplication.getApp(), 0, intent, 0);
                    NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(AppApplication.getApp())
                            .setContentTitle(titleNotify)
                            .setContentText(contentNotify)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
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
                    nm.notify(1,notification);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            java.net.Socket socket2=new java.net.Socket();

        }

        public void listen3(int port){ //java 的socket 长连接  server 就是一直等待后台的到来  太费电了  推送  socket 两种 一直是直接 ip port  另一种通过server获取
            /**
             *
             */
            StringBuilder sb=new StringBuilder();
            try {

                ServerSocket serverSocket=new ServerSocket(port);
                java.net.Socket socket=serverSocket.accept();
                InputStream is=socket.getInputStream();
                byte []bytes=new byte[1024];
                int len=0;
                while ((len=is.read(bytes))!=-1){
                    sb.append(new String(bytes,0,len));
                }
                JSONObject jsonObject=new JSONObject(sb.toString());
                if (jsonObject.getString("au")==HandlerFinal.userId){
                    //架构概念 数据在系统内怎么流通  有序 有效

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            java.net.Socket socket2=new java.net.Socket();

        }


    }

    /**
     * 如何写一个抽象共有的
     * 1 提出问题 3问题的关键 5尝试解释 7抽象这种解释可不可能更好
     */

    public class ServiceD extends IntentService{

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         *
         */


        public ServiceD() {
            super(null);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            startService(new Intent(this,NotificationService.class));
        }
    }
}
