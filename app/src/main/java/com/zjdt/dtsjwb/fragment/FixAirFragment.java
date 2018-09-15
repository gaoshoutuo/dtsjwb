package com.zjdt.dtsjwb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.Activity.SignActivity;
import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ParseXml;

import org.json.JSONException;
import org.json.JSONObject;

public class FixAirFragment extends Fragment {
    private int layoutId;
    private static String xmlstr;
    private View view;
    private static JSONObject json;
    public static String getJsonStr() {
        return json.toString();
    }
    private static String data[];
    public static String reasonStr;
    public static JSONObject getJson(){
        return json;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(layoutId,container,false);
        if(json==null)
            initJson();
        switch (layoutId){
            case R.layout.air_fix_head:
                //initUpsInsHeadView();
                initAirFixHeadView();
                break;

            case R.layout.air_fix_body:
                //initUpsInsBodyView();
                initAirFixBodyView();
                break;
            default:break;
        }
        return view;
    }

    private void initJson(){

        json=new JSONObject();
        try {
            json.put("au","air_fix");
            json.put("step",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 工具
     *
     */
    //xml 转化string
    public void initXmlStr(String filename){
        xmlstr= ParseXml.getFileString(getResources(), filename);
    }
    //标题textview 文字 修改为指定xml中的值
    public void initAirFixHead(int includeId,String type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }
    //修改textview 文字
    private void setViewText(View view,int rid,String name){
        TextView tv= view.findViewById(rid);
        tv.setText(name);
    }
    //获得include id 的 view
    private View getIncludeView(View view,int rid){
        View view2= view.findViewById(rid);
        return view2;
    }
    //明明获取某个值 怎么被我弄得如此复杂
    public String initReStr(String[]linkstr){
        return ParseXml.parseXMLWithPullArray(xmlstr,linkstr);
    }
    //设置edittext hint
    private void setViewEdit(View view,int rid, String name){
        EditText et=view.findViewById(rid);//如果findviewbyid 也是一次扫描xml的话 能不能一次扫出很多东西类
        et.setHint(name);
    }
    public void init4View(int includeId,String []type){
        View view1= getIncludeView(view, includeId);
        setViewEdit(view1,R.id.inspection_1,type[0]);
        setViewEdit(view1,R.id.inspection_2,type[1]);
        setViewEdit(view1,R.id.inspection_3,type[2]);
        setViewEdit(view1,R.id.inspection_4,type[3]);
    }
    //设置故障现象 view text
    public void setText(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.ups_fix_four_text,
                type);//initupstest_text
    }
    //json解析工具
    private void initHeadJson(String typ,String []type,int includeId){
        JSONObject jsonObject=new JSONObject();
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.inspection_1);
        String str1= getEditData(text1);
        singleStr(jsonObject,type[0],str1);

        EditText text2= view1.findViewById(R.id.inspection_2);
        String str2= getEditData(text2);
        singleStr(jsonObject,type[1],str2);

        EditText text3= view1.findViewById(R.id.inspection_3);
        String str3= getEditData(text3);
        singleStr(jsonObject,type[2],str3);

        EditText text4= view1.findViewById(R.id.inspection_4);
        String str4= getEditData(text4);
        singleStr(jsonObject,type[3],str4);
        // Log.e("kk",jsonObject.toString());
        singleJson(this.json,typ,jsonObject);
    }

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString()+"";
    }
    //json 单值添加
    private void singleStr(JSONObject json,String jsonKey,String jsonValue){
        try {
            json.put(jsonKey,jsonValue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //json 单Obj添加
    private void singleJson(JSONObject json,String jsonKey,JSONObject jsonValue){
        try {
            json.put(jsonKey,jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //cost json

    private JSONObject initCostJson(int includeId){
        View view1= getIncludeView(view,includeId);
        EditText text1= view1.findViewById(R.id.cost_fee1);
        String str1= getEditData(text1);

        EditText text2= view1.findViewById(R.id.cost_fee2);
        String str2= getEditData(text2);

        EditText text3= view1.findViewById(R.id.cost_fee3);
        String str3= getEditData(text3);

        EditText text4= view1.findViewById(R.id.cost_fee4);
        String str4= getEditData(text4);

        EditText text5= view1.findViewById(R.id.cost_fee5);
        String str5= getEditData(text5);

        EditText text6= view1.findViewById(R.id.cost_fee6);
        String str6= getEditData(text6);

        EditText text7= view1.findViewById(R.id.cost_fee7);
        String str7= getEditData(text7);

        EditText text8= view1.findViewById(R.id.cost_fee8);
        String str8= getEditData(text8);

        JSONObject jsonObject=jsonObjMake(new String[]{"Maintenance","warr_inner","warr_out","labor","materal","travel","transport","sum_cost"},
                new String[]{str1,str2,str3,str4,str5,str6,str7,str8});
        return jsonObject;
    }

    // 8个 json 值
    private JSONObject jsonObjMake(String[] edit,String[]edit2){//反正str1 str2 这样子
        JSONObject jsonObject=new JSONObject();
        //jsonObject.put("str1",edit);
        for(int i=0;i<edit.length;i++){
            try {
                jsonObject.put(edit[i],edit2[i]);//完美  字符串怎么就不行呢
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    //四奇葩
    private String initBodyJson(int includeId,String type){
        View view1= getIncludeView(view,includeId);
        EditText text= view1.findViewById(R.id.ups_fix_error_phon);
        String str=getEditData(text);
        singleStr(this.json,type,str);
        return str;
        //if (type.equals("fix_reason"))reasonStr=str;
    }

    //another_
    public void makeAnother_(){
        View another_view=view.findViewById(R.id.another_);
        EditText edit1=another_view.findViewById(R.id.another_1);
        EditText edit2=another_view.findViewById(R.id.another_2);
        EditText edit3=another_view.findViewById(R.id.another_3);
        EditText edit4=another_view.findViewById(R.id.another_4);
        String busType=edit1.getText().toString();
        String result=edit2.getText().toString();
        String legacy=edit3.getText().toString();
        String progress=edit4.getText().toString();
        try {
            JSONObject another=new JSONObject();
            another.put("bus_type",busType);
            another.put("result",result);
            another.put("legacy",legacy);
            another.put("progress",progress);
            json.put("another_v",another);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 渲染
     */

    private void initAirFixHeadView(){
        //初始化 data
        if(xmlstr==null) {
            initXmlStr("air_fix_data.xml");
            data = ParseXml.parseAndArray2(xmlstr);
        }
        //渲染

        //客户信息
        initAirFixHead(R.id.air_fix_1,"100");
        init4View(R.id.air_fix_2,new String[]{data[1],data[2],data[3],data[4]});
        init4View(R.id.air_fix_3,new String[]{data[5],data[6],data[7],data[8]});

        //故障信息
        initAirFixHead(R.id.air_fix_33,"200");
        setText(R.id.air_fix_4,data[11]);
        setText(R.id.air_fix_5,data[12]);
        setText(R.id.air_fix_6,data[13]);
        setText(R.id.air_fix_7,data[14]);

        //cost 不需要init

        //其他信息
        initAirFixHead(R.id.air_fix_9,"400");

        initSignButton();

    }

    private void initAirFixBodyView(){

    }

    private void initSignButton(){
        Button button=view.findViewById(R.id.air_fix_engineer_sign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fixIntent=new Intent(AppApplication.getApp(), SignActivity.class);
                long timestamp=System.currentTimeMillis();
                String filename=timestamp+".png";
                singleStr(json,"other_eng_id", HandlerFinal.userId);
                singleStr(json,"filename",filename);
                singleStr(json,"timestamp",timestamp+"");
                try {
                    singleStr(json,"reason",json.getString("fix_suggest"));//这句话似乎不需要鸭
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                fixIntent.putExtra("str",filename);
                try {
                    fixIntent.putExtra("bussiness_type",json.getString("au"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(fixIntent);
            }
        });
    }



    /**
     *  json 获取
     */

    public void initAirFixJson(){
        initHeadJson("cus_data_1",new String[]{"custom_contacts","phone_num","brand","custom_location"},R.id.air_fix_2);
        initHeadJson("cus_data_2",new String[]{"device_id","device_type","err_time","fix_time"},R.id.air_fix_3);

        initBodyJson(R.id.air_fix_4,"error_phon");
        initBodyJson(R.id.air_fix_5,"error_analysis");
        initBodyJson(R.id.air_fix_6,"handle_error");
        reasonStr=initBodyJson(R.id.air_fix_7,"fix_reason");
        singleJson(this.json,"cost",initCostJson(R.id.air_fix_8));

        makeAnother_();
    }



}
