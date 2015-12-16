package com.jumeng.shop.utils;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jumeng.shop.MyApplication;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class KeyBoardUtils {

    private KeyBoardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(View paramEditText) {
        ((InputMethodManager) MyApplication.getInstance().getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
    }

    /**
     * 显示键盘
     */
    public static void showKeyBoard(final View paramEditText) {
        paramEditText.requestFocus();
        paramEditText.post(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) MyApplication.getInstance().getSystemService("input_method")).showSoftInput(paramEditText, 0);
            }
        });
    }
}
