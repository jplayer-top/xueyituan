package com.xueyituanchina.xueyituan;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;

import java.lang.ref.WeakReference;

import io.rong.imkit.RongIM;
import top.jplayer.baseprolibrary.BaseInitApplication;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

/**
 * Created by Obl on 2018/8/16.
 * com.xueyituanchina.xueyituan
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class XYTApplication extends MultiDexApplication {
    public static String uid = "";
    public static String cuid = "1";
    public static String token = "";
    public static boolean isVip = false;
    public static String login_name = "匿名";
    public final static String APP_ID = "wx2afd95f49d8dd6be";
    public static String lnglat = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }

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
        Bugly.init(this, "236a5634ef", false);
        UMConfigure.init(this, "5c11e9d4b465f58c190001ab", "push0", 1,
                "8f87fd0783f4f8dcba0e693ea851ad80");
        PushAgent.getInstance(this).register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    public static boolean assert2Login(Activity activity) {
        activity = new WeakReference<>(activity).get();
        String isLogin = (String) SharePreUtil.getData(activity, "mark_login", "0");
        if (!"1".equals(isLogin)) {
            ActivityUtils.init().start(activity, LoginActivity.class);
            return false;
        } else {
            return true;
        }
    }

    public static boolean assertNoLogin(Activity activity) {
        activity = new WeakReference<>(activity).get();
        String isLogin = (String) SharePreUtil.getData(activity, "mark_login", "0");
        if (!"1".equals(isLogin)) {
            ToastUtils.init().showInfoToast(activity, "请先登录");
            return false;
        } else {
            return true;
        }
    }
}
