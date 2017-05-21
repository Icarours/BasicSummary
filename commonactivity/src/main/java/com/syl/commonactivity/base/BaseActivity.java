package com.syl.commonactivity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.commonactivity.R;

/**
 * author   Bright
 * date     2017/4/25 15:24
 * desc
 * 设置Activity的一些共有属性
 * 在清单文件中配置的固定方向属性纵屏,子类Activity不能继承
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        //设置Activity屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
