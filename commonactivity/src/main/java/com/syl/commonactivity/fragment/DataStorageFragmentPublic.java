package com.syl.commonactivity.fragment;

import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe
 * 保存数据到公共文件夹在
 * @Called
 */

public class DataStorageFragmentPublic extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private CheckBox mCb;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_data_storage, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mCb = (CheckBox) mRootView.findViewById(R.id.cb);
        //回显已有的信息
        recoveryData();

    }

    /**
     * 回显信息
     */
    private void recoveryData() {
        //错用API Environment.getDataDirectory();导致保存文件失败
        //保存到公共文件夹下
        //File file = new File("/mnt/sdcard","info.txt");
        File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = bufferedReader.readLine();
            /**
             * 这儿可能会出现角标越界的异常,因为用户可能将用户名,密码输一半,然后去干别的事情
             */
            String[] arr = s.split("-");
            String user = arr[0];
            String password = arr[1];
            boolean checked = Boolean.parseBoolean(arr[2]);
            mEtUser.setText(user);
            mEtPassword.setText(password);
            mCb.setChecked(checked);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_login).setOnClickListener(this);
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

    private void login() {
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        boolean checked = mCb.isChecked();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名和密码都不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取当前sd卡的状态
        String externalStorageState = Environment.getExternalStorageState();
        //如果没有sd就弹出提示框,提示用户插入sd卡
        if (!Environment.MEDIA_MOUNTED.equals(externalStorageState)) {
            Toast.makeText(getActivity(), "请插入sd卡..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checked) {
            long totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();
            long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();

            String totalSize = Formatter.formatFileSize(getActivity(), totalSpace);
            String freeSize = Formatter.formatFileSize(getActivity(), freeSpace);
            System.out.println("totalSize===" + totalSize + "-----" + "freeSize===" + freeSize);
            //公共文件夹
            //				File file = new File("/mnt/sdcard","info.txt");
            File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
            try {
                OutputStream outputStream = new FileOutputStream(file);
                String str = user + "-" + password + "-" + checked;
                outputStream.write(str.getBytes());
                outputStream.close();
                Toast.makeText(getActivity(), "用户名和密码保存成功...", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "不需要记住用户名和密码...", Toast.LENGTH_SHORT).show();
        }
    }
}
