package com.syl.basicsummary.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.basicsummary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity
 * 在onCreate方法中开启子线程,在子线程中刷新UI(没什么用)
 */
public class ActivityActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv)
    ImageView mIv;
    private Thread mThread;
    private Looper mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        ButterKnife.bind(this);
        mThread = new Thread("Thread11") {
            @Override
            public void run() {
                Looper.prepare();
                mLooper = Looper.myLooper();
                long currentTimeMillis = System.currentTimeMillis();
                mTv.setText("" + currentTimeMillis);
                mTv.append(Thread.currentThread().getName());
                mIv.setImageResource(R.mipmap.m1);
                System.out.println(mThread.getState() + "out");

                Looper.loop();
            }
        };
        mThread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void btn(View view) {
        try {
            mLooper.quitSafely();
            mLooper.quit();//调用mLooper.quit()之后,线程中止需要一点时间
            System.out.println(mThread.getState() + "quit");
        } catch (Exception i) {
            System.out.println(i + "" + mThread.getState());
        }
    }
}
