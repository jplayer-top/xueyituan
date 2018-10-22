package com.xueyituanchina.xueyituan;

import android.support.multidex.MultiDexApplication;

import io.rong.imkit.RongIM;
import top.jplayer.baseprolibrary.BaseInitApplication;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

/**
 * Created by Obl on 2018/8/16.
 * com.xueyituanchina.xueyituan
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class XYTApplication extends MultiDexApplication {
    public static String uid = "";
    public static String token = "";
    public final static String APP_ID = "wx2afd95f49d8dd6be";
    public static String lnglat = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
        }
        BaseInitApplication.with(this)
                .retrofit()
                .swipeBack()
                .zxing()
                .skin()
                .fixFileProvide();
    }
}
