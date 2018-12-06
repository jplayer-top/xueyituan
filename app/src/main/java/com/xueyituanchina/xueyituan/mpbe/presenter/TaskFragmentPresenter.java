package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskDestoryBean;
import com.xueyituanchina.xueyituan.mpbe.bean.TaskGoodsListBean;
import com.xueyituanchina.xueyituan.mpbe.model.AwardModel;
import com.xueyituanchina.xueyituan.ui.fragment.TaskSubmitFragment;

import java.util.Map;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.DialogLoading;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskFragmentPresenter extends BasePresenter<TaskSubmitFragment> {

    private final AwardModel mModel;
    private DialogLoading mLoading;

    public TaskFragmentPresenter(TaskSubmitFragment iView) {
        super(iView);
        mModel = new AwardModel(XYTServer.class);
    }

    public void taskGoodsList() {
        mModel.taskGoodsList().subscribe(new NetCallBackObserver<TaskGoodsListBean>() {
            @Override
            public void responseSuccess(TaskGoodsListBean bean) {
                mIView.goodsList(bean);
            }

            @Override
            public void responseFail(TaskGoodsListBean bean) {

            }
        });
    }

    public void tasksDestory() {
        mModel.tasksDestory().subscribe(new NetCallBackObserver<PushTaskDestoryBean>() {
            @Override
            public void responseSuccess(PushTaskDestoryBean bean) {
                mIView.tesksDestory(bean);
            }

            @Override
            public void responseFail(PushTaskDestoryBean bean) {

            }
        });
    }

    public void taskPush(Map<String, String> map) {
        mModel.taskPub(map).subscribe(new NetCallBackObserver<PushTaskBean>(new PostImplTip(mIView.mActivity)) {
            @Override
            public void responseSuccess(PushTaskBean bean) {
                mIView.pushTaskOk(bean);
            }

            @Override
            public void responseFail(PushTaskBean bean) {

            }
        });
    }
}
