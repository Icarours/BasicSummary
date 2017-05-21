package com.syl.commonactivity.fragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.util.List;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe 传感器
 * 光传感器
 * @Called
 */

public class SensorFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = SensorFragment.class.getSimpleName();
    private View mRootView;
    private TextView mTvSensor;
    private TextView mTvSensorOrientation;
    private TextView mTvSensorLight;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_sensor, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mTvSensorLight = (TextView) mRootView.findViewById(R.id.tv_sensor_light);
        mTvSensorOrientation = (TextView) mRootView.findViewById(R.id.tv_sensor_orientation);
        mTvSensor = (TextView) mRootView.findViewById(R.id.tv_sensor);
        //TextView设置显示多行文本
        mTvSensor.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTvSensorLight.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTvSensorOrientation.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_sensor).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_sensor_light).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_sensor_orientation).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        switch (v.getId()) {
            case R.id.btn_sensor://获取所有传感器
                allSensor(sensorManager);
                break;
            case R.id.btn_sensor_light://光传感器
                lightSensor(sensorManager);
                break;
            case R.id.btn_sensor_orientation:
                orientationSensor(sensorManager);
                break;
            default:
                break;
        }
    }

    /**
     * 方向传感器
     *
     * @param sensorManager
     */
    private void orientationSensor(SensorManager sensorManager) {
        Sensor orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
//				values[0]: 手机的Y轴与正北方向的夹角 ： . 0=North, 90=East, 180=South, 270=West
                int val = (int) event.values[0];

                if (val == 0) {
                    Log.d(TAG, "当前是正北方向:" + val);
                    mTvSensorOrientation.setText("当前是正北方向:" + val);
                } else if (val == 90) {
                    Log.d(TAG, "当前是正东方向:" + val);
                    mTvSensorOrientation.setText("当前是正东方向:" + val);
                } else if (val == 180) {
                    Log.d(TAG, "当前是正南方向:" + val);
                    mTvSensorOrientation.setText("当前是正南方向:" + val);
                } else if (val == 270) {
                    Log.d(TAG, "当前是正西方向:" + val);
                    mTvSensorOrientation.setText("当前是正西方向:" + val);
                } else if (val > 0 && val < 90) {
                    Log.d(TAG, "当前是东北方向:" + val);
                    mTvSensorOrientation.setText("当前是东北方向:" + val);
                } else if (val > 90 && val < 180) {
                    Log.d(TAG, "当前是东南方向:" + val);
                    mTvSensorOrientation.setText("当前是东南方向:" + val);
                } else if (val > 180 && val < 270) {
                    Log.d(TAG, "当前是西南方向:" + val);
                    mTvSensorOrientation.setText("当前是西南方向:" + val);
                } else if (val > 270 && val < 360) {
                    Log.d(TAG, "当前是西北方向:" + val);
                    mTvSensorOrientation.setText("当前是西北方向:" + val);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 获得所有传感器
     *
     * @param sensorManager
     */
    private void allSensor(SensorManager sensorManager) {
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer sb = new StringBuffer();
        for (Sensor sensor :
                sensorList) {
            sb.append(sensor.getName() + "--" + sensor.getType() + "\r\n");
        }
        mTvSensor.setText(sb.toString());
    }

    private void lightSensor(SensorManager sensorManager) {
        Sensor sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Log.d(this.getClass().getSimpleName(), "sensorLight==" + sensorLight.toString());
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float values = event.values[0];
                Log.d(this.getClass().getSimpleName(), "values==" + values);
                mTvSensorLight.setText("光强等级==" + values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
