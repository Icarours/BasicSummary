package com.syl.commonactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
/**
 * author   Bright
 * date     2017/4/25 16:26
 * desc
 * 联系人界面
 */
public class NumberActivity extends BaseActivity {

    private ListView mLvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        final String[] numbers = {
                "13363028305",
                "13363028305",
                "13364028305",
                "13363583053",
                "13363028005",
                "10086",
        };
        mLvNumber = (ListView) findViewById(R.id.lv_number);
        mLvNumber.setAdapter(new ArrayAdapter<>(this, R.layout.list_tiltles_item, R.id.tv_title, numbers));
        mLvNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number = numbers[position];
                Intent intent = new Intent();
                intent.putExtra("number", number);
                setResult(11, intent);//结果码,结果内容,将intent中的数据带过去
                finish();//结束当前的Activity
            }
        });
    }
}
