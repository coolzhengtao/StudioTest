package com.rabraffe.studiotest.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rabraffe.studiotest.R;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.dpTime)
    TimePicker tpTime;


    @OnClick(R.id.btnSetAlarm)
    private void btnSetAlarmClick(View view) {
        Date dtAlarm = new Date();
        dtAlarm.setHours(tpTime.getCurrentHour());
        dtAlarm.setMinutes(tpTime.getCurrentMinute());
        dtAlarm.setSeconds(0);
        if (dtAlarm.before(new Date())) {
            //设置为新的一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtAlarm);
            calendar.add(Calendar.DATE, 1);
            dtAlarm = calendar.getTime();
        }
        Intent intent = new Intent(this, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, Intent.FILL_IN_ACTION);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, dtAlarm.getTime(), pendingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }
}
