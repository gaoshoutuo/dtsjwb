package com.zjdt.dtsjwb.NetUtil;

import android.os.Message;
import android.util.Log;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Util.HandlerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtil {
    /**
     * int port=3333;
     * 想写成一个服务
     */

    public static void sendMessageAdd(String ip,int port,String json){

        try {
            Socket socket=new Socket(ip,port);
           OutputStream os= socket.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            socket.shutdownOutput();
            InputStream is=socket.getInputStream();
            byte []bytes=new byte[1024];
            int len;
            StringBuffer sf=new StringBuffer();
            while ((len=is.read(bytes))!=-1){
                sf.append(new String(bytes,0,len,"UTF-8"));
            }
            if(sf.length()>5){
                switch (sf.toString()){
                    case "注册成功":
                        Message message=new Message();
                        message.what= HandlerFinal.AU_REGISTER_MSG;
                        message.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(message);
                        break;
                    case "notify_reply"://数据库消息返回语 json

                        break;

                    case "ins_fix_i_s":
                        Message messageMIX=new Message();
                        messageMIX.what= HandlerFinal.DTSJ_INS_FIX;
                        messageMIX.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(messageMIX);
                        break;

                    case "number_1":

                        break;

                    case "number_2":

                        break;

                    case "number_3":

                        break;
                    case "number_4":

                        break;

                        default:break;
                }


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
