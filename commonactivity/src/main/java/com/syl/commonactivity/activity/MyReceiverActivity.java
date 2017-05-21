package com.syl.commonactivity.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.receiver.FinalReceiver;
import com.syl.commonactivity.receiver.Min2Receiver;

/**
 * author   Bright
 * date     2017/4/25 17:58
 * desc
 * 广播的发送者和接收者也可以在同一个app中
 * 无序广播
 * 有序广播:有序广播的最终接收者谁受到两次广播
 */
public class MyReceiverActivity extends BaseActivity implements View.OnClickListener {

    private Min2Receiver mMin2Receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receiver);
        findViewById(R.id.btn_my_receiver).setOnClickListener(this);
        findViewById(R.id.btn_send_order_receiver).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_receiver:
                sendReceiver();
                break;
            case R.id.btn_send_order_receiver:
                sendOrderReceiver();
                break;
            default:
                break;
        }
    }

    /**
     * 发送有序广播
     */
    private void sendOrderReceiver() {
        Intent intent = new Intent();
        intent.setAction("com.syl.TEST_ORDER_RECEIVER");
        String data = "今年过年不收礼,收礼只收白大米...100";
        intent.putExtra("data", data);
        sendOrderedBroadcast(intent, null, new FinalReceiver(), null, RESULT_OK, null, null);
    }

    /**
     * 发送无序广播
     * 整个系统都能收到
     */
    private void sendReceiver() {
        Intent intent = new Intent();
        intent.setAction("com.syl.TEST_RECEIVER");
        intent.putExtra("data", "今天下雨..特大暴雨...");
        //发送广播
        sendBroadcast(intent);
        Toast.makeText(this, "自定义广播发送成功..", Toast.LENGTH_SHORT).show();
    }

    /**
     * 在Activity的onStart()方法中动态注册广播
     */
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.syl.TEST_ORDER_RECEIVER");
        filter.setPriority(100);
        mMin2Receiver = new Min2Receiver();
        registerReceiver(mMin2Receiver, filter);
    }

    /**
     * 在Activity的onStop()方法中动态解注册广播
     */
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mMin2Receiver);
        mMin2Receiver = null;
    }
}
