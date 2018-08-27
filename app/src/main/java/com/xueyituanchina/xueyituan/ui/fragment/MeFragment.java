package com.xueyituanchina.xueyituan.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

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
    @BindView(R.id.ivAvatar)
    ImageView mIvAvatar;
    @BindView(R.id.llShowMsgUser)
    LinearLayout mLlShowMsgUser;
    @BindView(R.id.ivIsVip)
    ImageView mIvIsVip;
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
    @BindView(R.id.llOrder)
    TextView mLlOrder;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.llRecommend)
    TextView mLlRecommend;
    @BindView(R.id.ivRecommend01)
    ImageView mIvRecommend01;
    @BindView(R.id.ivRecommend02)
    ImageView mIvRecommend02;
    @BindView(R.id.tvToLogin)
    TextView mTvToLogin;
    private Unbinder mUnbinder;

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
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        mUnbinder = ButterKnife.bind(this, rootView);
        mTvToLogin.setVisibility(View.VISIBLE);
        mTvToLogin.setOnClickListener(v -> ActivityUtils.init().start(this.getActivity(), LoginActivity.class));
    }

}
