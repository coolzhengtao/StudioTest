package com.rabraffe.studiotest.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rabraffe.studiotest.R;
import com.rabraffe.studiotest.entities.AlarmScheme;
import com.rabraffe.studiotest.entities.Alarms;
import com.rabraffe.studiotest.utils.NotifierUtil;
import com.rabraffe.studiotest.utils.StaticClasss;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.dpTime)
    TimePicker tpTime;

    @ViewInject(R.id.btnSetAlarm)
    Button btnSetAlarm;

    @ViewInject(R.id.tv_version)
    TextView tv_version;

    WindowManager windowManager;

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
        AlarmScheme scheme = new AlarmScheme();
        scheme.setAlarmTime(dtAlarm);
        scheme.setType(AlarmScheme.TYPE_ONCE);
        scheme.setIsEnabled(true);
        Alarms.getInstance().addAlarm(scheme);
        Alarms.getInstance().setNextAlarm(this);
        NotifierUtil.ShowToast(this, "设置闹钟成功", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        tv_version.setText("版本号：" + StaticClasss.version_code);
//        LayoutTransition transition = new LayoutTransition();
//        Animator animator = ObjectAnimator.ofFloat(null,
//                "rotationX", 90f, 0f).setDuration(2000);
//        transition.setAnimator(LayoutTransition.APPEARING, animator);
//
//
//        windowManager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
//        RotateAnimation rotateAnimation = new RotateAnimation(90f, 0f);
//        rotateAnimation.setDuration(2000);
//        btnSetAlarm.startAnimation(rotateAnimation);
//        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//        final LinearLayout ll_window = new LinearLayout(this);
//        ll_window.setLayoutTransition(transition);
//        Button btnTest = new Button(this);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv_add = new TextView(MainActivity.this);
//                tv_add.setText("我擦");
//                ll_window.addView(tv_add);
//                //windowManager.updateViewLayout(ll_window, params);
//            }
//        });
//        ll_window.addView(btnTest);
//        btnTest.setText("测试");
//
//        params.type = WindowManager.LayoutParams.TYPE_PHONE;
//        //设置图片格式，效果为背景透明
//        params.format = PixelFormat.RGBA_8888;
//        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
//        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        //调整悬浮窗显示的停靠位置为左侧置顶
//        params.gravity = Gravity.CENTER;
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        btnTest.measure(View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
//                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        windowManager.addView(ll_window, params);
    }
}
