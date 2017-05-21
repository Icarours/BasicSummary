package com.syl.receviertest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.syl.receviertest.IPayNeixian;

/**
 * author   Bright
 * date     2017/4/26 16:14
 * desc
 * 远程服务支付(服务端)
 */
public class AliPayService extends Service {
    public AliPayService() {
    }

    /**
     * author   Bright
     * date     2017/4/26 16:15
     * desc
     * 返回AliPayService本身
     * 适用于本地Service通讯
     */
    class LocalService extends Binder {
        public AliPayService getLocalService() {
            return AliPayService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(this.getClass().getSimpleName() + "绑定成功==onBind(Intent intent)");
        return new PayNeixian();
//        return new LocalService();
    }

    /**
     * author   Bright
     * date     2017/4/26 16:14
     * desc 返回AliPayService中的方法
     */
    class PayNeixian extends IPayNeixian.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void call(String merchantId, double money) throws RemoteException {
            payInAliPayService(merchantId, money);
        }
    }

    public void payInAliPayService(String merchantId, double money) {
        System.out.println(merchantId + "商家 , 收到 " + money);
    }

    @Override
    public void onCreate() {
        System.out.println(this.getClass().getSimpleName() + "创建成功==onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println(this.getClass().getSimpleName() + "销毁成功==onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(this.getClass().getSimpleName() + "解绑成功==onUnbind(Intent intent)");
        return super.onUnbind(intent);
    }
}
