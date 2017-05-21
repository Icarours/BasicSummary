package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.syl.basicsummary.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * author   j3767
 * date     2017/2/5 11:31
 * desc
 * ActionBar
 * DrawerLayout
 */
public class ABDLActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abdlacttivity);

        getOverflowMenu();//右上角溢出菜单一定会显示
        initView();
        initActionBar();

        initToggle();
    }

    /**
     * 创建Toggle,并设置其相关属性
     */
    private void initToggle() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        //同步状态
        mToggle.syncState();
        //添加监听
        mDrawerLayout.addDrawerListener(mToggle);
    }

    /**
     * 获取ActionBar,并对其进行相关设置
     */
    private void initActionBar() {
        //1.获得ActionBar
        mActionBar = getSupportActionBar();
        //2.设置Title
        mActionBar.setTitle("常用控件");
        mActionBar.setSubtitle("ActionBar&&DrawerLayout");
        //3.设置icon
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setIcon(R.mipmap.ic_launcher);
        //4.设置回退按钮,默认隐藏
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //5.设置logo,默认隐藏
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setLogo(R.mipmap.ic_action_locate);
        //6.菜单模式
//        listMode(actionBar);
        //其他按钮

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_main);
        Button btnList = (Button) findViewById(R.id.btn_list);
        Button btnTab = (Button) findViewById(R.id.btn_tab);
        Button startActionMode = (Button) findViewById(R.id.btn_startActionMode);
        btnList.setOnClickListener(this);
        btnTab.setOnClickListener(this);
        startActionMode.setOnClickListener(this);
    }

    /**
     * 系统会先检测设备是否用实体按钮,如果有实体按钮,该方法由实体按钮相应;
     * 如果没有其他的按钮会显示在设备的右上角
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //回退按钮
            case android.R.id.home:
                mToggle.onOptionsItemSelected(item);
                break;
            case R.id.item_call:
                Toast.makeText(this, "item_call", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_mail:
                Toast.makeText(this, "item_mail--", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_copy:
                Toast.makeText(this, "item_copy---", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list:
                listMode(mActionBar);//listMode
                break;
            case R.id.btn_tab:
                tabMode();//tabMode
                break;
            case R.id.btn_startActionMode:
                actionMode();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    private void actionMode() {
        startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.actionmode_menu, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_edit:
                        Toast.makeText(ABDLActivity.this, "item_edit", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_copy:
                        Toast.makeText(ABDLActivity.this, "item_copy", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_delete:
                        Toast.makeText(ABDLActivity.this, "item_delete", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_search:
                        Toast.makeText(ABDLActivity.this, "item_search", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    /**
     * TabMode
     */
    private void tabMode() {
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 1; i <= 10; i++) {
            ActionBar.Tab tab = mActionBar.newTab();
            tab.setText("tab" + i);
            tab.setIcon(R.mipmap.ic_action_call);
            final int finalI = i;
            tab.setTabListener(new ActionBar.TabListener() {
                @Override
                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                    Toast.makeText(ABDLActivity.this, "tab" + finalI + "was selected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                }
            });
            //添加tab到Actionbar中
            mActionBar.addTab(tab);
        }
    }

    /**
     * ListMode
     *
     * @param actionBar
     */
    private void listMode(ActionBar actionBar) {
        //6.设置NavigationMode,NAVIGATION_MODE_LIST
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        final List<String> list = new ArrayList<>();
        list.add("新闻");
        list.add("电影");
        list.add("音乐");

        SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        actionBar.setListNavigationCallbacks(adapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                Toast.makeText(ABDLActivity.this, list.get(itemPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
}
