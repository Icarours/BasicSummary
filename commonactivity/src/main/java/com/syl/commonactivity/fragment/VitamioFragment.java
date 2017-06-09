package com.syl.commonactivity.fragment;

import android.view.View;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.config.Constants;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Bright on 2017/5/8.
 *
 * @Describe
 * Vitamio 播放器
 * @Called
 */

public class VitamioFragment extends BaseFragment {

    private View mRootView;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_vitamio, null);
        final VideoView vv = (VideoView) mRootView.findViewById(R.id.vv);
        vv.setVideoPath(Constants.VIDEOURL); //设置播放路径
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
// 设置video的控制器
        vv.setMediaController(new MediaController(getContext()));
        return mRootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
