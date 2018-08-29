package com.zjdt.dtsjwb.Activity.NewRequirements;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Bean.AssertBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WatchAssetActivity extends BaseActivity {
    private RecyclerView recyclerView;
    public static ArrayList<AssertBean> list;
    public static WatchAssetActivity sInstance=null;
    public WatchAdapter wA;
    public ArrayList<AssertBean> getList() {
        return list;
    }

    public void setList(ArrayList<AssertBean> list) {
        this.list = list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sInstance=null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_asset);
        recyclerView=f(R.id.watch_recy);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        Toast.makeText(this,"正在请求服务器稍等..",Toast.LENGTH_SHORT).show();
        initData(getIntent().getStringExtra("type"));int step=0;
        sInstance=this;
    /*    while (list==null&&step<80){
            try {
                Thread.sleep(100);
                step++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        wA=new WatchAdapter(list,this);
    ThreadUtil.execute(new ThreadUtil.CallBack() {
        @Override
        public void exec() {

        }

        @Override
        public void run() {
            while (list!=null){
                wA.notifyDataSetChanged();
                break;
            }
        }
    });

        recyclerView.setAdapter(wA);
    }

    private void initView(){

    }

    private void initData(String data){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("au","query_sub");
            jsonObject.put("type",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
    }

    class WatchAdapter extends RecyclerView.Adapter<WatchAdapter.WatchViewHolder>{
        private ArrayList<AssertBean> list;
        private Activity activity;

        @Override
        public WatchAdapter.WatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(activity).inflate(R.layout.item_subtitle,null,false);
            WatchViewHolder watchViewHolder=new WatchViewHolder(view);
            return watchViewHolder;
        }

        @Override
        public void onBindViewHolder(WatchAdapter.WatchViewHolder holder, int position) {
            AssertBean ab=list.get(position);
            holder.headTitle.setText(ab.getHeadTitle()+"123");
            holder.assertBrand.setText("设备品牌:"+ab.getAssertBrand());
            holder.assertPara.setText("设备参数:"+ab.getAssertPara());
            holder.assertType.setText("设备类型:"+ab.getAssertType());
            holder.assertName.setText("设备名称"+ab.getAssertName());
            holder.assertNumber.setText("设备数量"+ab.getAssertNumber()+"台");
            holder.mainTitle.setText(ab.getMainTitle());

            holder.assetLife.setText("设备使用寿命"+ab.getAssetLife()+"年");
            holder.assetFirstTime.setText("使用起始时间"+ab.getAssetFirstTime());
            Log.e("debug",ab.getHeadTitle()+123);
        }

        public WatchAdapter(ArrayList<AssertBean> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }

        @Override
        public int getItemCount() {

            return list==null?0:list.size();
        }

        class WatchViewHolder extends RecyclerView.ViewHolder{
            private TextView headTitle,assertName,assertPara,assertType,assertBrand,assertNumber,mainTitle,assetLife,assetFirstTime;

            public WatchViewHolder(View itemView) {
                super(itemView);
                headTitle=itemView.findViewById(R.id.assert_head_title);
                assertName=itemView.findViewById(R.id.assert_name);
                assertPara=itemView.findViewById(R.id.assert_para);
                assertType=itemView.findViewById(R.id.assert_type);
                assertBrand=itemView.findViewById(R.id.assert_brand);
                assertNumber=itemView.findViewById(R.id.assert_number);
                mainTitle=itemView.findViewById(R.id.assert_main_title);
                assetLife=itemView.findViewById(R.id.assert_life);
                assetFirstTime=itemView.findViewById(R.id.assert_firsttime);


            }
        }
    }



}
