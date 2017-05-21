package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.syl.basicsummary.base.BaseActivity;

public class DataStorageActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] titles = {"sqlite", "sharedPreference", "data", "netStorage",};
        mActivities = new Class[]{SqliteActivity.class, SpActivity.class, DataActivity.class, NsActivity.class,};

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, mActivities[position]);
        startActivity(intent);
    }
}
