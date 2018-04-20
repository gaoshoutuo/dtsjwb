package com.zjdt.dtsjwb.Util;

import android.util.Log;

import com.zjdt.dtsjwb.Bean.HandlerFinal;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketIOUtil {

    /**
     * socketIo的    服务 + socketio +功能
     */

    private void doSocket(){

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
