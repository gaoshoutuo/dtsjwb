package com.zjdt.dtsjwb.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.zjdt.dtsjwb.Activity.CAssetsActivity;
import com.zjdt.dtsjwb.Activity.InfoActivity;
import com.zjdt.dtsjwb.Activity.ItAssetActivity;
import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.Activity.NewFixActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.AddSubFragment;
import com.zjdt.dtsjwb.Activity.NewRequirements.AsertFormActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.OfflineActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.SubAssetActivity;
import com.zjdt.dtsjwb.Activity.PdfLoaderActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.FixUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.SiteActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.TestUpsActivity;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.JsonUtilBean;
import com.zjdt.dtsjwb.Bean.PCDBean;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;

import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

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
    private HashMap makeHashmap(String location,String reason,String cusId){
        HashMap fixupsmap=new HashMap();
        fixupsmap.put("h_location",location);
        fixupsmap.put("h_reason",reason);
        //  fixupsmap.put("h_timestamp",timestamp);
        fixupsmap.put("h_custom_id",cusId);
        //   fixupsmap.put("h_engineer_id",engId);
        //  fixupsmap.put("h_filename",filename);
        return fixupsmap;
    }
    int step=0;
    public void materialDialog(final Activity context, final int position){

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
                builder.title(R.string.material_title)
                .iconRes(R.drawable.ic_launcher_background)
                        .positiveText("OK")
                .content(R.string.material_content)
                .inputType(InputType.TYPE_CLASS_TEXT)//可以输入的类型-电话号码
                //前2个一个是hint一个是预输入的文字

                .input(R.string.input_hint_id, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                        //HandlerFinal.myCustomId=input.toString();

                        HashMap mapAll=makeHashmap("地区",
                                "原因",
                                input.toString());
                        //HandlerFinal.dialogSwitch=true;
                        switch (position){//真烦改代码   点击之后要蹦出一个填写单子的popwindow 中间出现
                            case 0:
                               /* if (step==0){
                                    step++;
                                    JSONObject jsonObject=new JSONObject();
                                    try {
                                        jsonObject.put("au","idc_query");
                                        jsonObject.put("user_id",input.toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    //HandlerUtil
                                    ThreadUtil.sat(jsonObject);
                                    Toast.makeText(context,"正在查询机房稍等...",Toast.LENGTH_SHORT).show();
                                    // actionActivity(MenuActivity.this,AsertFormActivity.class,null);
                                    ThreadUtil.execute(new ThreadUtil.CallBack() {
                                        @Override
                                        public void exec() {

                                        }

                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(5000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            step=0;
                                        }
                                    });
                                }else{
                                    Toast.makeText(context,"查询频繁请5s后在尝试",Toast.LENGTH_SHORT).show();
                                }*/
                               HandlerFinal.nov=position;
                               helputil(input.toString(),context);
                                //NewFixActivity.actionActivity(context, InsUpsActivity.class,mapAll);
                                break;
                            case 1:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                //NewFixActivity.actionActivity(context, TestUpsActivity.class,mapAll);
                                break;
                            case 2:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                //NewFixActivity.actionActivity(context, FixUpsActivity.class,mapAll);
                                break;
                            case 3:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                mapAll.put("site","service");
                                //NewFixActivity.actionActivity(context, SiteActivity.class,mapAll);
                                break;
                            case 4:

                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                mapAll.put("site","install");
                                //NewFixActivity.actionActivity(context, SiteActivity.class,mapAll);
                                break;
                            case 5:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                //NewFixActivity.actionActivity(context, InsAirActivity.class,mapAll);
                                break;
                            case 6:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                //空调巡检

                                break;
                            case 7:
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);
                                //空调安装

                                break;

                            case 8://空调维修
                                HandlerFinal.nov=position;
                                helputil(input.toString(),context);

                                break;
                            case 9://pdu
                                break;

                          /*  case 0:
                                NewFixActivity.actionActivity(context, InsUpsActivity.class,mapAll);
                                break;
                            case 1:
                                NewFixActivity.actionActivity(context, TestUpsActivity.class,mapAll);
                                break;
                            case 2:
                                NewFixActivity.actionActivity(context, FixUpsActivity.class,mapAll);
                                break;
                            case 3:
                                mapAll.put("site","service");
                                NewFixActivity.actionActivity(context, SiteActivity.class,mapAll);
                                break;
                            case 4:
                                mapAll.put("site","install");
                                NewFixActivity.actionActivity(context, SiteActivity.class,mapAll);
                                break;
                            case 5:
                                NewFixActivity.actionActivity(context, InsAirActivity.class,mapAll);
                                break;
                            case 6:
                                break;
                            case 7:
                                break;*/

                            default:break;
                        }
                    }
                })//很难受 因为没有
                .show();

