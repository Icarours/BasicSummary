package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Min2Receiver extends BroadcastReceiver {
    public Min2Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        System.out.println(this.getClass().getSimpleName()+"收到..."+data);
    }
}
