package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Bright on 2017/4/25.
 *
 * @Describe 广播接收者
 * 需要在清单文件中注册
 * @Called
 */

public class OutGoingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        String data = intent.getDataString();
        String data = getResultData();
        System.out.println("向外拨打电话data---" + data);
        if (data.startsWith("0")) {
            data = "05587" + data;
            setResultData(data);
        }
        System.out.println("data---" + data);
    }
}
