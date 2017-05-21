package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.syl.basicsummary.R;
/**
 * author   j3767
 * date     2017/2/3 15:40
 * desc
 * 三种菜单之子菜单subMenu
 */
public class SubMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_item11:
                Toast.makeText(this, "option_item11", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item12:
                Toast.makeText(this, "option_item12", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item13:
                Toast.makeText(this, "option_item13", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item14:
                Toast.makeText(this, "option_item14", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item21:
                Toast.makeText(this, "option_item21", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item22:
                Toast.makeText(this, "option_item12", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item23:
                Toast.makeText(this, "option_item23", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_item24:
                Toast.makeText(this, "option_item24", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
