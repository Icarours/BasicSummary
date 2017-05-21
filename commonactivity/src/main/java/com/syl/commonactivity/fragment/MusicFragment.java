package com.syl.commonactivity.fragment;

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

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2017/4/27.
 *
 * @Describe 使用MediaPlayer播放本地音乐
 * @Called
 */

public class MusicFragment extends BaseFragment implements View.OnClickListener {

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
            mRootView = View.inflate(getContext(), R.layout.fragment_music, null);
        }
        //在根布局创建的时候就创建MediaPlayer
        if (mMediaPlayer == null) {
            synchronized (MusicFragment.class) {
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
        String etPath = mEtPath.getText().toString();
        if (TextUtils.isEmpty(etPath)) {
            Toast.makeText(getContext(), "文件路径不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(etPath);
        if (!file.exists()) {
            Toast.makeText(getContext(), "文件路径非法..", Toast.LENGTH_SHORT).show();
            return;
        }
        /*if (currentPath.equals(etPath)) {
            return;
        } else {
            mMediaPlayer.release();
        }*/
        try {
            mMediaPlayer.setDataSource(etPath);
            //在主线程中缓冲
            mMediaPlayer.prepare();
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });
//            currentPath = etPath;
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //播放按钮点击之后变灰,不可用
        mBtnPlay.setEnabled(false);
    }

    private void btnPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mBtnPause.setText("播放");
        } else {
            mMediaPlayer.start();
            mBtnPause.setText("继续");
        }
        //暂停按钮点击之后,播放按钮回复正常
        mBtnPlay.setEnabled(true);
    }

    //停止按钮点击之后,播放按钮回复正常
    private void btnStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        mBtnPlay.setEnabled(true);
    }
}
