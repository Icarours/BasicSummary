package com.syl.basicsummary.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.basicsummary.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

public class HttpClientActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);

        Button btnImg = (Button) findViewById(R.id.btn_image);
        mImg = (ImageView) findViewById(R.id.img);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mWebView = (WebView) findViewById(R.id.webView);

        btnImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loadImg();//加载图片
    }

    /**
     * 加载图片
     */
    private void loadImg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //1.创建HttpClient
                    HttpClient httpClient = new DefaultHttpClient();
                    //2.创建uri
                    String uri = "http://f.hiphotos.baidu.com/image/pic/item/37d12f2eb9389b50d25030038735e5dde7116e30.jpg";
                    //3.指定请求方法
                    HttpGet httpGet = new HttpGet(uri);
                    //4.获取返回信息
                    HttpResponse response = httpClient.execute(httpGet);
                    HttpEntity responseEntity = response.getEntity();
                    //5.处理返回信息,将返回信息封装成需要的格式
                    InputStream inputStream = responseEntity.getContent();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImg.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
