package com.syl.commonactivity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.config.Constants;
import com.syl.commonactivity.utils.StringTool;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Bright on 2017/5/5.
 *
 * @Describe 使用okHttp
 * get登录
 * post登录
 * 加载txt文版
 * 加载网络图片
 * @Called
 */

public class OkHttpFragment extends BaseFragment implements View.OnClickListener {
    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private TextView mTvContent;
    private ImageView mIvImg;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_okhttp, null);

        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mIvImg = (ImageView) mRootView.findViewById(R.id.iv_img);
        mTvContent = (TextView) mRootView.findViewById(R.id.tv_content);
        mTvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_login_get).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_login_post).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_load_net_img).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_load_txt).setOnClickListener(this);
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
                loadImg();
                break;
            case R.id.btn_load_txt:
                loadTxt();
            default:
                break;
        }
    }

    /**
     * get登录
     */
    private void loginGet() {
        //http://localhost:8080/Login/servlet/LoginServelet?username=admin&password=123
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        try {
            String url = Constants.BASEURL + "Login/servlet/LoginServelet" + "?username=" + URLEncoder.encode(user, "UTF-8") + "&password=" + password;
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);
            //异步请求
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Toast.makeText(getContext(), "请求失败...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    /**此处应该使用response.body().byteStream(),不要使用response.body().toString(),
                     * 使用response.body().toString()会出现一些意想不到的问题*/
                    InputStream inputStream = response.body().byteStream();
                    final String s = StringTool.inputStream2String(inputStream);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvContent.setText(s);
                        }
                    });
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * post登录
     */
    private void loginPost() {
        //http://localhost:8080/Login/servlet/LoginServelet?username=admin&password=123
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        OkHttpClient client = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("username", user);
        builder.add("password", password);
        Request request = new Request.Builder()
                .url(Constants.BASEURL + "Login/servlet/LoginServelet")
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getActivity(), "io读写异常...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                final String s = StringTool.inputStream2String(inputStream);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvContent.setText(s);
                    }
                });
            }
        });
    }

    /**
     * 加载图片
     */
    private void loadImg() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(Constants.IMGURL.IMGURL1)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getContext(), "图片加载失败..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mIvImg.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

    /**
     * 加载网络文本
     */
    private void loadTxt() {
//http://localhost:8080/Login/servlet/LoginServelet?username=admin&password=123
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.TXTURL.TXTURL1)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                final String s = response.body().string();
                System.out.println(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvContent.setText(s);
                    }
                });
            }
        });
    }
}
