package com.syl.commonactivity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe 调用相机拍照, 然后将拍好的照片展示出来
 * @Called
 */

public class CameraFragment extends BaseFragment implements View.OnClickListener {

    private static final int CAMERAFRAGMENT_BTN_CAMERA = 211;//CameraFragment请求相机拍照的请求码
    private View mRootView;
    private ImageView mIvCamera;
    private File mFile;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_camera, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mIvCamera = (ImageView) mRootView.findViewById(R.id.iv_camera);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_camera).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        //设置打开相机的意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        //设置图片的名称
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
        //打开相机
        startActivityForResult(intent, CAMERAFRAGMENT_BTN_CAMERA);
    }

    /**
     * 返回之后待会上一个Activity中的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 211 && resultCode == RESULT_OK) {
            if (mFile.exists()) {
                //解析图片文件
                Bitmap bitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath());
                mIvCamera.setImageBitmap(bitmap);
            }
        }
    }
}
