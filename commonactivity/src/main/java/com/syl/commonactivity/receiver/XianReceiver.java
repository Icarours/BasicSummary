package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * author   Bright
 * date     2017/4/25 20:52
 * desc
 * 在注册广播的时候设置广播的优先级
 * 有序广播可被终止
 */
public class XianReceiver extends BroadcastReceiver {
    public XianReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        System.out.println(this.getClass().getSimpleName() + "县长收到大米" + resultData);
        setResultData("每人发20斤大米");
        //终止广播
//        abortBroadcast();
    }
}
