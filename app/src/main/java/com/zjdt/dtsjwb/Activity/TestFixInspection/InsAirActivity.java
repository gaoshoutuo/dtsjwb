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
import com.zjdt.dtsjwb.fragment.AirInsFragment;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;

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
        airInsHead.setLayoutId(R.layout.ups_test_report_body);
        addFragment(R.id.air_ins_frame,airInsHead,null);
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
            case R.id.ups_fix_button1://处理head
                headButton.setVisibility(View.GONE);
                bodyButton.setVisibility(View.VISIBLE);
                airInsBody=new AirInsFragment();
                airInsBody.setLayoutId(R.layout.ups_test_report_body);
                addFragment(R.id.ups_test_frame,airInsBody,airInsHead);
                break;

            case R.id.ups_fix_button2://处理body
                bodyButton.setVisibility(View.GONE);
                footButton.setVisibility(View.VISIBLE);
                airInsFoot=new AirInsFragment();
                airInsFoot.setLayoutId(R.layout.ups_test_report_foot);
                addFragment(R.id.ups_test_frame,airInsFoot,airInsBody);
                break;

            case R.id.ups_fix_button3://处理foot  上传
                String json= airInsFoot.getJsonStr();
                break;
            default:break;
        }
    }
}
