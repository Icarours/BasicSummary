package com.syl.commonactivity.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.activity.VideoJumpActivity;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.utils.StringTool;

import java.io.IOException;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe 使用SurfaceView播放视频
 * 播放视频的代码写在
 * SurfaceHolder.Callback()后面
 * 对SurfaceView的监听,SurfaceView的一些状态记录在surfaceDestroyed(SurfaceHolder holder)方法中
 * @Called
 */

public class SVVideoFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private SurfaceView mSvVideo;
    private MediaPlayer mMediaPlayer;
    private int mPosition;
    private SurfaceHolder mHolder;
    private SeekBar mSeekBarTest;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_sv_video, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mSvVideo = (SurfaceView) mRootView.findViewById(R.id.sv_video);
        mSeekBarTest = (SeekBar) mRootView.findViewById(R.id.seekbar_test);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_sv_video).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_sv_video_new_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoJumpActivity.class);
                mMediaPlayer.pause();
                StringTool.myMediaPlayer=mMediaPlayer;
                startActivity(intent);
            }
        });
        mSeekBarTest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMediaPlayer.seekTo(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        Log.d(this.getClass().getSimpleName(), "btn_sv_video was clicked..");

        mHolder = mSvVideo.getHolder();
        try {
            mMediaPlayer.setDisplay(mHolder);
            mMediaPlayer.setDataSource("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
            mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if (what==MediaPlayer.MEDIA_INFO_BUFFERING_START){
                        Toast.makeText(getActivity(),"缓冲开始",Toast.LENGTH_SHORT).show();
                    }

                    if (what==MediaPlayer.MEDIA_INFO_BUFFERING_END){
                        Toast.makeText(getActivity(),"缓冲结束",Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mSeekBarTest.setMax(mp.getDuration());
                }
            });
            mMediaPlayer.prepare();
            mMediaPlayer.seekTo(mPosition);
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                SharedPreferences sp = getActivity().getPreferences(0);
                SharedPreferences.Editor edit = sp.edit();
                int currentPosition = mMediaPlayer.getCurrentPosition();
                edit.putInt("position", currentPosition);
                edit.apply();
//                mMediaPlayer.release();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getActivity().getPreferences(0);
        mPosition = sp.getInt("position", 0);
        if (mMediaPlayer == null) {
            synchronized (SVVideoFragment.class) {
                mMediaPlayer = new MediaPlayer();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
