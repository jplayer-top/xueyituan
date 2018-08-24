package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.model.GiftDetailModel;
import com.xueyituanchina.xueyituan.ui.fragment.GiftFragment;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class GiftDetailPresenter extends BasePresenter<GiftFragment> {

    private final GiftDetailModel mModel;

    public GiftDetailPresenter(GiftFragment iView) {
        super(iView);
        mModel = new GiftDetailModel(XYTServer.class);
    }

    public void giftDetail() {
        mModel.requestGiftDetail().subscribe(new NetCallBackObserver<GiftDetailBean>(new GetImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(GiftDetailBean bean) {
                mIView.responseSuccess();
                mIView.giftList(bean);
            }

            @Override
            public void responseFail(GiftDetailBean nearbyActiveBean) {
                mIView.showError();
            }
        });
    }
}
