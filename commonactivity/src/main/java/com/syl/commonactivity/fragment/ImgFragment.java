package com.syl.commonactivity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2017/4/26.
 *
 * @Describe 图片的一些处理操作
 * 平移(水平,竖直)
 * 镜像(水平,竖直)
 * 缩放
 * copyBitmap,canvas,Paint,Matrix原本可以使用同一个对象的,不过为了练习代码,多敲几次,就重新创建了
 * <p>
 * 注意:
 * 1.new Canvas(bitmap)构造方法中忘了传入bitmap,导致图片没有加载成功
 * <p>
 * 2.调用drawBitmap(mSrcBitmap, matrix, paint)方法时第一个参数是已经存在,加载了图片的Bitmap的mSrcBitmap,
 * 不能用刚创建的bitmap, 因为bitmap本来就是一个空的bitmap
 * @Called
 */

public class ImgFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.btn_load_img)
    Button mBtnLoadImg;
    @BindView(R.id.iv_src)
    ImageView mIvImg;
    @BindView(R.id.btn_img_rotate)
    Button mBtnImgRotate;
    @BindView(R.id.iv_target)
    ImageView mIvTarget;
    @BindView(R.id.btn_img_translation_x)
    Button mBtnImgTranslationX;
    @BindView(R.id.btn_img_translation_y)
    Button mBtnImgTranslationY;
    @BindView(R.id.btn_img_scale)
    Button mBtnImgScale;
    @BindView(R.id.btn_img_mirror_x)
    Button mBtnImgMirrorX;
    @BindView(R.id.btn_img_mirror_y)
    Button mBtnImgMirrorY;
    private Bitmap mSrcBitmap;
    private View mRootView;

    @Override
    public View initRootView() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mBtnLoadImg.setOnClickListener(this);
        mBtnImgRotate.setOnClickListener(this);
        mBtnImgTranslationX.setOnClickListener(this);
        mBtnImgTranslationY.setOnClickListener(this);
        mBtnImgScale.setOnClickListener(this);
        mBtnImgMirrorX.setOnClickListener(this);
        mBtnImgMirrorY.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_img:
                loadSrcImg();
                break;
            case R.id.btn_img_rotate:
                rotateImg();
                break;
            case R.id.btn_img_translation_x:
                imgTranslationX();
                break;
            case R.id.btn_img_translation_y:
                imgTranslationY();
                break;
            case R.id.btn_img_mirror_x:
                imgMirrorX();
                break;
            case R.id.btn_img_mirror_y:
                imgMirrorY();
                break;
            case R.id.btn_img_scale:
                imgScale();
                break;
            default:
                break;
        }
    }

    /**
     * 图片缩放
     */
    private void imgScale() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        System.out.println("matrix变换前==" + matrix);
        matrix.setScale(0.5f, 0.5f, mSrcBitmap.getWidth() * .5f, mSrcBitmap.getHeight() * .5f);
        System.out.println("matrix变换后==" + matrix);
        /**调用drawBitmap(mSrcBitmap, matrix, paint)方法时第一个参数是已经存在,加载了图片的Bitmap的mSrcBitmap,
         * 不能用刚创建的bitmap, 因为bitmap本来就是一个空的bitmap*/
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(bitmap);
    }

    /**
     * X轴镜像
     */
    private void imgMirrorY() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        System.out.println("matrix前===" + matrix);
        matrix.setScale(1, -1);
        matrix.postTranslate(0, mSrcBitmap.getHeight());
        System.out.println("matrix后===" + matrix);
        /**调用drawBitmap(mSrcBitmap, matrix, paint)方法时第一个参数是已经存在,加载了图片的Bitmap的mSrcBitmap,
         * 不能用刚创建的bitmap, 因为bitmap本来就是一个空的bitmap*/
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(bitmap);
    }

    /**
     * 镜像Y轴
     */
    private void imgMirrorX() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        System.out.println("matrix前===" + matrix);
        matrix.setScale(-1f, 1f);
        System.out.println("matrix后===" + matrix);
        //矩阵转换后图片跑到了左下角,将其还原到右下角
        matrix.postTranslate(mSrcBitmap.getWidth(), 0);
        /**调用drawBitmap(mSrcBitmap, matrix, paint)方法时第一个参数是已经存在,加载了图片的Bitmap的mSrcBitmap,
         * 不能用刚创建的bitmap, 因为bitmap本来就是一个空的bitmap*/
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(bitmap);

    }

    /**
     * y轴平移
     */
    private void imgTranslationY() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        System.out.println("matrix前===" + matrix);
        matrix.setTranslate(0, height * .5f);
        System.out.println("matrix后===" + matrix);
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(bitmap);
    }

    /**
     * x轴平移
     */
    private void imgTranslationX() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap bitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();

        matrix.setTranslate(100, 0);
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(bitmap);

        /*------------------ 为什么上面的代码不能正常显示图片 -----------------*/
        /*//1.复制原图,在复制后的图片进行操作
        Bitmap translateBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        //2.创建画布
        Canvas canvas = new Canvas(translateBitmap);
        //3.创建画笔
        Paint paint = new Paint();
        //4.创建矩阵
        Matrix matrix = new Matrix();
        //在矩阵中平移
        matrix.setTranslate(100, 100);
        //5.在画布上作画
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        //6.在目标ImageView上显示画好的图片
        mIvTarget.setImageBitmap(translateBitmap);*/
    }

    /**
     * 旋转图片
     * 屏幕坐标系:
     * 右x轴正向,
     * 下y轴正向
     */
    private void rotateImg() {
        if (mSrcBitmap == null) {
            loadSrcImg();
        }
        Bitmap copyBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), mSrcBitmap.getConfig());
        Canvas canvas = new Canvas(copyBitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        System.out.println("matrix变换前==" + matrix);
        //角度顺时针
        matrix.setRotate(60, mSrcBitmap.getWidth() / 2, mSrcBitmap.getHeight() / 2);
        System.out.println("matrix变换后==" + matrix.toString());
        canvas.drawBitmap(mSrcBitmap, matrix, paint);
        mIvTarget.setImageBitmap(copyBitmap);
    }

    /**
     * 加载本地图片
     */
    private void loadSrcImg() {
        //        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "mm4.jpg");
//        mSrcBitmap = BitmapFactory.decodeFile("/mnt/sdcard/" + "mm4.jpg");
        mSrcBitmap =BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.mm1);
        System.out.println("ResourcesPath=="+getActivity().getResources());
        mIvImg.setImageBitmap(mSrcBitmap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null && mRootView == null) {
            mRootView = View.inflate(getContext(), R.layout.fragment_img, null);
            ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }
}
