package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.ui.adapter.MeOrderAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderListActivity extends CommonToolBarActivity {

    ArrayList<MyInfoBean.OrderListBean> mOrder_list;
    MeOrderAdapter mOrderAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mOrder_list = mBundle.getParcelableArrayList("order_list");
        mOrderAdapter = new MeOrderAdapter(mOrder_list);
        mRecyclerView.setAdapter(mOrderAdapter);
        mOrderAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("orderId", mOrderAdapter.getData().get(position).order_id);
            ActivityUtils.init().start(mActivity, OrderInfoActivity.class, "订单详情", bundle);
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
            OrderListActivity.this.responseSuccess();
        });
    }
}
