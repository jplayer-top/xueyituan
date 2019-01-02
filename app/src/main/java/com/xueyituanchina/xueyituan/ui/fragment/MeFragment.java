package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.mpbe.event.AliPayOkEvent;
import com.xueyituanchina.xueyituan.mpbe.event.LoginSuccessEvent;
import com.xueyituanchina.xueyituan.mpbe.event.LogoutEvent;
import com.xueyituanchina.xueyituan.mpbe.event.MessageOkEvent;
import com.xueyituanchina.xueyituan.mpbe.event.NoPayBackEvent;
import com.xueyituanchina.xueyituan.mpbe.event.PayVipROkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.MePresenter;
import com.xueyituanchina.xueyituan.ui.activity.CollectionActivity;
import com.xueyituanchina.xueyituan.ui.activity.IssueActivity;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;
import com.xueyituanchina.xueyituan.ui.activity.MeInvActivity;
import com.xueyituanchina.xueyituan.ui.activity.OrderListActivity;
import com.xueyituanchina.xueyituan.ui.activity.ProPertyActivity;
import com.xueyituanchina.xueyituan.ui.activity.RechargeActivity;
import com.xueyituanchina.xueyituan.ui.activity.SettingActivity;
import com.xueyituanchina.xueyituan.ui.activity.ShopCreateActivity;
import com.xueyituanchina.xueyituan.ui.activity.StoreActivity;
import com.xueyituanchina.xueyituan.ui.activity.StoreInfoActivity;
import com.xueyituanchina.xueyituan.ui.activity.UserTaskListActivity;
import com.xueyituanchina.xueyituan.ui.activity.VipShowActivity;
import com.xueyituanchina.xueyituan.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.dialog.DialogLogout;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static com.xueyituanchina.xueyituan.XYTApplication.assert2Login;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeFragment extends SuperBaseFragment {

    @BindView(R.id.llCollection)
    LinearLayout mLlCollection;
    @BindView(R.id.llIssue)
    LinearLayout mLlIssue;
    @BindView(R.id.llInvit)
    LinearLayout llInvit;

    @BindView(R.id.llWork)
    TextView mLlWork;
    @BindView(R.id.llShop)
    TextView mLlShop;
    @BindView(R.id.llChat)
    TextView mLlChat;


    @BindView(R.id.tvToLogin)
    TextView mTvToLogin;

    @BindView(R.id.tvNick)
    TextView mTvNick;
    @BindView(R.id.llSetting)
    TextView tvSetting;
    @BindView(R.id.tvTipVip)
    TextView tvTipVip;
    @BindView(R.id.tvProperty)
    TextView tvProperty;
    @BindView(R.id.ivIsVip)
    ImageView mIvIsVip;
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.ivSubmitTask)
    ImageView ivSubmitTask;
    @BindView(R.id.ivVipSet)
    ImageView ivVipSet;
    @BindView(R.id.tvLoadMoreOrder)
    LinearLayout tvLoadMoreOrder;
    @BindView(R.id.llShowMsgUser)
    LinearLayout mLlShowMsgUser;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private Unbinder mUnbinder;
    private MePresenter mPresenter;
    private MyInfoBean bean;

    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }


    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        EventBus.getDefault().register(this);
        mUnbinder = ButterKnife.bind(this, rootView);
        mTvToLogin.setVisibility(View.VISIBLE);
        mTvToLogin.setOnClickListener(v -> ActivityUtils.init().start(this.getActivity(), LoginActivity.class));
        mPresenter = new MePresenter(this);
        mPresenter.requestMyInfo();
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.requestMyInfoNoLoadding();
        });

