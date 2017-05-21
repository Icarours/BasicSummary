package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * author   Bright
 * date     2017/4/25 21:49
 * desc
 * 在注册广播的时候设置广播的优先级
 */
public class ShengReceiver extends BroadcastReceiver {
    public ShengReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        System.out.println(this.getClass().getSimpleName()+"省收到大米"+data);
        setResultData("每人发50斤大米");
    }
}
