package com.zjdt.dtsjwb.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

import java.util.HashMap;

public class BaseActivity extends FragmentActivity {
  //  android.app.ActionBar actionBar;

   protected ImageView leftBack,rightRoot;
   protected TextView text;
   protected View toolbar_layout;
    private LinearLayout root_layout;
    protected SlidrInterface sif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_layout);

       // actionBar=getActionBar();
        //actionBar.hide();
        initView();
        setTitle("jjjjj");
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sif=Slidr.attach(this);

    }

    protected <T extends View>T f(int rid){

        return findViewById(rid);
    }
  /*  @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        root_layout = findViewById(R.id.root_layout);
        if (root_layout != null) {
            root_layout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            initView();
        }
    }*/

    private void initView(){
        toolbar_layout=f(R.id.dtsj_root);
        leftBack=f(R.id.left_root);
        rightRoot=f(R.id.right_root);
        text=f(R.id.text_root);
        leftBack.setOnClickListener(listener);
        //rightRoot.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.left_root:
                    finish();
                    break;
                case R.id.right_root:

                    break;
                    default:break;
            }
        }
    };
    public void setTitle(String name){
        text.setText(name);
    }
    public void setRightRoot(int sId,View.OnClickListener listener){
        rightRoot.setBackgroundResource(sId);
        rightRoot.setOnClickListener(listener);
    }

    /**
     *
     * @param context1
     * @param context2
     * @param hashMap
     * Intent 除了基本数据类型之外 无法传递  只有通过bundle包装一下了
     */
    public static void actionActivity(Context context1,Class context2, HashMap<String,String>hashMap){
        Intent intent=new Intent(context1,context2);Bundle bundle=new Bundle();
        bundle.putSerializable("key",hashMap);
        intent.putExtras(bundle);
        context1.startActivity(intent);
    }

    /**
     * 上一个活动返回的参数，总之 基本都是因activty而不同
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
