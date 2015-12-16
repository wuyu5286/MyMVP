package com.jumeng.shop.activities;

import com.jumeng.shop.activities.delegate.SearchDelegate;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class SearchActivity extends BaseActivity<SearchDelegate> {
    @Override
    protected Class<SearchDelegate> getDelegateClass() {
        return SearchDelegate.class;
    }

    @Override
    protected void onBind() {
        super.onBind();
    }
}
