package com.syl.commonactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.config.Constants;
import com.syl.commonactivity.factory.FragmentFactory;

/**
 * author   Bright
 * date     2017/4/19 22:40
 * desc
 * 详情界面,根据不同的需要使用不同的Fragment
 */
public class DetailActivity extends BaseActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        initView(position);

        initData(position);
    }

    /**
     * 初始化数据
     * @param position
     */
    private void initData(int position) {
        mTv.setText(Constants.titles[position]);
    }

    /**
     * 初始化视图
     * @param position
     */
    private void initView(int position) {
        mTv = (TextView) findViewById(R.id.tv_title);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl, FragmentFactory.createFragment(position));
        transaction.commit();
    }
}
