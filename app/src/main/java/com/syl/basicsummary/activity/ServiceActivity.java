package com.syl.basicsummary.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.syl.basicsummary.R;
import com.syl.basicsummary.interface_.INeiXian;
import com.syl.basicsummary.service.MyService;
import com.syl.basicsummary.utils.WebUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author   j3767
 * date     2017/3/1 20:54
 * desc
 * 和Service进行交互的界面
 * <p>
 * Service的使用步骤:
 * 1.创建Service的子类MyService,实现对应的生命周期方法
 * 2.在MyService中创建一个内部类MyBinder,继承自Binder,提供给外界访问
 * 3.在Activity中创建一个MyConnection,实现ServiceConnection接口,将回调参数IBinder service赋值给
 * mMyBinder = (MyService.MyBinder) service;
 */
public class ServiceActivity extends AppCompatActivity {
    public static final String TAG = ServiceActivity.class.getSimpleName();
    @BindView(R.id.btn_start_service)
    Button mBtnStartService;
    @BindView(R.id.btn_stop_service)
    Button mBtnStopService;
    @BindView(R.id.webView)
    WebView mWebView;
    private INeiXian mINeiXian;
    private MyConn mMyConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setUseWideViewPort(true);//适应分辨率
//        settings.setLoadWithOverviewMode(true);
    }

    /**
     * 开启服务
     *
     * @param view
     */
    @OnClick(R.id.btn_start_service)
    public void btn_start_service(View view) {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }

    /**
     * 停止服务
     *
     * @param view
     */
    @OnClick(R.id.btn_stop_service)
    public void btn_stop_service(View view) {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        stopService(intent);
    }

    /**
     * 绑定服务
     *
     * @param view
     */
    @OnClick(R.id.btn_bind_service)
    public void btn_bind_service(View view) {
        if (mMyConn == null) {
            mMyConn = new MyConn();
        }
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mMyConn, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    @OnClick(R.id.btn_unbind_service)
    public void btn_unbind_service(View view) {
        if (mMyConn == null) {
            mMyConn = new MyConn();
        }
        unbindService(mMyConn);
    }

    /**
     * 调用服务中的方法
     *
     * @param view
     */
    @OnClick(R.id.btn_call_in_service)
    public void btn_call_in_service(View view) {
        mINeiXian.callMethodInService();
    }

    /**
     * 实现回调接口ServiceConnection的的类
     */
    private class MyConn implements ServiceConnection {
        /**
         * 连接成功
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mINeiXian = (INeiXian) service;
            Log.d(TAG, "服务成功绑定---" + service.toString());
        }

        /**
         * 断开连接
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_load_code:
                loadCodeInHtml();//加载html中的代码
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载html
     */
    private void loadCodeInHtml() {
        AssetManager assetManager = getResources().getAssets();
        String url = WebUtil.getFromAssets("serviceDemo.htm", assetManager);
        mWebView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
    }
}
