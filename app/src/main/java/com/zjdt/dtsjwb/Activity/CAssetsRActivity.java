package com.zjdt.dtsjwb.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.fragment.EsAssit;

public class CAssetsRActivity extends BaseActivity implements View.OnClickListener{
    //资产登记  通过碎片 登记机房的多个系统部件
    private Button button;
    private String[]buttonName={"保存电力子系统数据","保存空调子系统数据","保存新风排风子系统数据","保存监控软件数据","保存监控软件接口数据","保存监控硬件数据",
            "保存监控门禁数据","保存监控视频数据","保存机房机柜数据"};
    private int []buttonRid={R.id.ele_assit,R.id.air_assit,R.id.emi_assit,R.id.mon_soft_assit,R.id.mon_soft_interface,R.id.mon_soft_hardware,R.id.mon_soft_ac,
    R.id.mon_video,R.id.mon_cabient};
    private int []layoutT={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassets_r);
        CAssetsRActivity.this.initButton(0);
    }
    private void initButton(int i){
        button=f(buttonRid[i]);
        button.setOnClickListener(this);
        button.setText(buttonName[i]);
    }
    private void buttonGone(){
        button.setVisibility(View.GONE);
    }
    private void buttonVisibilty(){
        button.setVisibility(View.VISIBLE);
    }

    /**
     * 显示的步骤
     * @param fragment
     */
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.assit_framelayout,fragment);
        transaction.commit();
    }
    private void setFragment(int layoutId){//可以选择layout【i】来的 但是不像改了
        EsAssit esAssit= new EsAssit();
        esAssit.setRid(layoutId);
        replaceFragment(esAssit);
    }

    private void setButtonNine(int i){
        buttonGone();
        CAssetsRActivity.this.initButton(i);
        buttonVisibilty();
    }
    private void specialButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 整理好的数据上传
                // 每个碎片放入返回栈
                // 每个碎片对于一种数据结构并发送给activity
                // 结束之后 把碎片删除
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ele_assit:
           /*    EsAssit esAssit= new EsAssit();
               esAssit.setRid(R.layout.assit_es);
                replaceFragment(esAssit);
                buttonGone();
                CAssetsRActivity.this.initButton(i);
                buttonVisibilty();*/
                setFragment(R.layout.assit_es);
                setButtonNine(1);
                break;
            case R.id.air_assit:
                setFragment(R.layout.assit_air);
                setButtonNine(2);
                break;
            case R.id.emi_assit:
                setFragment(R.layout.assit_emi);
                setButtonNine(3);
                break;
            case R.id.mon_soft_assit:
                setFragment(R.layout.assit_mon_soft);
                setButtonNine(4);
                break;
            case R.id.mon_soft_interface:
                setFragment(R.layout.assit_mon_interface);
                setButtonNine(5);
                break;
            case R.id.mon_soft_hardware:
                setFragment(R.layout.assit_mon_hard);
                setButtonNine(6);
                break;
            case R.id.mon_soft_ac://我确实在用面向对象的特性吗
                setFragment(R.layout.assit_mon_ac);
                setButtonNine(7);
                break;
            case R.id.mon_video:
                setFragment(R.layout.assit_mon_video);
                setButtonNine(8);
                break;
            case R.id.mon_cabient:
                setFragment(R.layout.assit_cabient);
                //setButtonNine(8);
                specialButton();
                break;


                default:break;
        }
    }
}
