package com.syl.commonactivity.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Bright on 2017/5/12.
 *
 * @Describe
 * 自定义圆形图片
 * @Called
 */

public class CircleImgView extends ImageView {
    public CircleImgView(Context context) {
        super(context);
    }

    public CircleImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        rectF.inset(20 / 2, 20 / 2);
        canvas.drawOval(rectF, paint);

        RectF rectF2 = new RectF(0, 0, getWidth(), getHeight());
        rectF2.inset(20, 20);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawOval(rectF2, paint);

        //Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Drawable drawable = getDrawable();

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Matrix matrix = new Matrix();
        matrix.setScale(rectF2.width() / bitmap.getWidth(), rectF2.height() / bitmap.getHeight());
        matrix.postTranslate(20,20);
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        Paint paint1 = new Paint();
        paint1.setShader(bitmapShader);
        canvas.drawOval(rectF2,paint1);
    }
}
