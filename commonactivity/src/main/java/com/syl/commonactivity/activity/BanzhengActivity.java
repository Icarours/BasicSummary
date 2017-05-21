package com.syl.commonactivity.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.interface_.IXiaoMi;
import com.syl.commonactivity.service.BanzhengService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2017/4/26 11:29
 * desc
 * 办证用户界面
 */
public class BanzhengActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_bind_service)
    Button mBtnBindService;
    @BindView(R.id.btn_call_method_in_service)
    Button mBtnCallMethodInService;
    @BindView(R.id.btn_unbind_service)
    Button mBtnUnbindService;
    private IXiaoMi mXiaoMi;
    private ServiceConnection mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banzheng);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        mBtnBindService.setOnClickListener(this);
        mBtnCallMethodInService.setOnClickListener(this);
        mBtnUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_service:
                connectXiaoMi();
                break;
            case R.id.btn_call_method_in_service:
                if (mXiaoMi == null) {//如果没有绑定服务,就弹出友好提示,返回
                    Toast.makeText(this, "请先绑定服务,找小蜜咨询..", Toast.LENGTH_SHORT).show();
                    System.out.println(this.getClass().getSimpleName() + "请先绑定服务,找小蜜咨询..");
                    return;
                }
                mXiaoMi.banzheng("张三", 100);
                System.out.println(this.getClass().getSimpleName() + "调用Service中的方法..");
                Toast.makeText(this, "调用Service中的方法..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_unbind_service:
                unbindService(mConn);
                mXiaoMi = null;
                System.out.println(this.getClass().getSimpleName() + "解除绑定Service..");
                Toast.makeText(this, "解除绑定Service..", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 连接小蜜/内线
     */
    private void connectXiaoMi() {
        Intent intent = new Intent(BanzhengActivity.this, BanzhengService.class);
        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mXiaoMi = (IXiaoMi) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, mConn, BIND_AUTO_CREATE);
        System.out.println(this.getClass().getSimpleName() + "绑定服务成功..");
        Toast.makeText(this, "绑定服务成功..", Toast.LENGTH_SHORT).show();
    }
}
