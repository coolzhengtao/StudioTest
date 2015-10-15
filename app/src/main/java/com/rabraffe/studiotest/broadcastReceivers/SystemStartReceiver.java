package com.rabraffe.studiotest.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rabraffe.studiotest.activities.MainActivity;

public class SystemStartReceiver extends BroadcastReceiver {
    public SystemStartReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //开机读取闹钟计划
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
