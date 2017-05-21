package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * author   Bright
 * date     2017/5/5 9:49
 * desc
 * 接收自定义广播,其他app的(receviertest)
 */
public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        System.out.println("收到自定义广播了..data=="+data);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
