package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.zjdt.dtsjwb.Activity.TestFixInspection.FixAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.FixUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.SiteActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.TestAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.TestUpsActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.DeviceBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DatabaseUtil;
import com.zjdt.dtsjwb.Util.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class FixDeviceActivity extends BaseActivity implements View.OnClickListener{
    /**
     * recyclerview  显示已经有的 可以添加六五的，在关闭之前会询问是否退出，然后将非空的数据填入数据表，伴随增删改查操作后台数据也也要跟上操作
     * 效果交互越复杂越好，不然总是一个demo而不是产品
     * 锚定特定的 position 也就锚定特定的 arraylist里面的数据结构   json解析做好全用string 除非int确实用来做别的
     * progressbar
     *
     *  数据库技术   加入一个碎片 碎片显示流程
     * @param savedInstanceState
     */
    private RecyclerView recyclerView;
    private Button createButton;
    private ArrayList<DeviceBean> menuList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_device);
        initView();
    }

    private void initView(){
        createButton=f(R.id.create_tra);
        recyclerView=f(R.id.device_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        final DfdAdapter dfdAdapter=new DfdAdapter(menuList);
        recyclerView.setAdapter(dfdAdapter);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list add 但是数据是要数据库里或者sharedpref拿出来的  shared 那个for的好处就在于是一次性多存储，但是不像数据库那样的增删改查的方便
                menuList.add(new DeviceBean("",0,"","",""));
                dfdAdapter.notifyDataSetChanged();
            }
        });

    }


    public void onBackPressed() {
     /*   new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        IntelligenceAddActivity.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();*/
        DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(FixDeviceActivity.this);
        alertDialogUtil.setAlertDialog("关闭","保存","警告","是否需要保存",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==-2){
                    //数据库插入一行
                   SQLiteDatabase db= DatabaseUtil.getDatabase("DTSJ.db",1);

                   db.insert("dtsjcache",null,new ContentValues());

                    Toast.makeText(FixDeviceActivity.this,"保存",Toast.LENGTH_SHORT).show();
                }else if(which==-1){
                    Toast.makeText(FixDeviceActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    /**
     * 这玩意可以先不上
     */

    private void addData(){
       DeviceBean db1= new DeviceBean("空调",11,"老用户","嘉兴","性能有问题");
       menuList.add(db1);
        DeviceBean db2= new DeviceBean("ups",11,"老用户","嘉兴","电压有问题");
        menuList.add(db2);
        DeviceBean db3= new DeviceBean("机柜把",11,"老用户","嘉兴","机柜开关太用力");
        menuList.add(db3);
        DeviceBean db4= new DeviceBean("money",11,"老用户","嘉兴","服务 兼收问题有");
        menuList.add(db4);

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
       // private String[]data={"精密空调","机柜","供电pdu","ups电池","服务器IT设备","定期检修","正常维保巡检"};
       private String[]data={"服务类型","定期检修","现场安装","现场测试","维修更换","现场服务"};
        private String[]score={"服务种类","ups电池","pdu供电","精密空调","消防设备"};

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
        public void onBindViewHolder(final ViewHolderDevice holder, final int position) {
            DeviceBean deviceBean= (DeviceBean) deviceList.get(position);
            holder.fix_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //提交逻辑 跳转到选择业务选择界面
                    //FixDeviceActivity.this.startActivity(new Intent(FixDeviceActivity.this,CAssetsActivity.class));
                   // actionActivity(FixDeviceActivity.this,);

                    HashMap mapAll=makeHashmap(holder.location.getText().toString(),
                            holder.reason.getText().toString(),
                            holder.coustomerID.getText().toString());
                    Log.e("定期",holder.device_spinner.getSelectedItem().toString()+holder.scoreSpinner.getSelectedItem().toString());
                    switch (holder.device_spinner.getSelectedItem().toString()+holder.scoreSpinner.getSelectedItem().toString()){

                       case "定期检修ups电池":
                           actionActivity(FixDeviceActivity.this, InsUpsActivity.class,mapAll);
                            break;

                        case "定期检修pdu供电":

                            break;

                        case "定期检修精密空调":
                            actionActivity(FixDeviceActivity.this, InsAirActivity.class,mapAll);
                            break;

                        case "定期检修消防设备":

                            break;

                        case "现场测试ups电池":

                            actionActivity(FixDeviceActivity.this, TestUpsActivity.class,mapAll);
                            break;

                        case "现场测试pdu供电":

                            break;

                        case "现场测试精密空调":
                            actionActivity(FixDeviceActivity.this, TestAirActivity.class,mapAll);
                            break;

                        case "现场测试消防设备":

                            break;

                        case "现场安装服务种类":

                            mapAll.put("site","install");
                            actionActivity(FixDeviceActivity.this, SiteActivity.class,mapAll);
                            break;

                        case "现场服务服务种类":
                         /*   HashMap mapService=new HashMap();
                            mapService.put("site","service");*/
                            mapAll.put("site","service");
                            actionActivity(FixDeviceActivity.this, SiteActivity.class,mapAll);
                            break;

                        case "维修更换ups电池":
                            HashMap fixupsmap=makeHashmap(holder.location.getText().toString(),
                                    holder.reason.getText().toString(),
                                    holder.coustomerID.getText().toString());
                            actionActivity(FixDeviceActivity.this, FixUpsActivity.class,fixupsmap);
                            break;

                        case "维修更换pdu供电":

                            break;

                        case "维修更换精密空调":
                            actionActivity(FixDeviceActivity.this, FixAirActivity.class,mapAll);
                            break;

                        case "维修更换消防设备":

                            break;

                            default:break;
                    }



                }
            });

            holder.location.setText(deviceBean.getLocation());
            holder.reason.setText(deviceBean.getReason());
            holder.deviceID.setText(deviceBean.getDeviceId()+"");
            holder.coustomerID.setText(deviceBean.getCoustomerId());

            Log.e("清醒",holder.device_spinner.getSelectedItem()+"");
            holder.device_spinner.setAdapter(new ArrayAdapter<String>(AppApplication.getApp(),R.layout.spinner_display_style,R.id.txtvwSpinner,data));
            holder.scoreSpinner.setAdapter(new ArrayAdapter<String>(AppApplication.getApp(),R.layout.spinner_display_style,R.id.txtvwSpinner,score));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   deviceList.remove(position);
                   DfdAdapter.this.notifyDataSetChanged();//当然得实时的在内存中保存这些 bundle
                }
            });
        }

        private HashMap makeHashmap(String location,String reason,String cusId){
            HashMap fixupsmap=new HashMap();
            fixupsmap.put("h_location",location);
            fixupsmap.put("h_reason",reason);
          //  fixupsmap.put("h_timestamp",timestamp);
            fixupsmap.put("h_custom_id",cusId);
         //   fixupsmap.put("h_engineer_id",engId);
          //  fixupsmap.put("h_filename",filename);
            return fixupsmap;
        }


        /**
         * 1打开activity 搜索数据库 插入成arraylist
         * 2
         * @return
         */

        @Override
        public int getItemCount() {
            return deviceList.size();
        }

        class ViewHolderDevice extends RecyclerView.ViewHolder{
            Button delete;
             View view;
             Button fix_send;
             Spinner device_spinner,scoreSpinner;
             EditText deviceID,coustomerID,reason,location;
             ViewHolderDevice(View itemView) {
                super(itemView);
                view=itemView;
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //出现一个pop 删除

                        return false;//return true 则只有不会冒泡  return false 则继续冒泡
                    }
                });
                 scoreSpinner=itemView.findViewById(R.id.socre_spinner);//为啥不能用socre
                fix_send=itemView.findViewById(R.id.send_fix);
                device_spinner=itemView.findViewById(R.id.device_spinner);
                deviceID=itemView.findViewById(R.id.device_id);
                coustomerID=itemView.findViewById(R.id.coustomer_id);
                reason=itemView.findViewById(R.id.reason);
                location=itemView.findViewById(R.id.location);
                 delete=itemView.findViewById(R.id.device_delete);
            }
        }
    }

}
