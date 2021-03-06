package com.zjdt.dtsjwb.Activity.NewRequirements;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.FixAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.FixUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InspectionAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InstallAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.SiteActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.TestUpsActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.IdcBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AsertFormActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private FrameLayout frameLayout;

    //也不用一个activity 直接dialog 就能解决

    public void otherInitView1(){
        frameLayout=f(R.id.add_sub_frag);
        frameLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void otherInitView2(){
        //frameLayout=f(R.id.add_sub_frag);
        frameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asert_form);
        ArrayList<IdcBean> list=initview();
        recyclerView=f(R.id.idc_recy);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        IdcAdapter ia=new IdcAdapter(list,R.layout.item_select_form,AsertFormActivity.this);
        recyclerView.setAdapter(ia);

    }

    //fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.add_sub_frag, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private ArrayList<IdcBean> initview(){
       /*HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
        ArrayList<IdcBean> list= (ArrayList<IdcBean>) map.get("list");*/
        ArrayList<IdcBean> list= (ArrayList<IdcBean>) getIntent().getExtras().getSerializable("key");
        return list;
    }


    class IdcAdapter extends RecyclerView.Adapter<IdcAdapter.IdcViewHolder>implements View.OnClickListener{
        private ArrayList<IdcBean> list;
        private int layoutId;
        private Activity context;

        public IdcAdapter(ArrayList<IdcBean> list, int layoutId, Activity context) {
            this.list = list;
            this.layoutId = layoutId;
            this.context = context;
        }

        @Override
        public IdcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(layoutId,null,false);
            IdcViewHolder ivh=new IdcViewHolder(view);
            //HashMap mapAll=new HashMap();
            if (HandlerFinal.nov==-1){
                view.setOnClickListener(this);
            }else{
                view.setOnClickListener(listener);
            }


            return ivh;
        }

        @Override
        public void onBindViewHolder(IdcViewHolder holder, int position) {
            IdcBean idc=list.get(position);
            holder.idcLocation.setText(idc.getSimpleLocation());
            holder.idcName.setText(idc.getIdcName());
            holder.idcId.setText(idc.getIdcId());
            holder.idcType.setText(idc.getIdcType());
            AFTag tag=new AFTag(idc.getIdcType(),position,idc);
            holder.view.setTag(tag);
            //holder.view.setTag(position); //tag不一定一定要存储
        }

        private OnItemClickListener onItemClickListener=new OnItemClickListener() {
            @Override
            public void onItemClick(AFTag tag) {// 不像改成activity 的方式  太丑的实现
                switch (tag.getType()){
                    case "single"://单台机资产
                        AsertFormActivity.this.otherInitView1();
                        DialogUtil.getDialogUtil().materialSelect(AsertFormActivity.this,tag.getIb().getIdcId());
                        break;
                    case "multi"://多联机资产  这怎么做呢
                        AsertFormActivity.this.otherInitView1();
                        DialogUtil.getDialogUtil().materialSelect(AsertFormActivity.this,tag.getIb().getIdcId());
                        break;

                    case "all"://整机资产  还是比较困难的呢  强制类型转换是怎么做的的呢
                        AsertFormActivity.this.otherInitView1();
                        DialogUtil.getDialogUtil().materialSelect(AsertFormActivity.this,tag.getIb().getIdcId());

                        break;

                        default:
                            break;

                }
            }
        };

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View v) {//这其实就是 view 里面实现其他的listener
            if (onItemClickListener!=null){
                onItemClickListener.onItemClick((AFTag)v.getTag());
            }
        }

        class IdcViewHolder extends RecyclerView.ViewHolder{
            private TextView idcId,idcName,idcLocation,idcType;
            private View view;
            public IdcViewHolder(View itemView) {
                super(itemView);
                view=itemView;
                idcId=itemView.findViewById(R.id.idc_select_id);
                idcName=itemView.findViewById(R.id.idc_select_name);
                idcLocation=itemView.findViewById(R.id.idc_select_location);
                idcType=itemView.findViewById(R.id.idc_select_type);
            }
        }//改来改去 总归还是耦合少一点比较好
}
 /*   HashMap mapAll=makeHashmap("地区",
            "原因",
            HandlerFinal.novIden);*/
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
        private View.OnClickListener listener=new View.OnClickListener() {

            HashMap mapAll=makeHashmap("地区",
                    "原因",
                    HandlerFinal.novIden);

            @Override
            public void onClick(View v) {
                switch (HandlerFinal.nov){
                    case 0:
                        //此处建立机房四信息的
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, InsUpsActivity.class,mapAll);
                        HandlerFinal.nov=-1;//我也搞不懂 为啥要重新置为 -1
                        break;
                    case 1:
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, TestUpsActivity.class,mapAll);
                        HandlerFinal.nov=-1;
                        break;
                    case 2:
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, FixUpsActivity.class,mapAll);
                        HandlerFinal.nov=-1;
                        break;
                    case 3:
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        mapAll.put("site","service");
                        actionActivity(AsertFormActivity.this, SiteActivity.class,mapAll);
                        HandlerFinal.nov=-1;
                        break;
                    case 4:
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        mapAll.put("site","install");
                        actionActivity(AsertFormActivity.this, SiteActivity.class,mapAll);
                        HandlerFinal.nov=-1;
                        break;
                    case 5://空调老巡检
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, InsAirActivity.class,mapAll);
                        HandlerFinal.nov=-1;
                        break;
                    case 6://空调新巡检
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, InspectionAirActivity.class,mapAll);
                        HandlerFinal.nov=-1;

                        break;
                    case 7: //空调安装
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, InstallAirActivity.class,mapAll);
                        HandlerFinal.nov=-1;

                        break;

                    case 8://空调维修
                        mapAll.put("four_idc",((AFTag)v.getTag()).getIb());
                        Log.e("four_idc",((AFTag)v.getTag()).getIb().getIdcId());
                        actionActivity(AsertFormActivity.this, FixAirActivity.class,mapAll);
                        HandlerFinal.nov=-1;

                        break;
                    case 9://pdu
                        break;

                    case 10://消防
                        break;

                    default:break;
                }
            }
        };

   public class AFTag implements Serializable{
        private String type;
        private int position;
        private IdcBean ib;

       public AFTag(String type, int position, IdcBean ib) {
           this.type = type;
           this.position = position;
           this.ib = ib;
       }

       public String getType() {
           return type;
       }

       public void setType(String type) {
           this.type = type;
       }

       public int getPosition() {
           return position;
       }

       public void setPosition(int position) {
           this.position = position;
       }

       public IdcBean getIb() {
           return ib;
       }

       public void setIb(IdcBean ib) {
           this.ib = ib;
       }
   }

}
