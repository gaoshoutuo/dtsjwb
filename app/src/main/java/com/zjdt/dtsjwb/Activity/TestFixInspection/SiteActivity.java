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
import com.zjdt.dtsjwb.fragment.SiteFrag;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SiteActivity extends BaseActivity implements View.OnClickListener{

    private FragmentManager fm = getSupportFragmentManager();
    private SiteFrag installSite,serviceSite;
    private Button installButton, serviceButton, footButton;

    /**
     * 写了=蠢代码 前后一致性不够  目前代码行数应该超过10w行了吧
     * 1 view frame button initview
     * 2 head body foot setid
     * 3 click
     * 4 fragmentTransaction
     * 5 修改 json static 及获取方式
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        initView();
        HashMap map=(HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
        if(map.get("site")!=null&&map.get("site").equals("install")){
            installSite=new SiteFrag();
            installSite.setLayoutId(R.layout.site_device_install);
            addFragment(R.id.site_frame,installSite,new AirAssit());
            installButton.setVisibility(View.VISIBLE);
            serviceButton.setVisibility(View.GONE);
        }else if(map.get("site")!=null&&map.get("site").equals("service")){
            serviceSite=new SiteFrag();
            serviceSite.setLayoutId(R.layout.site_device_service);
            addFragment(R.id.site_frame,serviceSite,new AirAssit());
            serviceButton.setVisibility(View.VISIBLE);
            installButton.setVisibility(View.GONE);
        }

    }

    private void initView() {
        installButton=findViewById(R.id.site_button1);
        serviceButton=findViewById(R.id.site_button2);
        //footButton=findViewById(R.id.ups_fix_button3);
        installButton.setOnClickListener(this);
        serviceButton.setOnClickListener(this);
        //footButton.setOnClickListener(this);
    }

    private void addFragment(int frameId, Fragment addFragment, Fragment removeFragment){//static 是类变量 所以我要注意一下用法  虽然我不知道replace 是否会把类变量也去除 写程序 尤其要做实验
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(frameId, addFragment).hide(removeFragment).commit();
    }

    /**
     * {"cus_data":{"custom_name":"","custom_location":"","custom_contacts":"","phone_num":""},
     * "product_info":{"para":"","brand":"","type":"","power":""},
     * "info":{"bar_code":"","material":"","install_process":"","install_result":""},
     * "cost":{"Maintenance":"","warr_inner":"","warr_out":"","labor":"","materal":"","travel":"","transport":"","sum_cost":""},"sum":""}


     {"cus_data":{"custom_name":"","custom_location":"","custom_contacts":"","phone_num":""},
     "product_info":{"para":"","brand":"","type":"","power":""},
     "info":{"bar_code":"","material":"","install_process":"","install_result":""},
     "cost":{"Maintenance":"","warr_inner":"","warr_out":"","labor":"","materal":"","travel":"","transport":"","sum_cost":""},"sum":""}

     * @param v
     */


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.site_button1://处理head
                try {
                if ( SignActivity.isUp==true){
                    SignActivity.isUp=false;
                    installSite.initManyJson();
                    HashMap mapinstall = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");

                    SiteFrag.getJson().put("other_location",mapinstall.get("h_location"));
                    SiteFrag.getJson().put("h_custom_id",mapinstall.get("h_custom_id"));
                    JSONObject ano=new JSONObject();
                    ano.put("cus_id",mapinstall.get("h_custom_id"));
                    ano.put("timestamp",System.currentTimeMillis()+"");
                    ano.put("reason",mapinstall.get("h_reason"));
                    ano.put("business", HandlerFinal.BUSINESS_STR[6]);
                    ano.put("eng_id",HandlerFinal.userId);
                    ano.put("eng_name",HandlerFinal.userName);
                    ano.put("step",1);

                    //four_idc
                    IdcBean ib=(IdcBean)mapinstall.get("four_idc");
                    ano.put("idc_id",ib.getIdcId());
                    ano.put("idc_location",ib.getSimpleLocation());
                    ano.put("idc_type",ib.getIdcType());
                    ano.put("idc_name",ib.getIdcName());

                    SiteFrag.getJson().put("another",ano);

                    final String jsonInstall= installSite.getJsonStr();
                    Log.e("install",jsonInstall);

                    ThreadUtil.execute(new ThreadUtil.CallBack() {
                        @Override
                        public void exec() {

                        }

                        @Override
                        public void run() {
                            //SocketUtil.sendMessageAdd("218.108.146.98",88,json);
                            SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonInstall);
                        }
                    });
                }else{
                    Toast.makeText( SiteActivity.this,"请先签名再上传",Toast.LENGTH_SHORT).show();
                }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.site_button2://处理head
                try {
                if ( SignActivity.isUp=true){
                    SignActivity.isUp=false;
                    installButton.setVisibility(View.GONE);
                    serviceButton.setVisibility(View.VISIBLE);
                    serviceSite.initManyJson();

                    HashMap mapService = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");

                    SiteFrag.getJson().put("other_location",mapService.get("h_location"));
                    SiteFrag.getJson().put("h_custom_id",mapService.get("h_custom_id"));
                    JSONObject ano=new JSONObject();
                    ano.put("cus_id",mapService.get("h_custom_id"));
                    ano.put("timestamp",System.currentTimeMillis()+"");
                    ano.put("reason",mapService.get("h_reason"));
                    ano.put("business", HandlerFinal.BUSINESS_STR[7]);
                    ano.put("eng_id",HandlerFinal.userId);
                    ano.put("eng_name",HandlerFinal.userName);
                    ano.put("step",1);

                    //four_idc
                    IdcBean ib=(IdcBean)mapService.get("four_idc");
                    ano.put("idc_id",ib.getIdcId());
                    ano.put("idc_location",ib.getSimpleLocation());
                    ano.put("idc_type",ib.getIdcType());
                    ano.put("idc_name",ib.getIdcName());

                    SiteFrag.getJson().put("another",ano);

                    final String jsonService= serviceSite.getJsonStr();
                    Log.e("service",jsonService);

                    ThreadUtil.execute(new ThreadUtil.CallBack() {
                        @Override
                        public void exec() {

                        }

                        @Override
                        public void run() {
                            //SocketUtil.sendMessageAdd("218.108.146.98",88,json);
                            SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonService);
                        }
                    });
                }else{
                    Toast.makeText( SiteActivity.this,"请先签名再上传",Toast.LENGTH_SHORT).show();
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            break;
            default:break;
        }
    }
}
