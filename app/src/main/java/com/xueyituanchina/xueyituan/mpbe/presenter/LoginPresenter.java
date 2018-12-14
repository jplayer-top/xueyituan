package com.xueyituanchina.xueyituan.mpbe.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.event.LoginSuccessEvent;
import com.xueyituanchina.xueyituan.mpbe.model.LoginModel;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

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
                connectIm(loginBean);
                String imtoken = loginBean.imtoken;
                String uid = loginBean.uid + "";
                SharePreUtil.saveData(mIView, "login_phone", phone);
                SharePreUtil.saveData(mIView, "login_password", password);
                SharePreUtil.saveData(mIView, "login_uid", uid);
                SharePreUtil.saveData(mIView, "login_token", imtoken);
                SharePreUtil.saveData(mIView, "mark_login", "1");
                XYTApplication.uid = uid;
                XYTApplication.token = imtoken;
                if (loginBean.shield != 1) {
                    EventBus.getDefault().post(new LoginSuccessEvent(uid));
                } else {
                    ToastUtils.init().showErrorToast(mIView, "您的账户存在违规操作\n请致电客服0635 8091618 ");
                }
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

    private void connectIm(LoginBean loginBean) {
        String imtoken = loginBean.imtoken;
        if (imtoken == null) {
            String token = (String) SharePreUtil.getData(mIView, "token", "");
            if (token != null && !token.equals("")) {
                connect(token);
            }
        } else {
            connect(imtoken);
        }
    }

    private void connect(String token) {

        if (mIView.getApplicationInfo().packageName.equals(getCurProcessName(mIView.getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
}
