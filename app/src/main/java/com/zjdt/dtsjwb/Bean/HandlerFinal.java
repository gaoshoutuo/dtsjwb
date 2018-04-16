package com.zjdt.dtsjwb.Bean;



/**
 * handler的常量类
 * Created by 71568 on 2018/4/8.
 */

public class HandlerFinal {
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
