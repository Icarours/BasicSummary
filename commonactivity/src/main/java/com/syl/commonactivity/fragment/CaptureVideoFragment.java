package com.syl.commonactivity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe 使用手机自带的相机录制视频, 然后在VideoView中播放
 * @Called
 */

public class CaptureVideoFragment extends BaseFragment implements View.OnClickListener {

    private static final int CAPTUREVIDEOFRAGMENT_BTN_CAPTURE_VIDEO = 221;//调用录像机的请求码
    private View mRootView;
    private VideoView mVvVideo;
    private File mFile;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_capture_video, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mVvVideo = (VideoView) mRootView.findViewById(R.id.vv_video);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_capture_video).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        //请求录像的意图
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //创建存放录制好的视频的路径
        mFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".mp4");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, CAPTUREVIDEOFRAGMENT_BTN_CAPTURE_VIDEO);
    }

    /**
     * 接收跳转目标Activity传回的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //使用VideoView播放视频
        if (requestCode == CAPTUREVIDEOFRAGMENT_BTN_CAPTURE_VIDEO && resultCode == RESULT_OK) {
            if (mFile.exists()) {
                mVvVideo.setVideoPath(mFile.getAbsolutePath());
                MediaController mediaController = new MediaController(getContext());
                mediaController.setAnchorView(mVvVideo);
                mVvVideo.setMediaController(mediaController);
                mVvVideo.start();
            }
        }
    }
}
