package com.jumeng.shop.fragments;

import com.jumeng.shop.fragments.delegate.SelfDelegate;

/**
 * ============================================================
 * 描 述 : 首页
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class SelfFragment extends BaseFragment<SelfDelegate> {
    @Override
    protected Class<SelfDelegate> getDelegateClass() {
        return SelfDelegate.class;
    }
}
