package com.syl.commonactivity.fragment;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/5/7.
 *
 * @Describe 获得本机分辨率
 * @Called
 */

public class DisplayFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private TextView mTvResult;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_display, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_display).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        mTvResult.setText("width==" + width + ";height==" + height);
        //mTvResult.setText(";height=="+getActivity().findViewById(R.id.detail_activity_layout).getHeight());
    }
}
