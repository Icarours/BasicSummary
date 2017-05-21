package com.syl.viewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.exampleenen.ruedy.imagelib.widget.CircleImageView;
import com.exampleenen.ruedy.imagelib.widget.IdentityImageView;
import com.syl.viewdemo.R;

/**
 * author   Bright
 * date     2017/5/19 18:24
 * desc
 * 一个圆形的自定义第三方控件
 */
public class ImgActivity extends AppCompatActivity {

    private IdentityImageView mIdentityImageView;
    private float i = 10f;
    private float angle = 20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        mIdentityImageView = (IdentityImageView) findViewById(R.id.iiv);
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.civ);
        circleImageView.setImageResource(R.mipmap.mm2);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_btn1://填充大图,变换头像
                mIdentityImageView.getBigCircleImageView().setImageResource(R.mipmap.mm1);
                break;
            case R.id.btn_btn2://生成小图
                mIdentityImageView.setRadiusScale(0.1f);
                break;
            case R.id.btn_btn3://小图变大,增加边框
                mIdentityImageView.setBorderColor(R.color.colorPrimary);
                mIdentityImageView.setBorderWidth(50);
                break;
            case R.id.btn_btn4://移动小图,改变角度,没按一次,增加10
                mIdentityImageView.setIsprogress(true);
                mIdentityImageView.setProgressColor(R.color.colorAccent);
                mIdentityImageView.setProgress(i += 10);
                break;
            case R.id.btn_btn5://有圆边
                mIdentityImageView.getSmallCircleImageView().setImageResource(R.mipmap.v);
                break;
            case R.id.btn_btn6://有进度条
                mIdentityImageView.setProgress(angle += 5);
                break;
            default:
                break;
        }
    }
}
