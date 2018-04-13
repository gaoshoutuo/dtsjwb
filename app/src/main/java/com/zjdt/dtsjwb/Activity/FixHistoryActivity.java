package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjdt.dtsjwb.R;

public class FixHistoryActivity extends AppCompatActivity {
    /**
     * 也是recyclerview
     * 谁什么时候帮助哪里的客户修理了什么故障，客户态度。
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_history);
    }
}
