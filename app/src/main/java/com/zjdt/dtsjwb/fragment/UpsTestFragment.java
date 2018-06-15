package com.zjdt.dtsjwb.fragment;

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

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.JsonUtil;
import com.zjdt.dtsjwb.Util.ParseXml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UpsTestFragment extends Fragment implements View.OnClickListener{//upstest
    private static JSONObject json;
    private View view;
    private int rid;
    private static String xmlstr;
    private static String[]data;
    private int numGroup,presentNum;
    public void setRid(int rid) {
        this.rid = rid;
    }
    public void setNumGroup(int numGroup) {
        this.numGroup = numGroup;
    }

    public void setPresentNum(int presentNum) {
        this.presentNum = presentNum;
    }

    public static String getJsonStr() {
        return json.toString();
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
        if (json==null)
            initJson();

        view=inflater.inflate(rid,container,false);
        //initview 环节
        switch (rid){
            case R.layout.ups_test_report_head:
                initUpsTestHeadView();
                break;

            case R.layout.ups_test_report_body:
                initUpsTestBodyView();
                break;

            case R.layout.ups_test_report_foot:
                initUpsTestFootView();
                break;

                default:break;
        }
        return view;
    }

    /**
     * xmlstr 的init
     */
    public void initXmlStr(String filename){
        xmlstr= ParseXml.getFileString(getResources(), filename);
    }

    public String initReStr(String[]linkstr){
        return ParseXml.parseXMLWithPullArray(xmlstr,linkstr);
    }
    public String[] initReArr(String[]linkstr){
        return ParseXml.parseAndArray(xmlstr,linkstr);
    }

   /* public void initUpsTestHead(int includeId,String []type){
        //initXmlStr("vps_test_report.xml");
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1,R.id.d_name,initReStr(
                        new String[]{type[0],type[1],type[2]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[0]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head2,initReStr(
                        new String[]{type[1]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head3,initReStr(
                        new String[]{type[2]}));//initupstest_text
    }*/

    public void initUpsTestHead(int includeId,String []type){
        //initXmlStr("vps_test_report.xml");
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1,R.id.d_name,type[0]+type[1]+type[2]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,type[0]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head2,type[1]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head3,type[2]);//initupstest_text
    }



    //ups电池检测头
     private void initUpsTestHeadView(){
        //view.findViewById(R.id)

       /* View view1= getIncludeView(view, R.id.ups_head_1);
         setViewText(
          view1,R.id.d_name,initReStr(
           new String[]{"001","002","003"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"001"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"002"}));//initupstest_text

         setViewEdit(
          view1,R.id.ups_test_head1,initReStr(
           new String[]{"003"}));//initupstest_text*/

         if(xmlstr==null) {
             initXmlStr("vps_test_report.xml");
             data = ParseXml.parseAndArray2(xmlstr);
         }
         initUpsTestHead(R.id.ups_head_1,new String[]{data[1],data[2],data[3]});
         initUpsTestHead(R.id.ups_head_2,new String[]{data[4],data[5],data[6]});
         initUpsTestHead(R.id.ups_head_3,new String[]{data[7],data[8],data[10]});
         initUpsTestHead(R.id.ups_head_4,new String[]{data[7],data[8],data[11]});
         initUpsTestHead(R.id.ups_head_5,new String[]{data[7],data[9],data[10]});
         initUpsTestHead(R.id.ups_head_6,new String[]{data[7],data[9],data[11]});
    }

    public void initUpsTestBody(int includeId,int group){
        View view1=view.findViewById(includeId);

        setViewText(view1,R.id.ups_body_h,initReStr(
                new String[]{"012"})+group+"#");

    }

    public void initUpsTestBodyView(){

            initUpsTestBody(R.id.ups_body_1,1+presentNum);
            initUpsTestBody(R.id.ups_body_2,2+presentNum);
            initUpsTestBody(R.id.ups_body_3,3+presentNum);
            initUpsTestBody(R.id.ups_body_4,4+presentNum);
            initUpsTestBody(R.id.ups_body_5,5+presentNum);
            initUpsTestBody(R.id.ups_body_6,6+presentNum);
            initUpsTestBody(R.id.ups_body_7,7+presentNum);
            initUpsTestBody(R.id.ups_body_8,8+presentNum);
            initUpsTestBody(R.id.ups_body_9,9+presentNum);
            initUpsTestBody(R.id.ups_body_10,10+presentNum);

    }


  /*  //ups电池检测体

    private void initUpsTestBodyView(){

    }

    //ups电池检测尾

    private void initUpsTestFootView(){

    }*/




    private void initUpsTestFootView(){
       Button button1= view.findViewById(R.id.ups_test_engineer_sign);
        Button button2= view.findViewById(R.id.ups_test_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    //ups电池现场维修


    //ups巡检


    //工具还得重新写

    /**
     * 传入 含有include id 的view
     * @param view
     * @param rid
     * @return
     */
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_test_custom_sign://ups测试工程师签名
                break;
            case R.id.ups_test_engineer_sign://ups测试提醒用户签名
                break;
                default:break;
        }
    }
  /*  private View f(View view,int rid){
        return view.findViewById(rid);
    }
*/
   //private void init



    public void PieceJsonArray(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("ups_brand","123");
            jsonObject.put("ups_type","123");
            jsonObject.put("ups_cap","123");
            jsonObject.put("ups_vol","123");
            jsonObject.put("ups_ec","123");
            jsonObject.put("ups_status","123");

            JSONObject upsTcha=new JSONObject();
            upsTcha.put("str1","123");
            upsTcha.put("str1","123");
            upsTcha.put("str1","123");
            jsonObject.put("ups_t_charging",upsTcha);

            JSONObject upsEccha=new JSONObject();
            upsEccha.put("str1","123");
            upsEccha.put("str1","123");
            upsEccha.put("str1","123");
            jsonObject.put("ups_t_charging",upsEccha);

            JSONObject upscha=new JSONObject();
            upscha.put("str1","123");
            upscha.put("str1","123");
            upscha.put("str1","123");
            jsonObject.put("ups_t_charging",upscha);

            JSONObject upsDischa=new JSONObject();
            upsDischa.put("str1","123");
            upsDischa.put("str1","123");
            upsDischa.put("str1","123");
            jsonObject.put("ups_t_charging",upsDischa);

            jsonObject.put("to_sum_up","123");
            jsonObject.put("test_firm","123");
            jsonObject.put("custom_sign","123");
            jsonObject.put("engineer","123");
            jsonObject.put("test_time","123");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拆分
     * 仅仅插入json单条字符串的情况
     * 仅仅插入json单个json对象的情况
     * json 也分前中后的
     */
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
                jsonObject.put("str"+i,edit[i]);//完美  字符串怎么就不行呢
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString()+"";
    }

    private void initHeadJson(String []type,int includeId){
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.ups_test_head1);
        String str1= getEditData(text1);
        singleStr(this.json,type[0],str1);

        EditText text2= view1.findViewById(R.id.ups_test_head2);
        String str2= getEditData(text2);
        singleStr(this.json,type[1],str2);

        EditText text3= view1.findViewById(R.id.ups_test_head3);
        String str3= getEditData(text3);
        singleStr(this.json,type[2],str3);
    }

    private void initHeadJson(String type,int includeId){
        View view1= getIncludeView(view, includeId);
        EditText text1= view1.findViewById(R.id.ups_test_head1);
        String str1= getEditData(text1);

        EditText text2= view1.findViewById(R.id.ups_test_head2);
        String str2= getEditData(text2);

        EditText text3= view1.findViewById(R.id.ups_test_head3);
        String str3= getEditData(text3);

        singleJson(this.json,type,jsonObjMake(new String[]{str1,str2,str3}));
    }

    private JSONObject initBodyJson(int includeId){
        JSONObject jsonObject=null;
        try {
            View view1 =view.findViewById(includeId);
            EditText text1= view1.findViewById(R.id.ups_group_ir);//内阻
            String str1= getEditData(text1);
            // singleStr("internal_resist",str1);
            jsonObject=new JSONObject();
            jsonObject.put("internal_resist",str1);

            EditText text2= view1.findViewById(R.id.ups_group_unchar_vol1);
            String str2= getEditData(text2);

            EditText text3= view1.findViewById(R.id.ups_group_unchar_vol2);
            String str3= getEditData(text3);

            EditText text4= view1.findViewById(R.id.ups_group_unchar_vol3);
            String str4= getEditData(text4);
            singleJson(jsonObject,"ups_char_vol",jsonObjMake(new String[]{str2,str3,str4}));


            EditText text5= view1.findViewById(R.id.ups_group_ec_vol1);
            String str5= getEditData(text5);

            EditText text6= view1.findViewById(R.id.ups_group_ec_vol2);
            String str6= getEditData(text6);

            EditText text7= view1.findViewById(R.id.ups_group_ec_vol3);
            String str7= getEditData(text7);
            singleJson(jsonObject,"ups_dischar_vol",jsonObjMake(new String[]{str5,str6,str7}));

        } catch (JSONException e) {
            e.printStackTrace();
        }
            return jsonObject;

    }

    /**
     *
     {
     "ups_brand":"123","ups_type":"123","ups_cap":"123","ups_vol":"123","ups_ec":"123","ups_status":"123",
     "ups_t_charging":{"str1":"123","str2":"123","str3":"123"},"ups_ec_charging":{"str1":"123","str2":"123","str3":"123"},
     "ups_t_discha":{"str1":"123","str2":"123","str3":"123"},"ups_ec_charging":{"str1":"123","str2":"123","str3":"123"},
     "to_sum_up":"123","test_firm":"123","custom_sign":"123","engineer":"123","test_time":"123",
     "array":[{"internal_resist":"123","ups_char_vol":{"str1":"123","str2":"123","str3":"123"},"ups_dischar_vol":{"str1":"123","str2":"123","str3":"123"}},
     {"internal_resist":"123","ups_char_vol":{"str1":"123","str2":"123","str3":"123"},"ups_dischar_vol":{"str1":"123","str2":"123","str3":"123"}}
     ],
     "to_sum_up":"123"
     }


     {"ups_brand":"","ups_type":"","ups_cap":"","ups_vol":"","ups_ec":"","ups_status":"","ups_t_charging":{"str0":"","str1":"","str2":""},
     "ups_t_discha":{"str0":"","str1":"","str2":""},"ups_ec_discha":{"str0":"","str1":"","str2":""},
     "array":[{"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}},
     {"internal_resist":"","ups_char_vol":{"str0":"","str1":"","str2":""},"ups_dischar_vol":{"str0":"","str1":"","str2":""}}],
     "to_sum_up":"","my_sign":"123","cus_sign":"123"}

     */

    public void makeJsonHead(){//虽然结构三三四四 但是头名必须有 不然解析麻烦

        //电池 品牌 型号 容量
        initHeadJson(new String[]{"ups_brand","ups_type","ups_cap"},R.id.ups_head_1);

        //电池 电压 充电状态 充电电流
        initHeadJson(new String[]{"ups_vol","ups_ec","ups_status"},R.id.ups_head_2);

        //电池充电时间
        initHeadJson("ups_t_charging",R.id.ups_head_3);

        //电池充电电流
        initHeadJson("ups_ec_charging",R.id.ups_head_4);

        //电池放电时间
        initHeadJson("ups_t_discha",R.id.ups_head_3);

        //电池放电电流
        initHeadJson("ups_ec_discha",R.id.ups_head_4);


       // singleJson("ups_t_charging",jsonObjMake(new String[]{}));


        //犬牙交错 无去耦合的数据结构
    }

    public void makeJsonBody(){//照理说应该用fragment 的
        JSONArray jsonArray=new JSONArray();
        jsonArray.put(initBodyJson(R.id.ups_body_1));//总共10个
        //initBodyJson(1);
        jsonArray.put(initBodyJson(R.id.ups_body_2));

        jsonArray.put(initBodyJson(R.id.ups_body_3));

        jsonArray.put(initBodyJson(R.id.ups_body_4));

        jsonArray.put(initBodyJson(R.id.ups_body_5));

        jsonArray.put(initBodyJson(R.id.ups_body_6));

        jsonArray.put(initBodyJson(R.id.ups_body_7));

        jsonArray.put(initBodyJson(R.id.ups_body_8));

        jsonArray.put(initBodyJson(R.id.ups_body_9));

        jsonArray.put(initBodyJson(R.id.ups_body_10));


        try {
            this.json.put("array",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void makeJsonFoot(){
        EditText text= view.findViewById(R.id.ups_foot_sum);
        String sum= getEditData(text);
        singleStr(json,"to_sum_up",sum);

        singleStr(this.json,"my_sign","123");
        singleStr(this.json,"cus_sign","123");
    }



}
