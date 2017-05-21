package com.syl.basicsummary.utils;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by j3767 on 2017/2/3.
 *
 * @Describe
 * @Called
 */

public class WebUtil {
    /**
     * 获取HTML文件
     *
     * @param fileName
     * @return
     */
    public static String getFromAssets(String fileName, AssetManager assetManager) {
        try {
            //获取AssetManager
//            AssetManager assetManager = getResources().getAssets();
            //获取输入流
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
