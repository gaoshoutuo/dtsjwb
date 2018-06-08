package com.zjdt.dtsjwb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjdt.dtsjwb.R;
import com.zjdt.dtsjwb.Util.ParseXml;

import org.json.JSONException;
import org.json.JSONObject;

public class UpsFixFragment extends Fragment implements View.OnClickListener{
    private int viewId;
    private View view;
    private static String xmlstr;
    private static JSONObject json;
    private static String[]data;

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
    public static String getJsonStr() {
        return json.toString();
    }

    /**
     * 写了=蠢代码 前后一致性不够  目前代码行数应该超过10w行了吧
     * 1 view frame button initview
     * 2 head body foot setid
     * 3 click
     * 4 fragmentTransaction
     * 5 修改 json static 及获取方式
     */

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
        view =inflater.inflate(viewId,container,false);
        switch (viewId){
            case R.layout.ups_fix_report_head:
                initUpsFixHeadView();
                break;
            case R.layout.ups_fix_report_body:
                initUpsFixBodyView();
                break;
            case R.layout.ups_fix_report_foot:
                initUpsFixFootView();
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

   /* public void initUpsFixHead(int includeId,String []type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,initReStr(
                        new String[]{type[0],type[1],type[2]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[0]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[1]}));//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,initReStr(
                        new String[]{type[2]}));//initupstest_text
    }

    public void initUpsFixbody(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.ups_fix_four_text,initReStr(
                        new String[]{type}));//initupstest_text

    }
    */

    public void initUpsFixHead(int includeId,String []type){

        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.d_name,type[0]+type[1]+type[2]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,type[0]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,type[1]);//initupstest_text

        setViewEdit(
                view1,R.id.ups_test_head1,type[2]);//initupstest_text
    }

    public void initUpsFixbody(int includeId,String type){
        View view1= getIncludeView(view, includeId);
        setViewText(
                view1, R.id.ups_fix_four_text,type);//initupstest_text

    }


    //head
    public void initUpsFixHeadView(){
        if(xmlstr==null) {
            initXmlStr("vps_fix_report.xml");
            data = ParseXml.parseAndArray2(xmlstr);
        }

        initUpsFixHead(R.id.ups_fix_head1,new String[]{data[2],data[3],data[4]});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{data[5],data[6],data[7]});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{data[8],data[9],data[88]});
        initUpsFixHead(R.id.ups_fix_head2,new String[]{data[10],data[11],data[12]});
    }


    //body

    public void initUpsFixBodyView(){
        initUpsFixbody(R.id.ups_fix_body1,data[14]);
        initUpsFixbody(R.id.ups_fix_body2,data[13]);
        initUpsFixbody(R.id.ups_fix_body3,data[15]);
        initUpsFixbody(R.id.ups_fix_body4,data[16]);
        View view1= getIncludeView(view, R.id.ups_fix_body5);
    }


    //foot
    public void initUpsFixFootView(){
        Button button1=view.findViewById(R.id.ups_fix_engineer_sign);
        Button button2=view.findViewById(R.id.ups_fix_custom_sign);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    //cost
    public void initCostView(View view){
       // view.findViewById()
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
                jsonObject.put("str"+i,edit[i]);//完美  字符串怎么就不行呢
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

    public String getEditData(EditText text){//获取 editView 中的文字信息
        return text.getText().toString();
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


    private void initBodyJson(int includeId,String type){
        View view1= getIncludeView(view,includeId);
        EditText text= view1.findViewById(R.id.ups_fix_error_phon);
        String str=getEditData(text);
        singleStr(this.json,type,str);
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
                new String[]{str1,str2,str3,str4,str5,str6,str7,str8,});
        return jsonObject;
    }

    /**
     * {
     "custom_name":"123","contacts":"123","phone_number":"123","location":"123","device_brand":"123","device_t":"123",
     "device_power":"123","device_id":"123","device_work_pattern":"123","error_time":"123","fix_time":"123","fix_reason":"123",
     "error_phon":"123","error_analysis":"123","handle_error":"123","fix_reason":"123","fix_result":"123","fix_suggest":"123",
     "custom_opinion":"123","my_sign":"123","cus_sign":"123","fix_firm":"123","fix_time":"123","cost":
     {"Maintenance":"123","warr_inner":"132","warr_out":"123","labor":"","materal":"123","travel":"143","transport":"24","sum_cost":"133"}
     }
     */



    public void makeHeadJson(){//这里注意了 修改过

        EditText text=view.findViewById(R.id.cus_forget_name);
        String forgetname=getEditData(text);
        singleStr(this.json,"cus_name",forgetname);
        initHeadJson(new String[]{"contacts","phone_number","location"},R.id.ups_fix_head1);
        initHeadJson(new String[]{"device_brand","device_t","device_power"},R.id.ups_fix_head2);
        initHeadJson(new String[]{"device_id","device_work_pattern","kong"},R.id.ups_fix_head3);
        initHeadJson(new String[]{"error_time","fix_time","fix_location"},R.id.ups_fix_head4);
    }

    public void makeBodyJson(){
        initBodyJson(R.id.ups_fix_body1,"error_phon");
        initBodyJson(R.id.ups_fix_body2,"error_analysis");
        initBodyJson(R.id.ups_fix_body3,"handle_error");
        initBodyJson(R.id.ups_fix_body4,"fix_reason");
        singleJson(this.json,"cost",initCostJson(R.id.ups_fix_body5));
    }

    public void makeFootJson(){//除了这些 还应该有签名文件名 时间戳 能不能先生成好在update呀  可以吧
       EditText text= view.findViewById(R.id.ups_foot_sugg);
       String str=getEditData(text);
       singleStr(this.json,"fix_suggest",str);
        singleStr(this.json,"my_sign","123");
        singleStr(this.json,"cus_sign","123");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ups_fix_engineer_sign:

                break;
            case R.id.ups_fix_custom_sign:

                break;

                default:break;
        }
    }
}
