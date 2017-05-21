package com.syl.commonactivity.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.data.Constants;

import java.io.IOException;

import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
/**
 * author   Bright
 * date     2017/5/12 20:56
 * desc
 * IjkPlayer
 * 直播
 */
public class IjkPlayerActivity extends AppCompatActivity {

    private AndroidMediaPlayer mMediaPlayer;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_player);
        mMediaPlayer = new AndroidMediaPlayer();
        mMediaPlayer.setOnPreparedListener(prepareListener);
        SurfaceView svIjk = (SurfaceView) findViewById(R.id.sv_ijk);
        mHolder = svIjk.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    String  path = Constants.VIDEOURL;
                    mMediaPlayer.setDisplay(holder);
                    mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(path));
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mMediaPlayer.release();
                mMediaPlayer=null;
            }
        });
    }

    IMediaPlayer.OnPreparedListener prepareListener =new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            mMediaPlayer.start();
            /*Canvas canvas = mHolder.lockCanvas();
            TextView tv = new TextView(getApplicationContext());
            tv.setText("eeeeee");
            Bitmap bitmap = Bitmap.createBitmap(tv.getWidth(), tv.getHeight(), Bitmap.Config.ARGB_8888);
            canvas.drawBitmap(bitmap,new Matrix(),new Paint());*/
        }
    };
}
