package com.syl.commonactivity.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.syl.commonactivity.R;
import com.syl.commonactivity.view.VariedImageView;

/**
 * author   Bright
 * date     2017/5/12 21:01
 * desc
 * 测试自定义圆形图片
 */
public class ImageActivity extends AppCompatActivity {

    private VariedImageView mViv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mViv = (VariedImageView) findViewById(R.id.viv_variedImageView);

        mViv.setBorderOverlay(false);
        mViv.setBorderThick(10);
        mViv.setBorderColor(Color.BLUE);
    }

    public void change(View view) {
        VariedImageView variedImageView=new VariedImageView(this);
        variedImageView.setBorderThick(10);
        variedImageView.setBorderColor(Color.BLUE);
        variedImageView.setBorderOverlay(false);
        variedImageView.setImageResource(R.mipmap.mm2);
        variedImageView.refresh();
    }
}
