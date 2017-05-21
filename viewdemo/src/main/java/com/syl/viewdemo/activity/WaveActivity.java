package com.syl.viewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.viewdemo.R;
import com.syl.viewdemo.view.WaveView;
/**
 * author   Bright
 * date     2017/5/20 2:36
 * desc
 * 第三方自定义控件,波浪
 */
public class WaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        WaveView wvWave = (WaveView) findViewById(R.id.wv_wave);
        wvWave.setRunning();
    }
}
