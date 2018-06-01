package com.zjdt.dtsjwb.Activity.TestFixInspection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.fragment.SiteFrag;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;

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
            addFragment(R.id.site_frame,installSite,null);
            installButton.setVisibility(View.VISIBLE);
            serviceButton.setVisibility(View.GONE);
        }else if(map.get("site")!=null&&map.get("site").equals("service")){
            serviceSite=new SiteFrag();
            serviceSite.setLayoutId(R.layout.site_device_service);
            addFragment(R.id.site_frame,serviceSite,null);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.site_button1://处理head
                String jsonInstall= installSite.getJsonStr();
                break;

            case R.id.site_button2://处理head
                String jsonService= serviceSite.getJsonStr();
            break;
            default:break;
        }
    }
}