package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {


    public static void ggg(Context activity, String text){
        Toast.makeText(activity,text,Toast.LENGTH_SHORT).show();
    }
}
