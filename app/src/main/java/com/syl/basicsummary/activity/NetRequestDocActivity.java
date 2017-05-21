package com.syl.basicsummary.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.basicsummary.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetRequestDocActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] mTitles;
    private String[] mDatas;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_request_doc);

        mListView = (ListView) findViewById(R.id.listView);
        mWebView = (WebView) findViewById(R.id.webView);
        mTitles = new String[]{"HttpURLConnection", "HttpClient", "Http",};
        mDatas = new String[]{"httpConnection.htm", "httpClient.htm", "http.htm",};

        loadDoc(0);//默认显示第一个简介文档

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mTitles));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadDoc(position);
            }
        });
    }

    /**
     * 加载显示对应的文档
     * 直接从assets文件夹下加载对应的文件
     *
     * @param position
     */
    private void loadDoc(int position) {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/" + mDatas[position]);
    }

    /**
     * 获取HTML文件
     *
     * @param fileName
     * @return
     */
    public String getFromAssets(String fileName) {
        try {
            //获取AssetManager
            AssetManager assetManager = getResources().getAssets();
            //获取输入流
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
