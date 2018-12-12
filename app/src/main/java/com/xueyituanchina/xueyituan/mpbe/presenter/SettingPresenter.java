package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.SettingActivity;

import java.io.File;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SettingPresenter extends BasePresenter<SettingActivity> {

    private final MeModel mModel;

    public SettingPresenter(SettingActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }

    public void updateNick(String nick) {
        mModel.updateNick(nick).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.responseNick(nick);
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void verifyPw(String pw) {
        mModel.verifyPw(pw).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.verifyPw(pw);
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void verifyTxPw(String pw) {
        mModel.verifyTxPw(pw).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.verifyTxPw(pw);
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void updatePw(String opw, String npw) {
        mModel.updatePw(opw, npw).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.updatePw();
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void updateTxPw(String opw, String npw) {
        mModel.updateTxPw(opw, npw).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.updateTxPw();
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }

    public void updateAvatar(File file) {
        mModel.updateAvatar(file).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.responseAvatar();
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }

    public void logout() {
        mModel.logout().subscribe(new NetCallBackObserver<BaseBean>(new GetImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean baseBean) {
                mIView.logout();
            }

            @Override
            public void responseFail(BaseBean baseBean) {

            }
        });
    }
}
