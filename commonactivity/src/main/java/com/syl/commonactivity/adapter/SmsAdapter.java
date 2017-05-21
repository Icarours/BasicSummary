package com.syl.commonactivity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.activity.TemplateActivity;

/**
 * Created by Bright on 2017/4/25.
 *
 * @Describe
 * Adapter
 * @Called
 */

public class SmsAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mContents;

    public SmsAdapter(TemplateActivity templateActivity, String[] contents) {
        mContext = templateActivity;
        mContents = contents;
    }

    @Override
    public int getCount() {
        return mContents.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.list_item_template, null);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvContent.setText(mContents[position]);
        return convertView;
    }

    class ViewHolder {
        TextView tvContent;
    }
}
