package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.base.BaseActivity;

/**
 * author   j3767
 * date     2017/1/29 11:28
 * desc
 * 常用的控件
 * listView,RecyclerView,GridView,Dialog,ViewPager,Menu
 */
public class CommonWidget extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private Class[] mActivityNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_widget);

        mListView = (ListView) findViewById(R.id.activity_common_widget);
        String[] itemData = {"listView", "RecyclerView", "GridView", "Dialog对话框", "ViewPager", "Menu",
                "ActionBarAndDrawerLayout","SlidingMenuActivity侧滑菜单1","滑动冲突EventTouchConflict",
                "ScrollView嵌套ListView",};
        mActivityNames = new Class[]{LvActivity.class, RvActivity.class, GvActivity.class,DActivity.class,
                VpActivity.class, MenuActivity.class,ABDLActivity.class,SlidingMenuActivity1.class,
                EventTouchConflictActivity.class,ScrollListViewActivity.class,};
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemData));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(CommonWidget.this, mActivityNames[position]);
        startActivity(intent);
    }
}
