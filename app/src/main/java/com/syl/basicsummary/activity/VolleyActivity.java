package com.syl.basicsummary.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syl.basicsummary.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_StringRequest)
    Button mBtnStringRequest;
    @BindView(R.id.btn_JsonObjectRequest)
    Button mBtnJsonObjectRequest;
    @BindView(R.id.btn_JsonArrayRequest)
    Button mBtnJsonArrayRequest;
    @BindView(R.id.btn_ImageRequest)
    Button mBtnImageRequest;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.activity_volley)
    LinearLayout mActivityVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {
        mTv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void initListener() {
        mBtnStringRequest.setOnClickListener(this);
        mBtnJsonObjectRequest.setOnClickListener(this);
        mBtnJsonArrayRequest.setOnClickListener(this);
        mBtnImageRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_StringRequest:
                btn_StringRequest();
                break;
            case R.id.btn_JsonObjectRequest:
                btn_JsonObjectRequest();
                break;
            case R.id.btn_JsonArrayRequest:
                btn_JsonArrayRequest();
                break;
            case R.id.btn_ImageRequest:
                btn_ImageRequest();
                break;
            default:
                break;
        }
    }

    /**
     * ImageRequest请求
     */
    private void btn_ImageRequest() {
        String url = "http://f.hiphotos.baidu.com/image/pic/item/d62a6059252dd42ab559856b073b5bb5c8eab89a.jpg";
        //1.创建Request请求
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImg.setImageBitmap(response);
            }
        }, mImg.getWidth(), mImg.getHeight(), ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error--" + error.getMessage().toString());
            }
        });
        //2.创建RequestQueue请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //3.将Request请求加入RequestQueue发起网络请求
        requestQueue.add(request);
    }

    /**
     * JsonArrayRequest请求
     */
    private void btn_JsonArrayRequest() {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=";
        String city = "深圳";
        try {
            //URL需要手动编码
            String encodeCity = URLEncoder.encode(city, "UTF-8");
            url = url + encodeCity;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("response----" + response);
                mTv.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error----" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    /**
     * JsonObjectRequest请求
     */
    private void btn_JsonObjectRequest() {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=";
        String city = "深圳";
        try {
            //URL需要手动编码
            String encodeCity = URLEncoder.encode(city, "UTF-8");
            url = url + encodeCity;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response----" + response);
                mTv.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error----" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    /**
     * StringRequest请求
     */
    private void btn_StringRequest() {
        String url = "http://blog.csdn.net/guolin_blog/article/details/17482095/";
        //1.创建StringRequest请求
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //使用WebView展示加载成功的网页
                mWebView.loadDataWithBaseURL(null, response, "text/html", "utf-8", null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error--" + error.getMessage().toString());
            }
        });
        //2.创建RequestQueue队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //3.把StringRequest请求加入RequestQueue请求队列
        requestQueue.add(request);
    }
}
