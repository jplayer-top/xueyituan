package com.xueyituanchina.xueyituan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.widgets.ShSwitchView;

/**
 * Created by Obl on 2018/8/24.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShareFragment extends SuperBaseFragment {
    @BindView(R.id.tvToolTitle)
    TextView mTvToolTitle;
    @BindView(R.id.tvToolRightLeft)
    TextView mTvToolRightLeft;
    @BindView(R.id.tvToolRight)
    TextView mTvToolRight;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ivBigSrc)
    ImageView mIvBigSrc;
    @BindView(R.id.switchView)
    ShSwitchView mSwitchView;
    private Unbinder mUnbinder;

    @Override
    public int initLayout() {
        return R.layout.fragment_share;
    }

    @Override
    protected void initData(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
        initImmersionBar();
        mSwitchView.setOn(true);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarShare).init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
