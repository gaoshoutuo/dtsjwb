package com.zjdt.dtsjwb.Util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public final class PermissonUtil {
    //需要申请的权限
    private static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    //一次性申请权限
    public static String[] checkPermission(Context context){
        List<String>list=new ArrayList<>();
        for(String str:permissions){
            //权限没被同意过
            if(ContextCompat.checkSelfPermission(context,str)!= PackageManager.PERMISSION_GRANTED){
                 list.add(str);
            }
        }
        return list.toArray(new String[list.size()]);
    }


}
