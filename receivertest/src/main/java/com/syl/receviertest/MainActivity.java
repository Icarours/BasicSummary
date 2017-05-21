package com.syl.receviertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_my_receiver).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendReceiver();
    }

    /**
     * 全局的,整个系统都能收到,整个系统的其他app
     */
    private void sendReceiver() {
        Intent intent = new Intent();
        intent.setAction("com.syl.TEST_RECEIVER");
        intent.putExtra("data", "今天下雨..");
        //发送广播
        sendBroadcast(intent);
    }
}
