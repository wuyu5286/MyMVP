package com.jumeng.shop.activities.delegate;

import android.widget.ImageView;
import android.widget.RadioGroup;

import com.jumeng.shop.R;
import com.jumeng.shop.interfaces.AppDelegate;

import butterknife.Bind;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class MainDelegate extends AppDelegate {

    @Bind(R.id.main_more)
    ImageView mMainMore;
    @Bind(R.id.main_tab)
    RadioGroup mMainTab;

    @Override
    public int layoutResId() {
        return R.layout.activity_main;
    }

    public int getMainContainer() {
        return R.id.main_container;
    }

    public ImageView getMainMore() {
        return mMainMore;
    }

    public RadioGroup getMainTab() {
        return mMainTab;
    }
}
