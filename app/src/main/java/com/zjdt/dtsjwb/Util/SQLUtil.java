package com.zjdt.dtsjwb.Util;

import java.util.HashMap;

public class SQLUtil {
    //专门用来存储sql语句的

    public static final String TABLE_WAIT="123";

    /**
     * sql 缺陷 类型没法通过值 string来指定 应该设计某种数据类型与数据库的数据类型对应的
     *   private String deviceName;
     private int deviceId;
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
     * iterator 版本稍后       Arraylist <XXS>  xxs.name xxs.typeSQL
     */

}
