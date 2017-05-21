package com.syl.basicsummary.activity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.syl.basicsummary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/13 11:28
 * desc
 * 直接从app文件中加载视频
 */
public class AudioVideoActivity extends AppCompatActivity {

    @BindView(R.id.vv)
    VideoView mVv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_audio_video);
        ButterKnife.bind(this);
        //设置Activity为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    /**
     * 加载app中的音视频文件
     */
    private void loadAudio() {
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.my_video_file;
        mVv.setVideoURI(Uri.parse(uri));
        mVv.setMediaController(new MediaController(AudioVideoActivity.this));
        mVv.requestFocus();
        mVv.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_item1:
                loadAudio();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
