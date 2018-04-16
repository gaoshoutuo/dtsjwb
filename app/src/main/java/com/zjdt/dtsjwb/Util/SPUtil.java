package com.zjdt.dtsjwb.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.Password;

/**
 * 既然是util 就是静态方法咯  sharedpreference 缓存某些数据用的
 * Created by 71568 on 2018/4/8.
 */

public class SPUtil<T> {
   // public static SharedPreferences sharedPreferences;

    /**
     * 这里还是用bean包裹比较好
     * @param username
     * @param password
     */
    public static SPUtil spUtil;
    public static SPUtil getInstance(){
        if(spUtil==null)return spUtil=new SPUtil();
        return spUtil;
    }
    public void spDataSet(Password p,String filename){//login_password
        SharedPreferences.Editor editor= AppApplication.getApp().getSharedPreferences(filename, Context.MODE_PRIVATE).edit();
        editor.putString("username",p.getUsername());
        editor.putString("password",p.getPassword());
        editor.putBoolean("married",true);//为毛为嘛为情所困 yq
        editor.putString("au",p.getAuthorthm());
        editor.apply();
    }
   /* public static void spDataget(T data){
        用T范 的弊端就是没法具体的得到T对象的成员  以及无法修饰语静态语句
    }*/
   public Password spDataget(String filename){
       SharedPreferences sp=AppApplication.getApp().getSharedPreferences(filename,Context.MODE_PRIVATE);
       String username=sp.getString("username","dt");
       String password=sp.getString("password","sj");
       boolean married  =sp.getBoolean("married",true);;
       String au=sp.getString("au","1");
       Log.e("shared",username+password);
       return new Password(username,password,married,au);
   }

}
