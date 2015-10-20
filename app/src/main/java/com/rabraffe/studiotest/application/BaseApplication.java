package com.rabraffe.studiotest.application;

import android.app.Application;

import com.rabraffe.studiotest.utils.StaticClasss;

/**
 * Created by Neo on 2015/10/20 0020.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //应用初始化
        StaticClasss.initValues(getApplicationContext());
    }
}
