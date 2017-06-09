package com.syl.commonactivity.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syl.commonactivity.R;
import com.syl.commonactivity.adapter.WeatherAdapter;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.bean.Weather;
import com.syl.commonactivity.config.Constants;
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
import java.util.List;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe 天气预报2
 * 1.HttpURLConnection
 * 2.Gson数据解析
 * 3.ListView
 * <p>
 * 有时候会出现一些意想不到的bug,检查不出来就重启程序,是在不行就重启电脑
 * @Called
 */

public class WeatherFragment2 extends BaseFragment implements View.OnClickListener {

    private static final int QUERY_OK = 1;
    private static final int QUERY_NO_CITY = 2;
    private static final int QUERY_NO_URL = 3;
    private static final int QUERY_NO_IO = 4;
    private static final int QUERY_NO_JSON = 5;
    private List<Weather> mWeatherList;
    private WeatherAdapter mAdapter;
    private View mRootView;
    private EditText mEtCity;
    private ListView mLvWeather;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case QUERY_OK:
                    JSONArray jsonArray = (JSONArray) msg.obj;
                    String jsonString = jsonArray.toString();
                    mWeatherList = jsonStr2bean(jsonString);
                    //在此处刷新UI
                    refreshData(mWeatherList);
                    break;
                case QUERY_NO_CITY:
                    Toast.makeText(getContext(), "城市不存在..", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_URL:
                    Toast.makeText(getContext(), "url地址不规范..", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_IO:
                    Toast.makeText(getContext(), "io读写异常...", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_NO_JSON:
                    Toast.makeText(getContext(), "json读写异常...", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


    private List<Weather> jsonStr2bean(String jsonString) {
        Gson gson = new Gson();
        List<Weather> weatherList = gson.fromJson(jsonString, new TypeToken<List<Weather>>() {
        }.getType());
        return weatherList;
    }

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_weather2, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtCity = (EditText) mRootView.findViewById(R.id.et_city);
        mLvWeather = (ListView) mRootView.findViewById(R.id.lv_weather);

        // refreshData(mWeatherList);
    }

    /**
     * 如果数据集发生改变,就调用该方法进行刷新
     *
     * @param mWeatherList
     */
    private void refreshData(List<Weather> mWeatherList) {
        if (mAdapter == null) {
            mAdapter = new WeatherAdapter(getActivity(), mWeatherList);
            mLvWeather.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
            city = URLEncoder.encode(city, "UTF-8");
            queryWeather(city);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                super.run();
                String path = Constants.WEATHERURL + city;
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String result = StringTool.inputStream2String(inputStream);
                        JSONObject jsonObject = new JSONObject(result);
                        String desc = jsonObject.getString("desc");
                        JSONObject data = jsonObject.getJSONObject("data");
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
                    Message msg = Message.obtain();
                    msg.what = QUERY_NO_IO;
                    mHandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = QUERY_NO_JSON;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
}
