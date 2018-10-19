package com.xueyituanchina.xueyituan.mpbe.presenter;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.SearchActivity;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SearchPresenter extends BasePresenter<SearchActivity> {

    private final HomeModel mModel;

    public SearchPresenter(SearchActivity iView) {
        super(iView);
        mModel = new HomeModel(XYTServer.class);
    }

    public void homeList(String pid) {
        Disposable disposable = mModel.homeFilter(pid).subscribe(homeListBean -> {
            if (homeListBean != null) {
                mIView.homeTypeList(homeListBean);
            }
        }, throwable -> {
        });
        addSubscription(disposable);
    }

    public void homeGoodsList(Map<String, String> map) {
        mModel.homeGoodsList(map).subscribe(homeGoodsList -> {
            if (homeGoodsList != null) {
                mIView.responseSuccess();
                mIView.homeGoodsList(homeGoodsList);
            }
        }, throwable -> {
        });
    }

    public void areaList() {
        mModel.area_list().subscribe(areaListBean -> {
            if (areaListBean != null) {
                mIView.responseSuccess();
                mIView.areaList(areaListBean);
            }
        }, throwable -> {
        });
    }

    public void homeGoodsList(Map<String, String> map, boolean isLoadding) {
        mModel.homeGoodsList(map).subscribe(new NetCallBackObserver<HomeGoodsList>(new LoaddingImplTip(mIView)) {
            @Override
            public void responseSuccess(HomeGoodsList homeGoodsList) {
                if (homeGoodsList.list != null && homeGoodsList.list.size() > 0) {
                    mIView.responseSuccess();
                    mIView.homeGoodsList(homeGoodsList);
                } else {
                    mIView.showEmpty();
                }
            }

            @Override
            public void responseFail(HomeGoodsList homeGoodsList) {

            }
        });
    }

}
