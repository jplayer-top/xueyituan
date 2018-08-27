package com.xueyituanchina.xueyituan.mpbe.presenter;

import android.text.TextUtils;
import android.widget.Button;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.model.LoginModel;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/8/13.
 * top.jplayer.quick_test.mvp.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LoginPresenter extends BasePresenter<LoginActivity> {

    private final LoginModel mModel;

    public LoginPresenter(LoginActivity iView) {
        super(iView);
        mModel = new LoginModel(XYTServer.class);
    }

    public void verifySms(String phone, String smCode) {
        mModel.requestVerfiyCode(phone, smCode)
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
                    @Override
                    public void responseSuccess(BaseBean baseBean) {
                        if (TextUtils.equals("000", baseBean.code)) {
                            mIView.goNext();
                        }
                    }

                    @Override
                    public void responseFail(BaseBean baseBean) {

                    }
                });
    }

    public void sendSms(Map<String, String> map, Button rtnCode) {
        mModel.requestSms(map)
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
                    @Override
                    public void responseSuccess(BaseBean baseBean) {
                        if (TextUtils.equals("000", baseBean.code)) {
                            mIView.smsSend(rtnCode);
                        }
                    }

                    @Override
                    public void responseFail(BaseBean baseBean) {

                    }
                });
    }

    public void forget(Map<String, String> map) {
        mModel.requestForget(map).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.forget();

            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void login(String phone, String password) {
        mModel.requestLogin(phone, password).subscribe(new NetCallBackObserver<LoginBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(LoginBean loginBean) {
                mIView.login(loginBean);
            }

            @Override
            public void responseFail(LoginBean loginBean) {

            }

        });
    }

    public void register(Map<String, String> map) {
        mModel.requestRegister(map).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                login(map.get("phone"), map.get("password"));

            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }
}
