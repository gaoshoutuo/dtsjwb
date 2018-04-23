package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjdt.dtsjwb.R;

public class WebviewActivity extends AppCompatActivity {
    /**
     * webview  react js 载入网页用的  也算的util
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }
}
