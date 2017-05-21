package com.syl.commonactivity.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Bright
 * date     2017/4/29 22:13
 * desc
 * listView的refresh
 * 使用第三方刷新包
 */
public class RefreshActivity extends BaseActivity {
    private List<String> mData = new ArrayList<>();

    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull, R.drawable.mt_pull01, R.drawable.mt_pull02, R.drawable.mt_pull03, R.drawable.mt_pull04, R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01, R.drawable.mt_refreshing02, R.drawable.mt_refreshing03, R.drawable.mt_refreshing04, R.drawable.mt_refreshing05, R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01, R.drawable.mt_loading02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        final SpringView springView = (SpringView) findViewById(R.id.springView);
        
//        springView.setGive(SpringView.Give.NONE);
        springView.setType(SpringView.Type.FOLLOW);
        ListView listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i <= 100; i++) {
            mData.add("content----" + i);
        }
        listView.setAdapter(new ArrayAdapter<>(RefreshActivity.this, android.R.layout.simple_list_item_1, mData));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 3000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 3000);
            }
        });
        springView.setHeader(new MeituanHeader(this, pullAnimSrcs, refreshAnimSrcs));
        springView.setFooter(new MeituanFooter(this, loadingAnimSrcs));
    }
}
