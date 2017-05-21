package com.syl.basicsummary.activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.basicsummary.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author   j3767
 * date     2017/1/30 11:10
 * desc
 * 一般的网络请求分为:get,post,上传,下载
 */
public class HttpUrlActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button mBtnImg;
    private ImageView mImg;
    private TextView mTv;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url);

        mBtnImg = (Button) findViewById(R.id.btn_image);
        mImg = (ImageView) findViewById(R.id.img);
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mWebView = (WebView) findViewById(R.id.webVew);
        mBtnImg.setOnClickListener(this);
        mBtnImg.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_image:
                loadImg();
                break;
            default:
                break;
        }
    }

    private void loadImg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String url = "http://e.hiphotos.baidu.com/image/pic/item/0e2442a7d933c89515308c4ed41373f0820200bc.jpg";
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImg.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * 长按按钮加载相关文档
     * 加载assets目录下的文件
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        //直接加载网页
//        mWebView.loadUrl("http://www.cnblogs.com/zgz345/p/3768174.html");
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(new JsInterface(),"control");
        mWebView.loadUrl("file:///android_asset/httpConnection.htm");

        return false;
    }

    public class JsInterface {
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(HttpUrlActivity.this, toast, Toast.LENGTH_SHORT).show();
        }

        public void log(final String msg) {
            mWebView.post(new Runnable() {

                @Override
                public void run() {
                    mWebView.loadUrl("javascript log(" + "'" + msg + "'" + ")");
                }
            });
        }
    }

    /**
     * 获取HTML文件
     * 使用这种方法获取流加载在WebView中会导致某些换行出现问题
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
