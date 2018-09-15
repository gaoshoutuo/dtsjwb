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
import com.zjdt.dtsjwb.fragment.FixAirFragment;
import com.zjdt.dtsjwb.fragment.InspectionAirFragment;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FixAirActivity extends BaseActivity implements View.OnClickListener{
    // 标记
        //这个之前就有了
    private Button sendButton;
    private FragmentManager fm = getSupportFragmentManager();
    private FixAirFragment fixAirFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_air);//air_fix_frame
        initView();
        fixAirFragment=new FixAirFragment();
        fixAirFragment.setLayoutId(R.layout.air_fix_head);
        addFragment(R.id.air_fix_frame,fixAirFragment,new AirAssit());
    }

    /**
     * 流程  initview addFragment sign click sendData  pdf
     */

    private void initView(){
        sendButton=f(R.id.air_fix_button1);
        sendButton.setOnClickListener(this);
    }


    private void addFragment(int frameId, Fragment addFragment, Fragment removeFragment){//static 是类变量 所以我要注意一下用法  虽然我不知道replace 是否会把类变量也去除 写程序 尤其要做实验
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(frameId, addFragment).hide(removeFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.air_fix_button1:
                try {
                    if (SignActivity.isUp==true){
                        SignActivity.isUp=false;
                        fixAirFragment.initAirFixJson();
                        HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
                        //timestamp json不知道如何传输
/*
                    UpsFixFragment.getJson().put("other_location",map.get("h_location"));
                    UpsFixFragment.getJson().put("other_reason",map.get("h_reason"));*/
                        fixAirFragment.getJson().put("other_location",map.get("h_location"));
                        fixAirFragment.getJson().put("h_custom_id",map.get("h_custom_id"));


                        JSONObject ano=new JSONObject();
                        ano.put("cus_id",map.get("h_custom_id"));
                        ano.put("timestamp",System.currentTimeMillis()+"");
                        //ano.put("reason",UpsFixFragment.reasonStr); 这里很不方面  在这之前集成吧
                        ano.put("reason",map.get("h_reason"));  //那么我这里要地点有啥用呀 keke
                        ano.put("business", HandlerFinal.BUSINESS_STR[3]);
                        ano.put("eng_id",HandlerFinal.userId);
                        ano.put("eng_name",HandlerFinal.userName);
                        ano.put("step",1);

                        //four_idc
                        IdcBean ib=(IdcBean)map.get("four_idc");
                        ano.put("idc_id",ib.getIdcId());
                        ano.put("idc_location",ib.getSimpleLocation());
                        ano.put("idc_type",ib.getIdcType());
                        ano.put("idc_name",ib.getIdcName());


                        fixAirFragment.getJson().put("another",ano);

                        // UpsFixFragment
                        final String json= fixAirFragment.getJsonStr();
                        Log.e("upsfix",json);
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
                        Toast.makeText( FixAirActivity.this,"...上传成功,请提醒客户确认,之后到维保历史里面查看完整回执文件...",Toast.LENGTH_SHORT).show();
                        FixAirActivity.this.finish();
                    }else {
                        Toast.makeText( FixAirActivity.this,"请先签名再上传",Toast.LENGTH_SHORT).show();
                    } } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:break;
        }
    }
}
