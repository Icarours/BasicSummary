package com.syl.commonactivity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.config.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * author   Bright
 * date     2017/4/25 16:25
 * desc
 * 短信助手
 */
public class SmsHelperActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.btn_select_number)
    Button mBtnSelectNumber;
    @BindView(R.id.et_sms)
    EditText mEtSms;
    @BindView(R.id.btn_select_template)
    Button mBtnSelectTemplate;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    private String mNumber;
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_helper);
        ButterKnife.bind(this);

        mBtnSelectNumber.setOnClickListener(this);
        mBtnSelectTemplate.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_select_number:
                intent.setClass(SmsHelperActivity.this, Constants.activities[3]);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_select_template:
                intent.setClass(SmsHelperActivity.this, Constants.activities[4]);
                startActivityForResult(intent, 2);
                break;
            case R.id.btn_send:
                sendMsg();
                Toast.makeText(this, "发送成功..", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 发送短信
     * @param
     */
    private void sendMsg() {
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(mNumber,null,mContent,null,null);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+mNumber));
        intent.putExtra("sms_body", mContent);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 11) {//联系人号码
            mNumber = data.getStringExtra("number");
            mEtNumber.setText(mNumber);
        } else if (requestCode == 2 && resultCode == 12) {//短信模板
            mContent = data.getStringExtra("content");
            mEtSms.setText(mContent);
        }
    }
}
