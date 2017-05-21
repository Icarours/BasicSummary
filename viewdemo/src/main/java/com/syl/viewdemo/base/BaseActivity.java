package com.syl.viewdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.viewdemo.R;

/**
 * Created by Bright on 2017/5/18.
 *
 * @Describe
 * @Called
 * Activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
