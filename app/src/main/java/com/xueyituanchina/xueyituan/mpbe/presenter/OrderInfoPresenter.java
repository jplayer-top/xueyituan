package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderInfoBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.OrderInfoActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderInfoPresenter extends BasePresenter<OrderInfoActivity> {

    private final HomeModel mModel;

    public OrderInfoPresenter(OrderInfoActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }

    public void getOrderInfo(String orderId) {
        mModel.orderInfo(orderId)
                .subscribe(new NetCallBackObserver<OrderInfoBean>() {
                    @Override
                    public void responseSuccess(OrderInfoBean orderInfoBean) {
                        mIView.orderInfo(orderInfoBean);
                    }

                    @Override
                    public void responseFail(OrderInfoBean orderInfoBean) {

                    }
                });
    }

    public void submitIssue(String orderId, String desc, float rating, boolean checked) {
        mModel.orderIssue(orderId, desc, checked ? "1" : "0", (int) rating + "")
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
                    @Override
                    public void responseSuccess(BaseBean bean) {
                        mIView.orderIssue();
                    }

                    @Override
                    public void responseFail(BaseBean bean) {

                    }
                });
    }
}
