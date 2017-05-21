package com.syl.commonactivity.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Bright on 2017/4/19.
 *
 * @Describe 保存用户名和密码到私有文件夹
 * @Called
 */

public class DataStorageFragmentPrivate extends BaseFragment implements View.OnClickListener {

    private static final String TAG = DataStorageFragmentPrivate.class.getSimpleName();
    private View mRootView;
    private TextView mEtUser;
    private TextView mEtPassword;
    private CheckBox mCb;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_data_storage, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (TextView) mRootView.findViewById(R.id.et_user);
        mEtPassword = (TextView) mRootView.findViewById(R.id.et_password);
        mCb = (CheckBox) mRootView.findViewById(R.id.cb);
    }

    public void initListener() {
        mRootView.findViewById(R.id.btn_login).setOnClickListener(this);
    }

    /**
     * 登录
     */
    private void login() {
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        boolean checked = mCb.isChecked();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名或者密码不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checked) {
            loginSaveCache(user, password, checked);
        } else {
            Toast.makeText(getActivity(), "不需要记住用户名和密码...", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 保存数据到缓存文件夹
     *
     * @param user
     * @param password
     * @param checked
     */
    private void loginSaveCache(String user, String password, boolean checked) {
        try {
            System.out.println("记住用户名和密码..");
            // Do not hardcode "/data/"; use Context.getFilesDir().getPath() instead
//				File file = new File("/data/data/com.itheima.qqlogin/info.txt");
//				File file = new File(getFilesDir(),"info.txt");
            File file = new File(getActivity().getCacheDir(), "info.txt");
            OutputStream outputStream = new FileOutputStream(file);

            String str = user + "---" + password + "---" + checked;
//                outputStream.write(str.getBytes(),0,str.getBytes().length);
            outputStream.write(str.getBytes());
            outputStream.close();
            System.out.println("用户名和密码保存成功...");
            Log.d(TAG, "loginSaveCache---用户名和密码保存成功...");
            Toast.makeText(getActivity(), "loginSaveCache---用户名和密码保存成功...", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }
}
