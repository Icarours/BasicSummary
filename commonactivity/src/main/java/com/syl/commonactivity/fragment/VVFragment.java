package com.syl.commonactivity.fragment;

import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.data.Constants;

/**
 * Created by Bright on 2017/4/27.
 *
 * @Describe
 * 使用VideoView播放本地视频
 * @Called
 */

public class VVFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private VideoView mVideoView;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_vv, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mVideoView = (VideoView) mRootView.findViewById(R.id.vv);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_vv_start).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        startVV();
    }

    /**
     * 播放视频
     */
    private void startVV() {
        //设置路径
//        mVideoView.setVideoPath("/mnt/sdcard/" + "cr.mp4");
        mVideoView.setVideoPath(Constants.VIDEOURL);
        //创建MediaController
        MediaController mediaController = new MediaController(getContext());

        //指定MediaController控制那个mVideoView
        mediaController.setAnchorView(mVideoView);
        //给mVideoView设置MediaController
        mVideoView.setMediaController(mediaController);
        mVideoView.start();
    }
}
