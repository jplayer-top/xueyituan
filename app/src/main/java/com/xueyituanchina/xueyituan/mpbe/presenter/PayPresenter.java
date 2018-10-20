package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.PayActivity;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PayPresenter extends BasePresenter<PayActivity> {

    private final HomeModel mModel;

    public PayPresenter(PayActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }


    public void payAliOrder(String id) {
        mModel.payAli(id).subscribe(new NetCallBackObserver<AliPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(AliPayInfoBean bean) {
                mIView.payAliOrder(bean);
            }

            @Override
            public void responseFail(AliPayInfoBean bean) {

            }
        });
    }  public void payWxOrder(String id) {
        mModel.payWx(id).subscribe(new NetCallBackObserver<WxPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(WxPayInfoBean bean) {
                mIView.payWxOrder(bean);
            }

            @Override
            public void responseFail(WxPayInfoBean bean) {

            }
        });
    }
}
