package com.syl.basicsummary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * author   j3767
 * date     2017/3/2 0:09
 * desc
 * 线程私有
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new Thread("thread1") {
            @Override
            public void run() {
                MyOut();
            }
        }.start();

        new Thread("thread2") {
            @Override
            public void run() {
                MyOut();
            }
        }.start();
    }

    /**
     * 线程私有的方法
     * 每个线程独立调用,互不干涉
     */
    public static void MyOut() {
        final String MyList = Thread.currentThread().getName();
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
                System.out.println(MyList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
