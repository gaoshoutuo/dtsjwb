package com.zjdt.dtsjwb.Activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsAirActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.App.SignView;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.AirInsFragment;
import com.zjdt.dtsjwb.fragment.FixAirFragment;
import com.zjdt.dtsjwb.fragment.InspectionAirFragment;
import com.zjdt.dtsjwb.fragment.InstallAirFragment;
import com.zjdt.dtsjwb.fragment.SiteFrag;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;
import com.zjdt.dtsjwb.fragment.UpsInsFragment;
import com.zjdt.dtsjwb.fragment.UpsTestFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SignActivity extends BaseActivity implements View.OnClickListener{
    private SignView signView;
    private Button signviewSave,signviewClear,signviewSet;
    private String valueName,businessType,json;
    public static boolean isUp=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();

        valueName=getIntent().getStringExtra("str");
        businessType=getIntent().getStringExtra("bussiness_type");
        //json=getIntent().getStringExtra("json");

       sif.lock();
    }

    private void initView(){
        signView=f(R.id.signview);
        signviewClear=f(R.id.signview_clear);
        signviewSave=f(R.id.signview_save);
        signviewSet=f(R.id.signview_set);
        signviewSet.setOnClickListener(this);
        signviewSave.setOnClickListener(this);
        signviewClear.setOnClickListener(this);
    }

    private JSONObject switchBusiness(String type,String value){
        JSONObject changeObj=null;
        try {//blank1 工程师签名  blank2  客户签名  blank3   filepath

        switch (type){

            case "air_ins":
                if (HandlerFinal.indentity.equals("企业客户")){
                    //AirInsFragment.getJson().put("blank2",value);
                  /*  String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);//拿出obj 修改再放回去 不是这样的
                    //ThreadUtil.sat(jsonobj);
                    changeObj=jsonobj;*/

                    String json=getIntent().getStringExtra("json");
                    String blank2=getIntent().getStringExtra("str2");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("step",2);
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("au","air_ins");

                    //果然是这个地方出问题了

                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);


                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    AirInsFragment.getJson().put("blank1",value);
                }

                break;

            case "install":
                if (HandlerFinal.indentity.equals("企业客户")){
                    //SiteFrag.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/

                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","install");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);

                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    SiteFrag.getJson().put("blank1",value);
                }
                break;

            case "service":
                if (HandlerFinal.indentity.equals("企业客户")){
                    //SiteFrag.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/

                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","service");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);


                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    SiteFrag.getJson().put("blank1",value);
                }
                break;

            case "ups_fix":

                if (HandlerFinal.indentity.equals("企业客户")){
                    //UpsFixFragment.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/

                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","ups_fix");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);

                    Log.e("bussiness_type","3");

                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    UpsFixFragment.getJson().put("blank1",value);
                }
                break;

            case "ups_ins":
                Log.e("ups_ins",HandlerFinal.indentity);
                if (HandlerFinal.indentity.equals("企业客户")){
                    //UpsInsFragment.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/
                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);
                    ;
                    jsonobj.put("au","ups_ins");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);


                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    UpsInsFragment.getJson().put("blank1",value);
                }
                break;

            case "ups_test":
                if (HandlerFinal.indentity.equals("企业客户")){
                    //.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/
                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","ups_test");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);


                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    UpsTestFragment.getJson().put("blank1",value);
                }
                break;

                case "air_inspection"://air_inspection 新空调巡检    空调安装 air_install  空调新巡检air_inspection  air_fix

                    if (HandlerFinal.indentity.equals("企业客户")){
                        //.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/
                        String json=getIntent().getStringExtra("json");
                        JSONObject jsonobj=new JSONObject(json);
                        String blank2=getIntent().getStringExtra("str2");
                        jsonobj.put("blank2",value);

                        jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                        jsonobj.put("step",2);

                        jsonobj.put("au","air_inspection");
                        JSONObject jsonobj2=new JSONObject();
                        jsonobj2.put("step",-1);
                        jsonobj.put("another",jsonobj2);

                        changeObj=jsonobj;

                    }else if (HandlerFinal.indentity.equals("维保人员")){
                        InspectionAirFragment.getJson().put("blank1",value);
                    }
                    break;

            case "air_fix"://fix_air 空调维修

                if (HandlerFinal.indentity.equals("企业客户")){
                    //.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/
                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","air_fix");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);


                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    FixAirFragment.getJson().put("blank1",value);
                }

                break;

            case "air_install"://air_install 空调安装

                if (HandlerFinal.indentity.equals("企业客户")){
                    //.getJson().put("blank2",value);
                   /* String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    jsonobj.put("blank2",value);
                    JSONObject ano=jsonobj.getJSONObject("another");
                    ano.put("step",2);
                    jsonobj.put("another",ano);
                    changeObj=jsonobj;*/
                    String json=getIntent().getStringExtra("json");
                    JSONObject jsonobj=new JSONObject(json);
                    String blank2=getIntent().getStringExtra("str2");
                    jsonobj.put("blank2",value);

                    jsonobj.put("blank1",jsonobj.getString("timestamp")+".png");
                    jsonobj.put("step",2);

                    jsonobj.put("au","air_install");
                    JSONObject jsonobj2=new JSONObject();
                    jsonobj2.put("step",-1);
                    jsonobj.put("another",jsonobj2);

                    changeObj=jsonobj;

                }else if (HandlerFinal.indentity.equals("维保人员")){
                    InstallAirFragment.getJson().put("blank1",value);
                }

                break;

                default:break;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return changeObj;
    }

    @Override   //可以有继承listener 的方案 把所有listener 聚集在一起
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signview_clear:
                signView.clearPath();
                break;
            case R.id.signview_save://安全意识  没办法  加密下午看一下  争取不明文  密码学
               final String filepath=AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+valueName;
                Log.e("bussiness_type",businessType+"---"+valueName);
              JSONObject obj=switchBusiness(businessType,valueName);

                signView.saveImageToFile(filepath);
                Toast.makeText( SignActivity.this,"...正在保存签名...",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {

                    }

                    @Override
                    public void run() {
                        isUp=FtpUtil.uploadFile(filepath,valueName);
                    }
                });
                while (!isUp){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
             /*   if (getIntent()!=null&& getIntent().getStringExtra("identity").equals("custom")){
                    //  下载并且打开  但是耦合太严重了
                    String filename=getIntent().getStringExtra("filename");
                }else{
                    SignActivity.this.onBackPressed();
                }*/


             if (obj!=null){
                 Log.e("obj",obj.toString());
                 //obj.put("png","");
                 ThreadUtil.sat(obj);//客户的第二次上传
                 Toast.makeText( SignActivity.this,"服务流程已走完，请到维修历史中查看相应记录以及完整回执文件",Toast.LENGTH_LONG).show();

             }else{
                 Toast.makeText( SignActivity.this,"签名上传成功",Toast.LENGTH_SHORT).show();
             }

                SignActivity.this.onBackPressed();

                //Log.e("pathss", AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());

                break;
            case R.id.signview_set:

                break;
                default:break;
        }
    }


   /* @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("");
        setResult(111, intent);
        finish();
    }*/
}
