package com.syl.viewdemo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Bright on 2017/5/18.
 *
 * @Describe
 * @Called
 */

public class BaseFragment extends Fragment {
    public static final String BASEFRAGMENT_NUMBER = "BaseFragment_number";//Fragment 的编号

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText(this.getClass().getSimpleName());
        textView.setTextSize(30);
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
