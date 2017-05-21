package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.view.ExpandListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   j3767
 * date     2017/3/3 22:47
 * desc
 * 1.ScrollView 嵌套ListView
 * 2.ListView的标准写法
 */
public class ScrollListViewActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ExpandListView mListView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list_view);
        ButterKnife.bind(this);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("data----" + i);
        }
        mListView.setAdapter(new MyAdapter());
//        mListView.setAdapter(new ArrayAdapter<>(this,R.layout.item_list_tv,R.id.tv,mData));
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            if (mData != null) {
                return mData.get(position);
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HolderView holderView;
            if (convertView == null) {
                holderView = new HolderView();
                convertView = View.inflate(ScrollListViewActivity.this, R.layout.item_list_tv, null);
                holderView.tv = (TextView) convertView.findViewById(R.id.tv);
                holderView.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(holderView);
            } else {
                holderView = (HolderView) convertView.getTag();
            }
            holderView.tv.setText(mData.get(position));
            holderView.iv.setImageResource(R.mipmap.m1);
            return convertView;
        }
    }

    class HolderView {
        TextView tv;
        ImageView iv;
    }
}
