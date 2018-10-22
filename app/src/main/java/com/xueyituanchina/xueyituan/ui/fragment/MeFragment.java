package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.mpbe.event.LoginSuccessEvent;
import com.xueyituanchina.xueyituan.mpbe.event.LogoutEvent;
import com.xueyituanchina.xueyituan.mpbe.event.MessageOkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.MePresenter;
import com.xueyituanchina.xueyituan.ui.activity.CollectionActivity;
import com.xueyituanchina.xueyituan.ui.activity.IssueActivity;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;
import com.xueyituanchina.xueyituan.ui.activity.SettingActivity;
import com.xueyituanchina.xueyituan.ui.activity.StoreActivity;
import com.xueyituanchina.xueyituan.ui.adapter.MeOrderAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeFragment extends SuperBaseFragment {
    @BindView(R.id.ivToolRightLeft)
    ImageView mIvToolRightLeft;
    @BindView(R.id.ivToolRight)
    ImageView mIvToolRight;
    @BindView(R.id.llCollection)
    LinearLayout mLlCollection;
    @BindView(R.id.llIssue)
    LinearLayout mLlIssue;
    @BindView(R.id.llLook)
    LinearLayout mLlLook;
    @BindView(R.id.llWork)
    LinearLayout mLlWork;
    @BindView(R.id.llShop)
    LinearLayout mLlShop;
    @BindView(R.id.llChat)
    LinearLayout mLlChat;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.ivRecommend01)
    ImageView mIvRecommend01;
    @BindView(R.id.ivRecommend02)
    ImageView mIvRecommend02;

    @BindView(R.id.tvToLogin)
    TextView mTvToLogin;

    @BindView(R.id.tvNick)
    TextView mTvNick;
    @BindView(R.id.tvPoints)
    TextView mTvPoints;
    @BindView(R.id.ivIsVip)
    ImageView mIvIsVip;
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.llShowMsgUser)
    LinearLayout mLlShowMsgUser;
    private Unbinder mUnbinder;
    private MePresenter mPresenter;
    private MeOrderAdapter mAdapter;
    private MyInfoBean bean;

    @Override
    public int initLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarMe).init();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new MeOrderAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(View.inflate(this.getContext(), R.layout.layout_empty_view, null));
        mIvToolRightLeft.setOnClickListener(v -> {
            toSettingActivity();
        });
        mLlShowMsgUser.setOnClickListener(v -> {
            toSettingActivity();
        });
        mIvAvatar.setOnClickListener(v -> {
            toSettingActivity();
        });
        mLlCollection.setOnClickListener(v -> {
            ActivityUtils.init().start(mActivity, CollectionActivity.class, "收藏");
        });
        mLlIssue.setOnClickListener(v -> {
            ActivityUtils.init().start(mActivity, IssueActivity.class, "我的评价");
        });
        mIvRecommend01.setOnClickListener(v -> {
            clickToStore("", bean.rmdList.get(0).user_id + "");
        });
        mIvRecommend02.setOnClickListener(v -> {
            clickToStore("", bean.rmdList.get(1).user_id + "");
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


    public void responseMyInfo(MyInfoBean bean) {
        this.bean = bean;
        mTvToLogin.setVisibility(View.INVISIBLE);
        mTvNick.setText(String.format(Locale.CHINA, "会员昵称：%s", StringUtils.init().fixNullStr(bean.nick)));
        mTvPoints.setText(String.format(Locale.CHINA, "会员积分：%d", bean.points));
        mIvIsVip.setSelected(bean.vip != 0);
        initRecomend(bean.avator, mIvAvatar);
        List<MyInfoBean.RmdListBean> rmdList = bean.rmdList;
        if (rmdList.size() > 0) {
            initRecomend(rmdList.get(0).img, mIvRecommend01);
            if (rmdList.size() > 1) {
                initRecomend(rmdList.get(1).img, mIvRecommend02);
            }
        }
        if (bean.orderList != null && bean.orderList.size() > 0) {
            mAdapter.setNewData(bean.orderList.subList(0, 2));
        }
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
