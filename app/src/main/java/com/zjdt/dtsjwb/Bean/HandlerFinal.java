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





    //au   这几个增删改查最后要修改成 为包含固定表的信息
    public static final String AU_ADD="add";
    public static final String AU_SELECT="select";
    public static final String AU_UPDATE="update";
    public static final String AU_DELETE="delete";
    public static final String AU_REGISTER="register";
    public static final int AU_REGISTER_MSG=1001;
    public static final String AU_NOTIFY="add";
    public static final String AU_LOGIN="add";



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
}
