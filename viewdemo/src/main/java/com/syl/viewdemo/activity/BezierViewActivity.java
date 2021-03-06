package com.syl.viewdemo.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.syl.viewdemo.bean.Point;

import java.util.ArrayList;
import java.util.List;

public class BezierViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new BezierView(this));
    }
}

class BezierView extends View {
    /**
     *
     * @author liqiongwei
     * @param context
     *
     */
    public BezierView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {

        List<Float> points = new ArrayList<Float>();

        Paint paint = new Paint();
        // 添加第一个点(118.0, 294.0),
        points.add((float) 118.0);// X轴
        points.add((float) 294.0);// Y轴
        // 添加第二个点
        points.add((float) 206.0);
        points.add((float) 294.0);
        // 添加第三个点
        points.add((float) 294.0);
        points.add((float) 118.0);
        // 添加第四个点
        points.add((float) 382.0);
        points.add((float) 206.0);

        points.add((float) 470.0);
        points.add((float) 118.0);

        // 通过画折线和贝塞尔曲线可以知道，点得位置是不一样的。
        // 画折线
        for (int i = 0; i < points.size() - 2; i = i + 2) {
            canvas.drawLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3), paint);
            canvas.drawCircle(points.get(i), points.get(i + 1), 3, paint);
        }
        //画最后一个点
        canvas.drawCircle(points.get(points.size() - 2), points.get(points.size() - 1), 3, paint);

        // 贝塞尔曲线
        paint.setColor(Color.BLUE);
        Path p = new Path();
        Point p1 = new Point();//二阶贝塞尔曲线控制点1
        Point p2 = new Point();//二阶贝塞尔曲线控制点2
        Point p3 = new Point();//二阶贝塞尔曲线控制点3
        float xp = points.get(0);
        float yp = points.get(1);
        // 设置第一个点开始
        p.moveTo(xp, yp);
        int length = points.size();
        // 设置第一个控制点33%的距离
        float mFirstMultiplier = 0.3f;
        // 设置第二个控制点为66%的距离
        float mSecondMultiplier = 1 - mFirstMultiplier;

        for (int b = 0; b < length; b += 2) {
            // 设置第一个控制点
            int nextIndex = b + 2 < length ? b + 2 : b;
            int nextNextIndex = b + 4 < length ? b + 4 : nextIndex;
            calc(points, p1, b, nextIndex, mSecondMultiplier);
            // 设置第二个控制点
            p2.setX(points.get(nextIndex));
            p2.setY(points.get(nextIndex + 1));
            calc(points, p3, nextIndex, nextNextIndex, mFirstMultiplier);
            // 最后一个点就是赛贝尔曲线上的点
            p.cubicTo(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
            // 画点
        }
        PathMeasure mPathMeasure;
        mPathMeasure = new PathMeasure(p, false);
        // 设置为线
        paint.setStyle(Paint.Style.STROKE);
        reSetPointWithPath(mPathMeasure, points);
        for (int k = 0; k < points.size()-1; k +=2) {
            canvas.drawCircle(points.get(k), points.get(k+1), 5, paint);
        }
        canvas.drawPath(p, paint);

        invalidate();
    }

    /**
     * 计算控制点
     * @param points 点的集合
     * @param result 控制点
     * @param index1 第一个点的x值对应的索引
     * @param index2 第二个点的x值对应的索引
     * @param multiplier 控制点所在的比例
     */
    private void calc(List<Float> points, Point result, int index1, int index2, final float multiplier) {
        float p1x = points.get(index1);
        float p1y = points.get(index1 + 1);
        float p2x = points.get(index2);
        float p2y = points.get(index2 + 1);

        float diffX = p2x - p1x;
        float diffY = p2y - p1y;
        result.setX(p1x + (diffX * multiplier));
        result.setY(p1y + (diffY * multiplier));
    }

    /**
     * 重新设置点的位置，为曲线上的位置
     * @param mPathMeasure
     * @param pointsList
     */
    public void reSetPointWithPath(PathMeasure mPathMeasure, List<Float> pointsList){
        int length = (int) mPathMeasure.getLength();
        int pointsLength = pointsList.size();
        float[] coords = new float[2];
        for (int b = 0; b < length; b++) {
            mPathMeasure.getPosTan(b, coords, null);
            double prevDiff = Double.MAX_VALUE;
            boolean ok = true;
            for (int j = 0; j < pointsLength && ok; j += 2) {
                double diff = Math.abs(pointsList.get(j) - coords[0]);
                if (diff < 1) {
                    pointsList.set(j + 1, coords[1]);
                    prevDiff = diff;
                }
                ok = prevDiff > diff;
            }
        }
    }
}
