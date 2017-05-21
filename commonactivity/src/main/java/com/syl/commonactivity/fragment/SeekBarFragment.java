package com.syl.commonactivity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe SeekBar和CheckBox的简单用法
 * 数据的存储以及数据的回显
 * @Called
 */
public class SeekBarFragment extends BaseFragment {

    private View mRootView;
    private CheckBox mCb;
    private SeekBar mSeekbar;
    private SharedPreferences mConfig;
    boolean isRecord;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_seekbar, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mCb = (CheckBox) mRootView.findViewById(R.id.cb);
        mSeekbar = (SeekBar) mRootView.findViewById(R.id.seekbar);

        if (isRecord) {
            //回显
            mCb.setChecked(mConfig.getBoolean("isChecked", false));
            mSeekbar.setProgress(mConfig.getInt("progress", 30));
        }
    }

    @Override
    public void initListener() {
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor edit = mConfig.edit();
                edit.putBoolean("isChecked", isChecked);
                edit.apply();
            }
        });
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                SharedPreferences.Editor edit = mConfig.edit();
                edit.putInt("progress", progress);
                edit.apply();
                isRecord = true;
            }
        });
    }

    @Override
    public void initData() {
        mConfig = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
    }
}
