package com.jumeng.shop.utils;

import android.widget.Toast;

import com.jumeng.shop.MyApplication;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/9.
 * ============================================================
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 显示Toast
     */
    public static void show(CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
