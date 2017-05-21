package com.syl.commonactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * author   Bright
 * date     2017/4/26 16:10
 * desc
 * 测试用Service
 */
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
