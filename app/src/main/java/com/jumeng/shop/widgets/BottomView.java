package com.jumeng.shop.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * ============================================================
 * 描 述 : 底部弹出框
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/11.
 * ============================================================
 */
public class BottomView {

    private View convertView;
    private Context mContext;
    private int mTheme;
    private Dialog mDialog;
    private int animationStyle;
    private boolean isTop = false;

    public BottomView(Context context, int theme, View convertView) {
        this.mContext = context;
        this.mTheme = theme;
        this.convertView = convertView;
    }

    public BottomView(Context context, int theme, int resource) {
        this.mContext = context;
        this.mTheme = theme;
        this.convertView = View.inflate(context, resource, null);
    }

    public void showBottomView(boolean CanceledOnTouchOutside) {
        if (mTheme == 0) {
            mDialog = new Dialog(mContext);
        } else {
            mDialog = new Dialog(mContext, mTheme);
        }
        mDialog.setCanceledOnTouchOutside(CanceledOnTouchOutside);
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(convertView);
        Window window = mDialog.getWindow();
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = display.getWidth();
        if (isTop) {
            layoutParams.gravity = Gravity.TOP;
        } else {
            layoutParams.gravity = Gravity.BOTTOM;
        }
        if (animationStyle == 0) {
        } else {
            window.setWindowAnimations(animationStyle);
        }
        window.setAttributes(layoutParams);
        mDialog.show();
    }

    public void setTopIfNecessary() {
        isTop = true;
    }

    public void setAnimation(int animationStyle) {
        this.animationStyle = animationStyle;
    }

    public View getBottomView() {
        return convertView;
    }

    public void dismissBottomView() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
