package com.syl.commonactivity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe
 * @Called
 */

public class StudentOpenHelper extends SQLiteOpenHelper {
    public StudentOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table students(" +
                "_id integer primary key autoincrement," +
                "name varchar(20)," +
                "gender varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
