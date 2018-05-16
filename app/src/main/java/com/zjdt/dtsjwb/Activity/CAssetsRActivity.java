package com.zjdt.dtsjwb.Activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.EsAssit;

public class CAssetsRActivity extends BaseActivity implements View.OnClickListener{
    //资产登记  通过碎片 登记机房的多个系统部件
    private  FragmentManager fragmentManager=getSupportFragmentManager();
    private Button button;
    private boolean isopne=false;
    FragmentTransaction transaction;
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
        setFragment(R.layout.assit_es);
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


   /* public FragmentTransaction opentransation(){
        if(transaction==null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            transaction=fragmentManager.beginTransaction();
         }
        return transaction;
    }*/

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.assit_framelayout,fragment);
      //  transaction.addToBackStack(null);
        transaction.commit();

    }

    private void addHideFragment(Fragment from,Fragment to,int position){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(!to.isAdded()){
            transaction.hide(from).add(R.id.assit_framelayout,to,buttonName[position]).commit();
        }else{
            transaction.hide(from).show(to).commit();
        }
        transaction.addToBackStack(null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setFragment(int layoutId) {//可以选择layout【i】来的 但是不像改了
        EsAssit esAssit = new EsAssit();
        esAssit.setRid(layoutId);
        replaceFragment(esAssit);//似乎确实是替换
        //addHideFragment(Fragment from,Fragment to,int position);
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
    //获取 碎片 应用


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
                // setFragment(R.layout.assit_es);
               /* ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {
                        setFragment(R.layout.assit_air);
                    }

                    @Override
                    public void run() {

                    }
                });*/
                setFragment(R.layout.assit_air);

                setButtonNine(1);
                break;
            case R.id.air_assit:
                setFragment(R.layout.assit_emi);
                setButtonNine(2);
                break;
            case R.id.emi_assit:
                setFragment(R.layout.assit_mon_soft);
                setButtonNine(3);
                break;
            case R.id.mon_soft_assit:
                setFragment(R.layout.assit_mon_interface);
                setButtonNine(4);
                break;
            case R.id.mon_soft_interface:
                setFragment(R.layout.assit_mon_hard);
                setButtonNine(5);
                break;
            case R.id.mon_soft_hardware:
                setFragment(R.layout.assit_mon_ac);
                setButtonNine(6);
                break;
            case R.id.mon_soft_ac://我确实在用面向对象的特性吗
                setFragment(R.layout.assit_mon_video);
                setButtonNine(7);
                break;
            case R.id.mon_video:
                setFragment(R.layout.assit_cabient);
                setButtonNine(8);
                break;
            case R.id.mon_cabient:
                //setButtonNine(8); 上传
                specialButton();
                break;


                default:break;
        }
    }
    //handler 还是本地方便一点
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 301:
                    break;
                case 302:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(CAssetsRActivity.this);
        alertDialogUtil.setAlertDialog("确定","关闭","警告","机房资产仍未录入完毕是否退出编辑",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case -2:
                                finish();
                                break;
                            case -1:
                                break;
                        }

                    }
                }
        );
        super.onBackPressed();
    }
}
