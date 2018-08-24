package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;
import com.xueyituanchina.xueyituan.mpbe.model.ActiveModel;
import com.xueyituanchina.xueyituan.ui.fragment.NearbyFragment;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ActivePresenter extends BasePresenter<NearbyFragment> {

    private final ActiveModel mModel;

    public ActivePresenter(NearbyFragment iView) {
        super(iView);
        mModel = new ActiveModel(XYTServer.class);
    }

    public void nearbyActiveList() {
        mModel.nearbyList().subscribe(new NetCallBackObserver<NearbyActiveBean>(new GetImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(NearbyActiveBean nearbyActiveBean) {
                if (nearbyActiveBean != null && nearbyActiveBean.list != null && nearbyActiveBean.list.size() > 0) {
                    mIView.nearbyList(nearbyActiveBean);
                } else {
                    mIView.showEmpty();
                }
            }

            @Override
            public void responseFail(NearbyActiveBean nearbyActiveBean) {
                mIView.showError();
            }
        });
    }
}
