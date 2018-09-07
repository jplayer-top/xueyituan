package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.BrandBBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.BrandInfoActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;

/**
 * Created by Obl on 2018/9/7.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BrandPresenter extends BasePresenter<BrandInfoActivity> {

    private final HomeModel mModel;

    public BrandPresenter(BrandInfoActivity brandInfoActivity) {
        super(brandInfoActivity);
        mModel = new HomeModel(XYTServer.class);
    }

    public void brandInfo(String id) {
        mModel.brandInfo(id).subscribe(new NetCallBackObserver<BrandBBean>(new GetImplTip(mIView)) {
            @Override
            public void responseSuccess(BrandBBean brandBBean) {
                mIView.responseSuccess();
                mIView.brandInfo(brandBBean);
            }

            @Override
            public void responseFail(BrandBBean nearbyActiveBean) {
                mIView.showError();
            }
        });
    }
}
