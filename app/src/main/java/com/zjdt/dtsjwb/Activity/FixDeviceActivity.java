package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.DeviceBean;
import com.zjdt.dtsjwb.R;

import java.util.ArrayList;

public class FixDeviceActivity extends BaseActivity implements View.OnClickListener{
    /**
     * recyclerview  显示已经有的 可以添加六五的，在关闭之前会询问是否退出，然后将非空的数据填入数据表，伴随增删改查操作后台数据也也要跟上操作
     * 效果交互越复杂越好，不然总是一个demo而不是产品
     *
     * 锚定特定的 position 也就锚定特定的 arraylist里面的数据结构
     * progressbar
     * @param savedInstanceState
     */
    private RecyclerView recyclerView;
    private Button createButton;
    private ArrayList menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_device);
    }

    private void initView(){
        createButton=f(R.id.create_tra);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list add 但是数据是要数据库里或者sharedpref拿出来的  shared 那个for的好处就在于是一次性多存储，但是不像数据库那样的增删改查的方便
            }
        });
        recyclerView=f(R.id.device_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

    }

    /**
     * 添加6数据
     * @param v
     */
    @Override
    public void onClick(View v) {
        menuList.add(new DeviceBean("",0,"","",""));
    }


    class DfdAdapter extends RecyclerView.Adapter<DfdAdapter.ViewHolderDevice>{
        /**
         * 故障定性是否需要一个独立的spinner呢
         * 程序员讨厌被打断
         */
        private ArrayList deviceList;
        private String[]data={"精密空调","机柜","供电pdu","ups电池","服务器IT设备","定期检修","正常维保巡检"};

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
            DeviceBean deviceBean= (DeviceBean) deviceList.get(position);
            holder.fix_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //提交逻辑

                }
            });
            holder.location.setText(deviceBean.getLocation());
            holder.reason.setText(deviceBean.getReason());
            holder.deviceID.setText(deviceBean.getDeviceId());
            holder.coustomerID.setText(deviceBean.getCoustomerId());
            holder.device_spinner.setAdapter(new ArrayAdapter<String>(AppApplication.getApp(),R.layout.spinner_display_style,R.id.txtvwSpinner,data));

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
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //出现一个pop 删除

                        return false;
                    }
                });
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
