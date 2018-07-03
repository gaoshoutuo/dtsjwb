package com.zjdt.dtsjwb.Activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.r0adkll.slidr.Slidr;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.App.SignView;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.io.File;

public class SignActivity extends BaseActivity implements View.OnClickListener{
    private SignView signView;
    private Button signviewSave,signviewClear,signviewSet;
    private String valueName;
    private boolean isUp=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
        valueName=getIntent().getStringExtra("str");
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

    @Override   //可以有继承listener 的方案 把所有listener 聚集在一起
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signview_clear:
                signView.clearPath();
                break;
            case R.id.signview_save://安全意识  没办法  加密下午看一下  争取不明文  密码学
               final String filepath=AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+valueName;
                signView.saveImageToFile(filepath);
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
