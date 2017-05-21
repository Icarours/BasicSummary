package com.syl.commonactivity.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.utils.StringTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe 使用HttpURLConnection加载文本文件
 * @Called
 */

public class HttpUrlConnTxtFragment extends BaseFragment implements View.OnClickListener {

    private static final int LOAD_OK = 1;
    private static final int LOAD_URL_FAIL = 2;
    private static final int LOAD_IO_FAIL = 3;
    private View mRootView;
    private EditText mEtPath;//用户自己输入URL地址
    private TextView mTvResult;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_OK:
                    mTvResult.setText((String) msg.obj);
                    break;
                case LOAD_URL_FAIL:
                    Toast.makeText(getContext(), "url地址有误...", Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_IO_FAIL:
                    Toast.makeText(getContext(), "io读写有误...", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_httpurl_txt, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtPath = (EditText) mRootView.findViewById(R.id.et_path);
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
        //TextView内容过多,设置TextView可以自由滚动
        mTvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_load_txt).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        String path = mEtPath.getText().toString();
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(getContext(), "url地址不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        loadTxt(path);
    }

    /**
     * 加载文本文件
     */
    private void loadTxt(final String path) {
        new Thread() {
            @Override
            public void run() {
//                String path = Constants.TXTURL.TXTURL1;
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String result = StringTool.inputStream2String(inputStream);

                        Message msg = Message.obtain();
                        msg.what = LOAD_OK;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = LOAD_URL_FAIL;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = LOAD_IO_FAIL;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
}
