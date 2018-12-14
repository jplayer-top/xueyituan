package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.ProPertyBean;
import com.xueyituanchina.xueyituan.mpbe.event.ApplyEvent;
import com.xueyituanchina.xueyituan.mpbe.event.BankInfoOkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.PropertyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.polygon.PolygonImageView;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ProPertyActivity extends SuperBaseActivity {

    @BindView(R.id.ivToolLeft)
    ImageView mIvToolLeft;
    @BindView(R.id.ivAvatar)
    PolygonImageView mIvAvatar;
    @BindView(R.id.tvUserMoney)
    TextView mTvUserMoney;
    @BindView(R.id.tvTodayMoney)
    TextView mTvTodayMoney;
    @BindView(R.id.tvAllMoney)
    TextView mTvAllMoney;
    @BindView(R.id.llPay)
    LinearLayout mLlPay;
    @BindView(R.id.llTiX)
    LinearLayout mLlTiX;
    @BindView(R.id.llOrderList)
    TextView mLlOrderList;
    @BindView(R.id.llUserInput)
    TextView mLlUserInput;
    private PropertyPresenter mPresenter;
    private Unbinder mBind;


    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        mBind = ButterKnife.bind(this);
        mPresenter = new PropertyPresenter(this);
        mPresenter.property();
        EventBus.getDefault().register(this);
        mIvToolLeft.setOnClickListener(v -> finish());
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbarProp).init();
    }

    @Override
    protected int initRootLayout() {
        return R.layout.activity_property;
    }

    public void property(ProPertyBean bean) {
        mTvUserMoney.setText(String.format(Locale.CHINA, "￥%s", bean.wallet));
        mTvTodayMoney.setText(bean.todayIncome);
        mTvAllMoney.setText(bean.income);
        Glide.with(this).load(SharePreUtil.getData(this, "login_avatar", "")).into(mIvAvatar);
        mLlTiX.setOnClickListener(v -> {
            if (bean.bankCard == null || "".equals(bean.bankCard)) {
                ToastUtils.init().showInfoToast(this, "请先进行账户认证");
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("bank", bean.bankCard);
                bundle.putString("cantx", bean.wallet);
                ActivityUtils.init().start(this, TXActivity.class, "我要提现", bundle);
            }
        });
        mLlUserInput.setOnClickListener(v -> {
            if (bean.bankCard == null || "".equals(bean.bankCard)) {
                ActivityUtils.init().start(this, UserSignActivity.class, "账户认证");
            } else {
                ToastUtils.init().showSuccessToast(this, "已设置过账户信息，如需修改，请联系客服热线");
            }
        });
        mLlPay.setOnClickListener(v -> {
            ActivityUtils.init().start(this, WalletActivity.class, "充值中心");
        });
        mLlOrderList.setOnClickListener(v -> {
            ActivityUtils.init().start(this, UserOrderListActivity.class, "账单明细");
        });
    }

    @Subscribe
    public void onEvnet(ApplyEvent event) {
        mPresenter.property();
    }

    @Subscribe
    public void onEvnet(BankInfoOkEvent event) {
        mPresenter.property();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        EventBus.getDefault().unregister(this);
    }
}
