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

    private OfflineAdapter offlineAdapter,cusHistoryAdapter,engHistoryAdapter;
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
            jsonObject.put("type","2");
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
            jsonObject.put("au","double_msg");
            jsonObject.put("type","2");
            jsonObject.put("cus_id",HandlerFinal.userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ThreadUtil.sat(jsonObject);
        offActivity=this;
    }

    private void initCountDownAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        cusHistoryAdapter= new OfflineAdapter(layoutId,list,this);
        recyclerView.setAdapter(cusHistoryAdapter);
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
                initOfflineLayout(R.layout.item_offline);//客户id 客户机房  机房地区 上次服务类型
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
                    break;

                case HandlerFinal.COUNT_DOWN_REPLY:

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
            DialogUtil.getDialogUtil().selectShowDload(OfflineActivity.this,tag.getFilename());
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
            View view= LayoutInflater.from(context).inflate(layoutId,null,false);
            OfflineViewHolder viewHolder=new OfflineViewHolder(view);

            view.setOnClickListener(OfflineActivity.this);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(OfflineAdapter.OfflineViewHolder holder, int position) {
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
            MsgTag msgTag=new MsgTag(olb.getFilename(),position);
            holder.view.setTag(msgTag);
        }

        public class MsgTag implements Serializable{
            private String filename;
            private int position;

            public MsgTag(String filename, int position) {
                this.filename = filename;
                this.position = position;
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
            public View view;

            public OfflineViewHolder(View itemView) {
                super(itemView);
                view=itemView;
                timeTv=itemView.findViewById(R.id.offline_time);//艹 item 而不是 view
                nameTv=itemView.findViewById(R.id.offline_engname);
                bussTv=itemView.findViewById(R.id.offline_buss);
                idcName=itemView.findViewById(R.id.offline_idcname);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        offActivity=null;
    }
}
