package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.event.ApplyEvent;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.activity.TXActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;

/**
 * Created by Obl on 2018/12/10.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ApplySignPresenter extends BasePresenter<TXActivity> {
    private final MeModel mModel;

    public ApplySignPresenter(TXActivity iView) {
        super(iView);
        mModel = new MeModel(XYTServer.class);
    }


    public void apply(String money) {
        mModel.apply(money).subscribe(new NetCallBackObserver<BaseBean>(new
                PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                EventBus.getDefault().post(new ApplyEvent());
                Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(a -> mIView.finish());
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }

    public void applySign(String pw) {
        mModel.applySign(pw).subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                mIView.signOk();
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }
}
