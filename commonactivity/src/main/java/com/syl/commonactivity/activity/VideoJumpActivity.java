package com.syl.commonactivity.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.utils.StringTool;
/**
 * author   Bright
 * date     2017/5/8 11:28
 * desc
 * 视频跳转,无缝衔接
 */
public class VideoJumpActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mMediaPlayer= StringTool.myMediaPlayer;
        SurfaceView sv_test = (SurfaceView) findViewById(R.id.sv_test);
        sv_test.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mMediaPlayer.setDisplay(holder);
                mMediaPlayer.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
}
