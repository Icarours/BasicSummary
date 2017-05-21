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
public class ShiReceiver extends BroadcastReceiver {
    public ShiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        System.out.println(this.getClass().getSimpleName()+"市收到大米"+resultData);
        setResultData("每人发30斤大米");
    }
}
