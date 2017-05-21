package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.syl.basicsummary.R;
import com.syl.basicsummary.factory.FragmentFactory;
import com.syl.basicsummary.utils.UIUtils;
import com.syl.basicsummary.view.PagerSlidingTabStrip;

import java.lang.reflect.Field;

/**
 * author   j3767
 * date     2017/2/8 12:39
 * desc
 * 实用的快速开发框架,以后可以直接拿过来用
 * 侧滑菜单
 * 顶部Tab
 */
public class RapidDevelopment extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView mLeftMenuItem;
    private String[] mMainTitles;
    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mViewPager;
    private MyOnPagerChangeListener mMyOnPagerChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapid_development);
        getOverflowMenu();//确保溢出菜单时刻显示
        initView();
        initActionBar();
        initToggle();
        initData();
        initListener();
    }

    private void initListener() {
        mLeftMenuItem.setOnItemClickListener(new LeftMenuOnItemClickListener());
        mMyOnPagerChangeListener = new MyOnPagerChangeListener();
        mTabStrip.setOnPageChangeListener(mMyOnPagerChangeListener);
    }

    private void initData() {
        mMainTitles = UIUtils.getStringArr(R.array.main_titles);
        mLeftMenuItem.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMainTitles));
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mTabStrip.setViewPager(mViewPager);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_rapid_development);
        mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLeftMenuItem = (ListView) findViewById(R.id.left_menu_item);
    }

    private void initToggle() {
        //创建ActionBarDrawerToggle对象
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        //同步状态
        mToggle.syncState();
        //添加监听
        mDrawerLayout.addDrawerListener(mToggle);
    }

    private void initActionBar() {
        //1.获得ActionBar对象
        ActionBar actionBar = getSupportActionBar();
        //2.设置Title
        actionBar.setTitle("BasicSummary");
        actionBar.setSubtitle("Rapid Development");
        //3.设置icon
        actionBar.setIcon(R.mipmap.ic_launcher);
        //4.设置icon是否显示,默认隐藏
        actionBar.setDisplayShowHomeEnabled(true);
        //5.设置返回按钮显示,默认隐藏
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToggle.onOptionsItemSelected(item);
//                finish();
                break;
            case R.id.item_call:
                Toast.makeText(this, "item_call was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_copy:
                Toast.makeText(this, "item_copy was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_mail:
                Toast.makeText(this, "item_mail was clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 通过反射拿到右上角溢出菜单按钮,重新设置,无论设备是否有实体按钮,都显示右上角溢出菜单按钮,
     */
    private void getOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mMainTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mMainTitles[position];
        }
    }

    class LeftMenuOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("tag",position+"-------");
            mMyOnPagerChangeListener.onPageSelected(position);
        }
    }

    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
