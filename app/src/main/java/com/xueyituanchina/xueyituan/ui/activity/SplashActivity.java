package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.ui.MainActivity;

import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/7/27.
 * top.jplayer.quick_test.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SplashActivity extends SuperBaseActivity {
    @Override
    protected int initRootLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        ViewAnimator.animate(view.findViewById(R.id.image))
                . alpha(1, 0.75f, 0.5f)
                .duration(1500)
                .onStop(() -> {
                    ActivityUtils.init().start(this, MainActivity.class);
                    finish();
                })
                .start();

    }
}
