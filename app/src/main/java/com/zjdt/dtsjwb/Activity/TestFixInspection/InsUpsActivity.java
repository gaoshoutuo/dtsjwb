package com.zjdt.dtsjwb.Activity.TestFixInspection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.IdcBean;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;

import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.AirAssit;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;
import com.zjdt.dtsjwb.fragment.UpsInsFragment;
import com.zjdt.dtsjwb.fragment.UpsTestFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InsUpsActivity extends BaseActivity implements View.OnClickListener{

    /**
     * 写了=蠢代码 前后一致性不够  目前代码行数应该超过10w行了吧
     * 1 view frame button initview
     * 2 head body foot setid
     * 3 click
     * 4 fragmentTransaction
     * 5 修改 json static 及获取方式
     */

    private FragmentManager fm = getSupportFragmentManager();
    private UpsInsFragment upsInsHead, upsInsBody, upsInsFoot;
    private Button headButton, bodyButton, footButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_ups);

        initView();
        upsInsHead=new UpsInsFragment();
        upsInsHead.setLayoutId(R.layout.ups_inspection_report_head);
        addFragment(R.id.ups_ins_frame,upsInsHead,new AirAssit());
      /*  try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

    private void initView() {
        headButton=findViewById(R.id.ups_ins_button1);
        bodyButton=findViewById(R.id.ups_ins_button2);
       // footButton=findViewById(R.id.ups_fix_button3);
        headButton.setOnClickListener(this);
        bodyButton.setOnClickListener(this);
       // footButton.setOnClickListener(this);
    }

    private void addFragment(int frameId, Fragment addFragment, Fragment removeFragment){//static 是类变量 所以我要注意一下用法  虽然我不知道replace 是否会把类变量也去除 写程序 尤其要做实验
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(frameId, addFragment).hide(removeFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_ins_button1://处理head
                upsInsHead.makeInsHeadJson();

                headButton.setVisibility(View.GONE);
                bodyButton.setVisibility(View.VISIBLE);
                upsInsBody=new UpsInsFragment();
                upsInsBody.setLayoutId(R.layout.ups_inspection_report_body);
                addFragment(R.id.ups_ins_frame,upsInsBody,upsInsHead);

                break;

            case R.id.ups_ins_button2://处理body

                try {

                    if ( SignActivity.isUp==true){
                        SignActivity.isUp=false;
                        upsInsBody.makeInsBodyJson();
                        HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
                            UpsInsFragment.getJson().put("other_location",map.get("h_location"));
                            UpsInsFragment.getJson().put("h_custom_id",map.get("h_custom_id"));
                            JSONObject ano=new JSONObject();
                            ano.put("cus_id",map.get("h_custom_id"));
                            ano.put("timestamp",System.currentTimeMillis()+"");
                            ano.put("reason",map.get("h_reason"));
                            ano.put("business", HandlerFinal.BUSINESS_STR[2]);
                            ano.put("eng_id",HandlerFinal.userId);
                            ano.put("eng_name",HandlerFinal.userName);
                            ano.put("step",1);
                            //four_idc
                            IdcBean ib=(IdcBean)map.get("four_idc");
                            ano.put("idc_id",ib.getIdcId());
                            ano.put("idc_location",ib.getSimpleLocation());
                            ano.put("idc_type",ib.getIdcType());
                            ano.put("idc_name",ib.getIdcName());


                            UpsInsFragment.getJson().put("another",ano);

                        final String json= upsInsBody.getJsonStr();
                        ThreadUtil.execute(new ThreadUtil.CallBack() {
                            @Override
                            public void exec() {

                            }

                            @Override
                            public void run() {
                                //SocketUtil.sendMessageAdd("218.108.146.98",88,json);
                                SocketUtil.sendMessageAdd("218.108.146.98",3333,json);
                            }
                        });
                        Log.e("kk",json);
                        Toast.makeText(InsUpsActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                        InsUpsActivity.this.finish();


                    }else{
                        Toast.makeText(InsUpsActivity.this,"请先签名再上传",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            default:break;
        }
    }
}
