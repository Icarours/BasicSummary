package com.syl.commonactivity.fragment;

import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/27.
 *
 * @Describe
 * 声音池
 * @Called
 */

public class SoundPoolFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;

    @Override
    public View initRootView() {

        mRootView = View.inflate(getContext(), R.layout.fragment_sound_pool, null);
        return mRootView;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_sound_pool).setOnClickListener(this);
    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        SoundPool pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        int loadId = pool.load(getContext(), R.raw.shoot, 0);
        pool.play(loadId, 1f, 1f, 0, 0, 2f);
    }
}
