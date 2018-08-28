package com.xueyituanchina.xueyituan;

import android.support.multidex.MultiDexApplication;

import top.jplayer.baseprolibrary.BaseInitApplication;

/**
 * Created by Obl on 2018/8/16.
 * com.xueyituanchina.xueyituan
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class XYTApplication extends MultiDexApplication {
    public static String uid = "";
    public static String token = "";

    @Override
    public void onCreate() {
        super.onCreate();
        BaseInitApplication.with(this)
                .retrofit()
                .swipeBack()
                .zxing()
                .skin()
                .fixFileProvide();
    }
}
