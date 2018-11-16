package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.PointDetailBean;
import com.xueyituanchina.xueyituan.mpbe.model.GiftDetailModel;
import com.xueyituanchina.xueyituan.ui.adapter.PointDetailAdapter;

import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointsDetailActivity extends CommonToolBarActivity {

    private PointDetailAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_point_detail;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        presenterStart();
        mAdapter = new PointDetailAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void presenterStart() {
        new GiftDetailModel(XYTServer.class).requestPointDetail().subscribe(new NetCallBackObserver<PointDetailBean>
                (new GetImplTip(this)) {
            @Override
            public void responseSuccess(PointDetailBean pointDetailBean) {
                responseOk(pointDetailBean);
            }

            @Override
            public void responseFail(PointDetailBean pointDetailBean) {

            }
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        presenterStart();
    }

    private void responseOk(PointDetailBean pointDetailBean) {
        responseSuccess();
        mAdapter.setNewData(pointDetailBean.orderList);
    }
}
