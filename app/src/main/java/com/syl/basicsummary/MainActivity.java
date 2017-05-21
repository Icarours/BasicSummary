package com.syl.basicsummary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.basicsummary.activity.AnimationActivity;
import com.syl.basicsummary.activity.AssemblyActivity;
import com.syl.basicsummary.activity.AudioVideoActivity;
import com.syl.basicsummary.activity.BugActivity;
import com.syl.basicsummary.activity.CommonWidget;
import com.syl.basicsummary.activity.DataDecodeActivity;
import com.syl.basicsummary.activity.DataStorageActivity;
import com.syl.basicsummary.activity.HTML5Activity;
import com.syl.basicsummary.activity.ImageActivity;
import com.syl.basicsummary.activity.NetRequestActivity;
import com.syl.basicsummary.activity.NotificationActivity;
import com.syl.basicsummary.activity.OtherFrameWorkActivity;
import com.syl.basicsummary.activity.RapidDevelopment;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] mTitles;
    private Class[] mActivityNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置Activity为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {
        mTitles = new String[]{"音视频", "常用控件", "网络请求", "android动画", "数据存储", "图片的加载和处理",
                "四大组件", "HTML5和Android 的混合开发", "数据解析", "通知实现", "快速开发", "bug及其解决方法","其他框架",};
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));
        mActivityNames = new Class[]{AudioVideoActivity.class, CommonWidget.class, NetRequestActivity.class, AnimationActivity.class,
                DataStorageActivity.class, ImageActivity.class, AssemblyActivity.class, HTML5Activity.class,
                DataDecodeActivity.class, NotificationActivity.class, RapidDevelopment.class,
                BugActivity.class,OtherFrameWorkActivity.class,};
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, mActivityNames[position]);
                startActivity(intent);
            }
        });
    }
}
