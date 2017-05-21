package com.syl.basicsummary.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by j3767 on 2017/3/3.
 *
 * @Describe
 * sqlite数据库操作的一些工具方法,为了避免sql语句注入的问题,最好使用占位符
 * @Called
 */

public class SqliteUtils {

    private PersonSqliteOpenHelper mHelper;
    private SQLiteDatabase mDb;

    public SqliteUtils(PersonSqliteOpenHelper helper) {
        mHelper = helper;
    }

    /**
     * add
     *
     * @param name
     * @param number
     * @return
     */
    public long add(String name, String number) {
        //1.获取数据库
        synchronized (SqliteUtils.class) {//双重加锁,保证单例和不出现并发修改异常
            if (mDb == null) {
                synchronized (SqliteUtils.class) {
                    mDb = mHelper.getWritableDatabase();
                }
            }
        }
        //2.将参数名和列添加到ContentValues对象
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);
        long id = mDb.insert("person", null, contentValues);//有返回值
        //3.关闭数据库
        mDb.close();
        return id;
    }

    public void add1(String name1, String number1) {
        mDb.execSQL("insert into person (name,number) value (?,?)", new Object[]{name1, number1});//无返回值
        mDb.close();
    }

    /**
     * 修改name的number为newNumber
     *
     * @param name
     * @param newNumber
     * @return
     */
    public int update(String name, String newNumber) {
        ContentValues values = new ContentValues();
        values.put("number", newNumber);
        int number = mDb.update("person", values, "name=?", new String[]{name});
        mDb.close();
        return number;
    }

    /**
     * 删除字段name中name =name1的记录
     *
     * @param name1
     * @return
     */
    public int delete(String name1) {
        int number = mDb.delete("person", "name=?", new String[]{name1});
        mDb.close();
        return number;
    }

    /**
     * 查询name=name的所有条目
     * query
     *
     * @param name
     * @return
     */
    public boolean find(String name) {
        Cursor cursor = mDb.query("person", null, "name=?", new String[]{name}, null, null, null);
        boolean result = cursor.moveToNext();
        cursor.close();
        mDb.close();
        return result;
    }

    /**
     * 查询name=name的所有条目
     * rawQuery
     *
     * @param name
     * @return
     */
    public boolean find1(String name) {
//        Cursor cursor = mDb.query("person", null, "name=?", new String[]{name}, null, null, null);
        Cursor cursor = mDb.rawQuery("select * from person where name = ?", new String[]{name});
        boolean result = cursor.moveToNext();
        cursor.close();
        mDb.close();
        return result;
    }
}
