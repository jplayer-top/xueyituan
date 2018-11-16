package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.PointRecodeBean;
import com.xueyituanchina.xueyituan.mpbe.model.GiftDetailModel;
import com.xueyituanchina.xueyituan.ui.adapter.PointRecodeAdapter;

import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.GetImplTip;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointsRecodeActivity extends CommonToolBarActivity {

    private PointRecodeAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_point_detail;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        presenterStart();
        mAdapter = new PointRecodeAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void presenterStart() {
        new GiftDetailModel(XYTServer.class).requestPointRecode().subscribe(new NetCallBackObserver<PointRecodeBean>
                (new GetImplTip(this)) {
            @Override
            public void responseSuccess(PointRecodeBean pointBean) {
                responseOk(pointBean);
            }

            @Override
            public void responseFail(PointRecodeBean pointBean) {

            }
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        presenterStart();
    }

    private void responseOk(PointRecodeBean pointDetailBean) {
        responseSuccess();
        mAdapter.setNewData(pointDetailBean.orderList);
    }
}
