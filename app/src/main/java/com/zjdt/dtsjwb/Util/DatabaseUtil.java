package com.zjdt.dtsjwb.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zjdt.dtsjwb.App.AppApplication;
import com.zjdt.dtsjwb.Bean.HandlerFinal;

public class DatabaseUtil {
    //做Sqlite3 的数据库的工具   需要有万能bean 应付传入一bean 自动开始持久化一些东西
/**
 * 面对复杂的解析仍然从容的插入值
 *
 * CREATE TABLE BOOK(
 * id integer primary key autoincrement,
 * author text,
 * price real,
 * pages integer,
 * name text
 * )
 *好像没有什么多余的东西
 *
 *
 *
 * sqlite 的5大步骤  教书就跟小时候过家家一样
 * 1新建某类继承SQLiteOpenHelper  某类重写onCreate  onUpgrade  oncreate execSql 里面填入sql 建表语句
 * 2new (this,"xxs.db",null,1); 来新建  通过该类对象 的 getWriteableDatabase()视为db; 来创建这个数据库
 * 3注意 在onUpgrade 里面 execSql drop table if exists 表名 这个时候版本也得跟着修改
 * 4增删改查环节
 *    1)增 就是db.insert(表名称，null,contentValue) 注意 value 是put进去的 数据名称应该与表民相同  如果插入多条中间记得insert之后 value的 clear
 *    2)改db,update(表名，value,"name= ? ",new String[]{xxx})
 *    3)删db.delete(表名,"page>?",new String["500"])
 *    4)查  门路可大了 cursor=db.query(表名，6个null)  cursor.moveToFirst
 *    do String name=cursor.getString(cursor.getColumnIndex("name"));
 *    while cursor.moveToNext
 * 5
 *
 *
 * 当然也可以用sql来 操作数据库不那么orm了  db.execSQL( insert into 表名 (name,author,pages,price)values(?,?,?,?),new string[]{xxx,xxx,xxx,xsx} )
 *                                       update 表名 set price=? where name=?,new String[]{xx,xx}
 *                                       delete from 表名 where page>? new String[]{"500"}
 *                                       rawQuery(select * from 表名，null)
 *
 *                                       框架 litepal  orm sqlite 框架
 *
 */
public static DatabaseUtil dbt;
public static SQLiteDatabase myDatabase;


public static DatabaseUtil getInstance(){
    if(dbt==null){
        return dbt=new DatabaseUtil();
    }
    return dbt;
}
public static SQLiteDatabase getDatabase(String databasename,int varsionId) {
   /* if(myDatabase==null){
        return myDatabase=DatabaseUtil.getInstance().new MyDatabase(
                AppApplication.getApp(),databasename,null,varsionId).getWritableDatabase();
    }return myDatabase;*/

    if(myDatabase==null){
        return myDatabase=DatabaseUtil.getInstance().new MyDatabase().getWritableDatabase();
    }return myDatabase;
}


    public static SQLiteDatabase getDatabase() {
   /* if(myDatabase==null){
        return myDatabase=DatabaseUtil.getInstance().new MyDatabase(
                AppApplication.getApp(),databasename,null,varsionId).getWritableDatabase();
    }return myDatabase;*/

        if(myDatabase==null){
            return myDatabase=DatabaseUtil.getInstance().new MyDatabase().getWritableDatabase();
        }return myDatabase;
    }

    /**
     * 喜欢用内部类 把一些本来可以直接继承的类弄复杂化了
     */
     public class MyDatabase extends SQLiteOpenHelper{

           //其实尽量做到数据结构的通用性  不要改一个地方有很多地方跟着改 这时候注入就很有效了


// 此处也要改变 但是固定为1 是否很不方便
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //factory 造对象了   尴尬的自言自语

    public MyDatabase() {
               super(AppApplication.getApp(), "DTSJ.db", null, 1);
           }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLUtil.createTable(HandlerFinal.DTSJCACHESTR));
        db.execSQL(SQLUtil.createTableOfflineMsg(HandlerFinal.DTSJOFFLINEMSG));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists dtsjcache");
        db.execSQL("drop table if exists dtsjofflinemsg");
        onCreate(db);
    }
}


}
