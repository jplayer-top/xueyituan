package com.xueyituanchina.xueyituan.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.GiftDetailPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.GiftDetailAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.DialogPointsBuy;

import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.dialog.DialogShare;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;

/**
 * Created by Obl on 2018/8/23.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class GiftFragment extends SuperBaseFragment {

    private GiftDetailPresenter mPresenter;
    private GiftDetailAdapter mAdapter;
    private View mHeader;
    private TextView mTvPoints;
    private BGABanner mBgaBanner;

    @Override
    public int initLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        rootView.findViewById(R.id.ivToolLeft).setVisibility(View.INVISIBLE);
        TextView tvToolTitle = rootView.findViewById(R.id.tvToolTitle);
        tvToolTitle.setText("学艺商城");
        mAdapter = new GiftDetailAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new GiftDetailPresenter(this);
        mPresenter.giftDetail();
        showLoading();
        mHeader = View.inflate(this.getContext(), R.layout.header_gift, null);
        mTvPoints = mHeader.findViewById(R.id.tvPoints);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);
        mAdapter.addHeaderView(mHeader);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            GiftDetailBean.GoodsListBean listBean = mAdapter.getData().get(position);
            new DialogPointsBuy(this.getContext()).show();
            return false;
        });
    }


    public void initRootRecyclerView() {
        super.initRootRecyclerView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.giftDetail();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarGift).init();
    }

    public void giftList(GiftDetailBean bean) {
        mAdapter.setNewData(bean.goodsList);
        mTvPoints.setText(String.format(Locale.CHINA, "%d", bean.balance));
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {
            GiftDetailBean.AdvertListBean bean1 = (GiftDetailBean.AdvertListBean) model;
            if (bean1 != null) {
                Glide.with(mActivity).load(bean1.advert_img)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView);
            }
        });
        mBgaBanner.setData(bean.advertList, null);
    }
}
