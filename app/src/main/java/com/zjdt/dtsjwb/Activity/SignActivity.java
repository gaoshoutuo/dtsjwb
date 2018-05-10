package com.zjdt.dtsjwb.Activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.App.SignView;
import com.zjdt.dtsjwb.R;

import java.io.File;

public class SignActivity extends BaseActivity implements View.OnClickListener{
    private SignView signView;
    private Button signviewSave,signviewClear,signviewSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
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
            case R.id.signview_save:
                Log.e("pathss", AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());

                break;
            case R.id.signview_set:
                break;
                default:break;
        }
    }
}
