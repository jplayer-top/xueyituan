package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderInfoBean;
import com.xueyituanchina.xueyituan.mpbe.event.DialogIssueEvent;
import com.xueyituanchina.xueyituan.mpbe.event.PayOKStateEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.OrderInfoPresenter;
import com.xueyituanchina.xueyituan.ui.dialog.OrderIssueDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderInfoActivity extends CommonToolBarActivity {
    @BindView(R.id.ivSrc)
    ImageView mIvSrc;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvStatus)
    TextView mTvStatus;
    @BindView(R.id.tvOrder)
    TextView mTvOrder;
    @BindView(R.id.tvOrderTime)
    TextView mTvOrderTime;
    @BindView(R.id.tvOrderPrice)
    TextView mTvOrderPrice;
    @BindView(R.id.tvOrderPhone)
    TextView mTvOrderPhone;
    @BindView(R.id.btnPay)
    Button mBtnPay;
    private Unbinder mBind;
    private OrderInfoPresenter mPresenter;
    private String mOrderId;

    @Override
    public int initAddLayout() {
        return R.layout.activity_order_info;

    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new OrderInfoPresenter(this);
        mOrderId = mBundle.getString("orderId");
        mPresenter.getOrderInfo(mOrderId);
    }


    public void orderInfo(OrderInfoBean orderInfoBean) {
        OrderInfoBean.RecordBean recordBean = orderInfoBean.record;
        Glide.with(this).load(recordBean.thumbImg).apply(GlideUtils.init().options(R.drawable.placeholder)).into(mIvSrc);
        mTvTitle.setText(recordBean.order_title);
        mTvOrder.setText(recordBean.order_id);
        mTvOrderPhone.setText(recordBean.rp_phone);
        mTvOrderPrice.setText(recordBean.totalPriceStr);
        mTvOrderTime.setText(recordBean.create_time);
        boolean isPay = recordBean.pay_status == 0;
        mTvStatus.setText(String.format(Locale.CHINA, "订单状态：%s", isPay ? "未支付" : "已支付"));
        mBtnPay.setText(isPay ? "订单支付" : "订单评价");
        mBtnPay.setOnClickListener(v -> {
            if (isPay) {
                createOrder(recordBean);
            } else {
                new OrderIssueDialog(this).show();
            }
        });
    }

    @Subscribe
    public void onEvent(PayOKStateEvent event) {
        mPresenter.getOrderInfo(mOrderId);
    }

    @Subscribe
    public void onEvent(DialogIssueEvent event) {
        mPresenter.submitIssue(mOrderId, event.desc, event.rating, event.checked);
    }

    public void createOrder(OrderInfoBean.RecordBean orderBean) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderBean.order_id);
        bundle.putString("totalPrice", orderBean.totalPriceStr);
        bundle.putString("title", orderBean.order_title);
        bundle.putString("price", orderBean.priceStr);
        ActivityUtils.init().start(this, OrderPrePayActivity.class, "订单信息", bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void orderIssue() {
        mBtnPay.setText("已评价");
        mBtnPay.setEnabled(false);
    }
}
