package com.rabraffe.studiotest.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 静态值类
 * Created by Neo on 2015/10/20 0020.
 */
public final class StaticClasss {
    private StaticClasss() {
    }

    public static String version_code;          //版本号

    /**
     * 初始化所有值
     *
     * @param context
     */
    public static void initValues(Context context) {
        try {
            version_code = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
