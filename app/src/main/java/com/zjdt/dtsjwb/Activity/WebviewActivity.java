package com.zjdt.dtsjwb.Activity;


import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;


import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.VideoActivity;
import com.tencent.smtt.sdk.WebView;
import com.zjdt.dtsjwb.R;

import java.util.HashMap;

public class WebviewActivity extends BaseActivity {
    //private android.webkit.WebView webView;  系统webview
    /**
     * webview  react js 载入网页用的  也算的util
     * @param savedInstanceState
     */

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initview();
    }
@SuppressLint("SetJavaScriptEnabled")
    private void initview(){
        webView=f(R.id.webview_context);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //TbsVideo.openVideo(this,"http://176.122.185.2/picture/yq.mp4");
       HashMap map= (HashMap) getIntent().getExtras().get("key");
     String url= (String) map.get("url");
       // webView.loadUrl("http://www.taobao.com");
    webView.loadUrl(url);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
