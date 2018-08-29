package com.zjdt.dtsjwb.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Service.NotificationService;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;

import java.io.File;
import java.util.HashMap;

public class PdfLoaderActivity extends BaseActivity {
    PDFView pdfView;
    boolean isDOWN=false;
    public static String pdfname;
    public static String buss;
    public static String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_loader);
        sif.lock();
        //HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
      /*  if (getIntent().getStringExtra("eng")!=null){
            String filename= getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+getIntent().getStringExtra("eng");

            //initView(filename);

        }else if (getIntent().getStringExtra("cus")!=null){
            String filename= getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+getIntent().getStringExtra("cus");

            //initView(filename);
        }*/
        String externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
       // String test001=getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+"/test001.pdf";
       // String name=getIntent().getStringExtra("filename");//搞不懂为什么这个一直会这样 是android 的bug吗  不对可能是我在那里面的 app.getApp 的那种影响到了  没办法 智能 final
        while (pdfname==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //回调尝试
       // Notificationiice.Notify binder=




        //String name= getIntent().getStringExtra("pdfname");

     /*   String content=getIntent().getStringExtra("content");
        String title=getIntent().getStringExtra("title");*/
        Log.e("filename",pdfname+"-----");
        String []filename=pdfname.split("/");

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


    private void registerBroadcast(){
       // PdfLoaderActivity.
        PdfReceive pdfReceive=new PdfReceive();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.zjdt.dtsjwb.123");
        PdfLoaderActivity.this.registerReceiver(pdfReceive,filter);

    }

    public static class PdfReceive extends BroadcastReceiver{//

        @Override
        public void onReceive(Context context, Intent intent) {
           /* Bundle bundle=intent.getExtras();
            int count=bundle.getInt("count");*/
            pdfname=intent.getStringExtra("pdfname");
            buss=intent.getStringExtra("bussiness_type");
            json=intent.getStringExtra("json");
            Log.e("pdfname",pdfname);//嗯 广播的确是收到了呢
        }
    }

    private void initView(String filename){
        Toast.makeText(PdfLoaderActivity.this,"服务回执文件,请过目.翻阅到最后一页签名完成本次流程",Toast.LENGTH_SHORT).show();
        pdfView=f(R.id.pdfview);
        pdfView.recycle();
        pdfView.fromFile(new File(filename))
//                .pages(0, 2, 3, 4, 5); // 把0 , 2 , 3 , 4 , 5 过滤掉
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(true)

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
                        if (page==pageCount){// 工程师看这个也会有出现 待会给他改掉
                            if (json.length()>20){
                                Toast.makeText(PdfLoaderActivity.this,"已经是最后一页,满意的话就请签名完成本次服务的确认吧",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(PdfLoaderActivity.this,"请过目",Toast.LENGTH_LONG).show();
                            }

                          /*  Intent aiIntent=new Intent(AppApplication.getApp(), SignActivity.class);
                            long timestamp1=System.currentTimeMillis();
                            String filename1=timestamp1+".png";
                            aiIntent.putExtra("str",filename1);
                            aiIntent.putExtra("bussiness_type",buss);

                            aiIntent.putExtra("json",json+"");
                            startActivity(aiIntent);*/
                        }
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

    @Override
    public void onBackPressed() {
        if (json.length()>20){


        Toast.makeText(PdfLoaderActivity.this,"尊敬的客户请签名确认本次业务结束，您将在历史记录中看到完整清单",Toast.LENGTH_LONG).show();
       /* Intent fixIntent=new Intent(AppApplication.getApp(), SignActivity.class);
        long timestamp=System.currentTimeMillis();
        String filename=timestamp+".png";
        fixIntent.putExtra("str",filename);
        startActivity(fixIntent);*/
        Intent aiIntent=new Intent(AppApplication.getApp(), SignActivity.class);
        long timestamp1=System.currentTimeMillis();
        String filename1=timestamp1+".png";
        aiIntent.putExtra("str",filename1);
        aiIntent.putExtra("bussiness_type",buss);

        aiIntent.putExtra("json",json+"");
        startActivity(aiIntent);
        super.onBackPressed();
        }
    }
}
