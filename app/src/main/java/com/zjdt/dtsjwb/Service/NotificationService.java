package com.zjdt.dtsjwb.Service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zjdt.dtsjwb.Bean.HandlerFinal;

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

    public class Notify extends Binder {
        public void listen(){
            try {
                final Socket socket= IO.socket("http://192.168.1.102:3222");
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
                        Log.e("Socket.IO", "Server is connected!");

                    }
                }).on(HandlerFinal.UPS_2, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");

                    }
                }).on(HandlerFinal.UPS_3, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");

                    }
                }).on(HandlerFinal.UPS_4, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e("Socket.IO", "Server is connected!");

                    }
                });
                socket.connect();


            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
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
         * @param name Used to name the worker thread, important only for debugging.
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
