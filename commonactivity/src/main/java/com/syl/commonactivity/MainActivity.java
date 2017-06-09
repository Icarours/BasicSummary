package com.syl.commonactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.config.Constants;

/**
 * author   Bright
 * date     2017/4/19 22:40
 * desc
 * 主界面
 * 弹出式菜单是一些需要直接使用Activity的demo
 */
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.activity_main);
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.list_tiltles_item, R.id.tv_title, Constants.titles));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Activity只有一个,不需要变
                Intent intent = new Intent(MainActivity.this, Constants.activities[0]);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.item_intent:
                intent.setClass(MainActivity.this, Constants.activities[1]);
                startActivity(intent);
                break;
            case R.id.item_sms_helper:
                intent.setClass(MainActivity.this, Constants.activities[2]);
                startActivity(intent);
                break;
            case R.id.item_receiver:
                intent.setClass(MainActivity.this, Constants.activities[5]);
                startActivity(intent);
                break;
            case R.id.item_service_test:
                intent.setClass(MainActivity.this, Constants.activities[6]);
                startActivity(intent);
                break;
            case R.id.item_service_banzheng:
                intent.setClass(MainActivity.this, Constants.activities[7]);
                startActivity(intent);
                break;
            case R.id.item_service_pay:
                intent.setClass(MainActivity.this, Constants.activities[8]);
                startActivity(intent);
                break;
            case R.id.item_zxing:
                intent.setClass(MainActivity.this, Constants.activities[9]);
                startActivity(intent);
                break;
            case R.id.item_dagger2:
                intent.setClass(MainActivity.this, Constants.activities[10]);
                startActivity(intent);
                break;
            case R.id.item_refresh_lv:
                intent.setClass(MainActivity.this, Constants.activities[11]);
                startActivity(intent);
                break;
            case R.id.item_vitamio:
                intent.setClass(MainActivity.this, Constants.activities[12]);
                startActivity(intent);
                break;
            case R.id.item_web_view:
                intent.setClass(MainActivity.this, Constants.activities[13]);
                startActivity(intent);
                break;
            case R.id.item_ijk_player:
                intent.setClass(MainActivity.this, Constants.activities[14]);
                startActivity(intent);
                break;
            case R.id.item_img:
                intent.setClass(MainActivity.this, Constants.activities[15]);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
