package com.zjdt.dtsjwb.Util;

import com.zjdt.dtsjwb.Bean.RollBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 这个是纯粹的数组json 不是能够适应所有的json格式  形参就是注入 而
 * Created by 71568 on 2018/4/8.
 */

public class JsonUtil <T>{
    private ArrayList<T> list;

    public ArrayList<T> getList() {
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
            return new JsonUtil();
        }else {
            return ju;
        }
    }

    public  void parseJson(String data){
        if(list!=null){
            list.clear();
        }
        list=new ArrayList<>();
        try {
            JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++){
                JSONObject jb=ja.getJSONObject(i);
                RollBean rb= new RollBean(jb.getString("doctor_pic"));
                list.add((T) rb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
