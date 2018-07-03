package com.zjdt.dtsjwb.Activity;


import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.zjdt.dtsjwb.Bean.FixHistoryBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.DatabaseUtil;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.HandlerUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.ParseXml;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class HistoryActivity extends BaseActivity {

    // 两个界面
    // 1给维保人员看自己的维修记录
    // 2给客户看被维保的记录 以及pdf 提供下载   提供一些花样 比如
    public RecyclerView engineerView,customView;
    //总要有能够全局获得的对象
    public static HistoryActivity sInstance;


    public ArrayList list;

    @Override
    protected void onDestroy() {
        sInstance=null;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
        if (map.get("au").equals("fix_custom")){
           setContentView(R.layout.activity_fix_custom);
           sInstance=this;
            initCusView();
            ThreadUtil.execute(new ThreadUtil.CallBack() {
                @Override
                public void exec() {

                }

                @Override
                public void run() {
                    JSONObject cusJson=new JSONObject();
                    try {
                        cusJson.put("au","history_cus");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SocketUtil.sendMessageAdd("218.108.146.98",3333,cusJson.toString());
                }
            });

/*
             list= JsonUtil.parseHistory("  [{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"\"},\n" +
                    "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"\"},\n" +
                    "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"\"},\n" +
                    "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"\"},\n" +
                    "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"\"}\n" +
                    "      ]");*/
            while (list==null){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            CusAdapter cusAdapter=new CusAdapter(list,HistoryActivity.this,R.layout.item_fix_custom);
            customView.setAdapter(cusAdapter);


        }else if(map.get("au").equals("fix_engineer")){
           setContentView(R.layout.activity_fix_engineer);
            sInstance=this;
            initEngView();
            ThreadUtil.execute(new ThreadUtil.CallBack() {
                @Override
                public void exec() {

                }

                @Override
                public void run() {
                    JSONObject engJson=new JSONObject();
                    try {
                        engJson.put("au","history_eng");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SocketUtil.sendMessageAdd("218.108.146.98",3333,engJson.toString());
                }
            });

           /* list= JsonUtil.parseHistory("  [{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"555\"},\n" +
                   "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"555\"},\n" +
                   "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"555\"},\n" +
                   "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"555\"},\n" +
                   "{\"date\":\"11111111\",\"business\":\"22222222222\",\"human\":\"33333333333333\",\"text\":\"44444444\",\"str5\":\"\",\"filepath\":\"555\"}\n" +
                   "      ]");*/
            while (list==null){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           EngAdapter engAdapter=new EngAdapter(list,HistoryActivity.this,R.layout.item_fix_engineer);
           engineerView.setAdapter(engAdapter);
        }


    }


    //拼了太久的字符串 险些android 代码都不会写了

    /**
     * 此处解析json 按照我的想法 json 应该是个数组  //w我在想 还不如key value 假如存储无关系的数据 不需要rdbms 数据库一对多（有时一对多并不表现） 多对多就有用了
     *  [{"date":"","business":"","human":"","str4":"","str5":"","filepath":""},
     {"date":"","business":"","human":"","str4":"","str5":"","filepath":""},
     {"date":"","business":"","human":"","str4":"","str5":"","filepath":""},
     {"date":"","business":"","human":"","str4":"","str5":"","filepath":""},
     {"date":"","business":"","human":"","str4":"","str5":"","filepath":""},
     {"date":"","business":"","human":"","str4":"","str5":"","filepath":""}
     ]
     */
    private void initCusView(){
        customView=f(R.id.fix_custom_recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        customView.setLayoutManager(gridLayoutManager);
       // CusAdapter cusAdapter=new CusAdapter()
        //customView.setAdapter();
    }

    private void initEngView(){
        engineerView=f(R.id.fix_engineer_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        engineerView.setLayoutManager(lm);

    }


    //handler

    //init view
    private RecyclerView.OnItemTouchListener rListener=new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    };

    public View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ((String)v.getTag()){

            }
        }
    };


    class EngAdapter extends RecyclerView.Adapter<EngAdapter.ViewEng>{
        private ArrayList<FixHistoryBean>list;
        private Activity context;
        private int layoutId;



        public EngAdapter(ArrayList<FixHistoryBean> list, Activity context, int layoutId) {
            this.list = list;
            this.context = context;
            this.layoutId = layoutId;
        }

        @Override
        public ViewEng onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(layoutId,parent,false);

            final ViewEng viewEng=new ViewEng(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(HistoryActivity.this);
                    alertDialogUtil.setAlertDialog("否","是","温馨提醒","是否需要下载报告文件",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==-2){


                                ThreadUtil.execute(new ThreadUtil.CallBack() {
                                    @Override
                                    public void exec() {

                                    }

                                    @Override
                                    public void run() {
                                        int position=viewEng.getAdapterPosition();
                                        FixHistoryBean bean= list.get(position);
                                        String filename=bean.getFilePath();
                                        Log.e("filebean",filename);//这里把消息传给handler 来打开pdfloader
                                        Message msg=HandlerUtil.handler.obtainMessage();
                                        msg.what=HandlerFinal.MESSAGE_ENG;
                                        msg.obj=filename;
                                        HandlerUtil.handler.sendMessage(msg);

                                        //1 http

                                        //2 ftp式    一次数据库存一个 obj  当然全拿出来是list  转成jsonArray   list 能否 传来呢
                                        //FtpUtil.downloadFile(filename, HandlerFinal.PATH_MOVIE+"/"+filename+".pdf");

                                    }
                                });

                                //Toast.makeText(HistoryActivity.this,"保存",Toast.LENGTH_SHORT).show();
                            }else if(which==-1){
                                Toast.makeText(HistoryActivity.this,"关闭",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });

            return viewEng;
        }

        @Override
        public void onBindViewHolder(ViewEng holder, int position) {
            FixHistoryBean bean=list.get(position);
            //holder.engText.setText(bean.getDate());
            holder.engDate.setText(bean.getDate());
            holder.engBuss.setText(bean.getBusiness());
            holder.engHuman.setText(bean.getHuman());
            holder.engText.setText(bean.getTextReason());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }




        class ViewEng extends RecyclerView.ViewHolder{
            TextView engDate,engBuss,engHuman,engText;
            public ViewEng(View itemView) {
                super(itemView);
                engDate=itemView.findViewById(R.id.time_recy_engineer);
                engBuss=itemView.findViewById(R.id.buss_recy_engineer);
                engHuman=itemView.findViewById(R.id.human_recy_engineer);
                engText=itemView.findViewById(R.id.history_text_engineer);
            }
        }

    }



    class CusAdapter extends RecyclerView.Adapter<CusAdapter.ViewCus>{
        private ArrayList<FixHistoryBean>list;
        private Activity context;
        private int layoutId;

        public CusAdapter(ArrayList<FixHistoryBean> list, Activity context, int layoutId) {
            this.list = list;
            this.context = context;
            this.layoutId = layoutId;
        }

        @Override
        public ViewCus onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(context).inflate(layoutId,parent,false);
            final ViewCus viewCus=new ViewCus(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(HistoryActivity.this);
                    alertDialogUtil.setAlertDialog("否","是","温馨提醒","是否需要下载报告文件",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==-2){


                                ThreadUtil.execute(new ThreadUtil.CallBack() {
                                    @Override
                                    public void exec() {

                                    }

                                    @Override
                                    public void run() {
                                        int position=viewCus.getAdapterPosition();
                                        FixHistoryBean bean= list.get(position);
                                        String filename=bean.getFilePath();
                                        Log.e("filebean",filename);
                                        Message msg=HandlerUtil.handler.obtainMessage();
                                        msg.what=HandlerFinal.MESSAGE_CUS;
                                        msg.obj=filename;
                                        HandlerUtil.handler.sendMessage(msg);

                                        //1 http

                                        //2 ftp式    一次数据库存一个 obj  当然全拿出来是list  转成jsonArray   list 能否 传来呢
                                        //FtpUtil.downloadFile(filename, HandlerFinal.PATH_MOVIE+"/"+filename+".pdf");

                                    }
                                });

                                //Toast.makeText(HistoryActivity.this,"保存",Toast.LENGTH_SHORT).show();
                            }else if(which==-1){
                                Toast.makeText(HistoryActivity.this,"关闭",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });
            return viewCus;
        }

        @Override
        public void onBindViewHolder(ViewCus holder, int position) {//一个方法
                FixHistoryBean bean=list.get(position);
                holder.cusDate.setText(bean.getDate());
                holder.cusBuss.setText(bean.getBusiness());
                holder.cusHuman.setText(bean.getHuman());
                holder.cusText.setText(bean.getTextReason());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }



        class ViewCus extends RecyclerView.ViewHolder{
            TextView cusDate,cusBuss,cusHuman,cusText;
            public ViewCus(View itemView) {
                super(itemView);
                cusDate=itemView.findViewById(R.id.time_recy_cus);
                cusBuss=itemView.findViewById(R.id.buss_recy_cus);
                cusHuman=itemView.findViewById(R.id.human_recy_cus);
                cusText=itemView.findViewById(R.id.history_text_cus);
            }
        }

    }


}
