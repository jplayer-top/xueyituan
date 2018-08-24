package com.xueyituanchina.xueyituan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.kingja.magicmirror.MagicMirrorView;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.HomePresenter;
import com.xueyituanchina.xueyituan.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/8/16.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeFragment extends SuperBaseFragment {

    private HomeAdapter mAdapter;
    private ViewFlipper mViewFlipper;
    TabLayout mTabLayout;
    HomePresenter mPresenter;
    View mHeader;
    private BGABanner mBgaBanner;
    private TextView mTvWeekTitle;
    private MagicMirrorView mMirrorView;
    TextView mTvT0;
    TextView mTvT1;
    TextView mTvT2;
    TextView mTvT3;
    TextView mTvT4;
    ImageView mIvS0;
    ImageView mIvS1;
    ImageView mIvS2;
    ImageView mIvS3;
    ImageView mIvS4;
    private ArrayList<TextView> mListText;
    private ArrayList<ImageView> mListSrc;
    private TextView mTvShopT0;
    private TextView mTvShopT1;
    private TextView mTvShop1T0;
    private TextView mTvShop1T1;
    private ImageView mIvShop0;
    private ImageView mIvShop1;
    public int isLoadding = 0;
    private HashMap<String, String> mMap;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        mAdapter = new HomeAdapter(new ArrayList<>());
        initHeader();
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new HomePresenter(this);
        mPresenter.homeTop();
        mPresenter.homeList();
        mMap = new HashMap<>();
        mMap.put("orderType", "0");
        mPresenter.homeGoodsList(mMap);
        showLoading();
    }

    private void initHeader() {
        mHeader = View.inflate(getContext(), R.layout.header_home, null);

        mViewFlipper = mHeader.findViewById(R.id.viewFlipper);

        mTabLayout = mHeader.findViewById(R.id.tabLayout);

        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);

        mTvWeekTitle = mHeader.findViewById(R.id.tvWeekTitle);
        mMirrorView = mHeader.findViewById(R.id.mirrorView);

        mTvShopT0 = mHeader.findViewById(R.id.tvShopT0);
        mTvShopT1 = mHeader.findViewById(R.id.tvShopT1);
        mTvShop1T0 = mHeader.findViewById(R.id.tvShop1T0);
        mTvShop1T1 = mHeader.findViewById(R.id.tvShop1T1);
        mIvShop0 = mHeader.findViewById(R.id.ivShop0);
        mIvShop1 = mHeader.findViewById(R.id.ivShop1);


        mTvT0 = mHeader.findViewById(R.id.tvT0);
        mTvT1 = mHeader.findViewById(R.id.tvT1);
        mTvT2 = mHeader.findViewById(R.id.tvT2);
        mTvT3 = mHeader.findViewById(R.id.tvT3);
        mTvT4 = mHeader.findViewById(R.id.tvT4);
        mListText = new ArrayList<>();
        mListText.add(mTvT0);
        mListText.add(mTvT1);
        mListText.add(mTvT2);
        mListText.add(mTvT3);
        mListText.add(mTvT4);

        mIvS0 = mHeader.findViewById(R.id.ivSrc0);
        mIvS1 = mHeader.findViewById(R.id.ivSrc1);
        mIvS2 = mHeader.findViewById(R.id.ivSrc2);
        mIvS3 = mHeader.findViewById(R.id.ivSrc3);
        mIvS4 = mHeader.findViewById(R.id.ivSrc4);


        mListSrc = new ArrayList<>();
        mListSrc.add(mIvS0);
        mListSrc.add(mIvS1);
        mListSrc.add(mIvS2);
        mListSrc.add(mIvS3);
        mListSrc.add(mIvS4);

        mAdapter.addHeaderView(mHeader);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mMap.put("orderType", tab.getPosition() + "");
                mPresenter.homeGoodsList(mMap);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.method();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.method();
            }
        });
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.constraint).init();
    }

    public void homeList(HomeListBean homeListBean) {
        initClassify(homeListBean.list);
    }

    public void homeGoodsList(HomeGoodsList homeGoodsList) {
        mAdapter.setNewData(homeGoodsList.list);
    }

    public void homeTop(HomeTopBean homeTopBean) {
        initViewFlipper(homeTopBean);
        initBanner(homeTopBean);
        initNews(homeTopBean);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        isLoadding = 0;
        mPresenter.homeTop();
        mPresenter.homeList();
        mPresenter.homeGoodsList(mMap);
    }

    private void initNews(HomeTopBean homeTopBean) {
        mTvWeekTitle.setText(homeTopBean.acfamily.title);
        mTvShopT0.setText(homeTopBean.shop.get(0).recommend_title);
        mTvShopT1.setText(homeTopBean.shop.get(0).sp_name);
        mTvShop1T0.setText(homeTopBean.shop.get(1).recommend_title);
        mTvShop1T1.setText(homeTopBean.shop.get(1).sp_name);
        Glide.with(mActivity)
                .load(homeTopBean.acfamily.avator)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                .into(mMirrorView);
        Glide.with(mActivity)
                .load(homeTopBean.shop.get(0).sp_img)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                .into(mIvShop0);
        Glide.with(mActivity)
                .load(homeTopBean.shop.get(1).sp_img)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                .into(mIvShop1);
    }

    private void initViewFlipper(HomeTopBean homeTopBean) {
        List<HomeTopBean.AcTopBean> info = homeTopBean.acTop;
        Observable.fromIterable(info).subscribe(listBean -> mViewFlipper.addView(getTextImageView(listBean)));
    }

    private void initClassify(List<HomeListBean.ListBean> list) {
        Observable.fromIterable(list).subscribe(listBean -> {
            int index = list.indexOf(listBean);
            mListText.get(index).setText(listBean.name);
            Glide.with(mActivity)
                    .load(listBean.icon)
                    .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                    .into(mListSrc.get(index));

        });
    }

    private void initBanner(HomeTopBean homeTopBean) {
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {
            HomeTopBean.BannerBean bannerBean = (HomeTopBean.BannerBean) model;
            if (bannerBean != null) {
                Glide.with(mActivity).load(bannerBean.banner_img)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView);
            }

        });
        mBgaBanner.setData(homeTopBean.banner, null);
    }


    private View getTextImageView(HomeTopBean.AcTopBean listBean) {
        View view = View.inflate(getContext(), R.layout.layout_marquee_text_image, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle0);
        ImageView ivSrc = view.findViewById(R.id.ivSrc);
        TextView tvSubTitle = view.findViewById(R.id.tvTitle1);
        tvTitle.setText(listBean.title);
        tvSubTitle.setText(listBean.sub_title);
        Glide.with(mActivity).load(listBean.sp_img).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivSrc);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
