package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.ui.adapter.AwardAdapter;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/11/30.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardFragment extends SuperBaseFragment {

    private AwardAdapter mAdapter;
    private View mHeader;
    private BGABanner mBgaBanner;

    @Override
    public int initLayout() {
        return R.layout.fragment_award;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        mAdapter = new AwardAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        initHeader();
    }

    private void initHeader() {
        mHeader = View.inflate(getContext(), R.layout.layout_header_award, null);
        mAdapter.addHeaderView(mHeader);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);

    }

    private void initBanner(AwardBean bean) {
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {
            HomeTopBean.BannerBean bannerBean = (HomeTopBean.BannerBean) model;
            if (bannerBean != null) {
                Glide.with(mActivity).load(bannerBean.banner_img)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView);
                itemView.setOnClickListener(v -> {
                    startWeb(bannerBean.banner_id);
                });
            }

        });
        mBgaBanner.setData(bean.bannerList, null);
    }

    private void startWeb(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarAward).init();
    }

}
