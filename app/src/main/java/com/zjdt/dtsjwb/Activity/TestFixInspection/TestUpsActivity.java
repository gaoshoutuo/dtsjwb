package com.zjdt.dtsjwb.Activity.TestFixInspection;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zjdt.dtsjwb.Activity.BaseActivity;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.AirAssit;
import com.zjdt.dtsjwb.fragment.UpsFixFragment;
import com.zjdt.dtsjwb.fragment.UpsTestFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TestUpsActivity extends BaseActivity implements View.OnClickListener{

    //后悔了  应该把所有的fragment 都写到一个 而不是分多个 臃肿就臃肿一点 总比浪费 而且要分心重复那么多要好。  自食恶果了 子类新增的方法 没法多态 即使他们都一样  这是我写的最蠢的代码

    /**
     * 写了=蠢代码 前后一致性不够  目前代码行数应该超过10w行了吧
     * 1 view frame button initview
     * 2 head body foot setid
     * 3 click
     * 4 fragmentTransaction
     * 5 修改 json static 及获取方式
     */
    private FragmentManager fm=getSupportFragmentManager();
    private UpsTestFragment upsTestHead,upsTestBody,upsTestFoot;
    private Button headButton,bodyButton,footButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ups);
        initView();

        upsTestHead=new UpsTestFragment();
        upsTestHead.setRid(R.layout.ups_test_report_head);
        addFragment(R.id.ups_test_frame,upsTestHead,new AirAssit());
    }

    private void replaceFragment(int frameId,Fragment fragment){//不能replace 了  任然不懂 安卓呀
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(frameId,fragment);
        ft.commit();
    }

    private void initView(){
        headButton=findViewById(R.id.ups_test_button1);
        bodyButton=findViewById(R.id.ups_test_button2);
        footButton=findViewById(R.id.ups_test_button3);
        headButton.setOnClickListener(this);
        bodyButton.setOnClickListener(this);
        footButton.setOnClickListener(this);
    }

    private void addFragment(int frameId,Fragment addFragment,Fragment removeFragment){//static 是类变量 所以我要注意一下用法  虽然我不知道replace 是否会把类变量也去除 写程序 尤其要做实验
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(frameId, addFragment).hide(removeFragment).commit();
    }
    //权当测试
    private <T extends Fragment>void setFragment(Class<Fragment>fragmentClass,int frameId){//可以指定类 要用反射 这里先不这样做
        try {
            //TestInterface.class.cast(object); cast ()是静态的  而反射在 运行时  要去 读书了   只有我没有在指定类型之前静态的调用方法 就好了
            T fm= (T) fragmentClass.newInstance();
            //T fm2= (T) fragmentClass.cast(fm);
            replaceFragment(frameId,fm);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setFragment(){//可以指定类 要用反射 这里先不这样做

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_test_button1://处理head
                upsTestHead.makeJsonHead();
                headButton.setVisibility(View.GONE);
                bodyButton.setVisibility(View.VISIBLE);
                upsTestBody=new UpsTestFragment();
                upsTestBody.setRid(R.layout.ups_test_report_body);
                addFragment(R.id.ups_test_frame,upsTestBody,upsTestHead);
                break;

            case R.id.ups_test_button2://处理body
                upsTestBody.makeJsonBody();
                bodyButton.setVisibility(View.GONE);
                footButton.setVisibility(View.VISIBLE);
                upsTestFoot=new UpsTestFragment();
                upsTestFoot.setRid(R.layout.ups_test_report_foot);
                addFragment(R.id.ups_test_frame,upsTestFoot,upsTestBody);
                break;

            case R.id.ups_test_button3://处理foot  上传
                upsTestFoot.makeJsonFoot();
                HashMap map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");
                try {
                    UpsTestFragment.getJson().put("other_location",map.get("h_location"));
                    UpsTestFragment.getJson().put("h_custom_id",map.get("h_custom_id"));
                    JSONObject ano=new JSONObject();
                    ano.put("cus_id",map.get("h_custom_id"));
                    ano.put("timestamp",System.currentTimeMillis()+"");
                    ano.put("reason",map.get("h_reason"));
                    ano.put("business", HandlerFinal.BUSINESS_STR[1]);
                    ano.put("eng_id",HandlerFinal.userId);
                    ano.put("eng_name",HandlerFinal.userName);
                    UpsTestFragment.getJson().put("another",ano);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String json= upsTestFoot.getJsonStr();
                Log.e("upstest",json);
                ThreadUtil.execute(new ThreadUtil.CallBack() {
                    @Override
                    public void exec() {

                    }

                    @Override
                    public void run() {
                        //SocketUtil.sendMessageAdd("218.108.146.98",88,json);
                        SocketUtil.sendMessageAdd("218.108.146.98",3333,json);
                    }
                });
                break;
            default:break;
        }
    }
}
