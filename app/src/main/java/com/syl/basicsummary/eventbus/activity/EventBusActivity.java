package com.syl.basicsummary.eventbus.activity;

import android.content.Intent;
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
 * date     2017/3/4 21:18
 * desc
 * 发布者和订阅者
 * EventBus的使用步骤
 * 1.绑定EventBus和Activity的生命周期,注册和解注册
 * 2.创建一个信息类
 * 3.发布信息
 * 4.接收信息,接收消息的标记@Subscribe,在接收消息的方法前面加上该标记,方法名不作要求
 * <p>
 * 可用于子线程和主线程之间的通信,不同空间之间的通信
 */
public class EventBusActivity extends AppCompatActivity {


    @BindView(R.id.btn_sendMsg_main)
    Button mBtnSendMsg;
    @BindView(R.id.btn_sendMsg_sticky)
    Button mBtnSendMsgSticky;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.activity_event_bus)
    LinearLayout mActivityEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);

        //1.注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2.解注册
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_sendMsg_main)
    public void btn_sendMsg(View view) {
        Intent intent = new Intent(this, SendMsgActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_sendMsg_sticky)
    public void btn_sendMsg_sticky(View view) {
        EventBus.getDefault().postSticky(new MessageEvent("粘性事件", 24));
        Intent intent = new Intent(this, SendMsgActivity.class);
        startActivity(intent);
    }

    /**
     * 5.订阅者接收事件
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceive(MessageEvent messageEvent) {
        mTvContent.setText(messageEvent.toString());
    }
}
