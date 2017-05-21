package com.syl.basicsummary.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.syl.basicsummary.base.BaseFragment;
import com.syl.basicsummary.utils.UIUtils;

/**
 * Created by j3767 on 2017/2/8.
 *
 * @Describe
 * @Called
 */

public class MovieFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    public void initData() {

    }
}
