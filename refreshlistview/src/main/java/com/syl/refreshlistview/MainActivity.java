package com.syl.refreshlistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mData = new ArrayList<>();

    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull,R.drawable.mt_pull01,R.drawable.mt_pull02,R.drawable.mt_pull03,R.drawable.mt_pull04,R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01,R.drawable.mt_refreshing02,R.drawable.mt_refreshing03,R.drawable.mt_refreshing04,R.drawable.mt_refreshing05,R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01,R.drawable.mt_loading02};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SpringView springView = (SpringView) findViewById(R.id.springView);
        springView.setGive(SpringView.Give.NONE);
        springView.setType(SpringView.Type.FOLLOW);
        ListView listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i <= 100; i++) {
            mData.add("content----" + i);
        }
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,mData));
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
        springView.setHeader(new MeituanHeader(this,pullAnimSrcs,refreshAnimSrcs));
        springView.setFooter(new MeituanFooter(this,loadingAnimSrcs));
    }
}
