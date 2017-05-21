package com.syl.viewdemo.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.syl.viewdemo.R;

/**
 * author   Bright
 * date     2017/5/19 18:25
 * desc
 * 常见的图形绘制
 */
public class DrawImgActivity extends AppCompatActivity {

    private ImageView mIvImg;
    private int mScreenWidth;
    private int mScreenHeight;
    private float mDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_img);

        mIvImg = (ImageView) findViewById(R.id.iv_img);
        getDensity();//获取屏幕密度

        getScreenSize();//获取屏幕的宽高
    }

    /**
     * 获取屏幕的宽高,尺寸
     */
    private void getScreenSize() {
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        System.out.println("width==" + mScreenWidth + "--height==" + mScreenHeight);
    }

    /**
     * 获取屏幕密度
     */
    private void getDensity() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mDensity = metrics.density;
        System.out.println("density==" + mDensity);
    }

    public void click(View view) {
        Bitmap bitmap;
        switch (view.getId()) {
            case R.id.btn_draw_img1:
                bitmap = btn_draw_img1(Paint.Style.FILL_AND_STROKE, 25);
                mIvImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_draw_img2:
                bitmap = btn_draw_img2(Paint.Style.FILL, 12);
                mIvImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_draw_img3:
                bitmap = btn_draw_img3(Paint.Style.STROKE, 25);
                mIvImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_draw_img4:
                bitmap = btn_draw_img4(Paint.Style.STROKE, 25);
                mIvImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_draw_img5:
                bitmap = btn_draw_img5();
                mIvImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_draw_img6:
                bitmap = btn_draw_img6();
                mIvImg.setImageBitmap(bitmap);
                break;
            default:
                break;
        }
    }
    /**
     * 贝塞尔曲线
     * @return
     */
    private Bitmap btn_draw_img6() {
        Bitmap bitmap = Bitmap.createBitmap(900, 1200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Path mPath = new Path();

//        float x1 = 0;
//        float y1 = 500;
//        PointF point1 = new PointF(x1, y1);
        float x2 = 500;
        float y2 = 550;
        PointF point2 = new PointF(x2, y2);
        float cx1 = 200;
        float cy1 = 200;
        PointF controllerPoint1 = new PointF(cx1, cy1);
        float x3=900;
        float y3=600;
        PointF point3 = new PointF(x3, y3);
        float cx2=680;
        float cy2=1000;
        PointF controllerPoint2 = new PointF(cx2, cy2);
        mPath.moveTo(point2.x,point2.y);
        mPath.quadTo(controllerPoint2.x,controllerPoint2.y,point3.x,point3.y);
//        mPath.lineTo(point2.x,point2.y);
        mPath.lineTo(point2.x, 900);
        mPath.lineTo(point3.x, 900);
        mPath.lineTo(point3.x, point3.y);
        paint.setColor(0x88ff6666);
//        paint.setColor(Color.TRANSPARENT);
        canvas.drawPath(mPath, paint);
        paint.setColor(Color.BLUE);
        paint.setColor(Color.RED);
        canvas.drawCircle(point2.x, point2.y, 10, paint);
//        paint.setColor(Color.GREEN);
//        canvas.drawCircle(controllerPoint1.x, controllerPoint1.y, 10, paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(point3.x, point3.y, 10, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(controllerPoint2.x, controllerPoint2.y, 10, paint);
        return bitmap;
    }

    /**
     * 贝塞尔曲线
     * @return
     */
    private Bitmap btn_draw_img5() {
        Bitmap bitmap = Bitmap.createBitmap(900, 1200, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        Path mPath = new Path();

        float x1 = 0;
        float y1 = 500;
        PointF point1 = new PointF(x1, y1);
        float x2 = 480;
        float y2 = 550;
        PointF point2 = new PointF(x2, y2);
        float cx1 = 200;
        float cy1 = 200;
        PointF controllerPoint1 = new PointF(cx1, cy1);
        float x3=900;
        float y3=500;
        PointF point3 = new PointF(x3, y3);
        float cx2=700;
        float cy2=900;
        PointF controllerPoint2 = new PointF(cx2, cy2);
        mPath.moveTo(point1.x, point1.y);
        //quadTo(x1,y1,x2,y2)控制点的横纵坐标,边界点的横纵坐标
        mPath.quadTo(controllerPoint1.x, controllerPoint1.y, point2.x, point2.y);
//        mPath.moveTo(point2.x,point2.y);
        mPath.quadTo(controllerPoint2.x,controllerPoint2.y,point3.x,point3.y);
        mPath.lineTo(point1.x,point1.y);
        mPath.lineTo(point1.x, 900);
        mPath.lineTo(point3.x, 900);
        mPath.lineTo(point3.x, point3.y);
        paint.setColor(0x88ff6666);
//        paint.setColor(Color.TRANSPARENT);
        canvas.drawPath(mPath, paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(point1.x, point1.y, 10, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(point2.x, point2.y, 10, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(controllerPoint1.x, controllerPoint1.y, 10, paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(point3.x, point3.y, 10, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(controllerPoint2.x, controllerPoint2.y, 10, paint);

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        Path path2 = new Path();
        path2.moveTo(point2.x,point2.y);
        path2.quadTo(controllerPoint2.x,controllerPoint2.y,point3.x,point3.y);
        canvas.drawPath(path2,paint2);
        return bitmap;
    }

    /**
     * 扇形
     *
     * @param style       如果不设置Style属性,默认是填充的
     * @param strokeWidth 画笔的宽度
     * @return
     */
    private Bitmap btn_draw_img4(Paint.Style style, int strokeWidth) {
        Bitmap bitmap = Bitmap.createBitmap(800, 1200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(style);//不填充,如果不设置Style属性,默认是填充的
        paint.setStrokeWidth(strokeWidth);//画笔的宽度
        paint.setAntiAlias(true);
        RectF f = new RectF(50, 50, 300, 300);
        canvas.drawArc(f, 30, 120, true, paint);//第三个参数是true就是扇形,false就是弓形
        return bitmap;
    }

    /**
     * 椭圆
     *
     * @param style       如果不设置Style属性,默认是填充的
     * @param strokeWidth 画笔的宽度
     * @return
     */
    private Bitmap btn_draw_img3(Paint.Style style, int strokeWidth) {
        Bitmap bitmap = Bitmap.createBitmap(800, 1200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(style);//不填充,如果不设置Style属性,默认是填充的
        paint.setStrokeWidth(strokeWidth);//画笔的宽度
        paint.setAntiAlias(true);
        RectF f = new RectF(50, 50, 500, 300);
        canvas.drawOval(f, paint);
        return bitmap;
    }

    /**
     * 矩形
     *
     * @param style       如果不设置Style属性,默认是填充的
     * @param strokeWidth 画笔的宽度
     * @return
     */
    private Bitmap btn_draw_img2(Paint.Style style, int strokeWidth) {
        Bitmap bitmap = Bitmap.createBitmap(800, 1200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(style);//不填充,如果不设置Style属性,默认是填充的
        paint.setStrokeWidth(strokeWidth);//画笔的宽度
        paint.setAntiAlias(true);

        RectF f = new RectF(50, 50, 300, 300);
        canvas.drawRect(f, paint);
        return bitmap;
    }

    /**
     * 圆形
     *
     * @param style       如果不设置Style属性,默认是填充的
     * @param strokeWidth 画笔的宽度
     * @return
     */
    private Bitmap btn_draw_img1(Paint.Style style, int strokeWidth) {
        Bitmap bitmap = Bitmap.createBitmap(800, 1200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(233, 11, 1, 222));
        canvas.drawCircle(250, 250, 200, paint);
        return bitmap;
    }
}
