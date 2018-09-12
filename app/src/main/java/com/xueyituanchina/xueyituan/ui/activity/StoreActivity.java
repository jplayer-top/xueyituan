package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.StorePresenter;
import com.xueyituanchina.xueyituan.ui.adapter.FooterAdapter;
import com.xueyituanchina.xueyituan.ui.adapter.StoreAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.DialogPayBuyTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/9/5.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreActivity extends CommonToolBarActivity {

    private StoreAdapter mAdapter;
    private StorePresenter mPresenter;
    private View mHeader;
    private BGABanner mBgaBanner;
    private RatingBar mRatingBar;
    private TextView mTvShopPoint;
    private TextView mTvShopLocal;
    private TextView mTvShopName;
    private View mFooter;
    private TextView mTvSol;
    private TextView mTvChatTip;
    private RecyclerView mRecyclerViewTeach;
    private Button mBtnPay;
    private TextView mRvTeachNum;
    private String mId;
    private FooterAdapter mFooterAdapter;
    private TextView mTvBrandTip;
    private TextView mTvCollection;

    @Override
    public int initAddLayout() {
        return R.layout.activity_store;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mAdapter = new StoreAdapter(null);
        mPresenter = new StorePresenter(this);
        mRecyclerView.setAdapter(mAdapter);
        mId = mBundle.getString("id");
        mPresenter.storeInfo(mId);
        showLoading();
        initHeaderView();
        initFooterView();
        mBtnPay = rootView.findViewById(R.id.btnPay);
    }

    private void initFooterView() {
        mFooter = View.inflate(this, R.layout.layout_footer_store, null);
        mTvSol = mFooter.findViewById(R.id.tvSol);
        mTvChatTip = mFooter.findViewById(R.id.tvChatTip);
        mRecyclerViewTeach = mFooter.findViewById(R.id.recyclerViewTeach);
        mRvTeachNum = mFooter.findViewById(R.id.tvTeachNum);
        mTvBrandTip = mFooter.findViewById(R.id.tvBrandTip);
        mTvBrandTip.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", mId);
            ActivityUtils.init().start(this, BrandInfoActivity.class, "品牌介绍", bundle);
        });
        mAdapter.addFooterView(mFooter);
    }

    private void initHeaderView() {
        mHeader = View.inflate(this, R.layout.layout_header_store, null);
        mAdapter.addHeaderView(mHeader);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);
        mRatingBar = mHeader.findViewById(R.id.ratingBar);
        mTvShopPoint = mHeader.findViewById(R.id.tvShopPoint);
        mTvShopLocal = mHeader.findViewById(R.id.tvShopLocal);
        mTvShopName = mHeader.findViewById(R.id.tvShopName);
        mTvCollection = mHeader.findViewById(R.id.tvCollection);
        mTvCollection.setOnClickListener(v -> {
            if ("取消收藏".equals(mTvCollection.getText().toString())) {
                mPresenter.cancelCollType("1", mId);
            } else {
                mPresenter.collType("1", mId);
            }
        });
    }

    public void storeInfo(StoreBean bean) {
        mAdapter.setNewData(bean.goodsList);
        String sp_img = bean.shop.sp_img;
        ArrayList<String> strings = new ArrayList<>();
        strings.add(sp_img);
        initBanner(strings);
        initHeader(bean.shop);
        initFooter(bean);
        mBtnPay.setOnClickListener(v -> {
            new DialogPayBuyTest(this).show();
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.storeInfo(mId);
    }

    private void initFooter(StoreBean bean) {
        mTvSol.setText(bean.slogan);
        mTvChatTip.setText(String.format(Locale.CHINA, "用户评论（%d）", bean.shop.sales));
        mRecyclerViewTeach.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mFooterAdapter = new FooterAdapter(bean.teacherList);
        mRecyclerViewTeach.setAdapter(mFooterAdapter);
        mRvTeachNum.setText(String.format(Locale.CHINA, "全部 %d位老师", bean.teacherList.size()));
        mFooterAdapter.setOnItemClickListener((adapter, view, position) -> {
            StoreBean.TeacherListBean listBean = mFooterAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelable("bean", listBean);
            ActivityUtils.init().start(mActivity, TeacherInfoActivity.class, listBean.teacher_name, bundle);

        });
        if (bean.keep) {
            collection(R.drawable.collection_cancel, "取消收藏");
        } else {
            collection(R.drawable.collection, "收藏");
        }
    }

    private void initHeader(StoreBean.ShopBean bean) {
        mRatingBar.setRating((float) bean.score);
        mTvShopPoint.setText(String.format(Locale.CHINA, "%2.1f分", bean.score));
        mTvShopLocal.setText(bean.addr);
        mTvShopName.setText(bean.sp_name);
    }

    private void initBanner(List<String> bean) {
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {

            Glide.with(mActivity).load(model)
                    .apply(GlideUtils.init().options(R.drawable.placeholder))
                    .into((ImageView) itemView);
        });
        mBgaBanner.setData(bean, null);
    }

    public void collection(@DrawableRes int des, String text) {
        mTvCollection.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(des), null, null, null);
        mTvCollection.setText(text);
    }
}
