package com.zjdt.dtsjwb.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.zjdt.dtsjwb.R;

public class CCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccall);
        ProgressWheel progressWheel=new ProgressWheel(CCallActivity.this);
        progressWheel.setBarColor(Color.YELLOW);
    }
}
