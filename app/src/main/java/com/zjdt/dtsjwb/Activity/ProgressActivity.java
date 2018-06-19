package com.zjdt.dtsjwb.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.zjdt.dtsjwb.R;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        initview();
    }
    private void initview(){
        ProgressWheel pw=new ProgressWheel(this);
        pw.setBarColor(Color.BLUE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 007:
                if (resultCode==RESULT_OK)

                break;

                default:break;
        }
    }
}
