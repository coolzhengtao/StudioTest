package com.rabraffe.studiotest.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

/**
 * 提供通用的提醒功能，包括Toast、AlertDialog等
 *
 * @author Neo
 */
public class NotifierUtil {
    /**
     * 弹出一个简单的Toast
     *
     * @param context
     * @param strMsg
     * @param bShowLong
     */
    public static void ShowToast(Context context, String strMsg,
                                 boolean bShowLong) {
        Toast.makeText(context, strMsg,
                bShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出一个简单的Toast
     *
     * @param context
     * @param strMsg
     * @param bShowLong
     */
    public static void ShowToast(Context context, int strMsg, boolean bShowLong) {
        Toast.makeText(context, context.getResources().getString(strMsg),
                bShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出一个自定义布局的Toast
     *
     * @param context
     * @param vMsgGroup
     * @param bShowLong
     */
    public static void ShowToast(Context context, View vMsgGroup,
                                 boolean bShowLong) {
        Toast toast = new Toast(context);
        toast.setView(vMsgGroup);
        toast.setDuration(bShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 在线程中弹出一个Toast
     *
     * @param context
     * @param strMsg
     * @param bShowLong
     * @param handler
     */
    public static void ShowToastInThread(final Context context,
                                         final String strMsg, final boolean bShowLong, Handler handler) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                // 在UI线程中调用
                ShowToast(context, strMsg, bShowLong);
            }
        });
    }
}
