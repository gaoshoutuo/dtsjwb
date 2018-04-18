package com.zjdt.dtsjwb.Util;

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
 *                                       框架 litepal
 *
 */


}
