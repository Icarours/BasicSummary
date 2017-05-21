package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.basicsummary.R;
import com.syl.basicsummary.socket.ClientServer;

/**
 * 失败
 * author   j3767
 * date     2017/3/4 13:44
 * desc
 * socket通信
 */
public class SocketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        ClientServer clientServer = new ClientServer(SocketActivity.this, 9989);
        clientServer.start();
    }
}
