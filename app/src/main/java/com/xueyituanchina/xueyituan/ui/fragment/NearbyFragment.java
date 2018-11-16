package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.ActivePresenter;
import com.xueyituanchina.xueyituan.ui.adapter.NearbyAdapter;

import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class NearbyFragment extends SuperBaseFragment {

    private NearbyAdapter mAdapter;
    ActivePresenter mPresenter;

    @Override
    public int initLayout() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        rootView.findViewById(R.id.ivToolLeft).setVisibility(View.INVISIBLE);
        TextView tvToolTitle = rootView.findViewById(R.id.tvToolTitle);
        tvToolTitle.setText("附近活动");
        mAdapter = new NearbyAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new ActivePresenter(this);
        mPresenter.nearbyActiveList();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            NearbyActiveBean.ListBean listBean = mAdapter.getData().get(position);
            bundle.putString("url", "https://www.xueyituanchina.cn/info/article_active.html?id=" + listBean.id + "&uid=" + XYTApplication.uid);
            ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.nearbyActiveList();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarNearby).init();
    }

    public void nearbyList(NearbyActiveBean bean) {
        responseSuccess();
        mAdapter.setNewData(bean.list);
    }
}
