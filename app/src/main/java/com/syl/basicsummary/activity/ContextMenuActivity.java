package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.basicsummary.R;

/**
 * author   j3767
 * date     2017/2/3 15:41
 * desc
 * 三种菜单之contextMenu
 */
public class ContextMenuActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conent_menu);
        mTv = (TextView) findViewById(R.id.tv);
        //注册ContextMenu菜单
        registerForContextMenu(mTv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 重写 ContextMenu 方法
        // 动态加载
        menu.add(1, 1, 1, "干嘛");
        menu.add(1, 2, 1, "哇哦");
        menu.add(1, 3, 1, "咳咳");
        menu.add(1, 4, 1, "哈哈");

        //布局添加
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(getApplicationContext(), "我就是要" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "你快看" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "老头子！" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "你是。。。！" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_item1:
                Toast.makeText(getApplicationContext(), "context_item1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_item2:
                Toast.makeText(getApplicationContext(), "context_item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_item3:
                Toast.makeText(getApplicationContext(), "context_item3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_item4:
                Toast.makeText(getApplicationContext(), "context_item4", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
