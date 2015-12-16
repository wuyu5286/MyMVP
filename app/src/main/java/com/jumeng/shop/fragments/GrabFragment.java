package com.jumeng.shop.fragments;

import com.jumeng.shop.fragments.delegate.GrabDelegate;

/**
 * ============================================================
 * 描 述 : 首页
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class GrabFragment extends BaseFragment<GrabDelegate> {
    @Override
    protected Class<GrabDelegate> getDelegateClass() {
        return GrabDelegate.class;
    }
}
