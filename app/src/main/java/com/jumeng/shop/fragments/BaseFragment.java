package com.jumeng.shop.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jumeng.shop.interfaces.IDelegate;

/**
 * ============================================================
 * 描 述 : Fragment基础类
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/8.
 * ============================================================
 */
public abstract class BaseFragment<T extends IDelegate> extends Fragment {

    protected T viewDelegate;
    protected Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDelegate.init(inflater, container, savedInstanceState);
        return viewDelegate.getView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBind();
    }

    protected abstract Class<T> getDelegateClass();

    /**
     * 数据绑定
     */
    protected void onBind() {
    }

    @Override
    public final void onPause() {
        beforePause();
        super.onPause();
    }

    @Override
    public final void onResume() {
        super.onResume();
        afterResume();
    }

    @Override
    public final void onDestroy() {
        onDestroyDelegate();
        viewDelegate = null;
        super.onDestroy();
    }

    protected void beforePause() {
    }

    protected void afterResume() {
    }

    protected void onDestroyDelegate() {
    }
}
