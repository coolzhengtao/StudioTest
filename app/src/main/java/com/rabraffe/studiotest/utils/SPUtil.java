package com.rabraffe.studiotest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences读取类
 * Created by coolz on 2015/10/15 0015.
 */
public class SPUtil {
    private static final String FILE_NAME = "config";

    /**
     * 保存配置文件
     *
     * @param context
     * @param strKey
     * @param value
     */
    public static void saveValue(Context context, String strKey, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(strKey, value);
        sharedPreferences.edit().commit();
    }

    /**
     * 获取配置文件的值
     *
     * @param context
     * @param strKey
     * @return 如果没有改配置文件的key则返回null
     */
    public static String getValue(Context context, String strKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(strKey, null);
    }
}
