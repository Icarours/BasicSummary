package com.syl.viewdemo.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.syl.viewdemo.R;
import com.syl.viewdemo.base.MyApplication;
import com.syl.viewdemo.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2017/5/18 18:52
 * desc
 * ViewPager+indicator的常规用法
 * ViewPager无线轮播
 * ViewPager按下停止轮播
 */
public class VpActivity extends AppCompatActivity {
    private List<Integer> mPics = new ArrayList<>();
    @BindView(R.id.vp_container)
    ViewPager mVpContainer;
    @BindView(R.id.ll_container_indicator)
    LinearLayout mLlContainerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        ButterKnife.bind(this);
        initData();//向mPics中添加图片
        mVpContainer.setAdapter(new VpPagerAdapter());
        //添加指示器indicator
        for (int i = 0; i < mPics.size(); i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.mipmap.indicator_normal);
            if (i == 0) {
                iv.setImageResource(R.mipmap.indicator_selected);
            }
            int width = UIUtils.dp2px(6);
            int height = UIUtils.dp2px(6);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.leftMargin = UIUtils.dp2px(6);
            params.bottomMargin = UIUtils.dp2px(6);
            mLlContainerIndicator.addView(iv, params);
        }
        //给ViewPager设置监听
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position 是mVpContainer带过来的
             * @param positionOffset 屏幕宽度的百分比,position改变之后会归零,接着从0开始增加到1
             * @param positionOffsetPixels 最大值是屏幕的真实宽度,取值范围是0-屏幕最大值,position改变之后归0
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("position==" + position + "--positionOffset==" + positionOffset + "--positionOffsetPixels==" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                position = position % mPics.size();
                for (int i = 0; i < mPics.size(); i++) {
                    //getChildAt(i)是i,不是position,坑了我一个小时
                    ImageView imageView = (ImageView) mLlContainerIndicator.getChildAt(i);
                    //还原
                    imageView.setImageResource(R.mipmap.indicator_normal);
                    if (i == position) {//选中
                        imageView.setImageResource(R.mipmap.indicator_selected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //左右无线轮播
        int index = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE % mPics.size();
        mVpContainer.setCurrentItem(index);
        //开启自动轮播
        final AutoScrollTask autoScrollTask = new AutoScrollTask();
        autoScrollTask.start();

        /**
         * 如果按下ViewPager,就停止轮播,抬起就继续轮播
         */
        mVpContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        autoScrollTask.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:

                        break;
                    case MotionEvent.ACTION_UP:
                        autoScrollTask.start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 添加图片资源到mPics
     */
    private void initData() {
        mPics.add(R.mipmap.mm1);
        mPics.add(R.mipmap.mm2);
        mPics.add(R.mipmap.mm3);
        mPics.add(R.mipmap.mm4);
        mPics.add(R.mipmap.mm5);
    }

    /**
     * ViewPager的PagerAdapter
     * 一定要复写这几个方法
     * destroyItem()方法的父类方法删掉,只留下自己的代码
     * 除了instantiateItem()方法,其他三个方法的写法是固定的
     */
    class VpPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mPics.size();
            ImageView imageView = new ImageView(VpActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mPics.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            if (mPics != null) {
//                return mPics.size();
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 创建子线程,执行轮播任务
     */
    class AutoScrollTask implements Runnable {
        /**
         * ViewPager开始轮播,
         */
        public void start() {
            MyApplication.getMainThreadHandler().postDelayed(this, 3000);
        }

        /**
         * ViewPager结束轮播
         */
        public void stop() {
            MyApplication.getMainThreadHandler().removeCallbacks(this);
        }

        /**
         * ViewPager自动轮播
         */
        @Override
        public void run() {
            int currentItem = mVpContainer.getCurrentItem();
            currentItem++;
            mVpContainer.setCurrentItem(currentItem);
            start();
        }
    }
}
