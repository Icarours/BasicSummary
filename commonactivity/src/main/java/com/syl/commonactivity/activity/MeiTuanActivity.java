package com.syl.commonactivity.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.syl.commonactivity.R;
import com.syl.commonactivity.utils.ServiceUtils;
import com.syl.receviertest.IPayNeixian;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2017/4/26 15:35
 * desc
 * 客户端支付界面
 * <p>
 * com.syl.receviertest.service;对应的服务端接口
 */
public class MeiTuanActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_meituan_pay)
    Button mBtnMeituanPay;
    @BindView(R.id.btn_bind_service)
    Button mBtnBindService;
    @BindView(R.id.btn_unbind_service)
    Button mBtnUnbindService;
    private IPayNeixian mPayNeixian;
    private MyConn mConn = new MyConn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        mBtnMeituanPay.setOnClickListener(this);
        mBtnBindService.setOnClickListener(this);
        mBtnUnbindService.setOnClickListener(this);
    }

    /**
     * api21之后使用隐式意图启动远程service服务会出现
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"的异常
     * 需要用createExplicitFromImplicitIntent()方法对intent进行修饰
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*------------------ 远程服务start -----------------*/
            case R.id.btn_bind_service:
                Intent intent = new Intent();
                intent.setAction("com.syl.ALIPAY_SERVICE");
                //intent.setPackage("com.syl.receivertest");//不能加这一句
                System.out.println("conn==" + mConn);
                Intent intent1 = new Intent(ServiceUtils.createExplicitFromImplicitIntent(this, intent));
                boolean b = bindService(intent1, mConn, BIND_AUTO_CREATE);
                System.out.println("b===" + b);
                break;
            case R.id.btn_meituan_pay:
                try {
                    mPayNeixian.call("华忆影视--见龙卸甲", 45.89);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_unbind_service:
                unbindService(mConn);
                break;
            /*------------------ 远程服务end -----------------*/

            default:
                break;
        }
    }

    /**
     * author   Bright
     * date     2017/4/26 16:28
     * desc
     * ServiceConnection的实现类
     */
    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPayNeixian = IPayNeixian.Stub.asInterface(service);
            System.out.println("b===onServiceConnected(ComponentName name, IBinder service)连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
