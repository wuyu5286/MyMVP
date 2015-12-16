package com.jumeng.shop.fragments;

import com.jumeng.shop.fragments.delegate.MoreDelegate;

/**
 * ============================================================
 * 描 述 : 更多
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/15.
 * ============================================================
 */
public class MoreFragment extends BaseFragment<MoreDelegate> {
    @Override
    protected Class<MoreDelegate> getDelegateClass() {
        return MoreDelegate.class;
    }
}
