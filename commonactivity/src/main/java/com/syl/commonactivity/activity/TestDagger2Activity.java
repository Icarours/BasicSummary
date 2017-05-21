package com.syl.commonactivity.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
/**
 * author   Bright
 * date     2017/4/29 21:31
 * desc
 * Dagger失败
 */
public class TestDagger2Activity extends BaseActivity {

    private TextView mTvTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        mTvTxt = (TextView) findViewById(R.id.tv_txt);
        //TestDagger2ActivityComponent component = DaggerTestDagger2ActivityComponent.builder().mainActivityModuel(new MainActivityModuel()).build();
        //component.inject(this);
    }
}
