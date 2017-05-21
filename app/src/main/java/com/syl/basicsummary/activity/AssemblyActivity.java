package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.syl.basicsummary.base.BaseActivity;

/**
 * author   j3767
 * date     2017/3/1 11:00
 * desc
 * 四大组件
 */
public class AssemblyActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_assembly);
        String[] titles = {"Activity", "Service", "ContentProvider", "BroadcastReceiver"};
        mActivities = new Class[]{ActivityActivity.class, ServiceActivity.class,
                ContentProviderActivity.class, BroadcastReceiverActivity.class,};
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, mActivities[position]);
        startActivity(intent);
    }

}
