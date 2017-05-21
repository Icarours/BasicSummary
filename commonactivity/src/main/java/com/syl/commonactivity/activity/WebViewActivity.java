package com.syl.commonactivity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.syl.commonactivity.R;
/**
 * author   Bright
 * date     2017/5/8 11:27
 * desc
 * 使用WebView播放视频
 */
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = (WebView) findViewById(R.id.wv);
        String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        webView.loadUrl(path);
    }
}
