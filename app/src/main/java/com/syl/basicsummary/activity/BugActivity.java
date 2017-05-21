package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/9 9:30
 * desc
 * 遇到的bug及其解决方法
 */
public class BugActivity extends AppCompatActivity {

    @BindView(R.id.activity_bug)
    ListView mListView;
    private String[] mBugTitles;
    private Class[] mActivityNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug);
        ButterKnife.bind(this);
        initData();
        initListener();

    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BugActivity.this, mActivityNames[position]);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mBugTitles = UIUtils.getResources().getStringArray(R.array.bug_titles);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mBugTitles));
        mActivityNames = new Class[]{BugFragmentActivity.class,};
    }
}