/*
        MaterialDialog dialog=new MaterialDialog.Builder(context).build();
        DialogAdapter ad=new DialogAdapter(context,R.layout.item_single_edit,4);
          dialog.getBuilder().adapter(ad, new LinearLayoutManager(context));

        RecyclerView list = dialog.getRecyclerView();
        LinearLayoutManager lm= new LinearLayoutManager(context);
        list.setLayoutManager(lm);
        list.setAdapter(ad);

        dialog.show();*/
       /* MaterialDialog.Builder builder=dialog.getBuilder();
        builder.title(R.string.material_title)
                .iconRes(R.drawable.ic_launcher_background)
                .positiveText("OK")
                .content(R.string.material_content)
                .inputType(InputType.TYPE_CLASS_TEXT)//可以输入的类型-电话号码
                //前2个一个是hint一个是预输入的文字
                .input(R.string.input_hint_id, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        Log.i("yqy", "输入的是：" + input);
                    }
                })//很难受 因为没有
                .show();*/



                /*
                input(R.string.input_hint_name, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
            @Override
            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                Log.i("yqy", "输入的是：" + input);
            }
            })
                .input(R.string.input_hint_location, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                        Log.i("yqy", "输入的是：" + input);
                    }
                })
                .input(R.string.input_hint_company, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                        Log.i("yqy", "输入的是：" + input);
                    }
                })
                 */
    }
    //用来 it资产  基础资产的设置分布
    public void materialDialog2(final Activity context){
        View view=LayoutInflater.from(context).inflate(R.layout.item_single_edit,null,false);
        final EditText et=view.findViewById(R.id.single_edittext);
        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context,"123",Toast.LENGTH_SHORT).show();
                        //这里要申请一下，试一下  上传此时timestamp  相减小于3600 000  一小时之内允许
                        JSONObject object=new JSONObject();
                        try {
                            object.put("instruction","one_hour");
                            object.put("au","help_asset");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ThreadUtil.sat(object);
                        DialogUtil.getDialogUtil().materialDialog6(context);

                        /* if(true){//其实应该合理的利用线程  不要等到网络操作才想着用  凡是费时的操作都应该考虑线程
                             JsonUtilBean jub= new JsonUtilBean("au","help_asset");
                             JSONObject object=JsonUtil.makeJsonObj(new JSONObject(),jub);
                             JsonUtilBean jub2= new JsonUtilBean("instruction","one_hour");
                             JsonUtil.makeJsonObj(object,jub);光顾玩封装轮子 并没有好的实现使用

                        }*/
                        //HandlerFinal.isAuthorizeCusIdITAsset==null&&HandlerFinal.isAuthorizeCusIdBasicAsset==null
                        while(!HandlerFinal.oneHour){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        HandlerFinal.oneHour=false;

                        if (!HandlerFinal.isAgree){
                        String text=et.getText().toString();
                            JSONObject jsonObj=new JSONObject();
                        try {
                            jsonObj.put("cus_id",text);
                            jsonObj.put("au","help_asset");
                            jsonObj.put("eng_id",HandlerFinal.userId);
                            ThreadUtil.timeStamp();
                            jsonObj.put("timestamp",HandlerFinal.timeStamp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (r1.isChecked()){
                            try {
                                HandlerFinal.isAuthorizeCusIdBasicAsset=text;
                                jsonObj.put("instruction","add_base");
                                jsonObj.put("business_type","base");
                                //Date date=new Date(jsonObj.getString("timestamp"));

                                //Log.e("akkay",date.toString());
                               // Log.e("akkay",Long.parseLong(jsonObj.getString("timestamp"))+"");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.e("akkay",jsonObj.toString());

                            SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObj.toString());
                        }else if(r2.isChecked()){
                            try {
                                HandlerFinal.isAuthorizeCusIdITAsset=text;
                                jsonObj.put("instruction","add_it");
                                jsonObj.put("business_type","it");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObj.toString());
                        }
                    }else if(HandlerFinal.isAuthorizeCusIdITAsset!=null){
                           // 做it 资产的录入  避免卡顿这些问题  重复麻烦  但是存到内存也 不方便
                            Intent intent=new Intent(context, ItAssetActivity.class);
                            context.startActivity(intent);
                     }else if(HandlerFinal.isAuthorizeCusIdBasicAsset!=null){
                         // 做基础资产的录入   避免卡顿这些问题
                            Intent intent=new Intent(context, CAssetsActivity.class);
                            context.startActivity(intent);
                        }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Toast.makeText(context,"456",Toast.LENGTH_SHORT).show();// view 的listener 传值  TestPdf
            }
        })
               .customView(view,true)//.items(R.array.arr)
               /* .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        return true;
                    }
                }).itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                return true;
            }
        }).inputType(InputType.TYPE_CLASS_TEXT)//可以输入的类型-电话号码
                //前2个一个是hint一个是预输入的文字
                .input(R.string.input_hint_id, R.string.input_prefill, new MaterialDialog.InputCallback() {//避免魔法值
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                    }

                })*/

                .show();




    }

    public void materialDialog3(final Activity context){//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机
        View view=LayoutInflater.from(context).inflate(R.layout.item_three_radio_base,null,false);

        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);
        final RadioButton r3=view.findViewById(R.id.radio3);

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (r1.isChecked()){

                        }else if(r2.isChecked()){

                        }else if(r3.isChecked()){

                        }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                      }
                  }).customView(view,true).show();
    }

    public void materialDialog4(final Activity context){
        View view=LayoutInflater.from(context).inflate(R.layout.item_three_radio_it,null,false);

        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);
        final RadioButton r3=view.findViewById(R.id.radio3);

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (r1.isChecked()){

                        }else if(r2.isChecked()){

                        }else if(r3.isChecked()){

                        }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }

    public void materialDialog5(final Activity context,String title,String content,final String instruction) throws JSONException {//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机  隔天再看不认识之前的写的函数要干嘛了

       final String business;
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(title)
                .content(content)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ThreadUtil.execute(new ThreadUtil.CallBack() {
                            @Override
                            public void exec() {

                            }

                            @Override
                            public void run() {
                                JSONObject jsonObj=new JSONObject();
                                try {
                                    jsonObj.put("im_agree",HandlerFinal.IM_AGREE);
                                    jsonObj.put("au","help_asset");
                                    jsonObj.put("instruction",instruction);
                                    Toast.makeText(context,R.string.toast_reply1,Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObj.toString());
                            }
                        });
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                      @Override
                      public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                          JSONObject jsonObj=new JSONObject();
                          try {
                              jsonObj.put("im_agree",HandlerFinal.IM_NOT_AGREE);
                              jsonObj.put("au","help_asset");
                              jsonObj.put("instruction",instruction);
                              Toast.makeText(context,R.string.toast_reply2,Toast.LENGTH_SHORT).show();
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                          ThreadUtil.sat(jsonObj);

            }
        }).show();
    }

    public void materialDialog7(final Activity context){//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机
        View view=LayoutInflater.from(context).inflate(R.layout.item_three_radio_it,null,false);
        TextView tv=view.findViewById(R.id.sex_rg);
        tv.setText(R.string.hot_call);
        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);
        final RadioButton r3=view.findViewById(R.id.radio3);
        r1.setText(R.string.call_business_xx);r2.setText(R.string.call_cooperation);r3.setText(R.string.call_fix);

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (r1.isChecked()){//维保电话
                            ThreadUtil.sat(new JSONObject());//提交打电话的
                            MediumUtil.call("400-110-5500",context);

                        }else if(r2.isChecked()){//维保电话
                            MediumUtil.call("400-110-5500",context);
                        }else if(r3.isChecked()){//维保电话
                            MediumUtil.call("400-110-5500",context);
                        }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }

    public void materialDialog6(Activity context){
        new MaterialDialog.Builder(context)
                .title(R.string.wait_title)
                .content(R.string.wait_content)
                .progress(true, 0)
                .show();
    }

    public void materialCreateIDC(final PCDBean loca,final Activity context,final CityPickerView pic){//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机
        final View view=LayoutInflater.from(context).inflate(R.layout.item_create_idc,null,false);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        TextView t3=view.findViewById(R.id.location_city);
        final String locationSim=loca.getProvince()+"-"+loca.getCity()+"-"+loca.getDistrict();
        Log.e("poi",locationSim+"123");
        t3.setText(locationSim);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("提交")
                .negativeText("退出")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String idcType=null;
                         RadioButton r1=view.findViewById(R.id.radio1);
                         RadioButton r2=view.findViewById(R.id.radio2);
                         RadioButton r3=view.findViewById(R.id.radio3);
                        EditText editText=view.findViewById(R.id.idc_name);
                        EditText edit2=view.findViewById(R.id.idc_location_edit);
                        String str=editText.getText().toString();
                        String str2=edit2.getText().toString();
                        JSONObject createIDC=new JSONObject();
                        if (r1.isChecked()){
                            idcType="all";
                        }else if(r2.isChecked()){
                            idcType="single";
                        }else if(r3.isChecked()){
                            idcType="multi";
                        }
                        try {
                            createIDC.put("au","create_idc");
                            createIDC.put("idc_id",System.currentTimeMillis()+"");
                            createIDC.put("idc_name",str);
                            createIDC.put("idc_type",idcType);
                            createIDC.put("user_id",HandlerFinal.userId);
                            createIDC.put("user_name",HandlerFinal.userName);
                            createIDC.put("idc_location_deep",str2);
                            createIDC.put("idc_location_simple",locationSim);
                            //发现的确都在全表扫描
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ThreadUtil.sat(createIDC);
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }


    public void materialCreateIDCCus(final PCDBean loca,final Activity context,final CityPickerView pic){//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机
        final View view=LayoutInflater.from(context).inflate(R.layout.item_eng_create_idc,null,false);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        TextView t3=view.findViewById(R.id.location_city);
        final String locationSim=loca.getProvince()+"-"+loca.getCity()+"-"+loca.getDistrict();
        Log.e("poi",locationSim+"123");
        t3.setText(locationSim);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("提交")
                .negativeText("退出")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String idcType=null;
                        RadioButton r1=view.findViewById(R.id.radio1);
                        RadioButton r2=view.findViewById(R.id.radio2);
                        RadioButton r3=view.findViewById(R.id.radio3);
                        EditText editText=view.findViewById(R.id.idc_name);
                        EditText edit2=view.findViewById(R.id.idc_location_edit);
                        String str2=edit2.getText().toString();


                        EditText edit3=view.findViewById(R.id.idc_cus_id);
                        EditText edit4=view.findViewById(R.id.idc_cus_name);
                        String str=editText.getText().toString();
                        String str3=edit3.getText().toString();
                        String str4=edit4.getText().toString();

                        JSONObject createIDC=new JSONObject();
                        if (r1.isChecked()){
                            idcType="all";
                        }else if(r2.isChecked()){
                            idcType="single";
                        }else if(r3.isChecked()){
                            idcType="multi";
                        }
                        try {
                            createIDC.put("au","create_idc");
                            createIDC.put("idc_id",System.currentTimeMillis()+"");
                            createIDC.put("idc_name",str);
                            createIDC.put("idc_type",idcType);
                            createIDC.put("user_id",str3);
                            createIDC.put("user_name",str4);
                            createIDC.put("idc_location_deep",str2);
                            createIDC.put("idc_location_simple",locationSim);

                            //发现的确都在全表扫描
                            //createIDC.put("other_mean","have_mean");算了 直接就能跳转到那边就好了
                            //有这条消息的指示就可以发送回执 以及 发送一个回执
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ThreadUtil.sat(createIDC);
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }

    public void helpAssetDialog(final Activity context,final CityPickerView mPicker){

        View view=LayoutInflater.from(context).inflate(R.layout.item_help_asset_dialog,null,false);
        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);
        final EditText assetId=view.findViewById(R.id.help_asset_id);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                        if (r1.isChecked()){//创建机房

                            Toast.makeText(context,"请先选择机房地区",Toast.LENGTH_SHORT).show();
                            DialogUtil.getDialogUtil().showCityView(mPicker,context,2);
                        }else if(r2.isChecked()){//查询机房
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("au","idc_query");
                            jsonObject.put("user_id",assetId.getText().toString());
                            ThreadUtil.sat(jsonObject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();

    }


    public void infoDialog(final Activity context){
        View view=LayoutInflater.from(context).inflate(R.layout.item_info_dialog,null,false);

        final RadioButton r1=view.findViewById(R.id.radio1);
        final RadioButton r2=view.findViewById(R.id.radio2);
        final EditText e1=view.findViewById(R.id.cus_info);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                        if (r1.isChecked()){
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("au","info");
                            jsonObject.put("name",e1.getText().toString());
                            jsonObject.put("id","007");
                            ThreadUtil.sat(jsonObject);
                        }else if(r2.isChecked()){
                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("au","info");
                            jsonObject.put("id",e1.getText().toString());
                            jsonObject.put("name","007");
                            ThreadUtil.sat(jsonObject);
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }

    /**

     Session session = this.getHibernateTemplate().getSessionFactory()
     .openSession();
     Transaction tx = session.beginTransaction();
     String hql = "update CheckIn ch set ch.state = :state where ch.id= :id";
     Query query = session.createQuery(hql);
     query.setInteger("state", ch.getState());
     query.setInteger("id", ch.getId());
     query.executeUpdate();

     tx.commit();
     session.close();
     * @param context
     */

    public void materialSelectForm(final Activity context, AsertFormActivity.AFTag tag){
        final View view=LayoutInflater.from(context).inflate(R.layout.item_select_idc,null,false);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("提交")
                .negativeText("退出")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        RadioButton r1=view.findViewById(R.id.radio1);
                        RadioButton r2=view.findViewById(R.id.radio2);
                        RadioButton r3=view.findViewById(R.id.radio3);
                        RadioButton r4=view.findViewById(R.id.radio4);
                        String checkType=null;
                        JSONObject siJson=new JSONObject();

                        if (r1.isChecked()){

                        }else if(r2.isChecked()){

                        }else if(r3.isChecked()){

                        }else if(r4.isChecked()){

                        }
                        ThreadUtil.sat(null);
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }

    public void materialSelect(final AsertFormActivity context,final String idcId){//用在资产录入时候的选择  1整个机房的资产 2单体机 3多联机
        final View view=LayoutInflater.from(context).inflate(R.layout.item_four_select,null,false);

        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.material_title)
                .iconRes(R.drawable.help)
                .positiveText("OK")
                .negativeText("NO")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    RadioButton r1=view.findViewById(R.id.radio1);

                    RadioButton r4=view.findViewById(R.id.radio4);
                    String type=null;
                        if (r1.isChecked()){//添加资产
                            type="add";
                            //调出碎片 碎片里面才有这些玩意
                            AsertFormActivity asertFormActivity= context;
                            AddSubFragment addSubFragment=new AddSubFragment();
                            addSubFragment.setMactivity(context);
                            addSubFragment.setRid(R.layout.item_nine_select);
                            addSubFragment.setIdcId(idcId);
                           // asertFormActivity.otherInitView1();
                            asertFormActivity.replaceFragment(addSubFragment);

                        }else if(r4.isChecked()){//查看资产
                            type="query";

                            AsertFormActivity asertFormActivity= context;
                            AddSubFragment querySubFragment=new AddSubFragment();
                            querySubFragment.setMactivity(context);
                            querySubFragment.setRid(R.layout.item_nine_query);
                            querySubFragment.setIdcId(idcId);
                            asertFormActivity.replaceFragment(querySubFragment);
                            //ThreadUtil.sat(null);
                        }


                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();

    }

    public void selectShowDload(final Activity context,final OfflineActivity.OfflineAdapter.MsgTag tag){
        final View view=LayoutInflater.from(context).inflate(R.layout.item_select_show_dload,null,false);
        MaterialDialog.Builder builder= new MaterialDialog.Builder(context);
        builder.title(R.string.idc_dloadselect_)
                .iconRes(R.drawable.help)
                .positiveText("ok")
                .negativeText("退出")
                .onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull final DialogAction which) {
                        RadioButton r1=view.findViewById(R.id.radio1);
                        RadioButton r2=view.findViewById(R.id.radio2);
                        try {
                        String checkType=null;
                        JSONObject showDoladJson=new JSONObject();
                            showDoladJson.put("au","");
                        if (r1.isChecked()){
//查看 pdf  不管客户工程师 完成未完成 都是id
                            ThreadUtil.execute(new ThreadUtil.CallBack() {
                                @Override
                                public void exec() {

                                }

                                @Override
                                public void run() {
                                    //发广播

                                    //此处先获取 json
                               /*     JSONObject jsonobj=new JSONObject();
                                    try {
                                        jsonobj.put("au","json_1_2_msg");
                                        jsonobj.put("timestamp",tag.getTime());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    ThreadUtil.sat(jsonobj);*/
                                    try {

                                        String status=FtpUtil.getFtpClient().getStatus();
                                        Log.e("status_ftp",status);
                                        Log.e("status_ftp",FtpUtil.getFtpClient().getKeepAlive()+"");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //json 必须要有

                                     /*   while(!HandlerFinal.singleTs){
                                            try {
                                                Thread.sleep(100);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        String abc="";*/


                                    Intent intentPdf=new Intent();
                                   /* intentPdf.putExtra("pdfname",tag.getFilename());
                                    intentPdf.putExtra("bussiness_type",HandlerFinal.singleBs);
                                    intentPdf.putExtra("json",HandlerFinal.singleJson);*/
                                    JSONObject obj=new JSONObject();
                                    try {
                                        obj.put("timestamp",tag.getTime());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    intentPdf.putExtra("pdfname",tag.getFilename());
                                    intentPdf.putExtra("bussiness_type",tag.getType());
                                    Log.e("bussiness_type",tag.getType()+"1");
                                    intentPdf.putExtra("json",obj.toString());
                                    intentPdf.setAction("com.zjdt.dtsjwb.123");
                                    context.sendBroadcast(intentPdf);

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                        Intent dload=new Intent(context, PdfLoaderActivity.class);
                                        context.startActivity(dload);





                                }
                            });


                        }else if(r2.isChecked()){
//下载 pdf
                            ThreadUtil.execute(new ThreadUtil.CallBack() {
                                @Override
                                public void exec() {

                                }

                                @Override
                                public void run() {
                                    String externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
                                    String localFile=externalFilesDir+"/"+tag.getFilename();
                                    boolean isup=FtpUtil.downloadFile(tag.getFilename(),localFile);
                                    while (isup){
                                        Toast.makeText(context,"下载成功，请至"+localFile +"查看",Toast.LENGTH_SHORT).show();
                                    }
                                        //发广播
                                       /* Intent intentPdf=new Intent();
                                        intentPdf.putExtra("pdfname",filename);
                                        intentPdf.putExtra("bussiness_type","123");
                                        intentPdf.putExtra("json","123");
                                        intentPdf.setAction("com.zjdt.dtsjwb.123");
                                        context.sendBroadcast(intentPdf);

                                        Intent dload=new Intent(context, PdfLoaderActivity.class);
                                        context.startActivity(dload);*/


                                }
                            });
                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).customView(view,true).show();
    }



    public static class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder>{
        private Activity context;
        private  int layoutId;
        private int size;
        public String[]editStr=new String[4];


        public DialogAdapter(Activity context, int layoutId, int size) {
            this.context = context;
            this.layoutId = layoutId;
            this.size=size;
        }

        public interface Callback{//为啥要static 因为静态才能容纳内部的接口  调用内部接口d的static的方法
            //void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item);
        }

        @Override
        public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(layoutId,null,false);
            DialogViewHolder dvh=new DialogViewHolder(view);
            return dvh;
        }

        @Override
        public int getItemCount() {
            return size;
        }



        @Override
        public void onBindViewHolder(DialogViewHolder holder, int position) {
            switch (position){
                case 0:
                    holder.text.setHint(R.string.input_hint_id);
                    break;
                case 1:
                    holder.text.setHint(R.string.input_hint_name);
                    break;
                case 2:
                    holder.text.setHint(R.string.input_hint_location);
                    break;
                case 3:
                    holder.text.setHint(R.string.input_hint_company);
                    break;

                    default:break;
            }

            editStr[position]=holder.text.getText().toString();

        }

        public void getEditStr(){

        }

       public class DialogViewHolder extends RecyclerView.ViewHolder{
            EditText text;
            public DialogViewHolder(View itemView) {
                super(itemView);
                text=itemView.findViewById(R.id.single_edittext);
            }
        }
    }
    public class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    /*class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode listNode;
            if (l1.val+l2.val>=10){
                listNode=new ListNode(l1.val+l2.val-10);
                listNode.next=new ListNode(1);
            }

            else {
                listNode=new ListNode(l1.val+l2.val);
            }
            final ListNode headHandlerNode=listNode;

            while(l1.next!=null||l2.next!=null){
                ListNode node1=l1.next;
                ListNode node2=l2.next;
                if (node1==null){
                    node1=new ListNode(0);
                }

                if (node2==null){
                    node2=new ListNode(0);
                }

                if (listNode.next!=null){
                    if (node1.val+node2.val>=9){
                        listNode.next=new ListNode(node1.val+node2.val-9);
                        listNode.next.next=new ListNode(1);
                    }else{
                        listNode.next=new ListNode(node1.val+node2.val+1);
                    }

                }
                else {
                    if (node1.val+node2.val>=10){
                        listNode.next=new ListNode(node1.val+node2.val-10);
                        listNode.next.next=new ListNode(1);
                    }else{
                        listNode.next=new ListNode(node1.val+node2.val);
                    }

                }
                l1=node1;l2=node2;listNode=listNode.next;
            }
            return headHandlerNode;
        }
    }*/
    //modebus  这个协议在工业网上用的比较多
    public void showCityView(final CityPickerView pic, final Activity context, final int type){
        CityConfig cityConfig = new CityConfig.Builder().build();
        pic.setConfig(cityConfig);

        pic.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //省份
                if (province != null) {

                }

                //城市
                if (city != null) {

                }

                //地区
                if (district != null) {
                    PCDBean pcdBean=new PCDBean();
                    String p=province.toString();
                    String c=city.toString();
                    String d=district.toString();
                    pcdBean.setCity(c); pcdBean.setProvince(p); pcdBean.setDistrict(d);
                    if (type==1){
                        materialCreateIDC(pcdBean,context,pic);
                    }
                    else if (type==2){
                        materialCreateIDCCus(pcdBean,context,pic);
                    }



                }

            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(context, "已取消");
            }
        });

        //显示
        pic.showCityPicker( );
    }

    public void helputil(String input,Activity context) {
        if (step == 0) {
            step++;
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("au", "idc_query");
                jsonObject.put("user_id", input.toString());
                jsonObject.put("ide",HandlerFinal.ide);
                HandlerFinal.novIden=input.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //HandlerUtil



            ThreadUtil.sat(jsonObject);
            Toast.makeText(context, "正在查询机房稍等...", Toast.LENGTH_SHORT).show();
            // actionActivity(MenuActivity.this,AsertFormActivity.class,null);
            ThreadUtil.execute(new ThreadUtil.CallBack() {
                @Override
                public void exec() {

                }

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    step = 0;
                }
            });
        }

    }
}
