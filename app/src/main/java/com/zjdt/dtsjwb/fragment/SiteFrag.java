package com.zjdt.dtsjwb.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.ParseXml;

import org.json.JSONException;
import org.json.JSONObject;

public class SiteFrag extends Fragment implements View.OnClickListener{
    private int layoutId;
    private static String xmlstr;
    private static String[]data;
    private View view;
    private static JSONObject json;
    public static String reasomStr;
    public static JSONObject getJson(){
        return json;
    }

    public static String getJsonStr() {
        return json.toString();
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    /**
     * init4修改  渲染修改
     * activity修改
     * headview添加xmltoArray 判断
     * edit 加入非null
     * edittext 黑字修改
     * json导出
     * @param
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(layoutId,container,false);
        if (json==null)
            initJson();
        switch (layoutId){
            case R.layout.site_device_install:
                initInstallView();

                break;
            case R.layout.site_device_service:
                initServiceView();

                break;
                default:break;
        }
        return view;
    }


    public void initXmlStr(String filename){
        xmlstr= ParseXml.getFileString(getResources(), filename);
    }

    public String initReStr(String[]linkstr){
        return ParseXml.parseXMLWithPullArray(xmlstr,linkstr);
    }

    public String[] initReArr(String[]linkstr){
        return ParseXml.parseAndArray(xmlstr,linkstr);
    }

    private View getIncludeView(View view,int rid){
        View view2= view.findViewById(rid);
        return view2;
    }

    /**
     * 修改 textview 文字
     * @param view
     * @param name
     */

    private void setViewText(View view,int rid,String name){
        TextView tv= view.findViewById(rid);
        tv.setText(name);
    }

    /**
     *  editview hint  设置哪个include view 的editview hint
     * @param view
     * @param name
     */
    private void setViewEdit(View view,int rid, String name){
        EditText et=view.findViewById(rid);//如果findviewbyid 也是一次扫描xml的话 能不能一次扫出很多东西类
        et.setHint(name);
    }
//initXmlStr("site_service.xml");
   /* public void setText(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }*/
public void setText(int includeId,String type){
    View view1= getIncludeView(view, includeId);
    setViewText(
            view1, R.id.ups_fix_four_text,
                    type);//initupstest_text
}



    public void initInstallView(){
        if(xmlstr==null) {
            initXmlStr("site_install.xml");
            data = ParseXml.parseAndArray2(xmlstr);
        }
        init4View(R.id.install_edit,new String[]{data[55],data[5],data[6],data[7]});
        setText(R.id.site_device_i_2,data[8]);
        setText(R.id.site_device_i_3,data[9]);
        setText(R.id.site_device_i_4,data[10]);
        setText(R.id.site_device_i_5,data[11]);
        Button button1=view.findViewById(R.id.device_i_engineer_sign);
        Button button2=view.findViewById(R.id.device_i_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    public void initServiceView(){
        if(xmlstr==null) {
            initXmlStr("site_service.xml");
            data = ParseXml.parseAndArray2(xmlstr);
        }
        setText(R.id.site_device_s_2,data[5]);
        setText(R.id.site_device_s_2,data[14]);
        setText(R.id.site_device_s_2,data[15]);
        setText(R.id.site_device_s_2,data[16]);
        Button button1=view.findViewById(R.id.device_s_engineer_sign);
        Button button2=view.findViewById(R.id.device_s_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void init4View(int includeId,String []type){
        View view1= getIncludeView(view, includeId);
        setViewEdit(view1, R.id.inspection_1,type[0]);
        setViewEdit(view1,R.id.inspection_2,type[1]);
        setViewEdit(view1,R.id.inspection_3,type[2]);
        setViewEdit(view1,R.id.inspection_4,type[3]);
    }


    private void initJson(){
        json=new JSONObject();
    }

    private void singleStr(JSONObject json,String jsonKey,String jsonValue){
        try {
            json.put(jsonKey,jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void singleJson(JSONObject json,String jsonKey,JSONObject jsonValue){
        try {
            json.put(jsonKey,jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private JSONObject jsonObjMake(String[] edit){//反正str1 str2 这样子
        JSONObject jsonObject=new JSONObject();
        //jsonObject.put("str1",edit);
        for(int i=0;i<edit.length;i++){
            try {
                jsonObject.put("hard"+i,edit[i]);//完美  字符串怎么就不行呢
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

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

        singleJson(this.json,typ,jsonObject);
    }

    private void initHeadJson4(String typ,String []type,int includeId){
        JSONObject jsonObject=new JSONObject();
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.custom_name_site);
        String str1= getEditData(text1);
        singleStr(jsonObject,type[0],str1);

        EditText text2= view1.findViewById(R.id.custom_contact_site);
        String str2= getEditData(text2);
        singleStr(jsonObject,type[1],str2);

        EditText text3= view1.findViewById(R.id.custom_phone_site);
        String str3= getEditData(text3);
        singleStr(jsonObject,type[2],str3);

        EditText text4= view1.findViewById(R.id.custom_location_site);
        String str4= getEditData(text4);
        singleStr(jsonObject,type[3],str4);

        singleJson(this.json,typ,jsonObject);
    }

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString()+"";
    }




    private String initBodyJson(int includeId){
        View view1= getIncludeView(view,includeId);
        EditText text= view1.findViewById(R.id.ups_fix_error_phon);
        return getEditData(text);
    }

    public void makeBodyJson(String typ,int []rid,String []type){
        JSONObject jsonObject=new JSONObject();
        String str1=initBodyJson(rid[0]);
        singleStr(jsonObject,type[0],str1);
        String str2=initBodyJson(rid[1]);
        singleStr(jsonObject,type[1],str2);
        String str3=initBodyJson(rid[2]);
        singleStr(jsonObject,type[2],str3);
        String str4=initBodyJson(rid[3]);
        singleStr(jsonObject,type[3],str4);
        singleJson(this.json,typ,jsonObject);
    }

    /**
     *
     {
     "cus_data":{"custom_name":"123","custom_location":"123","custom_contacts":"123","phone_num":"123"},
     "product_info":{"para":"123","brand":"123","type":"123","power":"123","bar_code":"123","material":"123","install_process":"123","install_result":"123"},
     info{"bar_code":"123","material":"123","install_process":"123","install_result":"123"},
     "cost":{"Maintenance":"123","warr_inner":"132","warr_out":"123","labor":"","materal":"123","travel":"143","transport":"24","sum_cost":"133"},
     "other":{"str1":"123","str2":"123","str3":"123","str4":"123","str5":"123","str6":"123"}
     }

     {
     "cus_data":{"custom_name":"123","custom_location":"123","custom_contacts":"123","phone_num":"123"},
     "info":{"service_pro":"123","error_phon":"123",""handle_error":"123","fix_reason":"123"},
     "cost":{"Maintenance":"123","warr_inner":"132","warr_out":"123","labor":"","materal":"123","travel":"143","transport":"24","sum_cost":"133"},
     "other":{,"fix_result":"123","fix_suggest":"123","str3":"123","str4":"123","str5":"123","str6":"123"}
     }
     */

    public void initManyJson(){
        if(layoutId==R.layout.site_device_install){
            initHeadJson4("cus_data",new String[]{"custom_name","custom_location","custom_contacts","phone_num"},R.id.site_device_i_1);
            initHeadJson("product_info",new String[]{"para","brand","type","power"},R.id.install_edit );
            makeBodyJson("info",new int[]{R.id.site_device_i_2,R.id.site_device_i_3,R.id.site_device_i_4,R.id.site_device_i_5},
                    new String[]{"bar_code","material","install_process","install_result"});
            singleJson(this.json,"cost",initCostJson(R.id.site_device_i_6));

            EditText text=view.findViewById(R.id.device_i_sum);
            try {
                this.json.put("sum",text.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else if(layoutId==R.layout.site_device_service){
            initHeadJson4("cus_data",new String[]{"custom_name","custom_location","custom_contacts","phone_num"},R.id.site_device_s_1);

            makeBodyJson("info",new int[]{R.id.site_device_s_2,R.id.site_device_s_3,R.id.site_device_s_4,R.id.site_device_s_5},
                    new String[]{"service_pro","error_phon","handle_error","fix_reason"});
            singleJson(this.json,"cost",initCostJson(R.id.site_device_s_6));

            EditText text=view.findViewById(R.id.device_s_sum);
            try {
                this.json.put("sum",text.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }








    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.device_i_engineer_sign:

                Intent installIntent=new Intent(AppApplication.getApp(), SignActivity.class);
                long timestamp1=System.currentTimeMillis();
                String filename1=timestamp1+".png";
                singleStr(this.json,"au", "install");
                singleStr(this.json,"other_eng_id", HandlerFinal.userId);
                singleStr(this.json,"filename",filename1);
                singleStr(this.json,"timestamp",timestamp1+"");
                try {
                    singleStr(this.json,"reason",this.json.getString("fix_suggest"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                installIntent.putExtra("str",filename1);
                startActivity(installIntent);

                break;

            case R.id.device_i_custom_sign:
                break;

            case R.id.device_s_engineer_sign:

                Intent serviceIntent=new Intent(AppApplication.getApp(), SignActivity.class);
                long timestamp=System.currentTimeMillis();
                String filename2=timestamp+".png";
                singleStr(this.json,"au", "service");
                singleStr(this.json,"other_eng_id", HandlerFinal.userId);
                singleStr(this.json,"filename",filename2);
                singleStr(this.json,"timestamp",timestamp+"");
                try {
                    singleStr(this.json,"reason",this.json.getString("fix_suggest"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                serviceIntent.putExtra("str",filename2);
                startActivity(serviceIntent);

                break;

            case R.id.device_s_custom_sign:
                break;

                    default:break;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            return null;
        }

        public void doit(ListNode l1, ListNode l2){

        }
    }



      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

}
