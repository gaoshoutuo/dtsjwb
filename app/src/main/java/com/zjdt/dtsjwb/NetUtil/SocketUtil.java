package com.zjdt.dtsjwb.NetUtil;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.CAssetsActivity;
import com.zjdt.dtsjwb.Activity.HistoryActivity;
import com.zjdt.dtsjwb.Activity.InfoActivity;
import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.OfflineActivity;
import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.HandlerUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.NotifyUtil;
import com.zjdt.dtsjwb.Util.SPUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;
import static android.support.v4.app.NotificationCompat.getChannelId;

public class SocketUtil {
    /**
     * int port=3333;
     * 想写成一个服务
     */
    private static Socket socket=null;

    public static Socket getSocket(String ip,int port){
        if (socket==null){
        try {
            socket=new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return socket;
    }

    public static String sendMessageAdd(String ip,int port,String json){
        StringBuffer sf=null;
        try {

            Socket socket=new Socket(ip,port);
            //socket=getSocket(ip,port);原因在这里  我要及时的close 原先的socket 则可以建立新的连接不然端口一直被占用  下午尝试 socket 不在创建  服务器socket不关闭？
            //if (socket.isClosed())socket.
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
                    case HandlerFinal.REGISRER_REPLY:
                        Message message=new Message();
                        message.what= HandlerFinal.AU_REGISTER_MSG;
                        message.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(message);
                        break;
                    case HandlerFinal.NOTIFY_REPLY://数据库消息返回语 json

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

                        }
                        break;

                    case HandlerFinal.INS_FIX_I_S:
                        Message messageMIX=new Message();
                        messageMIX.what= HandlerFinal.DTSJ_INS_FIX;
                        messageMIX.obj=sf.toString();
                        HandlerUtil.handler.sendMessage(messageMIX);
                        break;

                    case HandlerFinal.TIS_HISTORY_ENG:
                        JSONArray engArray=reply.getJSONArray("array");
                        HistoryActivity.sInstance.list=JsonUtil.parseHistory(engArray.toString());
                        break;

                    case HandlerFinal.TIS_HISTORY_CUS:
                        JSONArray cusArray=reply.getJSONArray("array");
                        HistoryActivity.sInstance.list=JsonUtil.parseHistory(cusArray.toString());
                        break;

                    case HandlerFinal.UPS_FIX_REQUEST:
                        Message upsfixRequest= HandlerUtil.handler.obtainMessage();
                        upsfixRequest.what=HandlerFinal.FIX_UPS_REQUEST;
                        break;

                    case HandlerFinal.LOGIN_REPLY://其实这里的request
                        HandlerFinal.userId=  reply.getString("userid");
                        HandlerFinal.indentity=reply.getString("identity");
                        HandlerFinal.userName=reply.getString("name");
                        HandlerFinal.userLocation=reply.getString("location");
                        HandlerFinal.company=reply.getString("company");
                        //SPUtil.getInstance().spDataSet(new Password());
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

                        //loginreply 要做到返回小红点 返回offlineMsg

                        break;

                    case HandlerFinal.BATTERY_REPLY:
                        HandlerFinal.upsBatteryNum=reply.getInt("number");
                        Log.e("battery_num",HandlerFinal.upsBatteryNum+"");
                        break;

                    case HandlerFinal.INFO_REPLY:
                        InfoActivity.list=JsonUtil.parseInfo(reply.toString());// Intent infoIntent=new Intent(AppApplication.getApp(), InfoActivity.class);

                        //AppApplication.getApp().startActivity(infoIntent);
                        Message infoMsg=HandlerUtil.handler.obtainMessage();
                        infoMsg.what=HandlerFinal.INFO_MSG;
                        infoMsg.sendToTarget();
                        break;

                    case HandlerFinal.ASSERT_REPLY:
                        //CAssetsActivity.sInstance.getList()=JsonUtil.parseAssert(reply);  函数不能给函数吗  这只是传递
                        //ArrayList list=CAssetsActivity.sInstance.getList();
                        Log.e("122222",reply.toString()+"123");
                        if (reply.toString().length()<50){
                            Toast.makeText(AppApplication.getApp(),"对不起，您还没有录入资产",Toast.LENGTH_SHORT).show();

                            break;
                        }
                        CAssetsActivity.sInstance.setList(JsonUtil.template(HandlerFinal.HUNDRED_STR,reply));
                        break;

                    case HandlerFinal.NOTIFY_CUS_AUTH_BASIC://通知客户允许

                        // 跳出提醒界面提醒dialog客户工程师xx 想帮您   这就是一些提前的设计的问题
                        break;

                    case HandlerFinal.NOTIFY_ENG_KNOW_BASIC://我方工程师收到通知
                        break;

                    case HandlerFinal.NOTIFY_CUS_AUTH_IT://通知客户允许
                        break;

                    case HandlerFinal.NOTIFY_ENG_KNOW_IT://我方工程师收到通知
                        break;
                    case HandlerFinal.CHECK_U_I:
                        Log.e("cui",reply.toString());
                        if (reply.getString("ischecked").equals("no")){
                            HandlerFinal.isCheckUP=false;
                            Toast.makeText(AppApplication.getApp(),"密码错误",Toast.LENGTH_SHORT).show();
                        }else if (reply.getString("ischecked").equals("yes")){
                            HandlerFinal.isCheckUP=true;
                            HandlerFinal.ide=reply.getString("iden");
                            Log.e("cui", HandlerFinal.ide+"123");
                           // Toast.makeText(AppApplication.getApp(),"登录成功",Toast.LENGTH_SHORT).show();
                            Password pwd=new Password();
                            if (reply.getString("iden").equals("维保人员")){
                                pwd.setMarried(true);
                                pwd.setAuthorthm("1");
                                pwd.setUsername(reply.getString("user_id"));
                                pwd.setPassword(reply.getString("pwd"));
                            }else if (reply.getString("iden").equals("企业客户")){
                                pwd.setMarried(true);
                                pwd.setAuthorthm("2");
                                pwd.setUsername(reply.getString("user_id"));
                                pwd.setPassword(reply.getString("pwd"));
                            }
                            SPUtil.getInstance().spDataSet(pwd,"login_passowrd");
                           HandlerFinal.userId=  reply.getString("user_id");
                            HandlerFinal.indentity=reply.getString("iden");
                            HandlerFinal.userName=reply.getString("name");
                            HandlerFinal.userLocation=reply.getString("location");
                            HandlerFinal.company=reply.getString("company");
 /*
                            //再做一次  但是这样会有两次登录记录
               jsonLogin.put("au","login");
              jsonLogin.put("user_str",userStr);
              jsonLogin.put("pwd_str",pwdStr);
*/
                        }
                        break;


                    case HandlerFinal.NOTIFY_ENTRY_ASSET:
                        if (reply.getString("instruction").equals(HandlerFinal.NOTIFY_ADDED_BASE)){//这俩属于客户
                            NotifyUtil.notifySer(reply, MenuActivity.class);
                        }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_ADDED_IT)){
                            NotifyUtil.notifySer(reply, MenuActivity.class);
                        }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_AGREE_BASE)){//这俩属于工程师
                            //
                            HandlerFinal.isAgree=true;NotifyUtil.notifySer(reply, MenuActivity.class);
                            HandlerFinal.oneHour=true;
                        }else if(reply.getString("instruction").equals(HandlerFinal.NOTIFY_AGREE_IT)){
                            //
                            HandlerFinal.isAgree=true;NotifyUtil.notifySer(reply, MenuActivity.class);
                            HandlerFinal.oneHour=true;
                        }else if(reply.getString("instruction").equals(HandlerFinal.CHECK_ONE_HOUR_OFF)){// 如何能够异步起来 关闭则要重新提交
                            HandlerFinal.isAgree=false;
                        }else if(reply.getString("instruction").equals(HandlerFinal.CHECK_ONE_HOUR_ON)){
                            HandlerFinal.isAgree=true;
                            HandlerFinal.oneHour=true;
                            if (reply.getString("work_type").equals("it")){
                                HandlerFinal.isAuthorizeCusIdITAsset=reply.getString("cus_id");
                            }else if(reply.getString("work_type").equals("base")){
                                HandlerFinal.isAuthorizeCusIdBasicAsset=reply.getString("cus_id");
                            }
                        }else if(reply.getString("instruction").equals(HandlerFinal.REFUSE)){
                            HandlerFinal.isAgree=false;
                        }

                        break;
                    case "create_idc_reply":
                        Message cirMsh=HandlerUtil.handler.obtainMessage();
                        cirMsh.what=HandlerFinal.MSG_CREATE_IDC;
                        cirMsh.sendToTarget();

                        break;
                    case "idc_query_reply":
                   // {"array":[{"idc_s_location":"浙江省-杭州市-滨江区","idc_name":"g","idc_type":"整机房资产","idc_id":"1533693371827"}],"reply":"idc_query_reply"}
                       Message iqrMsg= HandlerUtil.handler.obtainMessage();
                       iqrMsg.what= HandlerFinal.IDC_QUERY_REPLY;
                        iqrMsg.obj=reply.toString();
                        iqrMsg.sendToTarget();
                        Log.e("idc_query_reply",reply.toString());
                        break;

                    case "query_sub_reply":
                        String subType=reply.getString("type");
                        Message qsrMsg= HandlerUtil.handler.obtainMessage();
                        qsrMsg.what= HandlerFinal.QUERY_SUB_REPLY;
                        qsrMsg.obj=reply.toString();
                        qsrMsg.arg1=subSwitch(subType);//记录类型来搞其他的一些事情

                        qsrMsg.sendToTarget();
                        Log.e("query_sub_reply",reply.toString());
                        break;


                    case "double_msg_offline_reply":
                        Log.e("SocketUtil","123"+reply.toString());
                        Message offlineMsg=OfflineActivity.offActivity.handler.obtainMessage();
                        offlineMsg.what=HandlerFinal.OFFLINE_REPLY;
                        JSONArray offlineArray=reply.getJSONArray("array");
                        offlineMsg.obj=offlineArray.toString();
                        offlineMsg.sendToTarget();
                        break;

                    case "double_msg_history_reply":
                        Log.e("SocketUtil","123"+reply.toString());
                        Message historyMsg=OfflineActivity.offActivity.handler.obtainMessage();
                        String dmType=reply.getString("type");
                        if (dmType.equals("eng")){
                            historyMsg.what=HandlerFinal.ENG_HISTORY_REPLY;
                        }else if(dmType.equals("cus")){
                            historyMsg.what=HandlerFinal.HISTORY_REPLY;
                        }

                        //historyMsg.what=HandlerFinal.HISTORY_REPLY;
                        JSONArray historyArray=reply.getJSONArray("array");
                        historyMsg.obj=historyArray.toString();
                        historyMsg.sendToTarget();
                        break;

                        //是不是需要一个eng_history 呢
                    case "json_1_2_reply":
                        HandlerFinal.setSingValue(reply.getString("json"),reply.getString("buss_type"));

                        break;

                    case "count_down_reply"://维保倒计时
                        Message countDownMsg=OfflineActivity.offActivity.handler.obtainMessage();
                        countDownMsg.what=HandlerFinal.COUNT_DOWN_REPLY;
                        JSONArray countDownArray=reply.getJSONArray("array");
                        countDownMsg.obj=countDownArray.toString();
                        countDownMsg.sendToTarget();
                        break;
                        //
                        default:break;
                }

                //socket 要重新封装 地址要切换  好吧 我屈服了 想想别的解决办法
            Log.e("SocketUtil","123"+reply.toString());

            os.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("SocketUtil","123"+sf.toString()+"");

        return sf.toString()+"";
    }

    private static int subSwitch(String type){
        int itype=-1;
        switch (type){
            case "sub_ups":
                itype=0;
                break;
            case "sub_air":
                itype=1;
                break;
            case "sub_emi":
                itype=2;
                break;

            case "sub_ms":
                itype=3;
                break;

            case "sub_mi":
                itype=4;
                break;

            case "sub_mh":
                itype=5;
                break;

            case "sub_mac":
                itype=6;
                break;

            case "sub_mv":
                itype=7;
                break;

            case "sub_cab":
                itype=8;
                break;


                default:break;
        }
        return itype;
    }
}
