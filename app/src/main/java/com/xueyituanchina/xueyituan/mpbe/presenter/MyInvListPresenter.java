package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInviteBean;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.MeInvActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MyInvListPresenter extends BasePresenter<MeInvActivity> {

    private final MeModel mModel;

    public MyInvListPresenter(MeInvActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }


    public void inviteList() {
        mModel.inviteList().subscribe(new NetCallBackObserver<MyInviteBean>() {
            @Override
            public void responseSuccess(MyInviteBean bean) {
                mIView.inviteList(bean);
            }

            @Override
            public void responseFail(MyInviteBean bean) {

            }
        });
    }
}
