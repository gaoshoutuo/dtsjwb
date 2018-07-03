package com.zjdt.dtsjwb.Activity.TestFixInspection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;

import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.AirAssit;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;
import com.zjdt.dtsjwb.fragment.UpsInsFragment;

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
                upsInsBody.makeInsBodyJson();
                HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");

                try {
                    UpsInsFragment.getJson().put("other_location",map.get("h_location"));
                    UpsInsFragment.getJson().put("h_custom_id",map.get("h_custom_id"));
                    JSONObject ano=new JSONObject();
                    ano.put("cus_id",map.get("h_custom_id"));
                    ano.put("timestamp",System.currentTimeMillis()+"");
                    ano.put("reason",map.get("h_reason"));
                    ano.put("business", HandlerFinal.BUSINESS_STR[2]);
                    ano.put("eng_id",HandlerFinal.userId);
                    ano.put("eng_name",HandlerFinal.userName);
                    UpsInsFragment.getJson().put("another",ano);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
                break;

            default:break;
        }
    }
}
