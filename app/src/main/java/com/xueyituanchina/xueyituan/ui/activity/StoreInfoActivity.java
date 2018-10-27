package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreInfoBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.adapter.StoreOrderAdapter;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.LoaddingImplTip;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.widgets.polygon.PolygonImageView;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreInfoActivity extends CommonToolBarActivity {
    @BindView(R.id.ivSpSrc)
    PolygonImageView mIvSpSrc;
    @BindView(R.id.tvShopName)
    TextView mTvShopName;
    @BindView(R.id.tvMoney)
    TextView mTvMoney;
    @BindView(R.id.tvToPay)
    TextView mTvToPay;
    @BindView(R.id.tvLoadMoreOrder)
    TextView mTvLoadMoreOrder;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tvTodayOrder)
    TextView mTvTodayOrder;
    @BindView(R.id.tvTodayPend)
    TextView mTvTodayPend;
    @BindView(R.id.tvTodayIncome)
    TextView mTvTodayIncome;
    @BindView(R.id.tvTotalIncome)
    TextView mTvTotalIncome;
    @BindView(R.id.tvTotalUser)
    TextView mTvTotalUser;
    @BindView(R.id.tvPayRate)
    TextView mTvPayRate;
    @BindView(R.id.tvPayOrder)
    TextView mTvPayOrder;
    @BindView(R.id.tvTotalOrder)
    TextView mTvTotalOrder;
    private Unbinder mBind;
    private StoreOrderAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_store_info;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mToolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mIvToolLeft.setImageResource(R.drawable.white_left_arrow);
        mBind = ButterKnife.bind(this);
        new HomeModel(XYTServer.class).storeInfo().subscribe(new NetCallBackObserver<StoreInfoBean>(new
                LoaddingImplTip(mActivity)) {
            @Override
            public void responseSuccess(StoreInfoBean storeInfoBean) {
                getInfoBean(storeInfoBean);
            }

            @Override
            public void responseFail(StoreInfoBean storeInfoBean) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StoreOrderAdapter(null);
        mAdapter.setEmptyView(View.inflate(this, R.layout.layout_empty_view_card, null));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            dialPhoneNumber(mAdapter.getData().get(position).rp_phone);
        });
        mTvToPay.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("recharge", "0.01");
            ActivityUtils.init().start(this, RechargeActivity.class, "商铺充值", bundle);
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void getInfoBean(StoreInfoBean bean) {
        if (bean.orderList != null) {
            if (bean.orderList.size() > 2) {
                mAdapter.setNewData(bean.orderList.subList(0, 2));
            } else {
                mAdapter.setNewData(bean.orderList);
            }
        }
        mTvLoadMoreOrder.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("order_list", (ArrayList<StoreInfoBean.OrderListBean>) bean.orderList);
            ActivityUtils.init().start(mActivity, StoreOrderListActivity.class, "订单列表", bundle);
        });
        mTvMoney.setText(String.format(Locale.CHINA, "余额：%s", bean.wallet));

        mTvShopName.setText(bean.spName);
        Glide.with(this).load(bean.spImg).apply(GlideUtils.init().options(R.drawable.shop_create)).into(mIvSpSrc);

        mTvPayRate.setText(String.format(Locale.CHINA, "%s%%", bean.rate));
        mTvPayOrder.setText(String.format(Locale.CHINA, "%d", bean.payOrder));
        mTvTotalOrder.setText(String.format(Locale.CHINA, "%d", bean.totalOrder));

        mTvTotalUser.setText(String.format(Locale.CHINA, "%d", bean.totalUser));
        mTvTotalIncome.setText(String.format(Locale.CHINA, "%s", bean.totalIncome));

        mTvTodayOrder.setText(String.format(Locale.CHINA, "%d", bean.todayOrder));
        mTvTodayIncome.setText(String.format(Locale.CHINA, "%s", bean.todayIncome));
        mTvTodayPend.setText(String.format(Locale.CHINA, "%s", bean.todayPend));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
