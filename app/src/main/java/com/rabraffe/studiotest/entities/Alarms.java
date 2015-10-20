package com.rabraffe.studiotest.entities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rabraffe.studiotest.activities.AlarmActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 闹钟管理类
 * Created by Neo on 2015/10/20 0020.
 */
public class Alarms implements Serializable {
    private static Alarms instance;
    private AlarmScheme schemeNext;         //下一个闹钟

    private Alarms() {
        listAlarm = new ArrayList<>();
    }

    private ArrayList<AlarmScheme> listAlarm;

    public static Alarms getInstance() {
        if (instance == null) {
            instance = new Alarms();
        }
        return instance;
    }

    /**
     * 新增一个闹钟
     *
     * @param scheme
     */
    public void addAlarm(AlarmScheme scheme) {
        listAlarm.add(scheme);
    }

    /**
     * 获取下一个闹钟
     *
     * @return
     */
    private AlarmScheme getNextAlarm() {
        AlarmScheme schemeReuslt = null;
        for (AlarmScheme scheme :
                listAlarm) {
            if (scheme.isEnabled()) {
                if (schemeReuslt == null) {
                    schemeReuslt = scheme;
                    continue;
                }
                if (scheme.getAlarmTime().getTime() < schemeReuslt.getAlarmTime().getTime()) {
                    schemeReuslt = scheme;
                }
            }
        }
        return schemeReuslt;
    }

    /**
     * 取消一个闹钟
     *
     * @param strUUID
     */
    public void disableAlarm(String strUUID) {
        for (AlarmScheme scheme :
                listAlarm) {
            if (scheme.getUUID().equals(strUUID)) {
                scheme.setIsEnabled(false);
            }
        }
    }

    /**
     * 设置下一个闹钟
     *
     * @param context
     */
    public void setNextAlarm(Context context) {
        AlarmScheme scheme = getNextAlarm();
        if (scheme != null && scheme != schemeNext) {
            schemeNext = scheme;
            Intent intent = new Intent(context, AlarmActivity.class);
            intent.putExtra("uuid", scheme.getUUID());
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, Intent.FILL_IN_ACTION);
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, scheme.getAlarmTime().getTime(), pendingIntent);
        }
    }
}
