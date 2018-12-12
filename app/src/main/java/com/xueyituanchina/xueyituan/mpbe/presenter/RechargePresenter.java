package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.RechargeActivity;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class RechargePresenter extends BasePresenter<RechargeActivity> {

    private final HomeModel mModel;

    public RechargePresenter(RechargeActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }

    public void wxPay(String money) {
        mModel.wxRecharge(money).subscribe(new NetCallBackObserver<WxPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(WxPayInfoBean orderBean) {
                mIView.wxPay(orderBean);
            }

            @Override
            public void responseFail(WxPayInfoBean orderBean) {

            }
        });
    }

    public void wxPayShop(String money) {
        mModel.wxShop(money).subscribe(new NetCallBackObserver<WxPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(WxPayInfoBean orderBean) {
                mIView.wxPay(orderBean);
            }

            @Override
            public void responseFail(WxPayInfoBean orderBean) {

            }
        });
    }

    public void wxPayWall(String money) {
        mModel.wxWall(money).subscribe(new NetCallBackObserver<WxPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(WxPayInfoBean orderBean) {
                mIView.wxPay(orderBean);
            }

            @Override
            public void responseFail(WxPayInfoBean orderBean) {

            }
        });
    }

    public void aliWall(String money) {
        mModel.aliWall(money).subscribe(new NetCallBackObserver<AliPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(AliPayInfoBean orderBean) {
                mIView.aliPay(orderBean);
            }

            @Override
            public void responseFail(AliPayInfoBean orderBean) {

            }
        });
    }

    public void aliPay(String money) {
        mModel.aliRecharge(money).subscribe(new NetCallBackObserver<AliPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(AliPayInfoBean orderBean) {
                mIView.aliPay(orderBean);
            }

            @Override
            public void responseFail(AliPayInfoBean orderBean) {

            }
        });
    }

    public void aliPayShop(String money) {
        mModel.aliShop(money).subscribe(new NetCallBackObserver<AliPayInfoBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(AliPayInfoBean orderBean) {
                mIView.aliPay(orderBean);
            }

            @Override
            public void responseFail(AliPayInfoBean orderBean) {

            }
        });
    }

    public void payVip(String recharge) {
        mModel.payVip(recharge).subscribe(new NetCallBackObserver<BaseBean>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean orderBean) {
                mIView.payVip(orderBean);
            }

            @Override
            public void responseFail(BaseBean orderBean) {

            }
        });
    }
}
