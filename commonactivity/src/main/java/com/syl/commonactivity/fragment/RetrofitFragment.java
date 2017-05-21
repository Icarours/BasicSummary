package com.syl.commonactivity.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.bean.DataBean;
import com.syl.commonactivity.bean.IPBean;
import com.syl.commonactivity.data.Constants;
import com.syl.commonactivity.interface_.IPInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bright on 2017/5/9.
 *
 * @Describe
 * @Called
 */

public class RetrofitFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private EditText mEtIp;
    private TextView mTvResult;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_retrofit, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtIp = (EditText) mRootView.findViewById(R.id.et_ip);
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_query_ip).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query_ip:
                queryIp();
                break;
            default:
                break;
        }
    }

    private void queryIp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.IPURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPInterface ipInterface = retrofit.create(IPInterface.class);
        Call<IPBean> call = ipInterface.getIp("183.39.232.133");
        call.enqueue(new Callback<IPBean>() {
            @Override
            public void onResponse(Call<IPBean> call, Response<IPBean> response) {
                IPBean body = response.body();
                DataBean data = body.getData();
                StringBuffer sb = new StringBuffer();
                sb.append(data.getArea()+"\r\n");
                sb.append(data.getArea_id()+"\r\n");
                sb.append(data.getCity()+"\r\n");
                sb.append(data.getCity_id()+"\r\n");
                sb.append(data.getIsp()+"\r\n");
                mTvResult.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<IPBean> call, Throwable t) {
                mTvResult.setText("请求失败");
            }
        });
    }
}
