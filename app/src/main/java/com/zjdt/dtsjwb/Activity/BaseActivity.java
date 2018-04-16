package com.zjdt.dtsjwb.Activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

import java.util.HashMap;

public class BaseActivity extends AppCompatActivity {
  //  android.app.ActionBar actionBar;

   protected ImageView leftBack,rightRoot;
   protected TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
       // actionBar=getActionBar();
        //actionBar.hide();
        initView();
    }

    protected <T extends View>T f(int rid){

        return findViewById(rid);
    }
    private void initView(){
        leftBack=f(R.id.left_root);
        rightRoot=f(R.id.right_root);
        text=f(R.id.text_root);
        leftBack.setOnClickListener(listener);
        rightRoot.setOnClickListener(listener);
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
}
