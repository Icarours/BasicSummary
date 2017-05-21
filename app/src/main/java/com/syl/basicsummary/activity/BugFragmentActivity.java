package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.syl.basicsummary.R;
import com.syl.basicsummary.fragment.ButtonFragment;
import com.syl.basicsummary.fragment.TextFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/2/9 9:41
 * desc
 * Fragment更新之后,notifyDataSetChange刷新无效
 */
public class BugFragmentActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_fragment);
        ButterKnife.bind(this);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ButtonFragment());
        for (int i = 0; i < 5; i++) {
            TextFragment textFragment = new TextFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", i + "");
            textFragment.setArguments(bundle);
            mFragmentList.add(textFragment);
        }
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    /**
     * 使用FragmentPagerAdapter会导致mAdapter.notifyDataSetChanged()无效,因为FragmentPagerAdapter缓存了
     * Fragment页面
     */
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    public void notifyChange() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ListIterator<Fragment> iterator = mFragmentList.listIterator();
        while (!iterator.hasNext()) {
            for (int i = 0; i < mFragmentList.size(); i++) {
                mFragmentList.remove(i);
            }
        }
        transaction.commit();

        mFragmentList.clear();
        mFragmentList.add(new ButtonFragment());
        for (int i = 0; i < 5; i++) {
            TextFragment textFragment = new TextFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", i + "数据已修改");
            textFragment.setArguments(bundle);
            mFragmentList.add(textFragment);
        }
        mAdapter.notifyDataSetChanged();
    }
}
