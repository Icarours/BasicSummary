package com.syl.commonactivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.syl.commonactivity.interface_.INeiXian;

public class TestService extends Service {
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(this.getClass().getSimpleName() + "--服务绑定了--onBind(Intent intent)..");
        return new NeiXian();
    }

    /**
     * author   Bright
     * date     2017/4/25 23:37
     * desc
     * 1.这个内部类必须用public修饰,否则外面的类拿不到他的对象(这句话作废,如果使用接口实现的,完全不用考虑NeiXian的全修饰符)
     * 2.将NeiXian的方法全部私有,需要对外提供的更能通过接口来实现
     */
    class NeiXian extends Binder implements INeiXian {
        public void readingInService() {
            reading();
        }

        private void movie() {
            System.out.println("一起看电影..");
        }

        private void play() {
            System.out.println("一起打游戏..");
        }
    }

    /**
     * TestService中的方法
     */
    public void reading() {
        System.out.println("我今天读了一本书,叫<傲慢与偏见>");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(this.getClass().getSimpleName() + "--服务创建了--onCreate()..");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(this.getClass().getSimpleName() + "--服务销毁了--onDestroy()..");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(this.getClass().getSimpleName() + "--服务解除绑定--onUnbind(Intent intent)..");
        return super.onUnbind(intent);
    }
}
