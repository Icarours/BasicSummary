package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.basicsummary.R;
import com.syl.basicsummary.sqlite.PersonSqliteOpenHelper;
import com.syl.basicsummary.sqlite.SqliteUtils;

public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        PersonSqliteOpenHelper helper = new PersonSqliteOpenHelper(this);
        SqliteUtils sqliteUtils = new SqliteUtils(helper);
        sqliteUtils.add("xiaoming", "12");
//        sqliteUtils.add1("xiaoming1", "121");
//        sqliteUtils.add1("xiaoming2", "122");
    }
}
