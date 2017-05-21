package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * author   Bright
 * date     2017/4/25 21:06
 * desc
 * 接收屏幕开启或者关闭的广播
 */
public class LockScreenReceiver extends BroadcastReceiver {
    public LockScreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        System.out.println("屏幕点亮了或者关闭了..action==" + action);
    }
}
