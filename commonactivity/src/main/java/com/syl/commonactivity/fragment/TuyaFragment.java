package com.syl.commonactivity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2017/4/27.
 *
 * @Describe 涂鸦板
 * touch事件响应的位置与实际不符:mIvImg的位置与画布接收touch事件的位置不符,
 * 解决方法:mIvImg的宽高都设置为match_parent,将mIvImg的宽高作为画布的宽高
 * 但是,控件的渲染需要时间,要给mIvImg添加全局监听才行,将设置画布宽高的代码放在
 * 全局监听的回调方法里面,监听结束之后再移除监听
 * @Called
 */

public class TuyaFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.btn_red)
    Button mBtnRed;
    @BindView(R.id.btn_green)
    Button mBtnGreen;
    @BindView(R.id.btn_blue)
    Button mBtnBlue;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.sb_paint)
    SeekBar mSbPaint;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private View mRootView;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private float mDownX;
    private float mDownY;

    @Override
    public View initRootView() {
        return null;
    }

    @Override
    public void initView() {
        final ViewTreeObserver viewTreeObserver = mIvImg.getViewTreeObserver();
        //保证控件mIvImg绘制完成
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("onGlobalLayout()=====");
                mBitmap = Bitmap.createBitmap(mIvImg.getWidth(), mIvImg.getHeight(), Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                Log.d(this.getClass().getSimpleName(), "x==" + mIvImg.getWidth() + "--,y" + mIvImg.getHeight());
                Log.d(this.getClass().getSimpleName(), "x==" + mIvImg.getMeasuredWidth() + "--,y===" + mIvImg.getMeasuredHeight());
                //绘制完成后,取消监听
                mIvImg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        mPaint = new Paint();
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(0x33ff0000);//设置默认颜色
    }

    @Override
    public void initListener() {
        mBtnRed.setOnClickListener(this);
        mBtnGreen.setOnClickListener(this);
        mBtnBlue.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mIvImg.setOnTouchListener(this);
        mSbPaint.setOnSeekBarChangeListener(this);
    }

    @Override
    public void initData() {
      /*  WindowManager wm = getActivity().getWindowManager();
        Display defaultDisplay = wm.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = View.inflate(getContext(), R.layout.fragment_tuya, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                mPaint.setColor(Color.RED);
                break;
            case R.id.btn_green:
                mPaint.setColor(Color.GREEN);
                break;
            case R.id.btn_blue:
                mPaint.setColor(Color.BLUE);
                break;
            case R.id.btn_save:
                saveImg();
                break;
            default:
                break;
        }
    }

    /**
     * 保存图片
     */
    private void saveImg() {
        File file = new File("/mnt/sdcard/" + System.currentTimeMillis() + ".jpg");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            //必须要用Context调用sendBroadcast(intent)方法
            getActivity().sendBroadcast(intent);
            Toast.makeText(getContext(), "图片保存成功..", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
               // Log.d(this.getClass().getSimpleName(), "ACTION_DOWN---mDownX==" + mDownX + "---mDownY===" + mDownY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                mCanvas.drawLine(mDownX, mDownY, moveX, moveY, mPaint);
                mIvImg.setImageBitmap(mBitmap);

                //再次赋值起始位置
                mDownX = moveX;
                mDownY = moveY;
               // Log.d(this.getClass().getSimpleName(), "ACTION_MOVE---mDownX==" + mDownX + "---mDownY===" + mDownY +
                //        "moveX==" + moveX + "---moveY===" + moveY);
                break;
            case MotionEvent.ACTION_UP:
                //Log.d(this.getClass().getSimpleName(), "ACTION_UP");
                break;
            default:
                break;
        }
        //返回值是true代表自己消费to.uch事件,不再向下或者向上传递
        return true;
    }

    /*------------------ OnSeekBarChangeListener_start -----------------*/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //设置画笔宽度,
        mPaint.setStrokeWidth(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    /*------------------ OnSeekBarChangeListener_end -----------------*/
}
