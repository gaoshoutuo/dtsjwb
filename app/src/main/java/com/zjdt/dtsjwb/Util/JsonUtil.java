package com.zjdt.dtsjwb.Util;

import android.util.Log;

import com.google.gson.JsonObject;
import com.zjdt.dtsjwb.Bean.AssertBean;
import com.zjdt.dtsjwb.Bean.FixHistoryBean;
import com.zjdt.dtsjwb.Bean.HandlerFinal;
import com.zjdt.dtsjwb.Bean.IdcBean;
import com.zjdt.dtsjwb.Bean.InfoBean;
import com.zjdt.dtsjwb.Bean.JsonUtilBean;
import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.Bean.RollBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是纯粹的数组json 不是能够适应所有的json格式  形参就是注入 而
 * Created by 71568 on 2018/4/8.
 */

public class JsonUtil<T> {
    private ArrayList list=new ArrayList<>();;

    public ArrayList getList() {
        return list;
    }

    /**
     * String 的数组json 解析
     * @param data
     */
    public static JsonUtil ju;

    public JsonUtil() {

    }
    public static JsonUtil getInstance(){
        if(ju==null){
            return ju=new JsonUtil();
        }
            return ju;

    }

    public  void parseJson(String data,String jsonField){
        if(list!=null){
            list.clear();
        }

        try {
            JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++){

                JSONObject jb=ja.getJSONObject(i);
                RollBean rb= new RollBean(jb.getString(jsonField));
                list.add(rb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Password> parseJson2(String data,String[] jsonField){// 好了 这种大bean范型 我终于要开始思索了
         ArrayList<Password>listCustom=new ArrayList<>();
        try {
            JSONArray ja=new JSONArray(data);
                /*JSONObject jb=ja.getJSONObject(i);
                RollBean rb= new RollBean(jb.getString(jsonField));*/

             /* rb.setMarried(true);
              rb.setUsername(jb.getString(jsonField[0]));
              rb.setPassword(jb.getString(jsonField[1]));*/
             for(int i=0;i<ja.length();i++){
                 JSONObject jb=ja.getJSONObject(i);
                 Password password=new Password(jb.getString(jsonField[0]),
                         jb.getString(jsonField[1]),true,jb.getString(jsonField[2]));
                 listCustom.add(password);
             }

            return listCustom;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 不仅仅需要解析json  更加需要 生成 做json   啦啦啦我会啦  {"date":"","business":"","human":"","text":"","str5":"","filepath":""}
     */
    public static ArrayList<FixHistoryBean> parseHistory(String json){
        ArrayList<FixHistoryBean> list=new ArrayList<>();
        try {
            JSONArray ja=new JSONArray(json);
            for (int i=0;i<ja.length();i++){
                JSONObject jsonObj=ja.getJSONObject(i);
                String date=jsonObj.getString("date")+"";
                String business=jsonObj.getString("business")+"";
                String human=jsonObj.getString("human")+"";
                String text=jsonObj.getString("text")+"";
                String filepath=jsonObj.getString("filepath")+"";
                FixHistoryBean bean=new FixHistoryBean(date,business,human,text,filepath);
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<InfoBean> parseInfo(String json){//json 字符串 反复装箱 拆箱 插入数据表 挺麻烦的
        ArrayList<InfoBean> infoList=null;
        try {
            infoList=new ArrayList<>();
            JSONObject jsonObject=new JSONObject(json);
            JSONArray infoArray=jsonObject.getJSONArray("array");
            for (int i=0;i<infoArray.length();i++){//难道jsonArray 可以放其他对象吗  不会吧
                JSONObject item= infoArray.getJSONObject(i);
                String userId=item.getString("user_id");
                String company=item.getString("company");
                String infoName=item.getString("info_name");
                String infoLocation=item.getString("info_location");//其实as 这种命名方式的自动提示会有一点机器学习的成分
                InfoBean ib=new InfoBean(infoName,userId,infoLocation,company);
                infoList.add(ib);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infoList;
    }


    public static ArrayList<AssertBean> parseAssert(JSONObject json){
      
        ArrayList<AssertBean> list=new ArrayList<>();
        
        //电力子系统
        String[]esBody1=getLastArray(json,"es_body_1", HandlerFinal.FIVE_STR);//3 3 4  8 13  这些东西要 做成一个数组
        AssertBean assertBean=new AssertBean("",esBody1,"");
        list.add(assertBean);
        
        return list;
    }
    
    //从jsonobj中获取jsonobj "mon_soft_body_1":{"device_name":"","device_para":"","device_type":"","device_brand":"","device_num":""},
    private static JSONObject getobjInJson(JSONObject json,String key){
        JSONObject obj = null;
        try {
            obj=json.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //从jsonobj 里面获取数组
    private static String[] getStrArrInJson(String[]arr,JSONObject json){//
        String[] str=new String[arr.length];
        for (int i=0;i<arr.length;i++){
            try {
                str[i]=json.getString(arr[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    // 前面两个是组成部分 后面是

    private static String[] getLastArray(JSONObject json,String key,String[]arr){
        JSONObject obj=getobjInJson(json,key);
        String []str=getStrArrInJson(arr,obj);
        return str;
    }
    // 一维数组 差一点  arr[]

    public static ArrayList<AssertBean>  template(String[] arr, JSONObject json){// arr3 3 5 6 8 13  这种封装 麻烦就麻烦在  最外层要为最内层提供
        ArrayList<AssertBean> list =new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            String[]esBody1=getLastArray(json,arr[i], HandlerFinal.FIVE_STR);//3 3 4  8 13  这些东西要 做成一个数组
            AssertBean assertBean=new AssertBean("",esBody1,"");
            list.add(assertBean);
        }
        return list;
    }
    private void test(){
        String [][]arr=new String[2][100];//两行100 列
    }


    public static JSONObject makeJsonObj(JSONObject obj, JsonUtilBean jub){// 生成JsonObj
        try {
            obj.put(jub.getKey(),jub.getValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JSONObject makeJsonObj2(JSONObject obj,JsonUtilBean jub){// 生成JsonObj
        try {
            obj.put(jub.getKey(),jub.getObj());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public JSONArray makeJsonArr(List<T> list){// 先不写  json 到bean 需要xml 的配置吧  xml 这种dsl
        JSONArray ja=new JSONArray();
        for (int i=0;i<list.size();i++){

        }
        return null;
    }

    public ArrayList<IdcBean> iqrJsonParse(JSONObject json){
        ArrayList<IdcBean>list=new ArrayList<>();
        try {
            JSONArray ja= json.getJSONArray("array");
            for (int i=0;i<ja.length();i++){
                JSONObject object=ja.getJSONObject(i);
                String location=object.getString("idc_s_location");
                String idcName=object.getString("idc_name");
                String idcType=object.getString("idc_type");
                String idcId=object.getString("idc_id");
                IdcBean idcBean=new IdcBean(idcId,idcName,idcType,location);
                list.add(idcBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 算了 重新写吧{"device_name":"","device_para":"","device_type":"","device_brand":"","device_num":""},

 /*   public void parseSubtitle(JsonObject jsonObject,int type){
        switch (type){

        }
    }*/

    public ArrayList<AssertBean> parseSubtitle(JSONObject object,String []arr){
        ArrayList<AssertBean> list=new ArrayList<>();
        try {
            for (int i=0;i<arr.length;i++){
                JSONObject obj=object.getJSONObject(arr[i]);
                String deviceName=obj.getString("device_name");
                String devicePara=obj.getString("device_para");
                String deviceType=obj.getString("device_type");
                String deviceBrand=obj.getString("device_brand");
                String deviceNum=obj.getString("device_num");
                AssertBean assertBean=new AssertBean("",deviceName,devicePara,deviceType,deviceBrand,deviceNum,"");
                list.add(assertBean);
                Log.e("ttt3","ppppp");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
