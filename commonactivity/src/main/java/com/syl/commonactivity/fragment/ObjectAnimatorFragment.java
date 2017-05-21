package com.syl.commonactivity.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

/**
 * Created by Bright on 2017/4/29.
 *
 * @Describe 属性动画
 * <p>
 * 移动的时候真正的改变了自己的位置,而且属性动画可以设置可变参数
 * 比补间动画更为灵活,效果更加绚丽多彩
 * <p>
 * ObjectAnimator
 * ValueAnimator类似于fori语句,本身没有动画效果,需要设置监听,在监听中设置动画效果
 *
 * 帧动画见SqliteFragment
 * @Called
 */

public class ObjectAnimatorFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private ImageView mIvImg1;
    private ImageView mIvImg2;
    private ImageView mIvImg3;
    private ImageView mIvImg4;
    private ImageView mIvImg5;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_object_animation, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mIvImg1 = (ImageView) mRootView.findViewById(R.id.iv_img1);
        mIvImg2 = (ImageView) mRootView.findViewById(R.id.iv_img2);
        mIvImg3 = (ImageView) mRootView.findViewById(R.id.iv_img3);
        mIvImg4 = (ImageView) mRootView.findViewById(R.id.iv_img4);
        mIvImg5 = (ImageView) mRootView.findViewById(R.id.iv_img5);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_translate).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_rotate).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_scale).setOnClickListener(this);
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
                btnTranslate();
                break;
            case R.id.btn_rotate:
                btnRotate();
                break;
            case R.id.btn_scale:
                btnScale();
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

    private void btnTranslate() {

        /**
         * 参数一： 谁取播放动画 ， 控件
         * 参数二：
         */
        imgVisible(mIvImg1);
        //ValueAnimator主要是一个提供插值器的作用,动画的最终显示需要在监听中实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 300);
        //给ValueAnimator设置监听,真正的动画在监听中执行.
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //animation.getAnimatedFraction()返回的是当前的状态的百分比
                mIvImg1.setTranslationX(animation.getAnimatedFraction() * 300);
            }
        });
        valueAnimator.setDuration(5000);
        valueAnimator.start();
    }

    /**
     * 每次只有一个img显示,其他的img都隐藏
     *
     * @param ivImg
     */
    private void imgVisible(ImageView ivImg) {
        mIvImg1.setVisibility(View.INVISIBLE);
        mIvImg2.setVisibility(View.INVISIBLE);
        mIvImg3.setVisibility(View.INVISIBLE);
        mIvImg4.setVisibility(View.INVISIBLE);
        mIvImg5.setVisibility(View.INVISIBLE);
        ivImg.setVisibility(View.VISIBLE);
    }

    private void btnRotate() {
        imgVisible(mIvImg2);
        //创建动画,设置参数
        ObjectAnimator animator = ObjectAnimator.ofFloat(mIvImg2, "rotation", 0, 360);
        animator.setDuration(2000);//动画持续时间
        animator.start();//播放动画
    }

    private void btnScale() {
        imgVisible(mIvImg3);
        //创建动画,设置参数
        ObjectAnimator animator = ObjectAnimator.ofFloat(mIvImg3, "scaleX", 0, 4, 0);
        animator.setDuration(2000);//动画持续时间

        animator.start();//播放动画
    }

    private void btnAlpha() {
        imgVisible(mIvImg4);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mIvImg4, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();

    }

    private void btnSet() {
        imgVisible(mIvImg5);
/**
 * 参数一： 谁取播放动画 ， 控件
 * 参数二：
 */
        ObjectAnimator animator = ObjectAnimator.ofFloat(mIvImg5, "translationX", 0, 300);
//        animator.setRepeatCount(ObjectAnimator.INFINITE);
//		animator.setRepeatMode(ObjectAnimator.REVERSE);

        //设置动画的播放时间
        animator.setDuration(2000);


        ObjectAnimator animatory = ObjectAnimator.ofFloat(mIvImg5, "translationY", 0, 300);

//		animatory.setRepeatCount(ObjectAnimator.INFINITE);
//        animatory.setRepeatMode(ObjectAnimator.REVERSE);

        //设置动画的播放时间
        animatory.setDuration(2000);

        AnimatorSet set = new AnimatorSet();

        //让动画一起播放
//		set.playTogether(animator , animatory);

        //按顺序播放
        set.playSequentially(animator, animatory);


        set.start();
    }
}
