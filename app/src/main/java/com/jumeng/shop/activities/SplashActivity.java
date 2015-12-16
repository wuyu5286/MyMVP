package com.jumeng.shop.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jumeng.shop.R;
import com.jumeng.shop.constants.PreferencesKey;
import com.jumeng.shop.utils.PreferencesUtils;
import com.jumeng.shop.utils.UIUtils;

public class SplashActivity extends AppCompatActivity {

    private PreferencesUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        utils = PreferencesUtils.instance();
        boolean isFirst = utils.getBoolean(PreferencesKey.IS_FIRST);
        if (isFirst) {
            toGuide();
        } else {
            delayToMain();
        }

    }

    private void delayToMain() {
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.launch(SplashActivity.this);
                finish();
            }
        }, 500);
    }

    private void toGuide() {

    }
}
