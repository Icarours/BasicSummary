package com.syl.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Bright on 2017/5/19.
 *
 * @Describe 贝塞尔曲线的绘制
 * @Called
 */

public class ViewBoLang extends ImageView {
    public ViewBoLang(Context context) {
        this(context, null);
    }

    public ViewBoLang(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewBoLang(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.reset();
        Point left = new Point();//最左边的点
        Point left2 = new Point();//左边的第二个点
        Point right = new Point();//最右边的点
        Point second = new Point();//第二个点
        Point controllerLeft1 = new Point();//左边的控制点1
        Point controllerLeft2 = new Point();//左边的控制点2
        path.moveTo(left.x, left.y);
        path.quadTo(controllerLeft1.x, controllerLeft1.y, left2.x, left2.y);

    }
}
