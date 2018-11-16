package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.WeekPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.NearbyAdapter;

import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/10/25.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class WeekListActivity extends CommonToolBarActivity {
    private NearbyAdapter mAdapter;
    WeekPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_week_list;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mAdapter = new NearbyAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new WeekPresenter(this);
        mPresenter.weekList();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            NearbyActiveBean.ListBean listBean = mAdapter.getData().get(position);
            bundle.putString("url", "https://www.xueyituanchina.cn/info/week.html?id=" + listBean.id);
            ActivityUtils.init().start(this, WebViewActivity.class, "", bundle);
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.weekList();
    }

    public void weekList(NearbyActiveBean bean) {
        responseSuccess();
        mAdapter.setNewData(bean.list);
    }
}
