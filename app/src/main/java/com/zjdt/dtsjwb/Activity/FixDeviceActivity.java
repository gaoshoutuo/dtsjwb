package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;

public class FixDeviceActivity extends BaseActivity implements View.OnClickListener{
    /**
     * recyclerview
     * 效果交互越复杂越好，不然总是一个demo而不是产品
     * progressbar
     * @param savedInstanceState
     */
    private RecyclerView recyclerView;
    private Button createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_device);
    }

    private void initView(){
        createButton=f(R.id.create_tra);
        recyclerView=f(R.id.device_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

    }

    @Override
    public void onClick(View v) {

    }


    class DfdAdapter extends RecyclerView.Adapter<DfdAdapter.ViewHolderDevice>{
        private ArrayList deviceList;

        public DfdAdapter(ArrayList deviceList) {
            this.deviceList = deviceList;
        }

        @Override
        public ViewHolderDevice onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(AppApplication.getApp()).inflate(R.layout.item_device,parent,false);
            ViewHolderDevice viewHolderDevice=new ViewHolderDevice(view);
            return viewHolderDevice;
        }

        @Override
        public void onBindViewHolder(ViewHolderDevice holder, int position) {

        }

        @Override
        public int getItemCount() {
            return deviceList.size();
        }

        class ViewHolderDevice extends RecyclerView.ViewHolder{
             View view;
             Button fix_send;
             Spinner device_spinner;
             EditText deviceID,coustomerID,reason,location;
             ViewHolderDevice(View itemView) {
                super(itemView);
                view=itemView;
                fix_send=itemView.findViewById(R.id.send_fix);
                device_spinner=itemView.findViewById(R.id.device_spinner);
                deviceID=itemView.findViewById(R.id.device_id);
                coustomerID=itemView.findViewById(R.id.coustomer_id);
                reason=itemView.findViewById(R.id.reason);
                location=itemView.findViewById(R.id.location);
            }
        }
    }

}
