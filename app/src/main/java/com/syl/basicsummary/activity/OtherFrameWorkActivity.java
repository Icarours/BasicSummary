package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.syl.basicsummary.R;
import com.syl.basicsummary.base.BaseActivity;
import com.syl.basicsummary.eventbus.activity.EventBusActivity;

public class OtherFrameWorkActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Class[] mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] data = {"EventBus",};
        mActivities = new Class[]{EventBusActivity.class,};
        mListView.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, data));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, mActivities[position]);
        startActivity(intent);
    }
}
