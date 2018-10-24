package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.ShopCreateActivity;

import okhttp3.RequestBody;
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

public class ShopCreatePresenter extends BasePresenter<ShopCreateActivity> {

    private final HomeModel mModel;

    public ShopCreatePresenter(ShopCreateActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }


    public void createShop(RequestBody body) {
        mModel.createShop(body)
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView.mActivity)) {
                    @Override
                    public void responseSuccess(BaseBean bean) {
                        mIView.createSuccess();
                    }

                    @Override
                    public void responseFail(BaseBean bean) {

                    }
                });
    }
}
