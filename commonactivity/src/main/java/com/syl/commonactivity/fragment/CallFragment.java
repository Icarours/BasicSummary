package com.syl.commonactivity.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/19.
 *
 * @Describe 打电话页面
 * 6.0以后需要申请动态权限
 * @Called
 */

public class CallFragment extends BaseFragment {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;//权限请求码
    private static final String TAG = CallFragment.class.getSimpleName();
    private View mRootView;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_call, null);
        return mRootView;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mRootView.findViewById(R.id.btn_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果没有权限就申请权限,如果有权限就直接打电话
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    call();
                }
            }
        });
    }

    private void call() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://10086"));
        startActivity(intent);
    }

    /**
     * 获得权限后处理回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                // Permission Denied
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
