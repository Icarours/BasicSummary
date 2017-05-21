package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * author   Bright
 * date     2017/4/25 20:16
 * desc
 * 有序广播的最终接收者会受到两次广播
 */
public class FinalReceiver extends BroadcastReceiver {
    public FinalReceiver() {
    }

    /**
     * getResultData()和intent.getStringExtra("data")获得的值不同
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        System.out.println(this.getClass().getSimpleName() + "data===" + data);
        System.out.println(this.getClass().getSimpleName() + "最后收到大米==" + getResultData());
        // throw new UnsupportedOperationException("Not yet implemented");
    }
}
