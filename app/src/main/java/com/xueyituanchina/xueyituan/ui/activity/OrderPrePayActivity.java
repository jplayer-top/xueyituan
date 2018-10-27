package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.PayOKStateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/10/20.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderPrePayActivity extends CommonToolBarActivity {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;
    @BindView(R.id.tvRemove)
    TextView mTvRemove;
    @BindView(R.id.tvEditNum)
    TextView mTvEditNum;
    @BindView(R.id.tvAdd)
    TextView mTvAdd;
    @BindView(R.id.tvTotal)
    TextView mTvTotal;
    @BindView(R.id.tvPayTotal)
    TextView mTvPayTotal;
    @BindView(R.id.tvPayPhone)
    TextView mTvPayPhone;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.btnPay)
    Button mBtnPay;
    private Unbinder mUnbinder;
    String mPrice;
    String mOrderId;
    String mTotalPrice;
    String mTitle;

    @Override
    public int initAddLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this);
        mPrice = mBundle.getString("price");
        mOrderId = mBundle.getString("orderId");
        mTotalPrice = mBundle.getString("totalPrice");
        mTitle = mBundle.getString("title");
        EventBus.getDefault().register(this);
        mBtnPay.setText(String.format(Locale.CHINA, "去支付%s元", mTotalPrice));
        String phone = (String) SharePreUtil.getData(this, "login_phone", "暂无");
        mTvPayPhone.setText(phone);
        mTvPrice.setText(String.format(Locale.CHINA, "￥%s", mPrice));
        mTvPayTotal.setText(String.format(Locale.CHINA, "￥%s", mTotalPrice));
        mTvTotal.setText(String.format(Locale.CHINA, "￥%s", mTotalPrice));
        mTvTitle.setText(mTitle);

        mTvAdd.setOnClickListener(v -> {
            String numStr = mTvEditNum.getText().toString();
            Integer num = Integer.valueOf(numStr);
            if (num >= 999) {
                ToastUtils.init().showInfoToast(this, "客官，分批买吧");
                return;
            }
            numChange(num, 1);
        });
        mTvRemove.setOnClickListener(v -> {
            String numStr = mTvEditNum.getText().toString();
            Integer num = Integer.valueOf(numStr);
            if (num <= 1) {
                ToastUtils.init().showInfoToast(this, "客官，不能再少了");
                return;
            }
            numChange(num, -1);
        });
        mBtnPay.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("orderId", mOrderId);
            bundle.putString("totalPrice", mTotalPrice);
            ActivityUtils.init().start(this, PayActivity.class, "订单支付", bundle);
        });
    }

    @Subscribe
    public void onEvent(PayOKStateEvent event) {
        finish();
    }

    private void numChange(int num, int value) {
        num += value;
        mTvEditNum.setText(String.valueOf(num));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
