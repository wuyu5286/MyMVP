package com.jumeng.shop.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.jumeng.shop.MyApplication;

import java.lang.reflect.Field;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/14.
 * ============================================================
 */
public class ProgressDialogUtils {

    public static ProgressDialog progressDialog;

    public static ProgressDialog createProgressDialog(String message, int widgetColor) {
        dismissProgressDialog();
        progressDialog = new MProgressDialog(MyApplication.getInstance(), widgetColor);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static void updateProgressDialog(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        }
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static class MProgressDialog extends ProgressDialog {
        private int color;
        public MProgressDialog(Context context, int color) {
            super(context);
            this.color = color;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (color != 0) {
                try {
                    Field progressBarField = MProgressDialog.class.getSuperclass().getDeclaredField("mProgress");
                    progressBarField.setAccessible(true);
                    ProgressBar progressBar = (ProgressBar) progressBarField.get(this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ColorStateList stateList = ColorStateList.valueOf(color);
                        progressBar.setProgressTintList(stateList);
                        progressBar.setSecondaryProgressTintList(stateList);
                        progressBar.setIndeterminateTintList(stateList);
                    } else {
                        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
                            mode = PorterDuff.Mode.MULTIPLY;
                        }
                        if (progressBar.getIndeterminateDrawable() != null)
                            progressBar.getIndeterminateDrawable().setColorFilter(color, mode);
                        if (progressBar.getProgressDrawable() != null)
                            progressBar.getProgressDrawable().setColorFilter(color, mode);
                    }
                } catch (Throwable throwable) {
                }
            }
        }
    }
}
