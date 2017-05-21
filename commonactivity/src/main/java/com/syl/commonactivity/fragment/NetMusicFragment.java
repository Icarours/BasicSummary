package com.syl.commonactivity.fragment;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2017/4/27.
 *
 * @Describe 使用MediaPlayer播放网络音乐
 * 该Fragment的布局和MusicFragment的布局一样,就直接使用MusicFragment的布局了
 * @Called
 */

public class NetMusicFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.et_path)
    EditText mEtPath;
    @BindView(R.id.btn_play)
    Button mBtnPlay;
    @BindView(R.id.btn_pause)
    Button mBtnPause;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    private View mRootView;
    private MediaPlayer mMediaPlayer;

    @Override
    public View initRootView() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mBtnPlay.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = View.inflate(getContext(), R.layout.fragment_net_music, null);
        }
        //保证整个NetMusicFragment只有一个mMediaPlayer
        if (mMediaPlayer == null) {
            synchronized (NetMusicFragment.class) {
                if (mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                }
            }
        }
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                btnPlay();
                break;
            case R.id.btn_pause:
                btnPause();
                break;
            case R.id.btn_stop:
                btnStop();
                break;
            default:
                break;
        }
    }

//    String currentPath;

    private void btnPlay() {
        final String etPath = mEtPath.getText().toString();
        if (TextUtils.isEmpty(etPath)) {
            Toast.makeText(getContext(), "文件路径不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        /*if (currentPath.equals(etPath)) {
            return;
        } else {
            mMediaPlayer.release();
        }*/
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("正在加载中...");
        //进度条出现
        progressDialog.show();
        try {
            mMediaPlayer.setDataSource(etPath);
            //异步缓冲,在子线程中
            mMediaPlayer.prepareAsync();
            //监听播放资源是否准备完成
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //进度条隐藏
                    progressDialog.dismiss();
                    mMediaPlayer.start();
//                    currentPath = etPath;
                }
            });
            //监听是否播放完毕
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //播放完毕后循环播放
                    mMediaPlayer.seekTo(0);
                    mMediaPlayer.start();
                }
            });
            //监听播放过程中是否出错
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getContext(), "播放过程中出错..", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btnPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
    }

    private void btnStop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
