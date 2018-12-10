package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.UserSignActivity;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/12/10.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserSignPresenter extends BasePresenter<UserSignActivity> {
    private final MeModel mModel;

    public UserSignPresenter(UserSignActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }


    public void bankinfo(Map<String, String> map) {
        mModel.bankinfo(map).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.bankinfo(bean);
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }
}
