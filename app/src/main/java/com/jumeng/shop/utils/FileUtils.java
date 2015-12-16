package com.jumeng.shop.utils;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class FileUtils {
    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getPath(Context context, String fileName) {
        String task = getDir(context, "task");
        String name = fileName + "_" + SystemClock.currentThreadTimeMillis();
        File file = new File(task, StringUtils.string2MD5(name));
        String path = file.getAbsolutePath();
        return path;
    }

    private static String getDir(Context context, String path) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equalsIgnoreCase(state)) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root, "Android/data/" + context.getPackageName() + "/" + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir.getAbsolutePath();
        } else {
            File dir = new File(context.getFilesDir(), path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir.getAbsolutePath();
        }
    }
}
