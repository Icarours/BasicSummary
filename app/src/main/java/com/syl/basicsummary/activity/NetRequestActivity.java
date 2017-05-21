package com.syl.basicsummary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.basicsummary.R;

public class NetRequestActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] mNetRequestTitles;
    private Class[] mRequestActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_request);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NetRequestActivity.this, mRequestActivity[position]);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mNetRequestTitles = new String[]{"手机与电脑浏览器进行socket通信","httpURLConnection", "httpClient", "volley", "okHttp",
                "retrofit", "NetRequestDoc",};
        mRequestActivity = new Class[]{SocketActivity.class,HttpUrlActivity.class, HttpClientActivity.class, VolleyActivity.class,
                OkHttpActivity.class, RetrofitActivity.class, NetRequestDocActivity.class,};
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mNetRequestTitles));
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.activity_net_request);
    }
}
