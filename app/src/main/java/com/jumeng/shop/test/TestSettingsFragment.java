package com.jumeng.shop.test;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;

import com.jumeng.shop.R;
import com.jumeng.shop.constants.PreferencesKey;
import com.jumeng.shop.fragments.MDColorsDialogFragment;
import com.jumeng.shop.utils.PreferencesUtils;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/11.
 * ============================================================
 */
public class TestSettingsFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    public static BasePreferenceFragment newInstance() {
        return new TestSettingsFragment();
    }


    private Preference theme;//主题设置

    private ListPreference textSize;//字体大小
    private CheckBoxPreference showRemark;//显示备注
    private CheckBoxPreference showDefGroup;//显示默认分组微博
    PreferencesUtils utils;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPreferencesFromResource(R.xml.test_settings_item);

        theme = findPreference("theme");
        theme.setOnPreferenceClickListener(this);
        utils = PreferencesUtils.instance();
        int index = utils.getInt(PreferencesKey.THEME_INDEX);
        theme.setSummary(getResources().getStringArray(R.array.mdColorNames)[index]);

        showRemark= (CheckBoxPreference) findPreference("showRemark");
        showRemark.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if ("theme".equals(preference.getKey())) {
            MDColorsDialogFragment.launch(getActivity());
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }
}
