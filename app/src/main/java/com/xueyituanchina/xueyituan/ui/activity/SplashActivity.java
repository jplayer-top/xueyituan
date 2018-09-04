package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.model.LoginModel;
import com.xueyituanchina.xueyituan.ui.MainActivity;

import java.util.Date;

import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/7/27.
 * top.jplayer.quick_test.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SplashActivity extends SuperBaseActivity {
    long preDateTime;
    boolean isLoginOk = false;

    @Override
    protected int initRootLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        ViewAnimator.animate(view.findViewById(R.id.image))
                .scale(1f, 1.1f)
                .duration(1500)
                .onStart(() -> {
                    preDateTime = new Date().getTime();
                    LoginModel model = new LoginModel(XYTServer.class);
                    String phone = (String) SharePreUtil.getData(this, "login_phone", "");
                    String password = (String) SharePreUtil.getData(this, "login_password", "");
                    if ("".equals(phone) || "".equals(password)) {
                        isLoginOk = true;
                        return;
                    }
                    model.requestLogin(phone, password).subscribe(new NetCallBackObserver<LoginBean>() {
                        @Override
                        public void responseSuccess(LoginBean loginBean) {
                            String imtoken = loginBean.imtoken;
                            String uid = loginBean.uid + "";

                            SharePreUtil.saveData(mActivity, "login_phone", phone);
                            SharePreUtil.saveData(mActivity, "login_password", password);
                            SharePreUtil.saveData(mActivity, "login_uid", uid);
                            SharePreUtil.saveData(mActivity, "login_token", imtoken);

                            XYTApplication.uid = uid;
                            XYTApplication.token = imtoken;
                        }

                        @Override
                        public void responseFail(LoginBean loginBean) {
                        }

                        @Override
                        public void onComplete() {
                            super.onComplete();
                            responseLogin();
                        }
                    });
                })
                .onStop(() -> {
                    if (isLoginOk) {
                        ActivityUtils.init().start(this, MainActivity.class);
                        finish();
                    }
                })
                .start();

    }

    private void responseLogin() {
        if (new Date().getTime() - 1000 > preDateTime) {
            ActivityUtils.init().start(mActivity, MainActivity.class);
            finish();
            return;
        }
        isLoginOk = true;
    }
}
