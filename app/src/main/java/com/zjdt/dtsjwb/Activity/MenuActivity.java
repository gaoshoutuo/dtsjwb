package com.zjdt.dtsjwb.Activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.zjdt.dtsjwb.Activity.NewRequirements.AsertFormActivity;
import com.zjdt.dtsjwb.Activity.NewRequirements.OfflineActivity;
import com.zjdt.dtsjwb.Adapter.MenuAdapter;
import com.zjdt.dtsjwb.Adapter.TestAdapter;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.AllBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.Bean.TableEntity.OfflineEntity;
import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.NetUtil.SocketUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Service.NotificationService;
import com.zjdt.dtsjwb.Util.DatabaseUtil;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.FtpUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.PermissonUtil;
import com.zjdt.dtsjwb.Util.SQLUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;
import com.zjdt.dtsjwb.fragment.AirAssit;
import com.zjdt.dtsjwb.fragment.ProgressFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MenuActivity extends AppCompatActivity {

    //https://www.zcy.gov.cn/eevees/shop?searchType=1&shopId=124184  商城网址 商城要搞什么 必须得自己的ajax服务器
    private CityPickerView mPicker=new CityPickerView();
    private ArrayList<View> viewList = new ArrayList<>();
    private HashMap<String, String> map;
    public static TextView test;
    private String json;
    private RollPagerView rollPagerView;
    public final String TAG = "menuactivity";
    private GridView gridView;
    private int columnWidth;
    private int[] imageM = {R.drawable.icons8_fix, R.drawable.icons8_history, R.drawable.icons8_my, R.drawable.icons8_update,
            R.drawable.mall, R.drawable.test64,R.drawable.help};
    private int[] imageCustom = {R.drawable.my_assets, R.drawable.my_register, R.drawable.intelligence, R.drawable.my_history,
            R.drawable.my_mall, R.drawable.my_call, R.drawable.my_notify, R.drawable.my_update,
            R.drawable.server};
    private int[] imageSales={};

    //private int []imageOther={R.drawable.icons8_fix,R.drawable.icons8_history,R.drawable.icons8_my,R.drawable.icons8_update};
    private String[] name = {"维修业务", "维修历史", "客户信息", "检查更新", "政采云商城", "我的信息","帮录资产"};
    //private String[] customName = {"基础资产", "资产登记", "故障上报", "维保历史", "政采云商城", "联系我们", "推送消息", "检查更新", "IT资产"};//维保倒计时 放到资产里面去
    private String[] customName = {"机房创建", "资产登记", "故障上报", "维保历史", "政采云商城", "联系我们", "待办消息", "检查更新", "IT资产"};
    // private String []otherNamer={};
    private String []salesName={};

    private ArrayList<Object> arrayList;
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //  text.setText("菜单界面");
        // gridView=f(R.id.menu_gridview);
        initView();
        mPicker.init(this);
        MenuAdapter menuAdapter = new MenuAdapter(arrayList, this, gridView);
        //这三者 直接是数据库 json 给的
        Log.e("badge", Build.MANUFACTURER.toLowerCase());
     /*   HandlerFinal.userId=null;
        HandlerFinal.userName=null;
        HandlerFinal.userLocation=null;*/

        gridView.setAdapter(menuAdapter);






        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                FtpUtil.ftpInit("218.108.146.98","21","root","dt123456");
                //从数据库获取这三个参数

/*                JSONObject threeValue=new JSONObject();
                try {
                    threeValue.put("three_value","threevalue");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               String reJson= SocketUtil.sendMessageAdd("218.108.146.98",3333,threeValue.toString());
                try {
                    JSONObject reJ=new JSONObject(reJson);
                    HandlerFinal.userId=reJ.getString("user_id");
                    HandlerFinal.userName=reJ.getString("user_name");
                    HandlerFinal.userLocation=reJ.getString("user_location");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              */
                HandlerFinal.upsBatteryNum=1;
                JSONObject jb=new JSONObject();
               // Log.e("user_id_id",HandlerFinal.userId);
                while (HandlerFinal.userId==null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    //不能做  现在是按照来的机房来的  不是客户来的
                    jb.put("au","battery_number");
                    jb.put("cus_id",HandlerFinal.userId);
                    //jb.put("auau","query");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SocketUtil.sendMessageAdd("218.108.146.98",3333,jb.toString());

            }
        });
        File pf=AppApplication.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getParentFile();
        File file=new File(pf.getAbsolutePath()+"/"+"wbdir");

        if(!file.exists()){
            try {
                file.createNewFile();
                Log.e("newfile",file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

        if (getIntent().getStringExtra("notify")!=null&&getIntent().getStringExtra("notify").equals(HandlerFinal.NOTIFY_ADDED_BASE)){
            DialogUtil.getDialogUtil().materialDialog5(this,getIntent().getStringExtra("title"),getIntent().getStringExtra("content"),HandlerFinal.NOTIFY_UPDATE_BASE);
        }else if (getIntent().getStringExtra("notify")!=null&&getIntent().getStringExtra("notify").equals(HandlerFinal.NOTIFY_ADDED_IT)){
            DialogUtil.getDialogUtil().materialDialog5(this,getIntent().getStringExtra("title"),getIntent().getStringExtra("content"),HandlerFinal.NOTIFY_UPDATE_IT);
        }/*else if (getIntent().getStringExtra("notify").equals(HandlerFinal.NOTIFY_AGREE_BASE)){
            //DialogUtil.getDialogUtil().materialDialog5(this,getIntent().getStringExtra("title"),getIntent().getStringExtra("content"));
            //开始录入资产
        }else if (getIntent().getStringExtra("notify").equals(HandlerFinal.NOTIFY_AGREE_BASE)){
            //DialogUtil.getDialogUtil().materialDialog5(this,getIntent().getStringExtra("title"),getIntent().getStringExtra("content"));
        }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
            // 时机成熟 打开服务




        ThreadUtil.execute(new ThreadUtil.CallBack() {
            @Override
            public void exec() {

            }

            @Override
            public void run() {
                while(HandlerFinal.userId==null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent serviceIntent=new Intent(MenuActivity.this, NotificationService.class);
               /* HandlerFinal.userId=  reply.getString("userid");
                HandlerFinal.indentity=reply.getString("identity");
                HandlerFinal.userName=reply.getString("name");
                HandlerFinal.userLocation=reply.getString("location");
                HandlerFinal.company=reply.getString("company");*/
                serviceIntent.putExtra("userid",HandlerFinal.userId);
                serviceIntent.putExtra("identity",HandlerFinal.indentity);
                serviceIntent.putExtra("name",HandlerFinal.userName);
                serviceIntent.putExtra("location",HandlerFinal.userLocation);
                serviceIntent.putExtra("company",HandlerFinal.company);

                startService(serviceIntent);
            }
        });



    }


    /**
     * progress material
     *
     */
    private void addFragment(int id, Fragment addFragment, Fragment removeFragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(id, addFragment).hide(removeFragment).commit();
    }
    private void hideFragment(Fragment removeFragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.hide(removeFragment).commit();
    }

    private void initView() {
        //检查权限 确认权限
        PermissonUtil.checkPermission(this);
        test = findViewById(R.id.testtext);
        gridView = findViewById(R.id.menu_gridview);
        gridView.setNumColumns(3);
        gridView.setColumnWidth(getColumns());
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        // gridView.setOnItemClickListener(new ItemClickListeber());
        gridView.setOnItemClickListener(listener);
        if (arrayList == null) {
            arrayList = new ArrayList();
        } else {
            arrayList.clear();
            arrayList = new ArrayList();
        }
        map = (HashMap<String, String>) (getIntent().getExtras()).getSerializable("key");

        switch (map.get("au")) {
            case "1":
                for (int i = 0; i < imageM.length; i++) {

                    MenuBean menuBean = new MenuBean(name[i], imageM[i],false);
                    arrayList.add(menuBean);
                }
                break;
            case "2":
                for (int i = 0; i < imageCustom.length; i++) {

                    MenuBean menuBean = new MenuBean(customName[i], imageCustom[i],false);

                    if (i==6){
                        menuBean.setHaveRp(true);
                    }

                    arrayList.add(menuBean);

                }
                break;
            default:
                break;
        }
        //http://218.108.146.98:88/program_language_bible.html
        //OkhttpUtil.getUrl("http://176.122.185.2/picture/doctor_intelligence.json");
        OkhttpUtil.getUrl("http://218.108.146.98:88/doctor_intelligence.json");
        ProgressFragment pf=new ProgressFragment();
        pf.setContext(this);
        pf.setLayoutId(R.layout.progress_frag);
        addFragment(R.id.menu_id,pf,new AirAssit());

        while (HandlerFinal.getHf().msg==null)
            try {
                Thread.sleep(100);
                //线程调度也要学
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

      /*  ProgressWheel pw = new ProgressWheel(MenuActivity.this);
        pw.setBarColor(Color.YELLOW);*/

        json = (String) HandlerFinal.getHf().msg;
        Log.e("jsonjson", (String) HandlerFinal.getHf().msg + "12");
        JsonUtil.getInstance().parseJson(json, "doctor_pic");
        ArrayList list = JsonUtil.getInstance().getList();
        Log.e("ddd", list.size() + "");
        rollPagerView = findViewById(R.id.rollpagerview);

        rollPagerView.setPlayDelay(1000);
        rollPagerView.setAnimationDurtion(500);
        hideFragment(pf);
        // rollPagerView.setAdapter(new TestAdapter("http://176.122.185.2/picture/doctor_intelligence.json"));

        //此处要生成viewlist
        viewList.clear();
        for (int j = 0; j < list.size(); j++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_roll_pager_image, null, false);
            viewList.add(view);
        }
        rollPagerView.setAdapter(new TestAdapter(list, viewList, this));
        rollPagerView.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

        test.setVisibility(View.GONE);
    }

    int step=0;

    /**
     * 获取屏幕宽高
     */
    private int getColumns() {
        Resources resource = this.getResources();
        DisplayMetrics dm = resource.getDisplayMetrics();
        columnWidth = dm.widthPixels / 3;
        return columnWidth;
    }

    class ItemClickListeber implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                //客户 第三方 维保人员要做三种  switch 与每次的if能否合并一下
                case 0:
                    //madan get key hairena    1.填写客户基本信息  2.    3.
                    //Toast.makeText(MenuActivity.this, map.get("au"), Toast.LENGTH_SHORT).show();
                    if (map.get("au").equals("1")) {//字符串别用==
                        //Intent intent = new Intent(MenuActivity.this, FixDeviceActivity.class);
                        Intent intent = new Intent(MenuActivity.this, NewFixActivity.class);
                        MenuActivity.this.startActivity(intent);
                    } else if ((map.get("au").equals("2"))) {
                        //Toast.makeText(MenuActivity.this, "xiaoyu", Toast.LENGTH_SHORT).show();
                      /*  ThreadUtil.execute(new ThreadUtil.CallBack() {
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
                        actionActivity(MenuActivity.this,CAssetsActivity.class,null);*/
                      Toast.makeText(MenuActivity.this,"请先选择机房地区",Toast.LENGTH_SHORT).show();
                        DialogUtil.getDialogUtil().showCityView(mPicker,MenuActivity.this,1);
                     // DialogUtil.getDialogUtil().materialCreateIDC(MenuActivity.this,mPicker);
                    }
                    break;
                case 1:
                    if (map.get("au").equals("1")) {//字符串别用==
                      /*  Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
                        MenuActivity.this.startActivity(intent);*/
                      HashMap map_11=new HashMap();
                      map_11.put("au","fix_engineer");
                      actionActivity(MenuActivity.this,HistoryActivity.class,map_11);
                    } else if ((map.get("au").equals("2"))) {
                        //Toast.makeText(MenuActivity.this, "xiaoyu", Toast.LENGTH_SHORT).show();
                        //actionActivity(MenuActivity.this,CAssetsRActivity.class,null);

                        if (step==0){
                            step++;
                        JSONObject jsonObject=new JSONObject();
                        try {
                            jsonObject.put("au","idc_query");
                            jsonObject.put("user_id",HandlerFinal.userId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //HandlerUtil
                        ThreadUtil.sat(jsonObject);
                        Toast.makeText(MenuActivity.this,"正在查询机房稍等...",Toast.LENGTH_SHORT).show();
                       // actionActivity(MenuActivity.this,AsertFormActivity.class,null);
                            ThreadUtil.execute(new ThreadUtil.CallBack() {
                                @Override
                                public void exec() {

                                }

                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    step=0;
                                }
                            });
                    }else{
                            Toast.makeText(MenuActivity.this,"查询频繁请5s后在尝试",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case 2:
                    Toast.makeText(MenuActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    if (map.get("au").equals("1")) {//此处  要获取客户信息  信息 id 地区 姓名 单位  的存在

                        actionActivity(MenuActivity.this,InfoActivity.class,null);
                        //actionActivity(MenuActivity.this,CCallActivity.class,null);
                    } else if ((map.get("au").equals("2"))) {
                        actionActivity(MenuActivity.this,IntellActivity.class,null);

                    }
                    break;
                case 3:

                    if (map.get("au").equals("2")) {//字符串别用==
                       /* HashMap map_32=new HashMap();
                        map_32.put("au","fix_custom");
                        actionActivity(MenuActivity.this,HistoryActivity.class,map_32);*/
                       Intent offlineIntent=new Intent(MenuActivity.this, OfflineActivity.class);
                        offlineIntent.putExtra("type",HandlerFinal.HISTORYMSG);
                       startActivity(offlineIntent);

                    } else if ((map.get("au").equals("1"))) {
                        Toast.makeText(MenuActivity.this, "xiaoyu", Toast.LENGTH_SHORT).show();
                        ThreadUtil.execute(new ThreadUtil.CallBack() {
                            @Override
                            public void exec() {

                            }

                            @Override
                            public void run() {
                                OkhttpUtil.getUrl("");//更新地址
                            }
                        });
                    }
                    Toast.makeText(MenuActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    //商城 测试 tel
                   /* DialogUtil dialogUtil=new DialogUtil();
                    DialogUtil.AlertDialogUtil alertDialogUtil= dialogUtil.new AlertDialogUtil(MenuActivity.this);*/
                   // alertDialogUtil.setAlertDialog("确定","关闭","是否拨打紧急热线" );
                    if (map.get("au").equals("2")) {
                        Toast.makeText(MenuActivity.this,"功能开发中...",Toast.LENGTH_SHORT).show();
                    }else if(map.get("au").equals("2")){
                        Toast.makeText(MenuActivity.this,"功能开发中...",Toast.LENGTH_SHORT).show();
                    }

                    break;
                case 5:
                    if (map.get("au").equals("2")) {
                        DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(MenuActivity.this);
                        alertDialogUtil.setAlertDialog("YES","NO","提示","是否拨通迪特运维热线",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case -2:
                                                break;
                                            case -1:
                                                call("4001105050");
                                                break;
                                        }

                                    }
                                }
                        );

                    }else if(map.get("au").equals("1")){
                        //Toast.makeText(MenuActivity.this,"功能开发中...",Toast.LENGTH_SHORT).show();
                       // MenuActivity.this.startActivity(new Intent(MenuActivity.this, FixHistoryTestActivity.class));
                        Toast.makeText(MenuActivity.this,"功能开发中...",Toast.LENGTH_SHORT).show();
                    }

                    break;
                case 6:
                   // actionActivity(MenuActivity.this,BaseActivity.class,null);
                    if (map.get("au").equals("1")) {//字符串别用==
                        //actionActivity(MenuActivity.this,HelpActivity.class,null);  其实不需要  很多跳转 可以dialog 的选定\
                       /* if (HandlerFinal.isAuthorizeCusIdITAsset!=null&&HandlerFinal.isAuthorizeCusIdITAsset.equals(HandlerFinal.myCustomId)){
                            //帮客户填基础资产


                        }else if(HandlerFinal.isAuthorizeCusIdBasicAsset!=null&&HandlerFinal.isAuthorizeCusIdBasicAsset.equals(HandlerFinal.myCustomId)){
                            //帮客户填it资产

                        }else{
                            DialogUtil.getDialogUtil().materialDialog2(MenuActivity.this);
                        }*/
                        Toast.makeText(MenuActivity.this,"请先选择机房地区",Toast.LENGTH_SHORT).show();
                        DialogUtil.getDialogUtil().showCityView(mPicker,MenuActivity.this,2);
                        //填资产的坑  要客户先填写资产  然后才能给它的机房加吗  嗯  嗯 嗯  应该还是完整的给一个客户的按钮吧  创建机房以及添加资产  中间隔一道权限
                    } else if ((map.get("au").equals("2"))) {
                        Toast.makeText(MenuActivity.this,"您的未处理信息放在此处，请及时处理以便生成完整记录",Toast.LENGTH_LONG).show();

                        // 待办展示  时间simpledate  业务名  工程师  8月7号  （鱼传奇）  ups检修

                        Intent offlineIntent=new Intent(MenuActivity.this, OfflineActivity.class);
                        offlineIntent.putExtra("type",HandlerFinal.OFFLINEMSG);
                        startActivity(offlineIntent);

                    }
                    break;
                case 7:
                    if (map.get("au").equals("1")) {

                    }else if ((map.get("au").equals("2"))) {

                        //  public static final String []DTSJOFFLINEMSG={"dtsjofflinemsg","id","timerecord","json_1","json_2","idc_id","idc_name","idc_type","idc_location"
                        //            ,"user_id","eng_id","bussiness_type","iswatch","eng_name","blank_1","blank_2","blank_3"};
                    /*    DatabaseUtil.MyDatabase dbdtsjHelp=DatabaseUtil.getInstance().new MyDatabase(MenuActivity.this,"DTSJ.db",null,2);
                        //Toast.makeText(MenuActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                        SQLiteDatabase dbDtsj=dbdtsjHelp.getWritableDatabase();//今天回头吧sql弄得滚瓜烂熟
                        ContentValues values=new ContentValues();
                        values.put("timerecord","123");values.put("json_1","json1");values.put("json_2","json2");
                        values.put("idc_id","123");values.put("idc_name","json1");values.put("idc_type","json2");values.put("idc_location","json2");

                        values.put("user_id","123");values.put("eng_id","json1");values.put("bussiness_type","json2");values.put("iswatch","json2");
                        values.put("eng_name","123");values.put("blank_1","json1");values.put("blank_2","json2");values.put("blank_3","json2");

                        dbDtsj.insert("dtsjofflinemsg",null,values);
                        SQLUtil.TableOffline tableOffline= SQLUtil.getInstance().new TableOffline();
                        ArrayList<AllBean>list=tableOffline.query(dbDtsj);
                        OfflineEntity ofe=(OfflineEntity)list.get(0);
                        tableOffline.delete(dbDtsj,"123");
                        Toast.makeText(MenuActivity.this,list.size()+"",Toast.LENGTH_LONG).show();
*/
                    }

                    break;
                case 8:

                    if (map.get("au").equals("1")) {//字符串别用==

                    } else if ((map.get("au").equals("2"))) {
                        Toast.makeText(MenuActivity.this, "功能开发中...", Toast.LENGTH_SHORT).show();
                       // actionActivity(MenuActivity.this,CAssetsActivity.class,null);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public static void actionActivity(Context context1, Class context2, HashMap<String,String> hashMap){
        Intent intent=new Intent(context1,context2);Bundle bundle=new Bundle();
        bundle.putSerializable("key",hashMap);
        intent.putExtras(bundle);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        AppApplication.getApp().startActivity(intent);
    }

    public void updateApk(String filename){//首先服务也得启动起来  会根据我的推送来下载apk 到本地这个文件夹
        //String path = Environment.getExternalStorageDirectory() + "/DateApp.apk";
        String path = getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString()+ filename;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        startActivity(intent);//包含在intent里面了
    }
}
