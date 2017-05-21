package com.syl.basicsummary.eventbus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author   j3767
 * date     2017/3/4 21:37
 * desc
 * 在不同的界面或者不同的控件发送消息到UI线程或者界面
 * <p>
 * 粘性事件和普通的订阅事件稍微不同,可以在发布之后再注册
 */
public class SendMsgActivity extends AppCompatActivity {


    @BindView(R.id.btn_sendMsg_to_main)
    Button mBtnSendMsgToMain;
    @BindView(R.id.btn_receive_sticky)
    Button mBtnReceiveSticky;
    @BindView(R.id.tv_content_sticky)
    TextView mTvContentSticky;
    @BindView(R.id.activity_send_msg)
    LinearLayout mActivitySendMsg;
    private boolean isFirstFlag = true;//保证EventBus只注册一次

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);
        ButterKnife.bind(this);

    }

    /**
     * publisher发布消息
     *
     * @param view
     */
    @OnClick(R.id.btn_sendMsg_to_main)
    public void btn_sendMsg_to_main(View view) {
        //3.创建消息,4发布消息
        EventBus.getDefault().post(new MessageEvent("lili", 22));
        finish();
    }

    @OnClick(R.id.btn_receive_sticky)
    public void btn_receive_sticky(View view) {
        if (isFirstFlag) {
            isFirstFlag = false;
            //接收粘性事件,注册
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //解注册
        EventBus.getDefault().unregister(this);
    }

    /**
     * 接收粘性事件
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveMsg(MessageEvent messageEvent) {
        mTvContentSticky.setText(messageEvent.toString());
    }
}
