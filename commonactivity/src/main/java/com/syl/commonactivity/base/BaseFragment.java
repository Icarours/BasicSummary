package com.syl.commonactivity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bright on 2017/4/19.
 *
 * @Describe
 * @Called
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//初始化Fragment页面显示之前的一些数据
        initView();//初始化Fragment页面中的视图
        initListener();//设置监听
    }

    /**
     * 必须提供一个根视图
     *
     * @return
     */
    public abstract View initRootView();

    /**
     * 交给子类实现
     */
    public abstract void initView();

    /**
     * 交给子类实现
     */
    public abstract void initListener();

    /**
     * 交给子类实现
     */
    public abstract void initData();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null && mRootView == null) {
            mRootView = initRootView();
        }
        return mRootView;
    }
}
