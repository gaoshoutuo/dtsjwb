package com.zjdt.dtsjwb.Activity.NewRequirements;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import com.google.gson.JsonObject;
import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Activity.MenuActivity;
import com.zjdt.dtsjwb.Bean.AllBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.OfflineBean;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OfflineActivity extends BaseActivity implements View.OnClickListener{// 奇怪这并不是万能的  只是在同一个activity下面实现不同item adapter的recy罢了  history 也是这个好了
    //好啦 我要在此处涉及好我的万能Recy  怎么做出流畅的东西呢
    private RecyclerView recyclerView;
   // private RecyclerView.Adapter<RecyclerView.ViewHolder>adapter;
    private RecyclerView.ViewHolder viewholder;
    private int layoutId=0;
    private ArrayList<OfflineBean>list=new ArrayList<>();
    public static OfflineActivity offActivity;

    private OfflineAdapter offlineAdapter,cusHistoryAdapter,engHistoryAdapter,countDownAdapter;
    private String type;

    private void initOfflineLayout(int layoutId){
        this.layoutId=layoutId;
    }

    private void initOfflineData(){
        //那本地数据库的数据  算了 不拿本地数据库了
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("au","double_msg");
            jsonObject.put("type","0");
            jsonObject.put("cus_id",HandlerFinal.userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
        offActivity=this;
    }

    private void initofflineAdapter(){

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        offlineAdapter= new OfflineAdapter(R.layout.item_offline,list,this);
        recyclerView.setAdapter(offlineAdapter);
    }


    private void initCusHistoryData(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("au","double_msg");
            jsonObject.put("type","2");
            jsonObject.put("cus_id",HandlerFinal.userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
        offActivity=this;
    }

    private void initCusHistiryAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        cusHistoryAdapter= new OfflineAdapter(R.layout.item_offline,list,this);
        recyclerView.setAdapter(cusHistoryAdapter);
    }


    private void initEngHistoryData(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("au","double_msg");
            jsonObject.put("type","3");
            jsonObject.put("eng_id",HandlerFinal.userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
        offActivity=this;
    }

    private void initEngHistiryAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        engHistoryAdapter= new OfflineAdapter(R.layout.item_offline,list,this);
        recyclerView.setAdapter(engHistoryAdapter);
    }


    private void initCountDownData(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("au","count_down");
            //jsonObject.put("type","2");
            //jsonObject.put("cus_id",HandlerFinal.userId);dai//
            // 则得到所有客户的 客户id 客户姓名  客户机房 客户地区 ups剩余时间(天) 空调剩余时间(天)  dialog 中间也做一次  排序做一次 sql 上面的排序  还是代码上面的排序



        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
        offActivity=this;
    }

    private void initCountDownAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        countDownAdapter= new OfflineAdapter(layoutId,list,this);
        recyclerView.setAdapter(countDownAdapter);
    }

    private void initView(){
        recyclerView=f(R.id.offline_msg_recy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        initView();
        Intent whereIntent=getIntent();
        type=whereIntent.getStringExtra("type");
        switch (type){
            case HandlerFinal.OFFLINEMSG:
                initOfflineLayout(R.layout.item_offline);
                initOfflineData();
                initofflineAdapter();
                Toast.makeText(OfflineActivity.this, "待办", Toast.LENGTH_SHORT).show();
                break;

            case HandlerFinal.HISTORYMSG:
                initOfflineLayout(R.layout.item_offline);
                initCusHistoryData();
                initCusHistiryAdapter();
                Toast.makeText(OfflineActivity.this, "客户历史", Toast.LENGTH_SHORT).show();
                break;

            case HandlerFinal.ENGHISTORYMSG:
                initOfflineLayout(R.layout.item_offline);
                initEngHistoryData();
                initEngHistiryAdapter();
                Toast.makeText(OfflineActivity.this, "工程师历史", Toast.LENGTH_SHORT).show();
                break;

            case HandlerFinal.COUNTDOWNMSG:
                initOfflineLayout(R.layout.item_count_down);//客户id 客户姓名  客户机房id  客户机房位置 剩余时间2
                initCountDownData();
                initCountDownAdapter();
                Toast.makeText(OfflineActivity.this, "维保倒计时", Toast.LENGTH_SHORT).show();
                break;

                default:break;
        }
    }

    public Handler handler=new Handler(Looper.getMainLooper()){//重写 回调  形式参数 插入 T Looper.getMainLooper()
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case HandlerFinal.OFFLINE_REPLY:
                    list.clear();
                    ArrayList<OfflineBean> cusOfflist=JsonUtil.parseDMOffline((String)msg.obj);
                    list.addAll(cusOfflist);
                    offlineAdapter.notifyDataSetChanged();

                break;

                case HandlerFinal.HISTORY_REPLY:
                    list.clear();
                    ArrayList<OfflineBean> cusHistory=JsonUtil.parseDMHistory((String)msg.obj);
                    list.addAll(cusHistory);
                    cusHistoryAdapter.notifyDataSetChanged();
                    Toast.makeText(OfflineActivity.this, "客户历史", Toast.LENGTH_SHORT).show();
                    break;

                case HandlerFinal.ENG_HISTORY_REPLY:
                    list.clear();
                    ArrayList<OfflineBean> engHistory=JsonUtil.parseDMHistory((String)msg.obj);
                    list.addAll(engHistory);
                    engHistoryAdapter.notifyDataSetChanged();
                    Toast.makeText(OfflineActivity.this, "工程师历史", Toast.LENGTH_SHORT).show();//服务一定要送到嘴里吗
                    break;

                case HandlerFinal.COUNT_DOWN_REPLY:
                    list.clear();
                    ArrayList<OfflineBean> countDownList=JsonUtil.parseCountDown((String)msg.obj);
                    list.addAll(countDownList);
                    countDownAdapter.notifyDataSetChanged();
                    Toast.makeText(OfflineActivity.this, "维保倒计时", Toast.LENGTH_SHORT).show();//服务一定要送到嘴里吗
                    break;
                    default:break;
            }

        }
    };


    @Override
    public void onClick(View v) {
        if (listener!=null){
            OfflineAdapter.MsgTag msgTag=(OfflineAdapter.MsgTag)v.getTag();
            listener.onItemClick(msgTag);
        }
    }

    private OnItemClickListener listener=new OnItemClickListener() {
        @Override
        public void onItemClick(OfflineAdapter.MsgTag tag) {
            Toast.makeText(OfflineActivity.this,"123",Toast.LENGTH_SHORT).show();
            //选择 下载还是查看
            DialogUtil.getDialogUtil().selectShowDload(OfflineActivity.this,tag);
        }
    };

    public interface OnItemClickListener{
        void onItemClick(OfflineAdapter.MsgTag tag);//是否满足序列化
    }

    public class OfflineAdapter extends RecyclerView.Adapter<OfflineAdapter.OfflineViewHolder>{
        private int layoutId;
        private ArrayList<OfflineBean>list;
        private Activity context;


        @Override
        public OfflineAdapter.OfflineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (layoutId!=R.layout.item_count_down){
                View view= LayoutInflater.from(context).inflate(layoutId,null,false);
                OfflineViewHolder viewHolder=new OfflineViewHolder(view);
                view.setOnClickListener(OfflineActivity.this);
                return viewHolder;
            }else{
                View view= LayoutInflater.from(context).inflate(layoutId,null,false);
                OfflineViewHolder viewHolder=new OfflineViewHolder(view,"2");
                //view.setOnClickListener(OfflineActivity.this);
                return viewHolder;
            }



        }

        @Override
        public void onBindViewHolder(OfflineAdapter.OfflineViewHolder holder, int position) {
            if (layoutId!=R.layout.item_count_down)
            bind1(holder,position);
            else
                bind2(holder,position);
        }
        public void bind1(OfflineAdapter.OfflineViewHolder holder, int position){
            //OfflineBean olb=(OfflineBean) list.get(position); 这样不行  强制类型转换不要想当然
            OfflineBean olb=list.get(position);
            String time=olb.getTime();
          /*  Date date=new Date(Long.parseLong(time));
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ss");
            String simDate=simpleDateFormat.format(date);*/
            Log.e("888888",time);
            Date date=new Date(Long.parseLong(time));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String res=simpleDateFormat.format(date);
            holder.timeTv.setText(res);
            String buss= olb.getBuss();
            holder.bussTv.setText(buss);
            String engName=olb.getEngname();
            holder.nameTv.setText(engName);
            String idcName=olb.getIdcname();
            holder.idcName.setText(idcName);
            //view.setTag(olb.getFilename());
            MsgTag msgTag=new MsgTag(olb.getFilename(),position,time,buss);
            holder.view.setTag(msgTag);
        }

        public void bind2(OfflineAdapter.OfflineViewHolder holder, int position){
            //OfflineBean olb=(OfflineBean) list.get(position); 这样不行  强制类型转换不要想当然
            OfflineBean olb=list.get(position);
          /*  String time=olb.getTime();
          *//*  Date date=new Date(Long.parseLong(time));
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ss");
            String simDate=simpleDateFormat.format(date);*//*
            Log.e("888888",time);
            Date date=new Date(Long.parseLong(time));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String res=simpleDateFormat.format(date);
            holder.timeTv.setText(res);
            String buss= olb.getBuss();
            holder.bussTv.setText(buss);
            String engName=olb.getEngname();
            holder.nameTv.setText(engName);
            String idcName=olb.getIdcname();
            holder.idcName.setText(idcName);
            //view.setTag(olb.getFilename());
            MsgTag msgTag=new MsgTag(olb.getFilename(),position,time,buss);
            holder.view.setTag(msgTag);*/

            String cusid=olb.getCusId();
            String cusName=olb.getCusName();
            String idcId=olb.getIdcId();
            String idcLocation=olb.getLocation();
            String upstime=olb.getUpstime();
            String airtime=olb.getAirtime();
            holder.cusIdTv.setText("客户ID:"+cusid);
            holder.cusNameTv.setText("客户姓名:"+cusName);
            holder.idcIdTv.setText("机房ID:"+idcId);
            holder.locationTv.setText("机房地区:"+idcLocation);
            holder.upsTimeTv.setText("ups倒计时:"+upstime+"（天）");
            holder.airTimeTv.setText("空调倒计时:"+airtime+"（天）");
        }




        public class MsgTag implements Serializable{
            private String filename;
            private int position;
            private String type;
            private String time;//这个也要拿来查  json  buss_type

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public MsgTag(String filename, int position, String time,String type) {
                this.filename = filename;
                this.position = position;
                this.time = time;
                this.type = type;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public OfflineAdapter(int layoutIdl, ArrayList<OfflineBean> list, Activity context) {
            this.layoutId = layoutIdl;
            this.list = list;
            this.context = context;
        }

        public class OfflineViewHolder extends RecyclerView.ViewHolder{
            public TextView timeTv,nameTv,bussTv,idcName;
            public TextView cusIdTv,cusNameTv,idcIdTv,locationTv,upsTimeTv,airTimeTv;
            public View view;


            public OfflineViewHolder(View itemView) {
                super(itemView);
                view=itemView;
                timeTv=itemView.findViewById(R.id.offline_time);//艹 item 而不是 view
                nameTv=itemView.findViewById(R.id.offline_engname);
                bussTv=itemView.findViewById(R.id.offline_buss);
                idcName=itemView.findViewById(R.id.offline_idcname);
            }

            public OfflineViewHolder(View itemView,String type) {
                super(itemView);
                view=itemView;
                //cusIdTv=itemView.findViewById()
                cusIdTv=itemView.findViewById(R.id.count_down_cusid);
                cusNameTv=itemView.findViewById(R.id.count_down_cusname);
                idcIdTv=itemView.findViewById(R.id.count_down_idcid);
                locationTv=itemView.findViewById(R.id.count_down_location);
                upsTimeTv=itemView.findViewById(R.id.count_down_upstime);
                airTimeTv=itemView.findViewById(R.id.count_down_airtime);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        offActivity=null;
    }
}
