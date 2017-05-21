package com.syl.viewdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.syl.viewdemo.R;
/**
 * author   Bright
 * date     2017/5/18 21:28
 * desc
 * 通过加载图片,测试获得手机分配的最大内存
 */
public class TestActivity extends AppCompatActivity {

    private ImageView mIv;
    private Bitmap[] mAaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mIv = (ImageView) findViewById(R.id.iv);

    }

    public void size(View view) {
        mIv.setImageResource(R.mipmap.test);
        Drawable drawable = mIv.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        System.out.println("intrinsicWidth==" + intrinsicWidth + "--intrinsicHeight==" + intrinsicHeight);
    }

    public void load(View view){
        mAaa = new Bitmap[150];
        for (int i = 0; i < 150; i++) {
//            ImageView imageView = new ImageView(this);
            System.out.println(i);
            mAaa[i] = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
        }
    }
}