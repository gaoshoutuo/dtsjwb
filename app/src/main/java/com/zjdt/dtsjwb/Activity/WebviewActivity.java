package com.zjdt.dtsjwb.Activity;


import android.graphics.PixelFormat;
import android.os.Bundle;


import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.VideoActivity;
import com.tencent.smtt.sdk.WebView;
import com.zjdt.dtsjwb.R;

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

    private void initview(){
        webView=f(R.id.webview_context);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //TbsVideo.openVideo(this,"http://176.122.185.2/picture/yq.mp4");
        webView.loadUrl("http://www.taobao.com");
    }


}
