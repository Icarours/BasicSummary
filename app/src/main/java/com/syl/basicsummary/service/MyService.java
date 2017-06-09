package com.syl.basicsummary.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.syl.basicsummary.interface_.INeiXian;

/**
 * Created by j3767 on 2017/3/1.
 *
 * @Describe 服务
 * @Called
 */

public class MyService extends Service {
    public static final String TAG = MyService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyService---onBind()被调用---", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onBind() ----绑定服务");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "MyService---onCreate()被调用---", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate() ----创建服务");
        //methodInService();
        super.onCreate();

       /* new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    methodInService();
                }
            }
        }.start();*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MyService---onStartCommand()被调用---", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStartCommand() ----");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService---onDestroy()被调用---", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy() ----销毁服务");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MyService---onUnbind()被调用---", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onUnbind() ----你解绑服务");
        return super.onUnbind(intent);
    }

    /**
     * author   j3767
     * date     2017/3/2 0:14
     * desc
     * 必须使用public修饰符,否则外部无法调用
     */
    class MyBinder extends Binder implements INeiXian{

        @Override
        public void callMethodInService() {
            methodInService();
        }
    }

    /**
     * 创建服务的代理,调用服务中的方法
     * 服务中的方法
     */
    public void methodInService() {

        Log.d(TAG, "methodInService----服务中的方法");
    }
}
