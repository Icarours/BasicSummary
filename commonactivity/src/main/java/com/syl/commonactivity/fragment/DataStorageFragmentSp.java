package com.syl.commonactivity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe 保存数据到sp
 * @Called
 */

public class DataStorageFragmentSp extends BaseFragment implements View.OnClickListener {

    private static final String TAG = DataStorageFragmentPrivate.class.getSimpleName();
    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private CheckBox mCb;
    private SharedPreferences mSpConfig;

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mCb = (CheckBox) mRootView.findViewById(R.id.cb);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_data_storage, null);
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginSp();
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void loginSp() {
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        boolean checked = mCb.isChecked();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名和密码都不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checked) {
            loginSaveSp(user, password, checked);
        } else {
            Toast.makeText(getActivity(), "不需要保存用户名和密码..", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 使用SharedPreferences保存数据
     *
     * @param user
     * @param password
     * @param checked
     */
    private void loginSaveSp(String user, String password, boolean checked) {
        mSpConfig = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mSpConfig.edit();
        edit.putString("user", user);
        edit.putString("password", password);
        edit.putBoolean("checked", checked);
        edit.apply();
        Log.d(TAG, "loginSaveSp---用户名和密码保存成功...");
        Toast.makeText(getActivity(), "loginSaveSp---用户名和密码保存成功...", Toast.LENGTH_SHORT).show();
    }
}
