package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;

import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

public class DialogUtil {
    /**
     * 包含Progressbar AlertDialog ProgressDialog
     *
     * 等多3个 工具
     * progress bar 需要有xmlprogressbar 的支持
     * AlertDialog  不需要xml
     * ProgressDialog 也不需要xml 一个圈在转
     * menuList 左上角的action bar
     * menuList 左上角的action bar
     *
     */
    public static DialogUtil dialogUtil;

    public static DialogUtil getDialogUtil() {
        if(dialogUtil==null){
            return dialogUtil=new DialogUtil();
        }
        return dialogUtil;
    }

    public class ProgressTuil{
        private Context context;
        private ProgressBar progressBar;
        public void showShapeProgressbar(){
            progressBar.setVisibility(View.VISIBLE);
        }
        /**
         * 圆形进度条
         */
        public void hideShapeProgressbar(){
            progressBar.setVisibility(View.GONE);
        }

        public ProgressTuil(Activity context, int rid) {
            //progressBar=context.findViewById(rid);
            this.context = context;
            this.progressBar = context.findViewById(rid);
        }

        /**
         * 长度为100的进度条
         *  水平不够任然是需要借助xml来实现 也不丢人
         */
        public void initProgress1(int rid,Activity context){

        }

        public void linearProgress(){
            progressBar= new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
            progressBar.setMax(100);
            progressBar.setBackgroundColor(context.getResources().getColor(R.color.intelligence));
            progressBar.setProgressDrawable(context.getResources().getDrawable(R.color.normal));
            //progressBar.setScrollBarStyle();
            // progressBar.set
        }
        public void setProgress(){
            int x=progressBar.getProgress();
            x=x+10;
            progressBar.setProgress(x);
        }
    }

    public class AlertDialogUtil{
        private AlertDialog.Builder alertDialog;
        private Context context;

        /**
         * DI注入一下
         *
         * 好多创建对象的逻辑都是在使用一个内部类的逻辑
         * 程序界的宗教党 与艺术党 实干党
         * @param
         */
        private DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        public AlertDialogUtil(Activity context) {
            this.context=context;
            this.alertDialog = new AlertDialog.Builder(context);
        }

        /**
         * 发现了 当形参一多就会比较麻烦  而且假如是类包类这样那不如去形成树呢
         * 凡是能带参数的都能提出来抽象
         * @param rightText
         * @param leftText
         * @param title
         * @param message
         *
         * di which 左边 -2 右边-1
         */
        public void setAlertDialog(String rightText, String leftText, String title, String message,DialogInterface.OnClickListener di){
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
          /*  alertDialog.setPositiveButton(rightText, new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("swswswsw",which+"");
                }
            } );
            alertDialog.setNegativeButton(leftText,new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("swswswsw",which+"");
                }
            } );*/
            alertDialog.setPositiveButton(rightText,di);//-1 右边
            alertDialog.setNegativeButton(leftText,di);//-2  左边
            alertDialog.show();
        }
    }

   public class ProgressDialogUtil{
        private ProgressDialog pd;

        public ProgressDialogUtil(Activity context) {
            this.pd = new ProgressDialog(context);
        }

        public void setProgressDialog(String title,String message){
            pd.setTitle(title);
            pd.setMessage(message);
            pd.setCancelable(true);
            pd.show();
        }
    }


}
