package com.zjdt.dtsjwb.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjdt.dtsjwb.Bean.AllBean;
import com.zjdt.dtsjwb.Bean.DeviceBean;
import com.zjdt.dtsjwb.Bean.TableEntity.OfflineEntity;
import com.zjdt.dtsjwb.Bean.TableInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLUtil {

    private static SQLUtil sqlUtil;

    public static SQLUtil getInstance(){
        if (sqlUtil==null)sqlUtil=new SQLUtil();
        return sqlUtil;
    }

    //专门用来存储sql语句的

    public static final String TABLE_WAIT="123";

    /**
     * sql 缺陷 类型没法通过值 string来指定 应该设计某种数据类型与数据库的数据类型对应的
     *   private String deviceName;
     private int deviceId;{"精密空调","机柜","供电pdu","ups电池","服务器IT设备","定期检修","正常维保巡检"} {1 2 3 4 5 6 7}
     private String coustomerId;
     private String location;
     private String reason;
     * @param
     * @return
     */

    public static String createTable(String[]s){
        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(s[0]).
                append("(").
                append(s[1]).append(" INTEGER PRIMARY KEY AUTOINCREMENT,").
                append(s[2]).append(" TEXT NOT NULL,").
                append(s[3]).append(" INTEGER DEFAULT 0 NOT NULL,").
                append(s[4]).append(" TEXT NOT NULL,").
                append(s[5]).append(" TEXT NOT NULL,").
                append(s[6]).append(" TEXT NOT NULL").
                append(");").toString();
        return sb.toString();
    }

    // public static final String []DTSJOFFLINEMSG={"dtsjofflinemsg","id","timerecord","json_1","json_2","idc_id","idc_name","idc_type","idc_location"
    //            ,"user_id","eng_id","bussiness_type","iswatch","eng_name","blank_1","blank_2","blank_3"};

    public static String createTableOfflineMsg(String[] s){
        StringBuilder sb=new StringBuilder();
        String sql=sb.append("CREATE TABLE IF NOT EXISTS ").append(s[0]).
                append("(").
                append(s[1]).append(" INTEGER PRIMARY KEY AUTOINCREMENT,").

                append(s[2]).append(" TEXT,").//timerecord
                append(s[3]).append(" TEXT,").//json_1
                append(s[4]).append(" TEXT,").//json_2

                append(s[5]).append(" TEXT,").//json_2
                append(s[6]).append(" TEXT,").//json_2
                append(s[7]).append(" TEXT,").//json_2
                append(s[8]).append(" TEXT,").//json_2

                append(s[9]).append(" TEXT,").//json_2
                append(s[10]).append(" TEXT,").//json_2
                append(s[11]).append(" TEXT,").//json_2
                append(s[12]).append(" TEXT,").//json_2
                append(s[13]).append(" TEXT,").//json_2
                append(s[14]).append(" TEXT,").//json_2
                append(s[15]).append(" TEXT,").//json_2
                append(s[16]).append(" TEXT").//json_2
                append(");").toString();
        return sql;
    }

    /**
     * 封装函数 起码要知道要干嘛
     * @param dbname
     * @param tablename
     * @param values
     * @param versionId
     * @return
     */
    public static String insertTable(String dbname,String tablename, ContentValues values,int versionId){
       long is=DatabaseUtil.getDatabase(dbname,versionId).insert(tablename,null,values);
        return null;
    }

    /**
     * getvalue 跟bean合一起好了
     * @return
     */

    public DeviceBean getValue(){


        return null;
    }

    /**
     * 删除表数据 删除arraylist内存 adapterchange  以reason为
     * @param dbname
     * @param tablename
     * @param versionId
     * @return
     */

    public static String deleteTable(String dbname,String tablename,int versionId,String[]str){
        DatabaseUtil.getDatabase(dbname,versionId).delete(tablename,"reason=?",str);
        return null;
    }

    /**
     * 我想做的泛化本质就是自动寻找匹配关系，但是hashmap类型擦除会很麻烦 不知道java10 var语法能否助我  反射应该也行的把
     * 3种方法获取class对象      类对象之间转换
     * 编程考试总会问你获取xx对象有几种方式 割裂了怎么去设计程序 为了考而考
     * 要不就在类里面 肯定初始化的时候指定或者object我猜的  要不就是在方法里面给T设置范围
     * @param cursor
     * @return
     */
    public <T extends AllBean>T queryTable(Cursor cursor,HashMap<String,Class<T>>map,String []str) throws InstantiationException {
        for(int i=0;i<str.length;i++){
            //回头试试我这种奇技
        }
        Class classY=map.get("fh");
        T able=null;
        try {
            //而且一定要有无参构造器
            able = (T) classY.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return able;
    }

    /**
     * iterator 版本稍后       Arraylist <XXS>  xxs.name xxs.typeSQL
     */

// public static final String []DTSJOFFLINEMSG={"dtsjofflinemsg","id","timerecord","json_1","json_2","idc_id","idc_name","idc_type","idc_location"
//            ,"user_id","eng_id","bussiness_type","iswatch","eng_name","blank_1","blank_2","blank_3"};
    public class TableOffline implements TableInterface {
        public ContentValues getValue(OfflineEntity offlineEntity){
            ContentValues values=new ContentValues();
            values.put("timerecord",offlineEntity.getTimerecord());
            values.put("json_1",offlineEntity.getJson1());
            values.put("json_2",offlineEntity.getJson2());

            values.put("idc_id",offlineEntity.getIdcId());
            values.put("idc_name",offlineEntity.getIdcName());
            values.put("idc_type",offlineEntity.getIdcType());
            values.put("idc_location",offlineEntity.getIdcLocation());

            values.put("user_id",offlineEntity.getUserId());
            values.put("eng_id",offlineEntity.getEngId());
            values.put("bussiness_type",offlineEntity.getBussinessType());
            values.put("iswatch",offlineEntity.getIswatch());
            values.put("eng_name",offlineEntity.getEngName());
            values.put("blank_1",offlineEntity.getBlank1());
            values.put("blank_2",offlineEntity.getBlank2());
            values.put("blank_3",offlineEntity.getBlank3());

            return values;
        }



        public void insert(SQLiteDatabase sqLiteDatabase,ContentValues values) {
            sqLiteDatabase.insert("dtsjofflinemsg",null,values);
        }

        public void delete(SQLiteDatabase sqLiteDatabase,String value) {
            sqLiteDatabase.delete("dtsjofflinemsg","timerecord=?",new String[]{value});
        }

        public ArrayList<AllBean> query(SQLiteDatabase sqLiteDatabase) {
            //sqLiteDatabase.insert("dtsjofflinemsg",null,values);
            ArrayList<AllBean> list=new ArrayList<>();
            Cursor cursor=sqLiteDatabase.query("dtsjofflinemsg",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    String timerecord=cursor.getString(cursor.getColumnIndex("timerecord"));
                    String json1=cursor.getString(cursor.getColumnIndex("json_1"));
                    String json2=cursor.getString(cursor.getColumnIndex("json_2"));

                    String idcId=cursor.getString(cursor.getColumnIndex("idc_id"));
                    String idcName=cursor.getString(cursor.getColumnIndex("idc_name"));
                    String idcType=cursor.getString(cursor.getColumnIndex("idc_type"));
                    String idcLocation=cursor.getString(cursor.getColumnIndex("idc_location"));

                    String userId=cursor.getString(cursor.getColumnIndex("user_id"));
                    String engId=cursor.getString(cursor.getColumnIndex("eng_id"));
                    String bussinessType=cursor.getString(cursor.getColumnIndex("bussiness_type"));
                    String iswatch=cursor.getString(cursor.getColumnIndex("iswatch"));
                    String engName=cursor.getString(cursor.getColumnIndex("eng_name"));
                    String blank1=cursor.getString(cursor.getColumnIndex("blank_1"));
                    String blank2=cursor.getString(cursor.getColumnIndex("blank_2"));
                    String blank3=cursor.getString(cursor.getColumnIndex("blank_3"));
                    OfflineEntity ofe=new OfflineEntity(timerecord,json1,json2,idcId,idcName,idcType,idcLocation,
                            userId,engId,bussinessType,iswatch,engName,blank1,blank2,blank3);
                    list.add(ofe);

                }while (cursor.moveToNext());
            }
            cursor.close();
            return list;
        }

        public void update(SQLiteDatabase sqLiteDatabase,ContentValues values) {
            //sqLiteDatabase.insert("dtsjofflinemsg",null,values);
        }



        @Override
        public void insert() {

        }

        @Override
        public void delete() {

        }

        @Override
        public void query() {

        }

        @Override
        public void update() {

        }


    }

}
