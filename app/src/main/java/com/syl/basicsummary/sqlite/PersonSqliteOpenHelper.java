package com.syl.basicsummary.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by j3767 on 2017/3/3.
 *
 * @Describe
 * @Called
 */

public class PersonSqliteOpenHelper extends SQLiteOpenHelper {
    public PersonSqliteOpenHelper(Context context) {
        this(context, "person.db", null, 1);
    }

    public PersonSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement,name varchar(20),number varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table person add account varchar(20)");
    }
}
