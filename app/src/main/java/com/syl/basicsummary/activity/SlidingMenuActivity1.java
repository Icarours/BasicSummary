package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.syl.basicsummary.R;
import com.syl.basicsummary.base.BaseActivity;
import com.syl.basicsummary.view.SlidingMenu;

/**
 * Created by j3767 on 2017/2/28.
 *
 * @Describe
 * @Called
 */
public class SlidingMenuActivity1 extends BaseActivity {

    private SlidingMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding1);
        mMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
    }

    public void toggleMenu(View view) {
        mMenu.toggle();
    }
}