package com.jumeng.shop.fragments;

import com.jumeng.shop.fragments.delegate.TogetherDelegate;

/**
 * ============================================================
 * 描 述 : 一起买
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class TogetherFragment extends BaseFragment<TogetherDelegate> {
    @Override
    protected Class<TogetherDelegate> getDelegateClass() {
        return TogetherDelegate.class;
    }
}
