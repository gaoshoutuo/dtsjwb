package com.zjdt.dtsjwb.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjdt.dtsjwb.Bean.AllBean;
import com.zjdt.dtsjwb.Bean.DeviceBean;

import java.util.HashMap;

public class SQLUtil {
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

    /**
     * 封装函数 起码要知道要干嘛
     * @param dbname
     * @param tablename
     * @param values
     * @param versionId
     * @return
     */
    public static String insertTable(String dbname,String tablename, ContentValues values,int versionId){
       long is=DatabaseUtil.getDatabase(dbname,versionId).insert("tablename",null,values);
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

    public static String dropTable(String dbname,String tablename,int versionId,String[]str){
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

}
