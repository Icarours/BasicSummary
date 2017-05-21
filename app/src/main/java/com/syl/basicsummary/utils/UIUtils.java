package com.syl.basicsummary.utils;

import android.content.Context;
import android.content.res.Resources;

import com.syl.basicsummary.base.MyApplication;

/**
 * Created by j3767 on 2017/2/8.
 *
 * @Describe
 * @Called
 */

public class UIUtils {
    /**
     * 获得Context
     *
     * @return
     */
    public static Context getContext() {
        return MyApplication.getmContext();
    }

    /**
     * 获得Resources文件
     *
     * @return
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获得color.xml文件
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获得string.xml文件中的String数据
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获得string.xml文件中的string数组
     *
     * @param strArrId
     * @return
     */
    public static String[] getStringArr(int strArrId) {
        return getResources().getStringArray(strArrId);
    }

    /**
     * 获得包名
     *
     * @return
     */
    public static String packageName() {
        return getContext().getPackageName();
    }
}
