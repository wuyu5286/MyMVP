package com.jumeng.shop.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.jumeng.shop.R;
import com.jumeng.shop.activities.delegate.MainDelegate;
import com.jumeng.shop.constants.ConstantValue;
import com.jumeng.shop.fragments.FragmentFactory;
import com.jumeng.shop.fragments.GrabFragment;
import com.jumeng.shop.fragments.HomeFragment;
import com.jumeng.shop.fragments.MoreFragment;
import com.jumeng.shop.fragments.SelfFragment;
import com.jumeng.shop.fragments.TogetherFragment;

/**
 * ============================================================
 * 描 述 : 主页
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class MainActivity extends BaseActivity<MainDelegate> implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private int index = 0;
    private HomeFragment mHomeFragment;
    private TogetherFragment mTogetherFragment;
    private MoreFragment mMoreFragment;
    private GrabFragment mGrabFragment;
    private SelfFragment mSelfFragment;
    private FragmentManager mFragmentManager;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void onBind() {
        super.onBind();
        mFragmentManager = getFragmentManager();
        onTabSelected(0);
        viewDelegate.getMainMore().setOnClickListener(this);
        viewDelegate.getMainTab().setOnCheckedChangeListener(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int position = intent.getIntExtra(ConstantValue.MAIN_INDEX, 0);
        onTabSelected(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_more:
                onTabSelected(2);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                onTabSelected(0);
                break;
            case R.id.main_together:
                onTabSelected(1);
                break;
            case R.id.main_grab:
                onTabSelected(3);
                break;
            case R.id.main_self:
                onTabSelected(4);
                break;
        }
    }

    private void onTabSelected(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();//必须放在这里,每次调用都要实例化
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (null == mHomeFragment) {
                    mHomeFragment = (HomeFragment) FragmentFactory.getFragment(0);
                    transaction.add(viewDelegate.getMainContainer(), mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (null == mTogetherFragment) {
                    mTogetherFragment = (TogetherFragment) FragmentFactory.getFragment(1);
                    transaction.add(viewDelegate.getMainContainer(), mTogetherFragment);
                } else {
                    transaction.show(mTogetherFragment);
                }
                break;
            case 2:
                if (null == mMoreFragment) {
                    mMoreFragment = (MoreFragment) FragmentFactory.getFragment(2);
                    transaction.add(viewDelegate.getMainContainer(), mMoreFragment);
                } else {
                    transaction.show(mMoreFragment);
                }
                break;
            case 3:
                if (null == mGrabFragment) {
                    mGrabFragment = (GrabFragment) FragmentFactory.getFragment(3);
                    transaction.add(viewDelegate.getMainContainer(), mGrabFragment);
                } else {
                    transaction.show(mGrabFragment);
                }
                break;
            case 4:
                if (null == mSelfFragment) {
                    mSelfFragment = (SelfFragment) FragmentFactory.getFragment(4);
                    transaction.add(viewDelegate.getMainContainer(), mSelfFragment);
                } else {
                    transaction.show(mSelfFragment);
                }
                break;
        }
        index = position;
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mTogetherFragment != null) {
            transaction.hide(mTogetherFragment);
        }
        if (mMoreFragment != null) {
            transaction.hide(mMoreFragment);
        }
        if (mGrabFragment != null) {
            transaction.hide(mGrabFragment);
        }
        if (mSelfFragment != null) {
            transaction.hide(mSelfFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 自己记录fragment的位置,防止activity被系统回收时，fragment错乱的问题
        outState.putInt(ConstantValue.MAIN_INDEX, index);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        index = savedInstanceState.getInt(ConstantValue.MAIN_INDEX);
    }
}
