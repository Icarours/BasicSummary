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
import com.syl.commonactivity.interface_.INeiXian;
import com.syl.commonactivity.service.TestService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2017/4/26 0:33
 * desc
 * 通过bindService启动Service
 * Service的生命周期
 * 解绑之后要将mNeiXian置为空,否则,依然可以通过mNeiXian调用TestService中的方法
 */
public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_start_service)
    Button mBtnStartService;
    @BindView(R.id.btn_bind_service)
    Button mBtnBindService;
    @BindView(R.id.btn_call_method_in_service)
    Button mBtnCallMethodInService;
    @BindView(R.id.btn_unbind_service)
    Button mBtnUnbindService;
    private INeiXian mNeiXian;
    private MyConnection mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        mBtnStartService.setOnClickListener(this);
        mBtnBindService.setOnClickListener(this);
        mBtnCallMethodInService.setOnClickListener(this);
        mBtnUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                break;
            case R.id.btn_bind_service:
                Intent intent = new Intent(ServiceActivity.this, TestService.class);
                mConn = new MyConnection();
                bindService(intent, mConn, BIND_AUTO_CREATE);
                System.out.println(this.getClass().getSimpleName() + "--绑定成功...");
                Toast.makeText(this, this.getClass().getSimpleName() + "---绑定成功...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_call_method_in_service:
                if (mNeiXian == null) {
                    Toast.makeText(this, "请先绑定服务..", Toast.LENGTH_SHORT).show();
                    return;
                }
                mNeiXian.readingInService();
                break;
            case R.id.btn_unbind_service:
                unbindService(mConn);
                //解绑之后要将mNeiXian置为空,否则,依然可以通过mNeiXian调用TestService中的方法
                mNeiXian = null;
                System.out.println(this.getClass().getSimpleName() + "--解除绑定成功...");
                Toast.makeText(this, this.getClass().getSimpleName() + "---解除绑定成功...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mNeiXian = (INeiXian) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
