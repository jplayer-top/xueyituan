package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.AwardModel;
import com.xueyituanchina.xueyituan.ui.activity.PayTaskActivity;
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

public class PayTaskPresenter extends BasePresenter<PayTaskActivity> {

    private final AwardModel mModel;

    public PayTaskPresenter(PayTaskActivity iView) {
        super(iView);
        mModel = new AwardModel(XYTServer.class);
    }

    public void wxPay(String orderId) {
        mModel.payTaskWx(orderId).subscribe(new NetCallBackObserver<WxPayInfoBean>(new LoaddingImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(WxPayInfoBean orderBean) {
                mIView.wxPay(orderBean);
            }

            @Override
            public void responseFail(WxPayInfoBean orderBean) {

            }
        });
    }

    public void aliPay(String orderId) {
        mModel.payTaskAli(orderId).subscribe(new NetCallBackObserver<AliPayInfoBean>(new LoaddingImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(AliPayInfoBean orderBean) {
                mIView.aliPay(orderBean);
            }

            @Override
            public void responseFail(AliPayInfoBean orderBean) {

            }
        });
    }

}
