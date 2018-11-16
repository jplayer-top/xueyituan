package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.BrandBBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.BrandPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.BrandInfoAdapter;

import top.jplayer.baseprolibrary.glide.GlideUtils;
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
    private ImageView mIvAvatarSrc;
    private TextView mTvTitle;
    private TextView mTvSol;
    private TextView mTvBrandBestText;
    private TextView mTvBrandText;

    @Override
    public int initAddLayout() {
        return R.layout.activity_brand_detail;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mPresenter = new BrandPresenter(this);
        mPresenter.brandInfo(mBundle.getString("id"));
        mAdapter = new BrandInfoAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        View view = View.inflate(this, R.layout.adapter_header_brand, null);
        mIvAvatarSrc = view.findViewById(R.id.ivAvatarSrc);
        mTvTitle = view.findViewById(R.id.tvTitle);
        mTvSol = view.findViewById(R.id.tvSol);
        mTvBrandBestText = view.findViewById(R.id.tvBrandBestText);
        mTvBrandText = view.findViewById(R.id.tvBrandText);
        mAdapter.addHeaderView(view);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.brandInfo(mBundle.getString("id"));
    }

    public void brandInfo(BrandBBean brandBBean) {
        if (brandBBean != null) {
            BrandBBean.BrandBean brand = brandBBean.brand;
            if (brand != null) {
                mAdapter.setNewData(brand.envirmtList);
                Glide.with(this).load(brand.envirmtList != null && brand.envirmtList.size() > 0 ?
                        brand.envirmtList.get(0) : brand.logo).apply
                        (GlideUtils.init().options(R.mipmap.ic_launcher)).into(mIvAvatarSrc);
                mTvTitle.setText(brand.brand);
                mTvSol.setText(brand.slogan);
                mTvBrandBestText.setText(brand.feature);
                mTvBrandText.setText(brand.story);
            }
        }
    }
}
