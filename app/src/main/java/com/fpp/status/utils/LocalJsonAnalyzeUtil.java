package com.fpp.status.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Description:
 * Author: fpp
 * Date: 2018/9/19  10:37
 */

public class LocalJsonAnalyzeUtil {

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为 对象
     *
     * @param context
     * @param fileName
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T JsonToObject(Context context, String fileName, Class<T> type) {
        Gson gson = new Gson();
        String json = getJson(context, fileName);
        return gson.fromJson(json, type);
    }


}
