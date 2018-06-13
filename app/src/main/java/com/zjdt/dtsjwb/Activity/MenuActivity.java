package com.zjdt.dtsjwb.Activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zjdt.dtsjwb.Adapter.MenuAdapter;
import com.zjdt.dtsjwb.Adapter.TestAdapter;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Service.NotificationService;
import com.zjdt.dtsjwb.Util.DialogUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.PermissonUtil;
import com.zjdt.dtsjwb.Util.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MenuActivity extends AppCompatActivity {

    //https://www.zcy.gov.cn/eevees/shop?searchType=1&shopId=124184  商城网址 商城要搞什么 必须得自己的ajax服务器
    private ArrayList<View> viewList = new ArrayList<>();
    private HashMap<String, String> map;
    public static TextView test;
    private String json;
    private RollPagerView rollPagerView;
    public final String TAG = "menuactivity";
    private GridView gridView;
    private int columnWidth;
    private int[] imageM = {R.drawable.icons8_fix, R.drawable.icons8_history, R.drawable.icons8_my, R.drawable.icons8_update,
            R.drawable.mall, R.drawable.test64};
    private int[] imageCustom = {R.drawable.my_assets, R.drawable.my_register, R.drawable.intelligence, R.drawable.my_history,
            R.drawable.my_mall, R.drawable.my_call, R.drawable.my_notify, R.drawable.my_update};
    //private int []imageOther={R.drawable.icons8_fix,R.drawable.icons8_history,R.drawable.icons8_my,R.drawable.icons8_update};
    private String[] name = {"维修业务", "维修历史", "我的信息", "检查更新", "商城", "测试"};
    private String[] customName = {"我的资产", "资产登记", "故障上报", "维保历史", "政采云商城", "联系我们", "推送消息", "检查更新"};//维保倒计时 放到资产里面去
    // private String []otherNamer={};

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
        MenuAdapter menuAdapter = new MenuAdapter(arrayList, this, gridView);

        gridView.setAdapter(menuAdapter);
        startService(new Intent(this, NotificationService.class));
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
                    MenuBean menuBean = new MenuBean(name[i], imageM[i]);
                    arrayList.add(menuBean);
                }
                break;
            case "2":
                for (int i = 0; i < imageCustom.length; i++) {
                    MenuBean menuBean = new MenuBean(customName[i], imageCustom[i]);
                    arrayList.add(menuBean);
                }
                break;
            default:
                break;
        }

        OkhttpUtil.getUrl("http://176.122.185.2/picture/doctor_intelligence.json");
        try {
            Thread.sleep(1000);
            //线程调度也要学
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        json = (String) HandlerFinal.getHf().msg;
        Log.e("jsonjson", (String) HandlerFinal.getHf().msg + "12");
        JsonUtil.getInstance().parseJson(json, "doctor_pic");
        ArrayList list = JsonUtil.getInstance().getList();
        Log.e("ddd", list.size() + "");
        rollPagerView = findViewById(R.id.rollpagerview);

        rollPagerView.setPlayDelay(1000);
        rollPagerView.setAnimationDurtion(500);
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
                    Toast.makeText(MenuActivity.this, map.get("au"), Toast.LENGTH_SHORT).show();
                    if (map.get("au").equals("1")) {//字符串别用==
                        Intent intent = new Intent(MenuActivity.this, FixDeviceActivity.class);
                        MenuActivity.this.startActivity(intent);


                    } else if ((map.get("au").equals("2"))) {
                        Toast.makeText(MenuActivity.this, "xiaoyu", Toast.LENGTH_SHORT).show();
                        actionActivity(MenuActivity.this,CAssetsActivity.class,null);
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
                        Toast.makeText(MenuActivity.this, "xiaoyu", Toast.LENGTH_SHORT).show();
                        actionActivity(MenuActivity.this,CAssetsRActivity.class,null);
                    }
                    break;
                case 2:
                    Toast.makeText(MenuActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    if (map.get("au").equals("1")) {//字符串别用==

                    } else if ((map.get("au").equals("2"))) {
                        actionActivity(MenuActivity.this,IntellActivity.class,null);



                    }
                    break;
                case 3:

                    if (map.get("au").equals("2")) {//字符串别用==
                        HashMap map_32=new HashMap();
                        map_32.put("au","fix_custom");
                        actionActivity(MenuActivity.this,HistoryActivity.class,map_32);
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
                    DialogUtil.AlertDialogUtil alertDialogUtil= DialogUtil.getDialogUtil().new AlertDialogUtil(MenuActivity.this);
                    alertDialogUtil.setAlertDialog("确定","关闭","警告","是否需要保存",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case -2:
                                            break;
                                        case -1:
                                            call("057183214565");
                                            break;
                                    }

                                }
                            }
                    );


                    break;
                case 5:
                    MenuActivity.this.startActivity(new Intent(MenuActivity.this, FixHistoryTestActivity.class));
                    break;
                case 6:
                   // actionActivity(MenuActivity.this,BaseActivity.class,null);
                    break;
                case 7:
                    break;
                case 8:
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
