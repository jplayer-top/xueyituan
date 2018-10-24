package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.ActiveModel;
import com.xueyituanchina.xueyituan.ui.fragment.ShareFragment;

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

public class SharePresenter extends BasePresenter<ShareFragment> {

    private final ActiveModel mModel;

    public SharePresenter(ShareFragment iView) {
        super(iView);
        mModel = new ActiveModel(XYTServer.class);
    }


    public void pubActivity(RequestBody body) {
        mModel.pubActivity(body)
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView.mActivity)) {
                    @Override
                    public void responseSuccess(BaseBean bean) {
                        mIView.pubSuccess();
                    }

                    @Override
                    public void responseFail(BaseBean bean) {

                    }
                });
    }
}
