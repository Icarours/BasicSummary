package com.syl.basicsummary.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by j3767 on 2017/2/8.
 *
 * @Describe
 * 系统提供全局单例
 * @Called
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static int mMainThreadId;
    private static Handler mHandler;

    public static Context getmContext() {
        return mContext;
    }

    public static int getmMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        /**
         * myTid()//当前线程的id,Thread
         * myUid()//用户id,User
         * myPid()//进程id,Process
         */
        mMainThreadId = Process.myTid();
        mHandler = new Handler();
    }
}
