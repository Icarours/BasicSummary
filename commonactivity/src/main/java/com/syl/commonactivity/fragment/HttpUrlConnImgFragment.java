package com.syl.commonactivity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.config.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe 使用HttpURLConnection加载图片
 * @Called
 */

public class HttpUrlConnImgFragment extends BaseFragment implements View.OnClickListener {
    private static final int LOAD_OK = 1;
    private static final int LOAD_IO_FAIL = 2;
    private static final int LOAD_URL_FAIL = 3;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_OK:
                    mIvImg.setImageBitmap((Bitmap) msg.obj);
                    break;
                case LOAD_IO_FAIL:
                    Toast.makeText(getContext(), "io读写错误..", Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_URL_FAIL:
                    Toast.makeText(getContext(), "URL地址错误..", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private View mRootView;
    private ImageView mIvImg;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_httpurl_img, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mRootView.findViewById(R.id.btn_load_img).setOnClickListener(this);
        mIvImg = (ImageView) mRootView.findViewById(R.id.iv_src);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_img:
                loadImg();
                break;
            default:
                break;
        }
    }

    /**
     * 加载图片
     */
    private void loadImg() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String path = Constants.IMGURL.IMGURL1;
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5 * 1000);
                    conn.setReadTimeout(5 * 1000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.what = LOAD_OK;
                        msg.obj = bitmap;
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
