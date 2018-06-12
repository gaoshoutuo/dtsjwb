package com.zjdt.dtsjwb.Bean;



/**
 * handler的常量类
 * Created by 71568 on 2018/4/8.
 */

public class HandlerFinal {
    //给socketio用的  ups
    public static final String UPS_1="ups1";
    public static final String UPS_2="ups2";
    public static final String UPS_3="ups3";
    public static final String UPS_4="ups4";
    public static final String UPS_5="ups5";
    public static final String UPS_6="ups6";
    public static final String UPS_7="ups7";


    public static final String NOTIFY_K="notify_k";

//fragment
public static final String[]upsString={"机架式ups主机","功率模块","蓄电池"};
    public static final String[]airString={"精密空调ec","ec室外机连接铜管","精密空调AC","AC室外机连接铜管"};
    public static final String[]emiString={"吊卧式新风处理机","轴流风机"};
    public static final String[]softString={"机房监控平台","IE远程浏览模块","短信报警系统","E-Mail报警系统"};
    public static final String[]interfaceString={"供配电系统软件模块","漏水系统软件模块","温湿度系统软件模块","视频监控系统软件模块","UPS接口协议开发","精密空调接口协议开发"};
    public static final String[]hardwareString={"专业监控主机","嵌入式采集主机","漏水系统（含控制器和漏水绳）","温湿度传感器"};
    public static final String[]acString={"双门门禁控制器","双门磁力锁","单门磁力锁","读卡器","门禁卡","开门按钮","电源","门禁控制箱"};
    public static final String[]videoString={"数字半球摄像机","数字硬盘录像机","硬盘","电源"};
    public static final String[]cabientString={"冷通道柜体（IT设备柜）","柜体后部综合理线板","柜体内部垂直弱电理线板","柜体底部封板","冷通道前后端侧门","柜体内盲板","冷通道封闭天窗（精密空调）",
            "冷通道封闭天窗（IT设备柜、配电柜布线柜）","冷通道封闭移门","冷通道封闭移门门盒组件","通道照明模块","柜体背景照明模块","配电采集箱","精密配电列头柜","冷通道柜体底座"};




    //au   这几个增删改查最后要修改成 为包含固定表的信息
    public static final String AU_ADD="add";


    public static final String AU_SELECT="select";


    public static final String AU_UPDATE="update";


    public static final String AU_DELETE="delete";


    public static final String AU_REGISTER="register";
    public static final int AU_REGISTER_MSG=1001;
    public static final int DTSJ_INS_FIX=1002;

    //public static final String

    public static final String AU_NOTIFY="notify";


    public static final String AU_LOGIN="add";

//userid
    public static String userId;
    public static String userName;
    public static String userLocation;


//midium
    public static final int MEDIA_PHOTO=8001;


    public static final String []DTSJCACHESTR={"dtsjcache","id","device_name","device_id","customer_id","location","reason"};



    public static String authorthm="0";
    public static HandlerFinal hf;

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        HandlerFinal.msg = msg;
    }

    public static HandlerFinal getHf() {
        if(hf==null)return hf=new HandlerFinal();
        return hf;
    }

    public static String getAuthorthm() {
        return authorthm;
    }

    public static void setAuthorthm(String authorthm) {
        HandlerFinal.authorthm = authorthm;
    }

    public static final int ROOL_MSG=11;
    public static String msg;
    public static String json;


    public static final String STR_ASSET="asset";

}
