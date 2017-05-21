package com.syl.commonactivity.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.syl.commonactivity.bean.Student;
import com.syl.commonactivity.db.StudentOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe 数据库表的增删改查
 * @Called
 */

public class StudentDao {
    private SQLiteOpenHelper mHelper;

    public StudentDao(Context context) {
        mHelper = new StudentOpenHelper(context);
    }

    /**
     * 增添数据
     *
     * @param name
     * @param gender
     */
    public void insert(String name, String gender) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "insert into students values(null,?,?)";
        database.execSQL(sql, new Object[]{name, gender});
        database.close();
    }

    /**
     * 删除
     *
     * @param name
     */
    public void delete(String name) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "delete from students where name =?";
        database.execSQL(sql, new Object[]{name});
        database.close();
    }

    /**
     * 更新
     *
     * @param name
     * @param newGender
     */
    public void update(String name, String newGender) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "update students set gender = ? where name = ?";
        database.execSQL(sql, new Object[]{newGender, name});
        database.close();
    }

    /**
     * 查询表中的某一个字段
     *
     * @param name
     * @return
     */
    public String selectGender(String name) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "select snumber from students where name = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{name});
        String gender = null;
        while (cursor.moveToNext()) {
            gender = cursor.getString(0);
        }
        //释放资源
        cursor.close();
        database.close();
        return gender;
    }

    /**
     * 查询表中所有的数据
     *
     * @return
     */
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        String sql = "select * from students";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);
            Student student = new Student(id, name, gender);
            list.add(student);
        }
        //释放资源
        cursor.close();
        database.close();

        return list;
    }
}
