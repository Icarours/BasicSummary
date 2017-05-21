package com.syl.commonactivity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.syl.commonactivity.R;


/**
 * Created by Lasting on 2017/5/11.
 * 设置圆形图片边框
 */

public class VariedImageView extends ImageView {
    private final int COLORDRAWABLE_BITMAPSIZE = 2;

    private boolean mBorderOverlay;

    public boolean isBorderOverlay() {
        return mBorderOverlay;
    }

    public void setBorderOverlay(boolean borderOverlay) {
        mBorderOverlay = borderOverlay;
    }

    public int getBorderThick() {
        return mBorderThick;
    }

    public void setBorderThick(int borderThick) {
        mBorderThick = borderThick;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
    }

    private int mBorderThick;//边框厚度
    private int mBorderColor;

    private Paint mBitmapPaint;
    private RectF mBitmapRectF;

    private Paint mBorderPaint;
    private RectF mBorderRectF;

    public VariedImageView(Context context) {
        this(context, null);
    }

    public VariedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.VariedImageViewStyle);
    }

    public VariedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFromAttributes(attrs, defStyleAttr, 0);
    }

    private void initFromAttributes(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VariedImageView, defStyleAttr, defStyleRes);
        this.mBorderOverlay = typedArray.getBoolean(R.styleable.VariedImageView_borderOverlay, false);
        this.mBorderColor = typedArray.getColor(R.styleable.VariedImageView_borderColor, Color.GRAY);
        this.mBorderThick = typedArray.getDimensionPixelSize(R.styleable.VariedImageView_borderThick, 0);
        typedArray.recycle();
    }

    //*通过onSizeChanged父ViewGroup测量出本View大小后再开始进行初始化
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.refresh();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable == null) {
            this.clear();
        }
        this.refresh();
    }

    private void clear() {
        mBitmapPaint = null;
        mBitmapRectF = null;
        mBorderPaint = null;
        mBorderRectF = null;
    }

    public void refresh() {
        buildVariedImage(getDrawable());
    }

    private void buildVariedImage(Drawable drawable) {
        if (drawable != null) {

            if (mBitmapPaint == null) {
                mBitmapPaint = new Paint();
                mBitmapPaint.setAntiAlias(true);//反锯齿
                mBitmapRectF = new RectF();
            }

            if (mBorderThick != 0 && mBorderPaint == null) {
                mBorderPaint = new Paint();
                mBorderPaint.setAntiAlias(true);//反锯齿
                mBorderPaint.setStyle(Paint.Style.STROKE);//空心
                mBorderRectF = new RectF();
            }

            //计算
            mBitmapRectF.set(0, 0, getWidth(), getHeight());
            if (mBorderThick != 0) {
                mBorderRectF.set(0, 0, getWidth(), getHeight());
                mBorderPaint.setColor(mBorderColor);//画笔颜色
                mBorderPaint.setStrokeWidth(mBorderThick);//边缘宽度
                mBorderRectF.inset(mBorderThick / 2, mBorderThick / 2);
                if (mBorderOverlay == false) {
                    mBitmapRectF.inset(mBorderThick, mBorderThick);
                }
            }

            //缩放
            Bitmap bitmap = toBitmap(drawable);
            if (bitmap != null) {
                BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                Matrix matrix = new Matrix();
                float scaleX = mBitmapRectF.width() / bitmap.getWidth();
                float scaleY = mBitmapRectF.height() / bitmap.getHeight();
                matrix.setScale(scaleX, scaleY);
                matrix.postTranslate(mBorderThick, mBorderThick);
                bitmapShader.setLocalMatrix(matrix);
                mBitmapPaint.setShader(bitmapShader);
                //重绘
                invalidate();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawOval(mBitmapRectF, mBitmapPaint);
            if (mBorderThick != 0) {
                canvas.drawOval(mBorderRectF, mBorderPaint);
            }
        }
    }

    private Bitmap toBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_BITMAPSIZE, COLORDRAWABLE_BITMAPSIZE, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}