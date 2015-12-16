package com.jumeng.shop.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.jumeng.shop.MyApplication;

/**
 * ============================================================
 * 描 述 : 网络相关的工具类
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class NetUtils {

    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isAvailable() {
        NetworkInfo info = getNetworkInfo();
        if (null != info && info.isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected() {
        NetworkInfo info = getNetworkInfo();
        if (null != info && info.isConnected()) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivity = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            return connectivity.getActiveNetworkInfo();
        }
        return null;
    }

    /**
     * 获取网络类型
     */
    public static String getNetworkType() {
        ConnectivityManager connMgr = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return "mobile";
                case ConnectivityManager.TYPE_WIFI:
                    return "wifi";
            }
        }
        return "none";
    }

    /**
     * 打开网络设置页面
     */
    public static void openNetSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_WIRELESS_SETTINGS);
        MyApplication.getInstance().startActivity(intent);
    }
}
