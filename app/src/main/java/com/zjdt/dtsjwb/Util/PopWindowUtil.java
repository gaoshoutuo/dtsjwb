package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.zjdt.dtsjwb.R;

public class PopWindowUtil {
    View view=null;
    private View.OnClickListener listener;
    private int rid;
    private Activity context;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;
    public void show(){
        initView1();
        View rootView = ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
      //  popupWindow.

    }

    public PopWindowUtil(View.OnClickListener listener, int rid, Activity context) {
        this.listener = listener;
        this.rid = rid;
        this.context = context;
    }

    private void initView1(){
        layoutInflater=LayoutInflater.from(context);
        View menuview=layoutInflater.inflate(rid,null,false);
        //这里我拿 之前的device menu 做实验
        popupWindow = new PopupWindow(menuview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.colorPrimary));
        popupWindow.setBackgroundDrawable(dw);
    }


}
