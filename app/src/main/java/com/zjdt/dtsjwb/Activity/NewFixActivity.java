package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjdt.dtsjwb.Activity.TestFixInspection.FixUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsAirActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.InsUpsActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.SiteActivity;
import com.zjdt.dtsjwb.Activity.TestFixInspection.TestUpsActivity;
import com.zjdt.dtsjwb.Adapter.UnionAdapter;
import com.zjdt.dtsjwb.Adapter.UnionBean;
import com.zjdt.dtsjwb.Adapter.UnionViewHolder;
import com.zjdt.dtsjwb.Bean.FDBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class NewFixActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<UnionBean>list;
    private int[]imageBack={R.drawable.battery_100,R.drawable.battery_wai100,R.drawable.berry_br100,R.drawable.engineer_100,
            R.drawable.fix_100,R.drawable.air_con_100,R.drawable.fire_extinguisher_100,R.drawable.electrical_100};
    private String[]textBusiness={"ups检测","ups测试","ups修理","现场服务","现场安装","空调检修","消防检修","pdu供电检修"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fix);
        initData();
        initView();
    }

    /**
     * 做了通用但轮子规划没做好有点难受
     */
    private void initView(){
        recyclerView=f(R.id.newdevice_recy);
        GridLayoutManager gm=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gm);
        NewDeviceAdapter adapter=new NewDeviceAdapter(list,R.layout.item_newfix_device,this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        list=new ArrayList<>();
        for (int i=0;i<imageBack.length;i++){
            FDBean fdBean=new FDBean(imageBack[i],textBusiness[i]);
            list.add(fdBean);
        }

    }

    public class NewDeviceAdapter extends UnionAdapter{
        private Activity context;
        private  int layoutId;
        private ArrayList<UnionBean> list;

        public NewDeviceAdapter(ArrayList<UnionBean> list, int layoutId, Activity context) {
            super(list, layoutId, context);
            this.list = list;
            this.layoutId = layoutId;
            this.context = context;
        }

        @Override
        public UnionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(layoutId,null,false);
            NewDevViewHolder uvh=new NewDevViewHolder(view);
            return uvh;
        }

        @Override
        public void setViewbid(UnionBean ub, UnionViewHolder uvh) {

        }

        @Override
        public void onBindViewHolder(UnionViewHolder holder, final int position) {
            FDBean fdBean=(FDBean)list.get(position);
            NewDevViewHolder holder2=(NewDevViewHolder)holder;
            holder2.image.setBackgroundResource(fdBean.getImageUrl());
            holder2.text.setText(fdBean.getText().toString());
            holder2.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogUtil.getDialogUtil().materialDialog(NewFixActivity.this,position);


                }
            });
        }

        public class NewDevViewHolder extends UnionViewHolder{
            TextView text;
            ImageView image;
            CardView cardView;

            public NewDevViewHolder(View itemView) {
                super(itemView);
                makeViewbid(itemView);
            }

            @Override
            public UnionViewHolder getUnionVH(View view) {
                return new NewDevViewHolder(view);
            }

            @Override
            public void makeViewbid(View itemView) {
                cardView=(CardView)itemView;
                text=itemView.findViewById(R.id.business_type);
                image=itemView.findViewById(R.id.newdevice_image);
            }
        }
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
}
