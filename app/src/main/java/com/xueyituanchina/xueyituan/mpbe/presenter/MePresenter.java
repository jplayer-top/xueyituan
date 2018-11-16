package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.fragment.MeFragment;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MePresenter extends BasePresenter<MeFragment> {

    private final MeModel mModel;

    public MePresenter(MeFragment iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }

    public void requestMyInfo() {
        String login_uid = (String) SharePreUtil.getData(mIView.mActivity, "login_uid", "");
        if ("".equals(login_uid)) {
            ToastUtils.init().showInfoToast(mIView.getContext(), "请先登录");
        } else {
            mModel.requestMyInfo().subscribe(new NetCallBackObserver<MyInfoBean>() {
                @Override
                public void responseSuccess(MyInfoBean myInfoBean) {
                    mIView.responseMyInfo(myInfoBean);
                }

                @Override
                public void responseFail(MyInfoBean myInfoBean) {

                }
            });
        }
    }

    public void requestMyInfoNoLoadding() {
        String login_uid = (String) SharePreUtil.getData(mIView.mActivity, "login_uid", "");
        if ("".equals(login_uid)) {
            ToastUtils.init().showInfoToast(mIView.getContext(), "请先登录");
        } else {
            mModel.requestMyInfo().subscribe(new NetCallBackObserver<MyInfoBean>() {
                @Override
                public void responseSuccess(MyInfoBean myInfoBean) {
                    mIView.responseMyInfo(myInfoBean);
                }

                @Override
                public void responseFail(MyInfoBean myInfoBean) {

                }
            });
        }
    }
}
