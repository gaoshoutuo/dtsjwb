package com.zjdt.dtsjwb.Activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjdt.dtsjwb.Bean.FixHistoryBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends BaseActivity {

    // 两个界面
    // 1给维保人员看自己的维修记录
    // 2给客户看被维保的记录 以及pdf 提供下载   提供一些花样 比如
    private RecyclerView engineerView,customView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
        if (map.get("au").equals("fix_custom")){
           setContentView(R.layout.activity_fix_custom);
            initCusView();

        }else if(map.get("au").equals("fix_engineer")){
           setContentView(R.layout.activity_fix_engineer);
            initEngView();

        }


    }
    //拼了太久的字符串 险些android 代码都不会写了
    private void initCusView(){
        customView=f(R.id.fix_custom_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        customView.setLayoutManager(lm);
    }

    private void initEngView(){
        engineerView=f(R.id.fix_engineer_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        engineerView.setLayoutManager(lm);
    }


    //handler

    //init view


    class EngAdapter extends RecyclerView.Adapter<EngAdapter.ViewEng>{
        private ArrayList<FixHistoryBean>list;

        @Override
        public ViewEng onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewEng holder, int position) {

        }

        @Override
        public int getItemCount() {
            return list.size();
        }



        class ViewEng extends RecyclerView.ViewHolder{
            TextView engDate,engBuss,engHuman,engText;
            public ViewEng(View itemView) {
                super(itemView);
            }
        }

    }


    class CusAdapter extends RecyclerView.Adapter<CusAdapter.ViewCus>{
        private ArrayList<FixHistoryBean>list;

        @Override
        public ViewCus onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewCus holder, int position) {

        }

        @Override
        public int getItemCount() {
            return list.size();
        }



        class ViewCus extends RecyclerView.ViewHolder{
            TextView cusDate,cusBuss,cusHuman,cusText;
            public ViewCus(View itemView) {
                super(itemView);
            }
        }

    }




}
