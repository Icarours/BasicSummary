package com.syl.viewdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.viewdemo.activity.BezierViewActivity;
import com.syl.viewdemo.activity.DlActivity;
import com.syl.viewdemo.activity.DrawImgActivity;
import com.syl.viewdemo.activity.ImgActivity;
import com.syl.viewdemo.activity.OkHttpActivity;
import com.syl.viewdemo.activity.VpActivity;
import com.syl.viewdemo.activity.WaveActivity;
import com.syl.viewdemo.base.BaseFragment;

/**
 * author   Bright
 * date     2017/5/18 18:42
 * desc
 * 作为主界面控制各个子界面
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private String[] mTitles;
    private String mTitle;
    private String mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = (String) getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //通过getSupportActionBar()方法获取ActionBar对象
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);//启用回退
        actionBar.setHomeButtonEnabled(true);//确保HomeAsUp回退按钮可以点击

        //创建ActionBarDrawerToggle切换对象，并绑定要操作的DrawerLayout
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();//开启与绑定的DrawerLayout保持同步
        //将抽屉动作事件交由Toggle切换对象内部的onDrawerSlide方法处理,图标联动的关键在于此
        mDrawerLayout.addDrawerListener(mDrawerToggle);//设置抽屉动作事件监听
        //endregion
        if (savedInstanceState == null) {
            selectItem(0);
        }
        mTitles = getResources().getStringArray(R.array.titles_array);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mTitles));
        initListener();
    }

    private void initListener() {
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    /**
     * 选中条目,返回相应的fragment
     *
     * @param position
     */
    private void selectItem(int position) {
        /**
         * Fragment传递数据
         */
        BaseFragment fragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BaseFragment.BASEFRAGMENT_NUMBER, position);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(getResources().getStringArray(R.array.titles_array)[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    //ActionBar子项目点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //交给Toggle去分析并操作绑定的DrawerLayout
        mDrawerToggle.onOptionsItemSelected(item);
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.item_drawer_layout:
                intent.setClass(MainActivity.this, DlActivity.class);
                startActivity(intent);
                break;
            case R.id.item_view_pager:
                intent.setClass(MainActivity.this, VpActivity.class);
                startActivity(intent);
                break;
            case R.id.item_okhttp:
                intent.setClass(MainActivity.this, OkHttpActivity.class);
                startActivity(intent);
                break;
            case R.id.item_img:
                intent.setClass(MainActivity.this, ImgActivity.class);
                startActivity(intent);
                break;
            case R.id.item_draw_img:
                intent.setClass(MainActivity.this, DrawImgActivity.class);
                startActivity(intent);
                break;
            case R.id.item_wave:
                intent.setClass(MainActivity.this, WaveActivity.class);
                startActivity(intent);
                break;
            case R.id.item_bezier:
                intent.setClass(MainActivity.this, BezierViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTitle = (String) title;
        getSupportActionBar().setTitle(title);
    }

    /**
     * 使用ActionBarDrawerToggle的时候必须重写onPostCreate()和onConfigurationChanged()
     *
     * @param savedInstanceState
     * @param persistentState
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        //同步状态
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //通过配置变化更改mDrawerToggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
