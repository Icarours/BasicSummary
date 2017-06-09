package com.syl.commonactivity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.syl.commonactivity.R;
import com.syl.commonactivity.config.Constants;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * author   Bright
 * date     2017/5/8 11:05
 * desc
 * vitamio播放器
 */
public class VitamioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
        vitamio();

    }

    private void vitamio() {
        //        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final VideoView vv = (VideoView) findViewById(R.id.vv);
        vv.setVideoPath(Constants.VIDEOURL1); //设置播放路径
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
// 设置video的控制器
        vv.setMediaController(new MediaController(this));
    }
}
