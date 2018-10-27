package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreInfoBean;
import com.xueyituanchina.xueyituan.ui.adapter.StoreOrderAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreOrderListActivity extends CommonToolBarActivity {

    ArrayList<StoreInfoBean.OrderListBean> mOrder_list;
    StoreOrderAdapter mOrderAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mOrder_list = mBundle.getParcelableArrayList("order_list");
        mOrderAdapter = new StoreOrderAdapter(mOrder_list);
        mRecyclerView.setAdapter(mOrderAdapter);
        mOrderAdapter.setOnItemClickListener((adapter, view, position) -> {
            dialPhoneNumber(mOrderAdapter.getData().get(position).rp_phone);
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
            StoreOrderListActivity.this.responseSuccess();
        });
    }
}
