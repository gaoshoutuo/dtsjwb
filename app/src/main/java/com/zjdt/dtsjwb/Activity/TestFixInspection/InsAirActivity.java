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
import com.zjdt.dtsjwb.fragment.AirInsFragment;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InsAirActivity extends BaseActivity implements View.OnClickListener{
    /**
     * 写了=蠢代码 前后一致性不够  目前代码行数应该超过10w行了吧
     * 1 view frame button initview
     * 2 head body foot setid
     * 3 click
     * 4 fragmentTransaction
     * 5 修改 json static 及获取方式
     */



    private FragmentManager fm = getSupportFragmentManager();
    private AirInsFragment airInsHead, airInsBody, airInsFoot;
    private Button headButton, bodyButton, footButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_air);
        initView();
        airInsHead=new AirInsFragment();
        airInsHead.setLayoutId(R.layout.air_inspection_head);//
        addFragment(R.id.air_ins_frame,airInsHead,new AirAssit());
    }

    private void initView() {
        headButton=findViewById(R.id.air_ins_button1);
        bodyButton=findViewById(R.id.air_ins_button2);
        footButton=findViewById(R.id.air_ins_button3);
        headButton.setOnClickListener(this);
        bodyButton.setOnClickListener(this);
        footButton.setOnClickListener(this);
    }

    private void addFragment(int frameId, Fragment addFragment, Fragment removeFragment){//static 是类变量 所以我要注意一下用法  虽然我不知道replace 是否会把类变量也去除 写程序 尤其要做实验
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(frameId, addFragment).hide(removeFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.air_ins_button1://处理head
                airInsHead.makeAirInsHeadJson();
                headButton.setVisibility(View.GONE);
                bodyButton.setVisibility(View.VISIBLE);
                airInsBody=new AirInsFragment();
                airInsBody.setLayoutId(R.layout.air_inspection_body);
                addFragment(R.id.air_ins_frame,airInsBody,airInsHead);
                break;

            case R.id.air_ins_button2://处理body
                airInsBody.makeAirInsBodyJson();
                bodyButton.setVisibility(View.GONE);
                footButton.setVisibility(View.VISIBLE);
                airInsFoot=new AirInsFragment();
                airInsFoot.setLayoutId(R.layout.air_inspection_foot);
                addFragment(R.id.air_ins_frame,airInsFoot,airInsBody);
                break;

            case R.id.air_ins_button3://处理foot  上传
                try {
                if ( SignActivity.isUp==true){
                    SignActivity.isUp=false;
                    airInsFoot.makeAirInsFootJson();
                    HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");

                    AirInsFragment.getJson().put("other_location",map.get("h_location"));
                    AirInsFragment.getJson().put("h_custom_id",map.get("h_custom_id"));
                    JSONObject ano=new JSONObject();
                    ano.put("cus_id",map.get("h_custom_id"));
                    ano.put("timestamp",System.currentTimeMillis()+"");
                    ano.put("reason",map.get("h_reason"));
                    ano.put("business", HandlerFinal.BUSINESS_STR[5]);
                    ano.put("eng_id",HandlerFinal.userId);
                    ano.put("eng_name",HandlerFinal.userName);
                    ano.put("step",1);
                    //four_idc
                    IdcBean ib=(IdcBean)map.get("four_idc");
                    ano.put("idc_id",ib.getIdcId());
                    ano.put("idc_location",ib.getSimpleLocation());
                    ano.put("idc_type",ib.getIdcType());
                    ano.put("idc_name",ib.getIdcName());

                    AirInsFragment.getJson().put("another",ano);


                    final String json= airInsFoot.getJsonStr();


                    Log.e("airins",json);
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
                }else{
                    Toast.makeText( InsAirActivity.this,"请先签名再上传",Toast.LENGTH_SHORT).show();
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:break;
        }
    }
}
