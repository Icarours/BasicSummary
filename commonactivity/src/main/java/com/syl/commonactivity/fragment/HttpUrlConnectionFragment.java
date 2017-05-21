package com.syl.commonactivity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.data.Constants;
import com.syl.commonactivity.utils.StringTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bright on 2017/5/5.
 *
 * @Describe 使用HttpURLConnection
 * 登录get
 * 登录post
 * 加载图片
 * @Called
 */

public class HttpUrlConnectionFragment extends BaseFragment implements View.OnClickListener {
    private static final int LOGIN_OK = 1;
    private static final int LOGIN_FAILURE_URL = 2;
    private static final int LOGIN_FAILURE_IO = 3;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOGIN_OK:
                    String result = (String) msg.obj;
                    mTvContent.setText(result);
                    break;
                case LOGIN_FAILURE_IO:
                    Toast.makeText(getContext(), "io读写异常..", Toast.LENGTH_SHORT).show();
                    break;
                case LOGIN_FAILURE_URL:
                    Toast.makeText(getContext(), "url异常...", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private TextView mTvContent;
    private ImageView mIvImg;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_httpurlconnection, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mTvContent = (TextView) mRootView.findViewById(R.id.tv_content);
        mIvImg = (ImageView) mRootView.findViewById(R.id.iv_img);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_login_get).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_login_post).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_load_net_img).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_get:
                loginGet();
                break;
            case R.id.btn_login_post:
                loginPost();
                break;
            case R.id.btn_load_net_img:
                loadNetImg();
                break;
            default:
                break;
        }
    }

    /**
     * get登录
     */
    private void loginGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String user = mEtUser.getText().toString();
                String password = mEtPassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "用户名和密码都不能为空..", Toast.LENGTH_SHORT).show();
                }
                // http://localhost:8080/Login/servlet/LoginServelet?username=admin&password=123
                String url = Constants.BASEURL + "Login/servlet/LoginServelet?username="+user+"&password="+password;
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    InputStream inputStream = connection.getInputStream();
                    String result = StringTool.inputStream2String(inputStream);

                    Message msg = Message.obtain();
                    msg.what = LOGIN_OK;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    Message msg = Message.obtain();
                    msg.what = LOGIN_FAILURE_URL;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                } catch (IOException e) {
                    Message msg = Message.obtain();
                    msg.what = LOGIN_FAILURE_IO;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * post登录
     */
    private void loginPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String user = mEtUser.getText().toString();
                String password = mEtPassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "用户名和密码都不能为空..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // http://localhost:8080/Login/servlet/LoginServelet?username=admin&password=123
                String url = Constants.BASEURL + "Login/servlet/LoginServelet";
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
//                    Content-Length:27
//                    Content-Type:application/x-www-form-urlencoded
                    String params = "username="+ URLEncoder.encode(user,"UTF-8")+"&password="+password;
                    connection.setRequestProperty("Content-Length",params.length()+"");
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                    connection.setDoOutput(true);
                    connection.getOutputStream().write(params.getBytes());
                    InputStream inputStream = connection.getInputStream();
                    String result = StringTool.inputStream2String(inputStream);

                    Message msg = Message.obtain();
                    msg.what = LOGIN_OK;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    Message msg = Message.obtain();
                    msg.what = LOGIN_FAILURE_URL;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                } catch (IOException e) {
                    Message msg = Message.obtain();
                    msg.what = LOGIN_FAILURE_IO;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 加载网络图片
     */
    private void loadNetImg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String url = "http://e.hiphotos.baidu.com/image/pic/item/0e2442a7d933c89515308c4ed41373f0820200bc.jpg";
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mIvImg.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
