package com.syl.basicsummary.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.syl.basicsummary.MainActivity;
import com.syl.basicsummary.R;
import com.syl.basicsummary.utils.WebUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/3 14:42
 * desc
 * Android通知(顶端)
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.btn_sendMsg)
    Button mBtnSendMsg;
    @BindView(R.id.btn_cancelMsg)
    Button mBtnCancelMsg;
    @BindView(R.id.webView)
    WebView mWebView;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        //解决WebView加载页面显示不全的问题
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//适应分辨率webSettings.setLoadWithOverviewMode(true);

        mBtnSendMsg.setOnClickListener(this);
        mBtnSendMsg.setOnLongClickListener(this);
        mBtnCancelMsg.setOnClickListener(this);
        //0.创建NotificationManager
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendMsg:
                sendNotification();
                break;
            case R.id.btn_cancelMsg:
                break;
            default:
                break;
        }
    }

    /**
     * 发送通知
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification() {
        //1.创建intent
        Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
        //2.创建pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
        //3.创建notification
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Demo test notification")//提示标题
                .setSmallIcon(R.mipmap.ic_launcher)//提示的icon
                .setTicker("hello world---")
                .setContentText("have you update now")//提示内容
                .setWhen(System.currentTimeMillis())//提示时间
                .setDefaults(Notification.DEFAULT_LIGHTS)//默认提示灯
                .setDefaults(Notification.DEFAULT_SOUND)//默认声音
                .setDefaults(Notification.DEFAULT_VIBRATE)//默认震动
                .setContentIntent(pendingIntent)
                .build();
        //4.发出通知
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    @Override
    public boolean onLongClick(View v) {
        mWebView.setVisibility(View.VISIBLE);
        String fileName = "notification.htm";
        AssetManager assetManager = getResources().getAssets();
        String fromAssets = WebUtil.getFromAssets(fileName, assetManager);
        mWebView.loadDataWithBaseURL(null, fromAssets, "text/html", "utf-8", null);
        return false;
    }
}
