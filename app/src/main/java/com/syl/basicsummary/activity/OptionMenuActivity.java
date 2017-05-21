package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.syl.basicsummary.R;
/**
 * author   j3767
 * date     2017/2/3 15:41
 * desc
 * 三种菜单之optionMenu
 */
public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单布局
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单条目选择,响应对应的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_item1:
                Toast.makeText(this, "option_item1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item2:
                Toast.makeText(this, "option_item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item3:
                Toast.makeText(this, "option_item3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item4:
                Toast.makeText(this, "option_item4", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
