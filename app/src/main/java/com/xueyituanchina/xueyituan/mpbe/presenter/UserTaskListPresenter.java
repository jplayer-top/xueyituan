package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.UserTaskListBean;
import com.xueyituanchina.xueyituan.mpbe.model.AwardModel;
import com.xueyituanchina.xueyituan.ui.activity.UserTaskListActivity;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserTaskListPresenter extends BasePresenter<UserTaskListActivity> {

    private final AwardModel mModel;

    public UserTaskListPresenter(UserTaskListActivity iView) {
        super(iView);
        mModel = new AwardModel(XYTServer.class);
    }

    public void taskList() {
        mModel.taskList().subscribe(new NetCallBackObserver<UserTaskListBean>() {
            @Override
            public void responseSuccess(UserTaskListBean bean) {
                mIView.taskList(bean);
            }

            @Override
            public void responseFail(UserTaskListBean bean) {

            }
        });
    }

}
