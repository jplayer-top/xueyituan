package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.model.AwardModel;
import com.xueyituanchina.xueyituan.ui.activity.TaskPushActivity;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.DialogLoading;
import top.jplayer.baseprolibrary.utils.BitmapUtil;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskPushPresenter extends BasePresenter<TaskPushActivity> {

    private final AwardModel mModel;
    private DialogLoading mLoading;

    public TaskPushPresenter(TaskPushActivity iView) {
        super(iView);
        mModel = new AwardModel(XYTServer.class);
    }

    public void pushShare(String id, String url) {
        mModel.pushShare(id, url).subscribe(new NetCallBackObserver<BaseBean>() {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.pushShare(bean);
                if (mLoading != null && mLoading.isShowing()) {
                    mLoading.dismiss();
                }
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }

    public void updatePoster(File file) {
        mLoading = new DialogLoading(mIView.mActivity);
        mLoading.show();
        Observable.just(file)
                .subscribeOn(Schedulers.io())
                .map(BitmapUtil::compressImage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(image -> {
                    mModel.updateScreen(image)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new NetCallBackObserver<UpdateUrlBean>() {
                                @Override
                                public void responseSuccess(UpdateUrlBean bean) {
                                    mIView.upSuccess(bean);
                                }

                                @Override
                                public void responseFail(UpdateUrlBean bean) {
                                    if (mLoading != null && mLoading.isShowing()) {
                                        mLoading.dismiss();
                                    }
                                }
                            });
                });

    }
}
