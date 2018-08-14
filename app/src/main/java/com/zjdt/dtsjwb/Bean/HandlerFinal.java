package com.zjdt.dtsjwb.Bean;


import android.os.Environment;

import com.zjdt.dtsjwb.App.AppApplication;

import java.sql.Timestamp;

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
    public static String userId,myCustomId;
    public static String userName,myCustomName;
    public static String userLocation,myCusLocation;
    public static String indentity;
    public static String company,myCusEng;
    public static String ip;// ip 地址 或者某些接口地址可能会改变


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
    public static final double VERSION=1.0;

    //文件路径
   // public static final String PATH_MOVIE = AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();我就在想  在初始化这个类之前 application有没有被初始化

    public static final int MESSAGE_ENG=1200;
    public static final int MESSAGE_CUS=1300;

    public static final int FIX_UPS_REQUEST=1400;
    public static final int TEST_UPS_REQUEST=1500;
    public static final int INS_UPS_REQUEST=1600;
    public static final int INS_AIR_REQUEST=1700;
    public static final int SERVICE_REQUEST=1800;
    public static final int INSTALL_REQUEST=1900;


    public static final String []BUSINESS_STR={"fix_ups",  "test_ups",  "ins_ups",  "fix_air",  "test_air",  "ins_air",  "site_install",  "site_service",  "firefighting",  "monitor"};
    //{"device_name":"","device_para":"","device_type":"","device_brand":"","device_num":""},
    public static final String []FIVE_STR={"device_name","device_para","device_type","device_brand","device_num"};
    public static final String []HUNDRED_STR={"es_body_1","es_body_2","es_body_3",  "air_body_1","air_body_2","air_body_3","air_body_4",  "emi_body_1", "emi_body_2",
    "mon_soft_body_1","mon_soft_body_2","mon_soft_body_3","mon_soft_body_4",  "mon_interface_body_1","mon_interface_body_2","mon_interface_body_3","mon_interface_body_4","mon_interface_body_5","mon_interface_body_6",
     "mon_hard_body_1","mon_hard_body_2","mon_hard_body_3","mon_hard_body_4",  "mon_ac_body_1","mon_ac_body_2","mon_ac_body_3","mon_ac_body_4","mon_ac_body_5","mon_ac_body_6","mon_ac_body_7","mon_ac_body_8",
    "mon_video_body_1","mon_video_body_2","mon_video_body_3","mon_video_body_4",  "cabient_body_1","cabient_body_2","cabient_body_3","cabient_body_4","cabient_body_5","cabient_body_6","cabient_body_7","cabient_body_8",
            "cabient_body_9","cabient_body_10","cabient_body_11","cabient_body_12","cabient_body_13","cabient_body_14","cabient_body_15"
    };

    public static final String []SUB_UPS={"es_body_1","es_body_2","es_body_3"};
    public static final String []SUB_AIR={"air_body_1","air_body_2","air_body_3","air_body_4"};
    public static final String []SUB_EMI={ "emi_body_1", "emi_body_2"};
    public static final String []SUB_MS={"mon_soft_body_1","mon_soft_body_2","mon_soft_body_3","mon_soft_body_4"};
    public static final String []SUB_MI={"mon_interface_body_1","mon_interface_body_2","mon_interface_body_3","mon_interface_body_4","mon_interface_body_5","mon_interface_body_6"};
    public static final String []SUB_MH={"mon_hard_body_1","mon_hard_body_2","mon_hard_body_3","mon_hard_body_4"};
    public static final String []SUB_MAC={ "mon_ac_body_1","mon_ac_body_2","mon_ac_body_3","mon_ac_body_4","mon_ac_body_5","mon_ac_body_6","mon_ac_body_7","mon_ac_body_8"};
    public static final String []SUB_MV={  "mon_video_body_1","mon_video_body_2","mon_video_body_3","mon_video_body_4"};
    public static final String []SUB_CAB={"cabient_body_1","cabient_body_2","cabient_body_3","cabient_body_4","cabient_body_5","cabient_body_6","cabient_body_7","cabient_body_8",
            "cabient_body_9","cabient_body_10","cabient_body_11","cabient_body_12","cabient_body_13","cabient_body_14","cabient_body_15"};


    public static final String []NAME_STR={"电气子系统","空调子系统","新风排风子系统","机房监控子系统","机房监控接口子系统","机房监控硬件子系统","门禁监控系统","视频监控系统","机柜子系统"};

    public static String pdfFIle;

    public static int upsBatteryNum;
    public static boolean dialogSwitch=false;
    //客户同意资产录入
    public static String isAuthorizeCusIdBasicAsset;
    public static String isAuthorizeCusIdITAsset;
    public static boolean isAgree=false;
    public static boolean oneHour=false;


    //socket  的值 为防止出现魔法值
    public static final String NOTIFY_REPLY="notify_reply";
    public static final String REGISRER_REPLY="register_reply";
    public static final String INS_FIX_I_S="ins_fix_i_s";
    public static final String TIS_HISTORY_ENG="tis_history_eng";
    public static final String TIS_HISTORY_CUS="tis_history_cus";
    public static final String UPS_FIX_REQUEST="ups_fix_request";
    public static final String LOGIN_REPLY="login_reply";
    public static final String BATTERY_REPLY="battery_reply";
    public static final String INFO_REPLY="info_reply";
    public static final String ASSERT_REPLY="assert_reply";
    //提前设计所遗弃的东西
    public static final String NOTIFY_CUS_AUTH_BASIC="notify_cus_auth_basic";
    public static final String NOTIFY_ENG_KNOW_BASIC="notify_eng_know_basic";
    public static final String NOTIFY_CUS_AUTH_IT="notify_cus_auth_it";
    public static final String NOTIFY_ENG_KNOW_IT="notify_eng_know_it";

    public static final String NOTIFY_ENTRY_ASSET="help_reply";
    public static final String NOTIFY_ADDED_BASE="added_base";
    public static final String NOTIFY_ADDED_IT="added_it";
    public static final String NOTIFY_AGREE_BASE="agree_base";
    public static final String NOTIFY_AGREE_IT="agree_it";

    public static final String NOTIFY_UPDATE_IT="update_it";
    public static final String NOTIFY_UPDATE_BASE="update_base";

    public static final String CHECK_ONE_HOUR_OFF="off_use";
    public static final String CHECK_ONE_HOUR_ON="on_use";
    public static final String IM_AGREE="我同意";
    public static final String IM_NOT_AGREE="我不同意";
    public static final String REFUSE="refuse";

    public static final String CHECK_U_I="check_u_i";
    public static  boolean isCheckUP=false;
    public static String ide=null;

    public static Timestamp timeStamp;












    public static final int MSG_CREATE_IDC=6200;
    public static final int IDC_QUERY_REPLY=6210;

    public static final int QUERY_SUB_REPLY=6220;
}
