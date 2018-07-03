package com.zjdt.dtsjwb.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjdt.dtsjwb.Bean.InfoBean;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class InfoActivity extends BaseActivity {
    private RecyclerView infoRecy;

    public static ArrayList<InfoBean>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                exec();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("au","info");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SocketUtil.sendMessageAdd("218.108.146.98",3333,jsonObject.toString());
            }
        });
        while (list==null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        initView();


    }

    private void initView(){
        infoRecy=f(R.id.info_recy);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        InfoAdapter ia=new InfoAdapter(list,this,R.layout.item_info);
        infoRecy.setLayoutManager(lm);
        infoRecy.setAdapter(ia);
    }

    class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoVH>{
        public ArrayList<InfoBean>list;
        public Activity context;
        public int layoutId;

        public InfoAdapter(ArrayList<InfoBean> list, Activity context, int layoutId) {
            this.list = list;
            this.context = context;
            this.layoutId = layoutId;
        }

        @Override
        public InfoVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(layoutId,null,false);
            InfoVH infoVH=new InfoVH(view);
            return infoVH;
        }

        @Override
        public void onBindViewHolder(InfoVH holder, int position) {
            InfoBean infoBean=list.get(position);
            holder.infoCompany.setText(infoBean.getInfoCompany());
            holder.infoId.setText(infoBean.getInfoId());
            holder.infoLocation.setText(infoBean.getInfoLocation());
            holder.infoName.setText(infoBean.getInfoName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class InfoVH extends RecyclerView.ViewHolder {
            TextView infoCompany,infoName,infoId,infoLocation;
            public InfoVH(View itemView) {
                super(itemView);
                infoCompany=itemView.findViewById(R.id.info_company);
                infoName=itemView.findViewById(R.id.info_name);
                infoId=itemView.findViewById(R.id.info_id);
                infoLocation=itemView.findViewById(R.id.info_location);
            }
        }
    }

}
