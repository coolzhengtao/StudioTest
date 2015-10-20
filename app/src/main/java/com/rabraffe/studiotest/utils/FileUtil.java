package com.rabraffe.studiotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 文件管理类
 * Created by Neo on 2015/10/19 0019.
 */
public class FileUtil {
    /**
     * 保存对象到磁盘
     *
     * @param path     路径
     * @param filename 文件名
     * @param obj      要保存的可序列化对象
     * @return
     */
    public static boolean saveObjectToFile(String path, String filename, Serializable obj) {
        boolean result = false;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(path + "/" + filename)));
            oo.writeObject(obj);
            result = true;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从文件中获取序列化过的对象
     *
     * @param path     路径
     * @param filename 文件名
     * @return
     */
    public static Object getObjectFromFile(String path, String filename) {
        try {
            ObjectInputStream oo = new ObjectInputStream(new FileInputStream(new File(path + "/" + filename)));
            try {
                return oo.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
