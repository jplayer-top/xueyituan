package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareImgBean;
import com.xueyituanchina.xueyituan.mpbe.model.AwardModel;
import com.xueyituanchina.xueyituan.ui.activity.AwardActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardActivityPresenter extends BasePresenter<AwardActivity> {

    private final AwardModel mModel;

    public AwardActivityPresenter(AwardActivity iView) {
        super(iView);
        mModel = new AwardModel(XYTServer.class);
    }

    public void awardList() {
        mModel.awardList().subscribe(new NetCallBackObserver<AwardBean>() {
            @Override
            public void responseSuccess(AwardBean bean) {
                mIView.awardList(bean);
            }

            @Override
            public void responseFail(AwardBean bean) {

            }
        });
    }

    public void shareImg(String taskId, int position) {
        mModel.shareImg(taskId).subscribe(new NetCallBackObserver<ShareImgBean>(new GetImplTip(mIView)) {
            @Override
            public void responseSuccess(ShareImgBean bean) {
                mIView.shareImg(bean,position);
            }

            @Override
            public void responseFail(ShareImgBean bean) {

            }
        });
    }

    public void shareCan(String taskId, int position) {
        mModel.shareCan(taskId).subscribe(new NetCallBackObserver<BaseBean>(new GetImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.shareCan(taskId,position);
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }

    public void shareOk(String taskId) {
        mModel.shareOk(taskId).subscribe(new NetCallBackObserver<BaseBean>() {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.shareOk(bean);
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }
}
