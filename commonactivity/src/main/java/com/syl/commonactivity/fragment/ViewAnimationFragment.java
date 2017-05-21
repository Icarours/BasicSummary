package com.syl.commonactivity.fragment;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/28.
 *
 * @Describe 补间动画
 * 平移,旋转,缩放,透明度,动画集合
 * <p>
 * Animation.ABSOLUTE :指定后面的值是绝对的像素点距离
 * Animation.RELATIVE_TO_PARENT : 后面的值，代表的是父控件的宽度或者是高度的倍数值
 * Animation.RELATIVE_TO_SELF: 后面的值，代表的是自己宽度或者高度的倍数值
 *
 * 移动的时候控件实际上并没有改变自身的位置
 * @Called
 */

public class ViewAnimationFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private ImageView mIvImg;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_view_animation, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mIvImg = (ImageView) mRootView.findViewById(R.id.iv_img);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_translate).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_scale).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_rotate).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_alpha).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_set).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
                btnTranslate2();
                break;
            case R.id.btn_scale:
                btnScale();
                break;
            case R.id.btn_rotate:
                btnRotate();
                break;
            case R.id.btn_alpha:
                btnAlpha();
                break;
            case R.id.btn_set:
                btnSet();
                break;
            default:
                break;
        }
    }

    /**
     * 动画集合
     */
    private void btnSet() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, -1, 1, -1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);//持续时间
        scaleAnimation.setRepeatMode(Animation.REVERSE);//重复模式
        scaleAnimation.setRepeatCount(Animation.INFINITE);//重复次数

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);

        mIvImg.startAnimation(animationSet);//启动动画集合
    }

    /**
     * 平移动画
     */
    private void btnTranslate() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 0);
        translateAnimation.setDuration(2000);//持续时间
        translateAnimation.setRepeatCount(Animation.INFINITE);//重复次数
        translateAnimation.setRepeatMode(Animation.REVERSE);//重复模式
        mIvImg.startAnimation(translateAnimation);//启动动画
    }

    /**
     * 从xml文件中加载动画
     * xml文件中的动画属性as暂时没有提示,还是直接在代码中设置动画属性吧
     */
    private void btnTranslate2() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.tr);
        mIvImg.startAnimation(animation);
    }

    /**
     * 缩放
     */
    private void btnScale() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 50, 0, 50);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setDuration(2000);
        mIvImg.startAnimation(scaleAnimation);
    }

    /**
     * 旋转
     */
    private void btnRotate() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(2000);
        mIvImg.startAnimation(rotateAnimation);
    }

    /**
     * 透明度变化
     */
    private void btnAlpha() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.2f);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setDuration(2000);
        mIvImg.startAnimation(alphaAnimation);
    }
}
