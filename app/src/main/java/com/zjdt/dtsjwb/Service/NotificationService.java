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

import com.google.gson.JsonObject;
import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

public class NotificationService extends Service{
    private int msgNum=0;
    private String serviceUserId;
    private String serviceIdentity;
    private String serviceName;
    private String serviceLocation;
    private String serviceCompany;

    private String pdfFilename;

    private void setData(Intent intent){

        serviceUserId=intent.getStringExtra("userid");
        serviceIdentity=intent.getStringExtra("identity");
        serviceName=intent.getStringExtra("name");
        serviceLocation=intent.getStringExtra("location");
        serviceCompany=intent.getStringExtra("company");

    }

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
        setData(intent);
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
                        socket.emit("foo","手机端连接");
                        socket.on(HandlerFinal.NOTIFY_K, new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                try {
                                    Log.e("9875",args[0].toString());

                                    JSONObject jsonObject=new JSONObject(args[0].toString());
                                    if (jsonObject.getString("cus_id").equals(HandlerFinal.userId)){
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
                                                .setPriority(PRIORITY_MAX)
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

                    }
                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");

                    }
                }).on(HandlerFinal.MSG, new Emitter.Listener() {//ji计算机最重要的能力 抽象
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO",  args[0].toString());

                       // Log.e("9875",args[0].toString().toString());
                        if (msgNum!=0){
                            return;
                        }
                        msgNum=1;
                        ThreadUtil.execute(new ThreadUtil.CallBack() {
                            @Override
                            public void exec() {

                            }

                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    msgNum=0;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        JSONObject notifyReply=null;
                        try {
                            notifyReply=new JSONObject(args[0].toString());
                            final String json=args[0].toString();
                            Log.e("Socket.IO",  serviceUserId+"-----");
                        if (notifyReply.getString("cus_id").equals(serviceUserId)){
                            //架构概念 数据在系统内怎么流通  有序 有效
                            String titleNotify= notifyReply.getString("title");
                            String contentNotify=notifyReply.getString("content");

                            //Intent intent = new Intent(AppApplication.getApp(), SignActivity.class);
                            Intent intent = new Intent(AppApplication.getApp(), PdfLoaderActivity.class);
                            //intent.putExtra("pdffile",reply.getString("filename"));
                            //此处不能这样  service 与 activity 的传值 还是老老实实intent把


                            //HandlerFinal.pdfFIle=notifyReply.getString("filename");
                            //{"reason":"原因","filename":"d:/ftp/1535351675851.pdf","business":"ins_ups","eng_name":"郑拓弘","title":
                            // "提醒通知","reply":"notify_reply","content":"工程师郑拓弘的工作ins_ups已经完成了，请您查收","timestamp":"1535351674380",
                            // "cus_id":"18812345678"} 是不返回这么多
                            pdfFilename=notifyReply.getString("filename");
                            final String buss=notifyReply.getString("business");
                            Log.e("9999",notifyReply.getString("filename"));
                            intent.putExtra("title", titleNotify);
                            intent.putExtra("content", contentNotify);
                            intent.putExtra("pdfname", pdfFilename);


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
                                    .setPriority(PRIORITY_MAX)//android.support.v7.app.NotificationCompat.PRIORITY_MAX  改了会不会出事 因为新增一个gradle
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
                            //padloader 必须静态广播
                            ThreadUtil.execute(new ThreadUtil.CallBack() {
                                @Override
                                public void exec() {

                                }

                                @Override
                                public void run() {//看来需要多传递一点
                                    Intent intentPdf=new Intent();
                                    intentPdf.putExtra("pdfname",pdfFilename);
                                    intentPdf.putExtra("bussiness_type",buss);
                                    intentPdf.putExtra("json",json);
                                    intentPdf.setAction("com.zjdt.dtsjwb.123");
                                    sendBroadcast(intentPdf);
                                }
                            });


                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).on(HandlerFinal.NOTIFY_K, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        try {
                            Log.e("9875",args[0].toString());

                            JSONObject jsonObject=new JSONObject(args[0].toString());
                            if (jsonObject.getString("cus_id").equals(HandlerFinal.userId)){
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
                                        .setPriority(PRIORITY_MAX)//android.support.v7.app.NotificationCompat.PRIORITY_MAX
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
                            .setPriority(PRIORITY_MAX)
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
