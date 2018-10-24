package com.xueyituanchina.xueyituan.mpbe.presenter;

import android.content.Context;
import android.os.SystemClock;

import com.google.gson.Gson;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaAllBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.ShopCreateActivity;

import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopCreatePresenter extends BasePresenter<ShopCreateActivity> {

    private final HomeModel mModel;

    public ShopCreatePresenter(ShopCreateActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }


    public void createShop(RequestBody body) {
        mModel.createShop(body)
                .subscribe(new NetCallBackObserver<BaseBean>(new PostImplTip(mIView.mActivity)) {
                    @Override
                    public void responseSuccess(BaseBean bean) {
                        mIView.createSuccess();
                    }

                    @Override
                    public void responseFail(BaseBean bean) {

                    }
                });
    }

    public void area() {
        LogUtil.str("net_s" + SystemClock.currentThreadTimeMillis());
        mModel.area().subscribe(areaListBean -> {
            if (areaListBean != null) {
                LogUtil.str("net_e" + SystemClock.currentThreadTimeMillis());
            }
        }, LogUtil::str);
    }

    public void areaLocal() {
        LogUtil.str("start" + SystemClock.currentThreadTimeMillis());
        Observable.just("area.json").subscribeOn(Schedulers.io())
                .map(s -> {
                    String txt = readAssetsTxt(mIView, s);
                    return new Gson().fromJson(txt, AreaAllBean.class);
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(areaAllBean -> mIView.setLocalBean
                (areaAllBean),throwable -> {});

    }

    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public String readAssetsTxt(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "utf-8");
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "";
    }

}
