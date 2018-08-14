package com.zjdt.dtsjwb.Activity;

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

import com.zjdt.dtsjwb.Bean.AssertBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CAssetsActivity extends BaseActivity {
    /**
     * 总之打开这个activity 确实就是打开资产列表  设置一个true或者false 来开关 listener 不同角色打开 会有 折旧  保修维修 这些属性的不同
     * @param savedInstanceState
     */
    private boolean isOpen=false;
    private RecyclerView recyclerView;
    private ArrayList<AssertBean> list;
    public ArrayList<AssertBean> getList(){
        return list;
    }
    public void setList(ArrayList<AssertBean> list){
        this.list=list;
    }

    public static CAssetsActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassets);
        //isOpen=getIntent().getBooleanExtra("isopen",false);

       // setTitle("我的资产");
        sInstance=this;
        setRightRoot(R.drawable.my_countdown, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionActivity(CAssetsActivity.this,CCountdownActivity.class,null);
            }
        });

       // initData();

        initview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sInstance=null;
    }

    public void initview(){
        recyclerView=f(R.id.asset_recyclerview);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        while (list==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AssetAdapter adapter=new AssetAdapter(this,list,R.layout.item_subtitle);
        recyclerView.setAdapter(adapter);//setAdapter 就是一个黑盒
    }
    private void initData(){
        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                JSONObject assertJson=new JSONObject();
                try {
                    assertJson.put("au","asset_query");
                    Log.e("kkkkk",HandlerFinal.userId);
                    assertJson.put("cus_id", HandlerFinal.userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SocketUtil.sendMessageAdd("218.108.146.98",3333,assertJson.toString());
            }
        });
    }

    public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.Vh>{
        private Activity activity;
        private ArrayList<AssertBean> list;
        private int layoutId;


        @Override
        public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(activity).inflate(layoutId,null,false);
            Vh vh=new Vh(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(Vh holder, int position) {
            AssertBean ab=list.get(position);
            holder.headTitleText.setText(ab.getHeadTitle());
            holder.assertBrandText.setText(ab.getAssertBrand());
            holder.assertParaText.setText(ab.getAssertPara());
            holder.assertTypeText.setText(ab.getAssertType());
            holder.assertNameText.setText(ab.getAssertName());
            holder.assertNumberText.setText(ab.getAssertNumber());
            holder.mainTitleText.setText(ab.getMainTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Vh extends RecyclerView.ViewHolder{
            TextView headTitleText,assertNameText,assertParaText,assertTypeText,assertBrandText,
                    assertNumberText,mainTitleText;

            public Vh(View itemView) {
                super(itemView);
                headTitleText=itemView.findViewById(R.id.assert_head_title);
                assertNameText=itemView.findViewById(R.id.assert_name);
                assertParaText=itemView.findViewById(R.id.assert_para);
                assertTypeText=itemView.findViewById(R.id.assert_type);
                assertBrandText=itemView.findViewById(R.id.assert_brand);
                assertNumberText=itemView.findViewById(R.id.assert_number);
                mainTitleText=itemView.findViewById(R.id.assert_main_title);
            }
        }

        public AssetAdapter() {
        }

        public AssetAdapter(Activity activity, ArrayList list, int layoutId) {
            this.activity = activity;
            this.list = list;
            this.layoutId = layoutId;
        }
    }



}
