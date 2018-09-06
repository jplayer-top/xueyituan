package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.StoreActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StorePresenter extends BasePresenter<StoreActivity> {

    private final HomeModel mModel;

    public StorePresenter(StoreActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }

    public void storeInfo(String id) {
        mModel.storeInfo(id).subscribe(new NetCallBackObserver<StoreBean>(new GetImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(StoreBean bean) {
                mIView.responseSuccess();
                mIView.storeInfo(bean);
            }

            @Override
            public void responseFail(StoreBean bean) {
                mIView.showError();
            }
        });
    }
}
