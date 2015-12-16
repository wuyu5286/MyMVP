package com.jumeng.shop.interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/4.
 * ============================================================
 */
public abstract class AppDelegate implements IDelegate {

    protected View rootView;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutResId(), container, false);
        ButterKnife.bind(this, rootView);
//        initView(rootView);
    }

    @Override
    public View getView() {
        return rootView;
    }

    public abstract int layoutResId();

//    protected abstract void initView(View view);

}
