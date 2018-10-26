package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.model.ActiveModel;
import com.xueyituanchina.xueyituan.ui.fragment.ShareFragment;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.DialogLoading;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;
import top.jplayer.baseprolibrary.utils.BitmapUtil;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SharePresenter extends BasePresenter<ShareFragment> {

    private final ActiveModel mModel;
    private DialogLoading mLoading;

    public SharePresenter(ShareFragment iView) {
        super(iView);
        mModel = new ActiveModel(XYTServer.class);
    }

    public void pubActivity(RequestBody body) {
        mModel.pubActivity(body)
                .subscribe(new NetCallBackObserver<ShareBean>(new LoaddingImplTip(mIView.mActivity)) {
                    @Override
                    public void responseSuccess(ShareBean bean) {
                        if (mLoading != null && mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                        mIView.pubSuccess(bean);
                    }

                    @Override
                    public void responseFail(ShareBean bean) {

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
                    mModel.updatePoster(image)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new NetCallBackObserver<UpdateUrlBean>() {
                                @Override
                                public void responseSuccess(UpdateUrlBean bean) {
                                    mIView.upSuccess(bean);
                                }

                                @Override
                                public void responseFail(UpdateUrlBean bean) {

                                }
                            });
                });

    }
}
