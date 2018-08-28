package com.xueyituanchina.xueyituan.mpbe.presenter;

import android.text.TextUtils;
import android.widget.Button;

import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.event.LoginSuccessEvent;
import com.xueyituanchina.xueyituan.mpbe.model.LoginModel;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

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
        mModel.requestLogin(phone, password).subscribe(new NetCallBackObserver<LoginBean>(new GetImplTip(mIView) {
        }) {
            @Override
            public void responseSuccess(LoginBean loginBean) {
                mIView.login(loginBean);

                String imtoken = loginBean.imtoken;
                String uid = loginBean.uid + "";

                SharePreUtil.saveData(mIView, "login_phone", phone);
                SharePreUtil.saveData(mIView, "login_password", password);
                SharePreUtil.saveData(mIView, "login_uid", uid);
                SharePreUtil.saveData(mIView, "login_token", imtoken);

                XYTApplication.uid = uid;
                XYTApplication.token = imtoken;

                EventBus.getDefault().post(new LoginSuccessEvent(uid));
            }

            @Override
            public void responseFail(LoginBean loginBean) {

            }

        });
    }

    public void register(Map<String, String> map) {
        mModel.requestRegister(map).subscribe(new NetCallBackObserver<BaseBean>(new LoaddingImplTip(mIView)) {
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
