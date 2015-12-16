package com.jumeng.shop;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.jumeng.shop.db.util.DbCore;
import com.jumeng.shop.okhttp.OkHttpClientManager;
import com.jumeng.shop.utils.PreferencesUtils;
import com.jumeng.shop.widgets.log.Logger;

import java.util.concurrent.TimeUnit;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/4.
 * ============================================================
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private static long mainThreadId;
    private static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainThreadId = android.os.Process.myTid();
        mainHandler = new Handler();
        OkHttpClientManager.getInstance().getOkHttpClient().setConnectTimeout(100000, TimeUnit.MILLISECONDS);
        PreferencesUtils.instance().init(this);
        Logger.init(BuildConfig.LOG_DEBUG);
        DbCore.init(this);
    }

    public static Context getInstance() {
        return instance;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }
}
