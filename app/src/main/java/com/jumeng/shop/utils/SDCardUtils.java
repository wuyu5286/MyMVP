package com.jumeng.shop.utils;

import android.os.Environment;
import android.os.StatFs;

/**
 * ============================================================
 * 描 述 : SD卡相关的辅助类
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/14.
 * ============================================================
 */
@SuppressWarnings("deprecation")
public class SDCardUtils {

    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean hasSDCard() {
        boolean mHasSDCard = false;
        if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
            mHasSDCard = true;
        } else {
            mHasSDCard = false;
        }
        return mHasSDCard;
    }

    private static boolean sdCardWrite() {
        return Environment.getExternalStorageDirectory().canWrite();
    }

    public static boolean hasSDCardAndCanWrite() {
        return hasSDCard() && sdCardWrite();
    }

    /**
     * 获取SD卡的可用大小,单位字节
     */
    public long getSDCardAvailableStore() {
        if (hasSDCardAndCanWrite()) {
            String path = getSDCardPath();
            if (path != null) {
                StatFs statFs = new StatFs(path);
                long blocSize = statFs.getBlockSize();
                long availaBlock = statFs.getAvailableBlocks();
                return availaBlock * blocSize;
            }
        }
        return 0;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     */
    public static long getSDCardAllSize() {
        if (hasSDCard()) {
            StatFs statFs = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) statFs.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = statFs.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "/sdcard/";
    }

    /**
     * 获取系统存储路径
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
