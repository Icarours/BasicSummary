package com.syl.commonactivity.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

/**
 * author   Bright
 * date     2017/4/25 17:54
 * desc
 * 这个不能用了,系统移除了相应的Action
 */
public class SmsSpyReceiver extends BroadcastReceiver {
    public SmsSpyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj :
                objs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String messageBody = smsMessage.getMessageBody();
            String address = smsMessage.getOriginatingAddress();
            if ("133".equals(address)) {
                abortBroadcast();
            }
            System.out.println("address==" + address + "===messageBody==" + messageBody);
        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
