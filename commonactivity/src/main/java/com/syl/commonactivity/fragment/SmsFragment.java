package com.syl.commonactivity.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/19.
 *
 * @Describe 发短信页面
 * android6.0以后添加动态权限
 * @Called
 */

public class SmsFragment extends BaseFragment {
    public static final int MY_REQUEST_PERMISSION_CODE = 1;//请求码
    private View mRootView;
    private EditText mEtNumber;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_sms, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtNumber = (EditText) mRootView.findViewById(R.id.et_number);
        mRootView.findViewById(R.id.btn_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果没有发短信的权限就申请权限,如果有权限,就直接发短信,
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, MY_REQUEST_PERMISSION_CODE);
                } else {
                    sms();
                }
            }
        });
    }

    /**
     * 发短信
     */
    private void sms() {
        String number = mEtNumber.getText().toString();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(getActivity(), "用户名和密码不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
//        sms1(number);
        sms2();
    }

    /**
     * 跳转到短信界面
     */
    private void sms2() {
        Uri smsToUri = Uri.parse("smsto:10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", "000088770");
        startActivity(intent);
    }

    /**
     * 发短信,不需要跳转到短信界面
     *
     * @param number
     */
    private void sms1(String number) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("10086", null, number + "---", null, null);

        System.out.println(number + "---");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sms();
                Toast.makeText(getContext(), "短信发送成功..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "用户拒绝发送短信..", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
