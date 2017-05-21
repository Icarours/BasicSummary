package com.syl.commonactivity.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.data.Constants;
import com.syl.commonactivity.utils.StringTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe 天气预报
 * 1.HttpURLConnection
 * 2.使用JSONArray/JSONObject解析数据
 * @Called
 */

public class WeatherFragment extends BaseFragment implements View.OnClickListener {

    private static final int QUERY_OK = 1;
    private static final int QUERY_NO_CITY = 2;
    private static final int QUERY_NO_URL = 3;
    private static final int QUERY_NO_IO = 4;
    private static final int QUERY_NO_JSON = 5;
    private View mRootView;
    private EditText mEtCity;
    private TextView mTvResult;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case QUERY_OK:
                    JSONArray jsonArray = (JSONArray) msg.obj;
                    try {
                        String str = jsonArray.getJSONObject(0).toString();
                        str += jsonArray.getJSONObject(1).toString();
                        str += jsonArray.getJSONObject(2).toString();
                        str += jsonArray.getJSONObject(3).toString();
                        str += jsonArray.getJSONObject(4).toString();
                        mTvResult.setText(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "json异常..", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case QUERY_NO_CITY:
                    Toast.makeText(getContext(), "没有该城市..", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_URL:
                    Toast.makeText(getContext(), "url异常...", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_IO:
                    Toast.makeText(getContext(), "io读写异常...", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_JSON:
                    Toast.makeText(getContext(), "json异常..", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_weather, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtCity = (EditText) mRootView.findViewById(R.id.et_city);
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_query_weather).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        String city = mEtCity.getText().toString();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(getContext(), "城市名称不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            //对汉字进行指定格式的编码
            city = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        queryWeather(city);
    }

    /**
     * 查询天气
     *
     * @param city
     */
    private void queryWeather(final String city) {
        new Thread() {
            @Override
            public void run() {
                String path = Constants.WEATHERURL + city;
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String jsonData = StringTool.inputStream2String(inputStream);
                        JSONObject jsonObject = new JSONObject(jsonData);
                        JSONObject data = jsonObject.getJSONObject("data");
                        String desc = jsonObject.getString("desc");
                        if ("OK".equals(desc)) {
                            JSONArray jsonArray = data.getJSONArray("forecast");
                            Message msg = Message.obtain();
                            msg.what = QUERY_OK;
                            msg.obj = jsonArray;
                            mHandler.sendMessage(msg);
                        } else {
                            Message msg = Message.obtain();
                            msg.what = QUERY_NO_CITY;
                            mHandler.sendMessage(msg);
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = QUERY_NO_URL;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = QUERY_NO_IO;
                    mHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = QUERY_NO_JSON;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
}
