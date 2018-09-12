package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.StoreActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

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

    public void collType(String favType, String favId) {
        mModel.collectionType(favType, favId).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView
                .mActivity)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.collection(R.drawable.collection_cancel, "取消收藏");
            }

            @Override
            public void responseFail(BaseBean bean) {

            }

        });
    }

    public void cancelCollType(String favType, String favId) {
        mModel.cancelCollectionType(favType, favId).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView
                .mActivity)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.collection(R.drawable.collection, "收藏");
            }

            @Override
            public void responseFail(BaseBean bean) {

            }

        });
    }
}
