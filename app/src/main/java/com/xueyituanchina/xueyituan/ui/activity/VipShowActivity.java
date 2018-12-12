package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.EquityBean;
import com.xueyituanchina.xueyituan.mpbe.event.AliPayOkEvent;
import com.xueyituanchina.xueyituan.mpbe.event.NoPayBackEvent;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.widgets.polygon.PolygonImageView;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class VipShowActivity extends CommonToolBarActivity {
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.ivAvatar)
    PolygonImageView mIvAvatar;
    @BindView(R.id.tvVipSol)
    TextView mTvVipSol;
    @BindView(R.id.btnAdd)
    Button mBtnAdd;
    @BindView(R.id.tvWeb01)
    TextView mTvWeb01;
    @BindView(R.id.tvWeb02)
    TextView mTvWeb02;
    @BindView(R.id.tvVipContent)
    TextView tvVipContent;
    private Unbinder mBind;
    private boolean mIsVip;
    private String mRecharge;

    @Override
    public int initAddLayout() {
        return R.layout.activity_show_vip;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        mTvWeb02.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.xueyituanchina.cn/info/vipagreement.html");
            ActivityUtils.init().start(mActivity, WebViewActivity.class, "", bundle);
        });
        mTvPhone.setText(mBundle.getString("phone"));
        mTvName.setText(mBundle.getString("name"));
        mRecharge = mBundle.getString("recharge");
        Glide.with(this).load(mBundle.getString("avator")).into(mIvAvatar);
        mIsVip = mBundle.getBoolean("isVip");
        mTvVipSol.setText(mIsVip ? "尊享会员" : "开通会员-立享特权");
        mBtnAdd.setText(mIsVip ? "邀请好友得现金" : "立即加入");
        new MeModel(XYTServer.class).equity().subscribe(new NetCallBackObserver<EquityBean>() {
            @Override
            public void responseSuccess(EquityBean equityBean) {
                tvVipContent.setText(equityBean.equity);
            }

            @Override
            public void responseFail(EquityBean equityBean) {

            }
        });
        mBtnAdd.setOnClickListener(v -> {
            if (!mIsVip) {
                Bundle bundle = new Bundle();
                bundle.putString("recharge", mRecharge);
                ActivityUtils.init().start(this, RechargeActivity.class, "会员充值", bundle);
            } else {
                ActivityUtils.init().start(this, MeInvActivity.class, "邀请好友");
            }
        });
    }

    @Subscribe
    public void onEvent(NoPayBackEvent event) {
    }

    @Subscribe
    public void onEvent(WXPayEntryActivity.WxPayEvent event) {
        isOkVip();
    }

    private void isOkVip() {
        mIsVip = true;
        mTvVipSol.setText("尊享会员");
        mBtnAdd.setText("邀请好友得现金");
    }

    @Subscribe
    public void onEvent(AliPayOkEvent event) {
        isOkVip();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        mBind.unbind();
    }
}
