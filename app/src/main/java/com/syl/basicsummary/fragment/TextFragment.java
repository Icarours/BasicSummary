package com.syl.basicsummary.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.basicsummary.utils.UIUtils;

/**
 * Created by j3767 on 2017/2/9.
 *
 * @Describe
 * @Called
 */

public class TextFragment extends Fragment {
    private TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        mTextView = new TextView(UIUtils.getContext());
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextColor(Color.RED);
        initData(title);
        return mTextView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData(String title) {
        mTextView.setText(this.getClass().getSimpleName() + title);
    }
}
