package com.zjdt.dtsjwb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class UpsInsFragment extends Fragment implements View.OnClickListener{
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

    /**
     * init4修改
     * activity修改
     * 添加xmltoArray 判断
     * edit 加入非null
     * edittext 黑字修改
     * json导出
     * @param layoutId
     */

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(layoutId,container,false);
        if(json==null)
        initJson();
        switch (layoutId){
            case R.layout.ups_inspection_report_head:
                initUpsInsHeadView();
                break;

            case R.layout.ups_inspection_report_body:
                initUpsInsBodyView();
                break;
                default:break;
        }
        return view;
    }
    //util

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

    public void initUpsFixHead(int includeId,String type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type}));//initupstest_text
    }

    //head
    public void initUpsInsHeadView(){
        if(xmlstr==null) {
            initXmlStr("vps_inspection_report.xml");
         /*   data = ParseXml.parseAndArray(xmlstr, new String[]{
                    "001", "002", "003", "004",
                    "005", "006", "007", "008",
                    "009", "010", "888", "888",
                    "011", "012", "013", "888",
                    *//*"014", "015", "016", "017",
                    "018", "019", "020", "021",
                    "022", "023", "024", "025",
                    "222", "223", "024", "025",
                    "026", "027", "028", "029",
                    "430", "431", "432", "035",
                    "530", "531", "532", "035",
                    "630", "631", "632", "035",
                    "730", "731", "732", "888",
                    "830", "831", "832", "888",
                    "036", "037", "038", "039",
                    "040", "041", "042", "043"*//*
            });*/
            data = ParseXml.parseAndArray2(xmlstr);
        }
      /*  //客户资料  可行性调查非常重要
        initUpsFixHead(R.id.fix_inspection_1,"100");
        init4View(R.id.fix_inspection_2,new String[]{"001","002","003","004"});

      //产品信息
        initUpsFixHead(R.id.fix_inspection_3,"200");
        init4View(R.id.fix_inspection_4,initReArr(new String[]{"005","006","007","008"}));
        init4View(R.id.fix_inspection_5,initReArr(new String[]{"009","010","888","888"}));

        //电池参数
        initUpsFixHead(R.id.fix_inspection_6,"300");
        init4View(R.id.fix_inspection_7,initReArr(new String[]{"011","012","013","888"}));
        init4View(R.id.fix_inspection_8,initReArr(new String[]{"014","015","016","017"}));
        init4View(R.id.fix_inspection_9,initReArr(new String[]{"018","019","020","021"}));

        //电气环境参数
        initUpsFixHead(R.id.fix_inspection_10,"400");
        init4View(R.id.fix_inspection_11,initReArr(new String[]{"022","023","024","025"}));
        init4View(R.id.fix_inspection_12,initReArr(new String[]{"222","223","024","025"}));
        init4View(R.id.fix_inspection_13,initReArr(new String[]{"026","027","028","029"}));

        //运行参数
        initUpsFixHead(R.id.fix_inspection_14,"500");
        init4View(R.id.fix_inspection_15,initReArr(new String[]{"430","431","432","035"}));
        init4View(R.id.fix_inspection_16,initReArr(new String[]{"530","531","532","035"}));
        init4View(R.id.fix_inspection_17,initReArr(new String[]{"630","631","632","035"}));
        init4View(R.id.fix_inspection_17_5,initReArr(new String[]{"730","731","732","888"}));
        init4View(R.id.fix_inspection_17_6,initReArr(new String[]{"830","831","832","888"}));

        //功能测试
        initUpsFixHead(R.id.fix_inspection_18,"600");
        init4View(R.id.fix_inspection_19,initReArr(new String[]{"036","037","038","039"}));
        init4View(R.id.fix_inspection_20,initReArr(new String[]{"040","041","042","043"}));*/

        //客户资料  可行性调查非常重要
        initUpsFixHead(R.id.fix_inspection_1,"100");
        init4View(R.id.fix_inspection_2,new String[]{data[1],data[2],data[3],data[4]});

        //产品信息
        initUpsFixHead(R.id.fix_inspection_3,"200");
        init4View(R.id.fix_inspection_4,new String[]{data[5],data[6],data[7],data[8]});
        init4View(R.id.fix_inspection_5,new String[]{data[9],data[10],data[888],data[888]});

        //电池参数
        initUpsFixHead(R.id.fix_inspection_6,"300");
        init4View(R.id.fix_inspection_7,new String[]{data[11],data[12],data[13],data[888]});
        init4View(R.id.fix_inspection_8,new String[]{data[14],data[15],data[16],data[17]});
        init4View(R.id.fix_inspection_9,new String[]{data[18],data[19],data[20],data[21]});

        //电气环境参数
        initUpsFixHead(R.id.fix_inspection_10,"400");
        init4View(R.id.fix_inspection_11,new String[]{data[22],data[23],data[24],data[25]});
        init4View(R.id.fix_inspection_12,new String[]{data[222],data[223],data[24],data[25]});
        init4View(R.id.fix_inspection_13,new String[]{data[26],data[27],data[28],data[29]});

        //运行参数
        initUpsFixHead(R.id.fix_inspection_14,"500");
        init4View(R.id.fix_inspection_15,new String[]{data[430],data[431],data[432],data[35]});
        init4View(R.id.fix_inspection_16,new String[]{data[530],data[531],data[532],data[35]});
        init4View(R.id.fix_inspection_17,new String[]{data[630],data[631],data[632],data[35]});
        init4View(R.id.fix_inspection_17_5,new String[]{data[730],data[731],data[732],data[888]});
        init4View(R.id.fix_inspection_17_6,new String[]{data[830],data[831],data[832],data[888]});

        //功能测试
        initUpsFixHead(R.id.fix_inspection_18,"600");
        init4View(R.id.fix_inspection_19,new String[]{data[36],data[37],data[38],data[39]});
        init4View(R.id.fix_inspection_20,new String[]{data[40],data[41],data[42],data[43]});


    }


    //body
    public void initUpsInsBodyView(){
        //果然




        //硬件检测
        Button button1=view.findViewById(R.id.ups_ins_engineer_sign);
        Button button2=view.findViewById(R.id.ups_ins_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    //init4view
    public void init4View(int includeId,String []type){
        View view1= getIncludeView(view, includeId);
        setViewEdit(view1,R.id.inspection_1,type[0]);
        setViewEdit(view1,R.id.inspection_2,type[1]);
        setViewEdit(view1,R.id.inspection_3,type[2]);
        setViewEdit(view1,R.id.inspection_4,type[3]);
    }







    private void initJson(){

        json=new JSONObject();
        try {
            json.put("au","ups_ins");
            json.put("step",-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString()+"";
    }//这样就不为空了

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

    private void initHeadJson(String type,int includeId){//str1  对于非重复字段 str 给他取消
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.ins_8_1);
        String str1= getEditData(text1);

        EditText text2= view1.findViewById(R.id.ins_8_2);
        String str2= getEditData(text2);

        EditText text3= view1.findViewById(R.id.ins_8_3);
        String str3= getEditData(text3);

        EditText text4= view1.findViewById(R.id.ins_8_4);
        String str4= getEditData(text4);

        EditText text5= view1.findViewById(R.id.ins_8_5);
        String str5= getEditData(text5);

        EditText text6= view1.findViewById(R.id.ins_8_6);
        String str6= getEditData(text6);

        EditText text7= view1.findViewById(R.id.ins_8_7);
        String str7= getEditData(text7);

        EditText text8= view1.findViewById(R.id.ins_8_8);
        String str8= getEditData(text8);

        singleJson(this.json,type,jsonObjMake(new String[]{str1,str2,str3,str4,str5,str6,str7,str8}));
    }

    /**
     * {
     "cus_data":{"custom_name":"123","custom_location":"123","custom_contacts":"123","phone_num":"123"},
     "product_info_1":{"brand":"123","type":"123","power":"123","pattern":"123"},
     "product_info_2":{"bar_code":"123","word_way":"123","kong":"123","kong":"123"},
     "ups_para_1":{"ups_brand":"123","ups_type":"123","ups_num":"123","kong":"123"},
     "ups_para_2":{"ups_group_num":"123","vol":"123","cha_vol":"123","cha_ec":"123"},
     "ups_para_3":{"vps_id":"123","vps_ustime":"123","need_ex":"123","vps_status":"四条数据正常无漏液发热..."},
     "electric_1":{"in_air":"123","in_cabie":"123","zero_vol":"123","conform":"123"},
     "electric_2":{"out_air":"123","out_cabie":"123","zero_vol":"123","conform":"123"},
     "electric_3":{"idc_tem":"123","idc_hum":"123","idc_clean":"123","conform":"123"},
     "run_para_1":{"in_vol_a":"123","in_vol_b":"123","in_vol_c":"123","hz":"123"},
     "run_para_2":{"near_vol_a":"123","near_vol_a":"123","near_vol_a":"123","hz":"123"},
     "run_para_3":{"out_vol_a":"123","out_vol_b":"123","out_vol_c":"123","hz":"123"},
     "run_para_4":{"in_ec_a":"123","in_ec_b":"123","in_ec_c":"123","kong":"123"},
     "run_para_5":{"out_ec_a":"123","out_ec_b":"123","out_ec_c":"123","kong":"123"},
     "feature_test_1":{"panel":"123","record":"123","inverse":"123","near":"123"},
     "feature_test_2":{"e_inverse":"123","emergency":"123","fix_near":"123","warning":"123"},
     "hard_test":{"hard1":"123","hard2":"123","hard3":"123","hard4":"123"},
     "hard_test":{"hard5":"123","hard6":"123","hard7":"123","hard8":"123"},
     "other":{"fix_result":"123","user_opinion":"123","my_sign":"123","custom_sign":"123","firm":"123","date":"123"}
     }

     这种无头json 挺费解的
     */


    public void makeInsHeadJson(){

        initHeadJson("cus_data",new String[]{"custom_name","custom_location","custom_contacts","phone_num"},R.id.fix_inspection_2);

        initHeadJson("product_info_1",new String[]{"brand","type","power","pattern"},R.id.fix_inspection_4);
        initHeadJson("product_info_2",new String[]{"bar_code","word_way","kong","kong"},R.id.fix_inspection_5);

        initHeadJson("ups_para_1",new String[]{"ups_brand","ups_type","ups_num","kong"},R.id.fix_inspection_7);
        initHeadJson("ups_para_2",new String[]{"ups_group_num","vol","cha_vol","cha_ec"},R.id.fix_inspection_8);
        initHeadJson("ups_para_3",new String[]{"vps_id","vps_ustime","need_ex","vps_status"},R.id.fix_inspection_9);

        initHeadJson("electric_1",new String[]{"in_air","in_cabie","zero_vol","conform"},R.id.fix_inspection_11);
        initHeadJson("electric_2",new String[]{"out_air","out_cabie","zero_vol","conform"},R.id.fix_inspection_12);
        initHeadJson("electric_3",new String[]{"idc_tem","idc_hum","idc_clean","conform"},R.id.fix_inspection_13);

        initHeadJson("run_para_1",new String[]{"in_vol_a","in_vol_b","in_vol_c","hz"},R.id.fix_inspection_15);
        initHeadJson("run_para_2",new String[]{"near_vol_a","near_vol_b","near_vol_c","hz"},R.id.fix_inspection_16);
        initHeadJson("run_para_3",new String[]{"out_vol_a","out_vol_b","out_vol_c","hz"},R.id.fix_inspection_17);
        initHeadJson("run_para_4",new String[]{"in_ec_a","in_ec_b","in_ec_c","kong"},R.id.fix_inspection_17_5);
        initHeadJson("run_para_5",new String[]{"out_ec_a","out_ec_b","out_ec_c","kong"},R.id.fix_inspection_17_6);

        initHeadJson("feature_test_1",new String[]{"panel","record","inverse","near"},R.id.fix_inspection_19);
        initHeadJson("feature_test_2",new String[]{"e_inverse","emergency","fix_near","warning"},R.id.fix_inspection_20);

    }

    public void makeInsBodyJson(){
        initHeadJson("hard_test",R.id.fix_inspection_body1);
        EditText text= view.findViewById(R.id.ups_foot_inspection);

        //还有 什么时间啊 这些 在商榷一下
        try {
            this.json.put("to_sum_up",text.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void makeInsFootJson(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_ins_engineer_sign://ok
                Intent insIntent=new Intent(AppApplication.getApp(), SignActivity.class);
                long timestamp=System.currentTimeMillis();
                String filename=timestamp+".png";
                singleStr(this.json,"other_eng_id", HandlerFinal.userId);
                singleStr(this.json,"filename",filename);
                singleStr(this.json,"timestamp",timestamp+"");
                try {
                    singleStr(this.json,"reason",this.json.getString("fix_suggest"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    insIntent.putExtra("bussiness_type",this.json.getString("au"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                insIntent.putExtra("str",filename);
                startActivity(insIntent);


                break;

            case R.id.ups_ins_custom_sign:
                break;
                default:break;

        }

    }

    //
}
