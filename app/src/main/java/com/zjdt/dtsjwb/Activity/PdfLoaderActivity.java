package com.zjdt.dtsjwb.Activity;

import android.os.Environment;

import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.io.File;
import java.util.HashMap;

public class PdfLoaderActivity extends BaseActivity {
    PDFView pdfView;
    boolean isDOWN=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_loader);

        //HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
        if (getIntent().getStringExtra("eng")!=null){
            String filename= getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+getIntent().getStringExtra("eng");

            //initView(filename);

        }else if (getIntent().getStringExtra("cus")!=null){
            String filename= getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+getIntent().getStringExtra("cus");

            //initView(filename);
        }
        String externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
       // String test001=getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+"/test001.pdf";
       // String name=getIntent().getStringExtra("filename");//搞不懂为什么这个一直会这样 是android 的bug吗  不对可能是我在那里面的 app.getApp 的那种影响到了  没办法 智能 final
        String name= HandlerFinal.pdfFIle;

        String content=getIntent().getStringExtra("content");
        String title=getIntent().getStringExtra("title");
        Log.e("filename",name+content+title);
        String []filename=name.split("/");

        final String realname=filename[filename.length-1];
        final String localName=externalFilesDir+"/"+realname;
        Log.e("filename",localName);
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
               isDOWN= FtpUtil.downloadFile(realname,localName);
            }
        });
        while (!isDOWN){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Log.e("pdf",test001);
        initView(localName);
    }
    private void initView(String filename){
        pdfView=f(R.id.pdfview);
        pdfView.recycle();
        pdfView.fromFile(new File(filename))
//                .pages(0, 2, 3, 4, 5); // 把0 , 2 , 3 , 4 , 5 过滤掉
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(true)
                //
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(1)
                //允许在当前页面上绘制一些内容，通常在屏幕中间可见。
//                .onDraw(onDrawListener)
//                // 允许在每一页上单独绘制一个页面。只调用可见页面
//                .onDrawAll(onDrawListener)
                //设置加载监听
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                })
                //设置翻页监听
                .onPageChange(new OnPageChangeListener() {

                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                //设置页面滑动监听
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
                // 首次提交文档后调用。
//                .onRender(onRenderListener)
                // 渲染风格（就像注释，颜色或表单）
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                // 改善低分辨率屏幕上的渲染
                .enableAntialiasing(true)
                // 页面间的间距。定义间距颜色，设置背景视图
                .spacing(0)
                .load();
    }
}
