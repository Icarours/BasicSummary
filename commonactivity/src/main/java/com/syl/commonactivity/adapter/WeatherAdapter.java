package com.syl.commonactivity.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.bean.Weather;

import java.util.List;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe
 * @Called
 */

public class WeatherAdapter extends BaseAdapter {
    private List<Weather> mWeatherList;
    private Context mContext;

    public WeatherAdapter(FragmentActivity activity, List<Weather> weathers) {
        mContext = activity;
        mWeatherList = weathers;
    }

    @Override
    public int getCount() {
        if (mWeatherList == null) {
            return 0;
        }
        return mWeatherList.size();
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
            convertView = View.inflate(mContext, R.layout.list_item_weather, null);
            holder.mFengxiang = (TextView) convertView.findViewById(R.id.tv_fengxiang);
            holder.mFengli = (TextView) convertView.findViewById(R.id.tv_fengli);
            holder.mHigh = (TextView) convertView.findViewById(R.id.tv_high);
            holder.mType = (TextView) convertView.findViewById(R.id.tv_type);
            holder.mLow = (TextView) convertView.findViewById(R.id.tv_low);
            holder.mDate = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Weather weather = mWeatherList.get(position);
        holder.mFengxiang.setText(weather.getFengxiang());
        holder.mFengli.setText(weather.getFengli());
        holder.mHigh.setText(weather.getHigh());
        holder.mType.setText(weather.getType());
        holder.mLow.setText(weather.getLow());
        holder.mDate.setText(weather.getDate());
        return convertView;
    }

    class ViewHolder {
        TextView mFengxiang;
        TextView mFengli;
        TextView mHigh;
        TextView mType;
        TextView mLow;
        TextView mDate;
    }
}
