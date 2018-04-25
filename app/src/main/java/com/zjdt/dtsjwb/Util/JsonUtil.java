package com.zjdt.dtsjwb.Util;

import com.zjdt.dtsjwb.Bean.Password;
import com.zjdt.dtsjwb.Bean.RollBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ObjectInputValidation;
import java.util.ArrayList;

/**
 * 这个是纯粹的数组json 不是能够适应所有的json格式  形参就是注入 而
 * Created by 71568 on 2018/4/8.
 */

public class JsonUtil {
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
     * 不仅仅需要解析json  更加需要 生成 做json
     */
}
