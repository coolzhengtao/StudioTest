package com.rabraffe.studiotest.activities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rabraffe.studiotest.R;

import java.io.IOException;

public class AlarmActivity extends BaseActivity {
    @ViewInject(R.id.btnStopVibrate)
    Button btnStopVibrate;

    Vibrator vibrator;                          //震动控制器
    SensorManager sensorManager;                //传感器管理
    PowerManager powerManager;                  //电源管理
    PowerManager.WakeLock wakeLock;             //屏幕锁
    SensorValueListener listener;               //传感器事件
    Sensor sensor;                              //传感器对象
    Uri uriAlarm;                               //闹钟铃声的URI
    MediaPlayer mediaPlayer;                    //媒体播放器
    AudioManager audioManager;                  //音频管理服务

    @OnClick(R.id.btnStopVibrate)
    private void btnStopVibrateClick(View view) {
        if (vibrator.hasVibrator())
            alarmClockOff();
    }

    /**
     * 关闭闹钟
     */
    private void alarmClockOff() {
        vibrator.cancel();
        mediaPlayer.stop();
        //关闭传感器
        sensorManager.unregisterListener(listener);
        wakeLock.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ViewUtils.inject(this);
        initView();
        alarmClockOn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭传感器
        alarmClockOff();
    }

    //初始化控件
    private void initView() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);         //获取重力感应器
        listener = new SensorValueListener();
        uriAlarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);   //获取默认的铃声URI
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, uriAlarm);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mediaPlayer.setLooping(true);
                mediaPlayer.prepare();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打开闹钟
    private void alarmClockOn() {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] fVibrate = new long[]{1000, 2000};
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();
            vibrator.vibrate(fVibrate, 0, audioAttributes);
        } else {
            vibrator.vibrate(fVibrate, 0);
        }
        //启动铃声
        mediaPlayer.start();
        //启动感应器
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        //亮屏并且解锁
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, "lock");
        wakeLock.acquire();
    }

    private class SensorValueListener implements SensorEventListener {
        float mLast;
        float value;
        boolean isUp;

        @Override
        public void onSensorChanged(SensorEvent event) {
            value = event.values[2];        //取重力的值
            if (value > 0 && mLast == 0.0f) {
                isUp = true;
            }
            if (isUp && mLast < -6.0f) {
                alarmClockOff();
            } else if (!isUp && mLast > 6.0f) {
                alarmClockOff();
            }
            mLast = value;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
