package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.UserOrderListActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserOrderListPresenter extends BasePresenter<UserOrderListActivity> {

    private final MeModel mModel;

    public UserOrderListPresenter(UserOrderListActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }


    public void bill() {
        mModel.bill().subscribe(new NetCallBackObserver<UserOrderListBean>() {
            @Override
            public void responseSuccess(UserOrderListBean bean) {
                mIView.bill(bean);
            }

            @Override
            public void responseFail(UserOrderListBean bean) {

            }
        });
    }
}
