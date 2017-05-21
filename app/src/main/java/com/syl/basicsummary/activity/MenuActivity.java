package com.syl.basicsummary.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.syl.basicsummary.R;
import com.syl.basicsummary.utils.WebUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/3 14:40
 * desc
 * 三种菜单
 * optionMenu
 * subMenu
 * contextMenu
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.btn_optionMenu)
    Button mBtnOptionMenu;
    @BindView(R.id.btn_subMenu)
    Button mBtnSubMenu;
    @BindView(R.id.btn_contextMenu)
    Button mBtnContextMenu;
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置Android可以执行js代码
        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);//适应各种屏幕的分辨率,但是手机屏幕的字体会变得很小,看不清

        mBtnOptionMenu.setOnClickListener(this);
        mBtnSubMenu.setOnClickListener(this);
        mBtnContextMenu.setOnClickListener(this);

        mBtnOptionMenu.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_optionMenu:
                Intent intent = new Intent(this, OptionMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_subMenu:
                Intent intent2 = new Intent(this, SubMenuActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_contextMenu:
                Intent intent3 = new Intent(this, ContextMenuActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mWebView.setVisibility(View.VISIBLE);
        String fileName = "AndroidMenu.htm";
        AssetManager assetManager = getResources().getAssets();
        String assets = WebUtil.getFromAssets(fileName, assetManager);
        mWebView.loadDataWithBaseURL(null, assets, "text/html", "utf-8", null);
        return false;
    }
}
