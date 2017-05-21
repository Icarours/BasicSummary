package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/1 9:44
 * desc
 * 解析服务器返回的数据
 * 数据解析:
 * Gson
 * JSONObject
 * JSONArray
 */
public class DataDecodeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.activity_data_decode)
    LinearLayout mActivityDataDecode;
    @BindView(R.id.btn_Gson)
    Button mBtnGson;
    @BindView(R.id.btn_JSONObject)
    Button mBtnJSONObject;
    @BindView(R.id.btn_JSONArray)
    Button mBtnJSONArray;
    private List<String> mStringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_decode);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }


    private void initData() {
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList));
    }

    private void initListener() {
        mBtnGson.setOnClickListener(this);
        mBtnJSONArray.setOnClickListener(this);
        mBtnJSONObject.setOnClickListener(this);
    }

    private void initView() {
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /**
     * 加载数据
     * 使用JSONObject和JSONArray解析数据
     */
    private void loadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String url = "http://wthrcdn.etouch.cn/weather_mini?city=";
                    String city = "深圳";
                    try {
                        //URL需要手动编码
                        String encodeCity = URLEncoder.encode(city, "UTF-8");
                        url = url + encodeCity;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        InputStream inputStream = connection.getInputStream();
                        String data = StringUtil.convertStreamToString(inputStream);
                        JSONObject jsonObject = new JSONObject(data);
                        String desc = jsonObject.getString("desc");
                        if ("OK".equals(desc)) {
                            JSONObject jsonObjectData = (JSONObject) jsonObject.get("data");
                            final String yes = jsonObjectData.get("yesterday").toString();
                            JSONArray forecastJsonArr = jsonObjectData.getJSONArray("forecast");
                            String firstDay = forecastJsonArr.get(0).toString();
                            String secondDay = forecastJsonArr.get(1).toString();
                            String thirdDay = forecastJsonArr.get(2).toString();
                            String fourthDay = forecastJsonArr.get(3).toString();
                            String fifthDay = forecastJsonArr.get(4).toString();
                            final String content = "yes----" + yes + "\r\n" +
                                    "firstDay----" + firstDay + "\r\n" +
                                    "secondDay----" + secondDay + "\r\n" +
                                    "thirdDay----" + thirdDay + "\r\n" +
                                    "fourthDay----" + fourthDay + "\r\n" +
                                    "fifthDay----" + fifthDay + "\r\n";
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTv.setText(content);
                                }
                            });
                        } else {
                            System.out.println("error==========");
                        }
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Gson:
                break;
            case R.id.btn_JSONObject:
                loadData();
                break;
            case R.id.btn_JSONArray:
                break;
            default:
                break;
        }
    }
}
