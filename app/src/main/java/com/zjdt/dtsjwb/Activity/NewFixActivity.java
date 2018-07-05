package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zjdt.dtsjwb.Adapter.UnionAdapter;
import com.zjdt.dtsjwb.Adapter.UnionBean;
import com.zjdt.dtsjwb.Adapter.UnionViewHolder;
import com.zjdt.dtsjwb.Bean.FDBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;

public class NewFixActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<FDBean>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fix);
    }
    private void initView(){

    }

    private void initData(){

    }
    public class NewDeviceAdapter extends UnionAdapter{


        public NewDeviceAdapter(ArrayList<UnionBean> list, int layoutId, Activity context) {
            super(list, layoutId, context);
        }

        @Override
        public UnionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void setViewbid(UnionBean ub, UnionViewHolder uvh) {

        }

        @Override
        public void onBindViewHolder(UnionViewHolder holder, int position) {

        }
    }

}