//        new DialogVip(mActivity).show(R.id.btnToVip, view -> {
//            if (bean != null) {
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("isVip", XYTApplication.isVip);
//                bundle.putString("avator", bean.avator);
//                bundle.putString("name", bean.nick);
//                bundle.putString("recharge", bean.recharge);
//                String login_phone = (String) SharePreUtil.getData(getContext(), "login_phone", "");
//                bundle.putString("phone", login_phone);
//                ActivityUtils.init().start(this.mActivity, VipShowActivity.class, "会员中心", bundle);
//            }
//        });
        tvSetting.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity)) {
                toSettingActivity();
            }
        });
        tvProperty.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity))
                ActivityUtils.init().start(mActivity, ProPertyActivity.class, "我的资产");
        });
        ivSubmitTask.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity))
                ActivityUtils.init().start(mActivity, UserTaskListActivity.class, "提交任务");
        });
        mLlCollection.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity)) {
                ActivityUtils.init().start(mActivity, CollectionActivity.class, "收藏");
            }
        });
        llInvit.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity))
                ActivityUtils.init().start(mActivity, MeInvActivity.class, "邀请好友");
        });
        mLlShop.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity)) {
                if (3 == bean.merchant) {
                    ActivityUtils.init().start(mActivity, StoreInfoActivity.class, "");
                } else {
                    ToastUtils.init().showInfoToast(mActivity, "请先进行商家入驻");
                }
            }
        });
        mLlIssue.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity))
                ActivityUtils.init().start(mActivity, IssueActivity.class, "我的评价");
        });

        mLlChat.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity)) {
                if (assert2Login(mActivity)) {
                    RongIM.getInstance().startConversation(mActivity, Conversation.ConversationType.PRIVATE,
                            "u_" + XYTApplication.cuid, "客服");
                }
            }
        });
        tvLoadMoreOrder.setOnClickListener(v -> {
            if (XYTApplication.assert2Login(mActivity)) {
                Bundle bundle = new Bundle();
                if (bean != null && bean.orderList != null) {
                    List<MyInfoBean.OrderListBean> orderList = bean.orderList;
                    bundle.putParcelableArrayList("order_list", (ArrayList<MyInfoBean.OrderListBean>) orderList);
                    ActivityUtils.init().start(mActivity, OrderListActivity.class, "订单列表", bundle);
                }
            }
        });
    }

    private void clickToStore(String name, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        ActivityUtils.init().start(this.mActivity, StoreActivity.class, name, bundle);
    }

    private void toSettingActivity() {
        if (bean == null) {
            ToastUtils.init().showInfoToast(this.getContext(), "请先登录");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("nick", bean.nick);
        bundle.putString("points", bean.points + "");
        bundle.putString("avatar", bean.avator);
        ActivityUtils.init().start(this.getActivity(), SettingActivity.class, "设置", bundle);
    }

    @Subscribe
    public void onEvent(LoginSuccessEvent event) {
        mPresenter.requestMyInfo();
    }

    @Subscribe
    public void onEvent(LogoutEvent event) {
        SharePreUtil.saveData(mActivity, "login_phone", "");
        SharePreUtil.saveData(mActivity, "login_password", "");
        SharePreUtil.saveData(mActivity, "login_uid", "");
        SharePreUtil.saveData(mActivity, "login_token", "");
        mActivity.finish();
    }

    @Subscribe
    public void onEvent(MessageOkEvent event) {
        mPresenter.requestMyInfo();
    }

    @Subscribe
    public void onEvent(NoPayBackEvent event) {
        mPresenter.requestMyInfo();
    }

    @Subscribe
    public void onEvent(WXPayEntryActivity.WxPayEvent event) {
        mPresenter.requestMyInfo();
    }

    @Subscribe
    public void onEvent(AliPayOkEvent event) {
        mPresenter.requestMyInfo();
    }

    @Subscribe
    public void onEvent(PayVipROkEvent event) {
        mPresenter.requestMyInfo();
    }

    public void responseMyInfo(MyInfoBean bean) {
        this.bean = bean;
        XYTApplication.cuid = bean.customerId;
        if (bean.avator != null) {
            SharePreUtil.saveData(getContext(), "login_avatar", bean.avator);
        }
        smartRefreshLayout.finishRefresh();
        mTvToLogin.setVisibility(View.INVISIBLE);
        mLlWork.setOnClickListener(v -> {
            if (0 == (bean.merchant)) {
                ActivityUtils.init().start(mActivity, ShopCreateActivity.class, "商家入驻");
            } else if (1 == (bean.merchant)) {
                DialogLogout dialogLogout = new DialogLogout(mActivity);
                dialogLogout
                        .setTitle("温馨提示")
                        .setSubTitle("当前已提交入驻信息\n请提交一百元审核金")
                        .show(R.id.btnSure, view -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("recharge", "500.00");
                            ActivityUtils.init().start(this.getActivity(), RechargeActivity.class, "商铺审核金", bundle);
                            dialogLogout.dismiss();
                        });
            } else if (2 == (bean.merchant)) {
                DialogLogout dialogLogout = new DialogLogout(mActivity);
                dialogLogout
                        .setTitle("温馨提示")
                        .setSubTitle("当前已认缴一百元审核金\n请耐心等待审核")
                        .show(R.id.btnSure, view -> {
                            dialogLogout.dismiss();
                        });
            } else if (3 == (bean.merchant)) {
                ToastUtils.init().showSuccessToast(mActivity, "当前已是商家，请直接查看商家端");
            } else if (4 == (bean.merchant)) {
                ActivityUtils.init().start(mActivity, ShopCreateActivity.class, "商家入驻");
            }
        });
        mTvNick.setText(String.format(Locale.CHINA, "%s", StringUtils.init().fixNullStr(bean.nick)));
        XYTApplication.login_name = StringUtils.init().fixNullStr(bean.nick);
        boolean isVip = bean.vip != 0;
        XYTApplication.isVip = isVip;
        mIvIsVip.setSelected(isVip);
        tvTipVip.setText(isVip ? "查看我的会员权益" : "开通即可免费听课");
        ivVipSet.setImageResource(isVip ? R.drawable.me_vip_new : R.drawable.me_vip_old);
        mIvIsVip.setOnClickListener(v -> {
            if (bean.vip == 0) {
                Bundle bundle = new Bundle();
                bundle.putString("recharge", this.bean.recharge);
                ActivityUtils.init().start(this.getActivity(), RechargeActivity.class, "会员充值", bundle);
            } else {
                ToastUtils.init().showInfoToast(getContext(), "当前已是Vip会员");
            }
        });
        tvTipVip.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isVip", XYTApplication.isVip);
            bundle.putString("avator", bean.avator);
            bundle.putString("name", bean.nick);
            bundle.putString("recharge", bean.recharge);
            String login_phone = (String) SharePreUtil.getData(getContext(), "login_phone", "");
            bundle.putString("phone", login_phone);
            ActivityUtils.init().start(this.mActivity, VipShowActivity.class, "会员中心", bundle);
        });
        ivVipSet.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isVip", XYTApplication.isVip);
            bundle.putString("avator", bean.avator);
            bundle.putString("name", bean.nick);
            bundle.putString("recharge", bean.recharge);
            String login_phone = (String) SharePreUtil.getData(getContext(), "login_phone", "");
            bundle.putString("phone", login_phone);
            ActivityUtils.init().start(this.mActivity, VipShowActivity.class, "会员中心", bundle);
        });
        initRecomend(bean.avator, mIvAvatar);
        XYTApplication.merchant = bean.merchant;
    }

    private void initRecomend(String img, ImageView iv) {
        Glide.with(this).load(img)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                .into(iv);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
