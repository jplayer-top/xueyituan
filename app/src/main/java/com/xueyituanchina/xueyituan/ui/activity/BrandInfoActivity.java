package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.BrandBBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.BrandPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.BrandInfoAdapter;

import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BrandInfoActivity extends CommonToolBarActivity {

    private BrandPresenter mPresenter;
    private BrandInfoAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_teach_detail;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mPresenter = new BrandPresenter(this);
        showLoading();
        mPresenter.brandInfo(mBundle.getString("id"));
        mAdapter = new BrandInfoAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.brandInfo(mBundle.getString("id"));
    }

    public void brandInfo(BrandBBean brandBBean) {
        mAdapter.setNewData(brandBBean.brand.envirmtList);
    }
}
