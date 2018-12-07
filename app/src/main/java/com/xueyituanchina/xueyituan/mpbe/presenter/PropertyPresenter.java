package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.ProPertyBean;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.ProPertyActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PropertyPresenter extends BasePresenter<ProPertyActivity> {

    private final MeModel mModel;

    public PropertyPresenter(ProPertyActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }


    public void property() {
        mModel.property().subscribe(new NetCallBackObserver<ProPertyBean>() {
            @Override
            public void responseSuccess(ProPertyBean bean) {
                mIView.property(bean);
            }

            @Override
            public void responseFail(ProPertyBean bean) {

            }
        });
    }
}
