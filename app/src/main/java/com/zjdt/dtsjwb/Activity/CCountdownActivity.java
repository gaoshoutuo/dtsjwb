package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjdt.dtsjwb.R;

public class CCountdownActivity extends BaseActivity {
    //显示倒数30天的  现在改到 offline 去好了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccountdown);
    }
}
