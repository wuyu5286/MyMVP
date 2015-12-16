package com.jumeng.shop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jumeng.shop.interfaces.IDelegate;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * ============================================================
 * 描 述 : Activity基础类
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/4.
 * ============================================================
 */
public abstract class BaseActivity<T extends IDelegate> extends AppCompatActivity {
    protected T viewDelegate;
    private static Activity activity;
    private static List<BaseActivity> activityList = new LinkedList<>();

//    private int theme = 0;// 当前界面设置的主题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            theme = configTheme();
//        } else {
//            theme = savedInstanceState.getInt("theme");
//        }
//        // 设置主题
//        if (theme > 0)
//            setTheme(theme);
        super.onCreate(savedInstanceState);
        synchronized (activityList) {
            activityList.add(this);
        }
        try {
            viewDelegate = getDelegateClass().newInstance();
            viewDelegate.init(getLayoutInflater(), null, savedInstanceState);
            setContentView(viewDelegate.getView());
            onBind();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt("theme", theme);
    }

//    protected int configTheme() {
//        if (theme > 0) return theme;
//        return -1;
//    }

    protected abstract Class<T> getDelegateClass();

    /**
     * 数据绑定
     */
    protected void onBind() {
    }

    @Override
    protected final void onPause() {
        beforePause();
        super.onPause();
        activity = null;
    }


    @Override
    protected final void onResume() {
        super.onResume();
        afterResume();
        activity = this;
//        if (theme == configTheme()) {
//        } else {
//            reLoad();
//            return;
//        }
    }

    public void reLoad() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        viewDelegate = null;
        onDestroyDelegate();
        super.onDestroy();
        synchronized (activityList) {
            activityList.remove(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (!handleBackPressed()) {
            super.onBackPressed();
        }
        //关闭窗体动画显示
        overridePendingTransition(0, android.R.anim.fade_out);
    }

    protected void beforePause() {
    }

    protected void afterResume() {
    }

    protected void onDestroyDelegate() {
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void exitApp() {
        ListIterator<BaseActivity> iterator = activityList.listIterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    public boolean handleBackPressed() {
        return false;
    }
}
