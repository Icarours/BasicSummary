package com.syl.commonactivity.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe SurfaceView简单举例
 * @Called
 */

public class SVFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private SurfaceView mSvCircle;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_sv, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mSvCircle = (SurfaceView) mRootView.findViewById(R.id.sv_circle);

        SurfaceHolder holder = mSvCircle.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 130; i++) {
                            int radius = 5;
                            Canvas canvas = holder.lockCanvas();
                            canvas.drawColor(Color.BLACK);
                            Paint paint = new Paint();
                            paint.setColor(Color.RED);
                            canvas.drawCircle(200, 200, radius+i, paint);
                            holder.unlockCanvasAndPost(canvas);
                            SystemClock.sleep(10);
                        }
                    }
                }.start();
                Log.d(this.getClass().getSimpleName(), "surfaceCreated===" + Thread.currentThread().getName());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(this.getClass().getSimpleName(), "surfaceChanged===" + Thread.currentThread().getName());
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(this.getClass().getSimpleName(), "surfaceDestroyed===" + Thread.currentThread().getName());
            }
        });
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_circle).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    /**
     * 使用SurfaceView在子线程中画圆
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 130; i++) {
                    int radius = 5;
                    SurfaceHolder holder = mSvCircle.getHolder();
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    canvas.drawCircle(200, 200, radius + i, paint);
                    holder.unlockCanvasAndPost(canvas);
                    SystemClock.sleep(10);
                }
            }
        }.start();
    }
}
