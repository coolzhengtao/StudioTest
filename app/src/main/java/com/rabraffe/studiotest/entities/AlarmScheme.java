package com.rabraffe.studiotest.entities;

import java.io.Serializable;

/**
 * 闹钟计划实体类
 * Created by coolz on 2015/10/15 0015.
 */
public class AlarmScheme implements Serializable {
    public static final int TYPE_ONCE = 0x1;        //单次
    public static final int TYPE_EVERYDAY = 0x2;    //每日
    public static final int TYPE_WORKDAY = 0x3;     //工作日
    public static final int TYPE_CUSTOM = 0x4;      //自定义


    private String name;                //闹钟名称
    private int type;                   //闹钟类型
    private boolean isVibrateOn;        //是否震动

}
