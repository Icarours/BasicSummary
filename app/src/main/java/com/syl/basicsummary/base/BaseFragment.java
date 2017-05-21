package com.syl.basicsummary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by j3767 on 2017/2/8.
 *
 * @Describe
 * @Called
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * @des 初始化
     */
    private void init() {

    }

    private void initListener() {

    }

    /**
     * @return
     * @des 初始化视图, 必须实现, 不知道具体实现, 交给子类实现
     */
    public abstract View initView();

    /**
     * 初始化数据,必须实现,不知道具体实现,交给子类实现
     */
    public abstract void initData();
}
