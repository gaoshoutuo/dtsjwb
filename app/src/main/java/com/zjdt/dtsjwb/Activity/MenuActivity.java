package com.zjdt.dtsjwb.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zjdt.dtsjwb.Adapter.MenuAdapter;
import com.zjdt.dtsjwb.Adapter.TestAdapter;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.MenuBean;
import com.zjdt.dtsjwb.Bean.RollBean;
import com.zjdt.dtsjwb.NetUtil.OkhttpUtil;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.HandlerUtil;
import com.zjdt.dtsjwb.Util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class MenuActivity extends AppCompatActivity {
    public static TextView test;
    public static String msg;
    private String json;
    private RollPagerView rollPagerView;
    public final String TAG="menuactivity";
    private GridView gridView;
    private int columnWidth;
    private int []imageM={R.drawable.icons8_fix,R.drawable.icons8_history,R.drawable.icons8_my,R.drawable.icons8_update};
    private String []name={"维修业务","维修历史","我的信息","检查更新"};
    private ArrayList <Object>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
      //  text.setText("菜单界面");
       // gridView=f(R.id.menu_gridview);
        initView();

        MenuAdapter menuAdapter=new MenuAdapter(arrayList,this,gridView);
        gridView.setAdapter(menuAdapter);
        gridView.setOnItemClickListener(new ItemClickListeber());
    }
    private void initView(){
        test=findViewById(R.id.testtext);
        gridView=findViewById(R.id.menu_gridview);
        gridView.setNumColumns(3);
        gridView.setColumnWidth(getColumns());
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        if(arrayList==null){
            arrayList=new ArrayList();
        }else{
            arrayList.clear();
            arrayList=new ArrayList();
        }
        for(int i=0;i<imageM.length;i++){
            MenuBean menuBean=new MenuBean(name[i],imageM[i]);
            arrayList.add(menuBean);
        }

        OkhttpUtil.getUrl("http://176.122.185.2/picture/doctor_intelligence.json");

        //此处getInstance一直为空
      //  json =MenuActivity.msg;
         // Log.e("gggg",(String) HandlerFinal.msg.obj+"1");
     //   json=(String)object.obj;
   /*     json="[{\"doctor_pic\":\"http://176.122.185.2/picture/doctor.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-001.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-002.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-003.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-004.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-005.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-006.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-007.jpg\",\"doctor_intelligence\":\"name|age|job\"},\n" +
                "{\"doctor_pic\":\"http://176.122.185.2/picture/doctor-008.jpg\",\"doctor_intelligence\":\"name|age|job\"}]";*/
        try {
            Thread.sleep(1000);
            //线程调度也要学
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      json=(String) HandlerFinal.getHf().msg;
      Log.e("jsonjson",(String) HandlerFinal.getHf().msg+"12");
        JsonUtil.getInstance().parseJson(json,"doctor_pic");

        ArrayList list= JsonUtil.getInstance().getList();
        Log.e("ddd",list.size()+"");
        rollPagerView=findViewById(R.id.rollpagerview);
        rollPagerView.setPlayDelay(1000);
        rollPagerView.setAnimationDurtion(500);
       // rollPagerView.setAdapter(new TestAdapter("http://176.122.185.2/picture/doctor_intelligence.json"));
        rollPagerView.setAdapter(new TestAdapter(list));
        rollPagerView.setHintView(new ColorPointHintView(this,Color.YELLOW,Color.WHITE));
        test.setVisibility(View.GONE);
    }

    /**
     * 获取屏幕宽高
     */
    private int getColumns(){
        Resources resource=this.getResources();
        DisplayMetrics dm=resource.getDisplayMetrics();
        columnWidth=dm.widthPixels/3;
        Log.e(TAG,columnWidth+"........................");
        return columnWidth;
    }

    class ItemClickListeber implements AdapterView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case 0:

                    break;

                    default:break;
            }
        }
    }

    public void parseJson(){

    }
    public static void actionActivity(Context context1, Class context2, HashMap<String,Object> hashMap){
        Intent intent=new Intent(context1,context2);Bundle bundle=new Bundle();
        bundle.putSerializable("key",hashMap);
        intent.putExtras(bundle);
        AppApplication.getApp().startActivity(intent);
    }

}
