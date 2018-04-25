package com.zjdt.dtsjwb.NetUtil;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtil {
    /**
     * 想写成一个服务
     */

    public static void sendMessage(){
        String ip=null;

        ip="192.168.1.68";

        int port=3333;
        try {
            Socket socket=new Socket(ip,port);
           OutputStream os= socket.getOutputStream();
            os.write("你好服务器，我是android 我给你发消息".getBytes("UTF-8"));
            socket.shutdownOutput();

            InputStream is=socket.getInputStream();
            byte []bytes=new byte[1024];
            int len;
            StringBuffer sf=new StringBuffer();
            while ((len=is.read(bytes))!=-1){
                sf.append(new String(bytes,0,len,"UTF-8"));
            }
            Log.e("SocketUtil",sf.toString());
            os.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
