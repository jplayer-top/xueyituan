package com.xueyituanchina.xueyituan.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.LocalBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.GiftDetailPresenter;
import com.xueyituanchina.xueyituan.ui.activity.PointsDetailActivity;
import com.xueyituanchina.xueyituan.ui.activity.PointsRecodeActivity;
import com.xueyituanchina.xueyituan.ui.adapter.GiftDetailAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.LocalDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.dialog.DialogShare;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;

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
    private GiftDetailBean.GoodsListBean mListBean;

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
        EventBus.getDefault().register(this);
        mAdapter = new GiftDetailAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new GiftDetailPresenter(this);
        mPresenter.giftDetail();
        mHeader = View.inflate(this.getContext(), R.layout.header_gift, null);
        mTvPoints = mHeader.findViewById(R.id.tvPoints);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);
        mHeader.findViewById(R.id.tvPointsDetail).setOnClickListener(v -> {
            ActivityUtils.init().start(mActivity, PointsDetailActivity.class, "积分详情");
        });
        mHeader.findViewById(R.id.tvPointsRecode).setOnClickListener(v -> {
            ActivityUtils.init().start(mActivity, PointsRecodeActivity.class, "兑换记录");

        });
        mAdapter.addHeaderView(mHeader);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            mListBean = mAdapter.getData().get(position);
            new LocalDialog(mActivity)
                    .setTvTitle(mListBean.title)
                    .setPrice(String.format(Locale.CHINA, "%d", mListBean.points))
                    .setIvShopPic(mListBean.thumb_img)
                    .setGoodsId(mListBean.points_goods_id)
                    .show(R.id.btnSure);

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

    @Subscribe
    public void onEvent(LocalBean localBean) {
        LogUtil.e(localBean);
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("amount", "1");
        map.put("rp_name", localBean.name);
        map.put("rp_phone", localBean.phone);
        map.put("rp_addr", localBean.local);
        map.put("points_goods_id", localBean.goods_id + "");
        mPresenter.giftCreate(map);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
