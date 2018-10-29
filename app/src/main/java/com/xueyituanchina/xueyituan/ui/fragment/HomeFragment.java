package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.github.florent37.viewanimator.ViewAnimator;
import com.kingja.magicmirror.MagicMirrorView;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.HomePresenter;
import com.xueyituanchina.xueyituan.ui.activity.SearchActivity;
import com.xueyituanchina.xueyituan.ui.activity.StoreActivity;
import com.xueyituanchina.xueyituan.ui.activity.WeekListActivity;
import com.xueyituanchina.xueyituan.ui.adapter.HomeAdapter;
import com.xueyituanchina.xueyituan.ui.adapter.LocalSetAdapter;
import com.xueyituanchina.xueyituan.ui.adapter.SelectCatAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.widgets.popup.CommonPopupWindow;

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
    private View mLlShow0;
    private View mLlShop1;
    private RecyclerView mRecyclerViewLocal;
    private boolean mIsGone = true;
    private LocalSetAdapter mLocalSetAdapter;
    private TextView mTvLocal;
    // popup window
    private CommonPopupWindow window;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private View mView;
    private RecyclerView mRecyclerViewSelect;

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
        rootView.findViewById(R.id.tvSearch).setOnClickListener(v -> {
            clickToSearch(0);
        });
        showLoading();
        window = new CommonPopupWindow(getActivity(),
                R.layout.layout_popup_view, ScreenUtils.dp2px(80),
                ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view = getContentView();
                mRecyclerViewSelect = view.findViewById(R.id.recyclerViewSelect);
                initRecyclerSelect();
            }

            @Override
            protected void initEvent() {
            }
        };
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.ALIGN_LEFT | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
    }

    private void initHeader() {
        initRecyclerLocal();
        mHeader = View.inflate(getContext(), R.layout.header_home, null);
        mView = mHeader.findViewById(R.id.viAr);
        mViewFlipper = mHeader.findViewById(R.id.viewFlipper);
        mHeader.findViewById(R.id.llWeek).setOnClickListener(v -> {
            ActivityUtils.init().start(getActivity(), WeekListActivity.class, "周末亲子游");
        });
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
        mLlShow0 = mHeader.findViewById(R.id.llShop0);
        mLlShop1 = mHeader.findViewById(R.id.llShop1);


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
                int position = tab.getPosition();
                if (position != 5) {
                    mMap.put("orderType", position + "");
                    mPresenter.homeGoodsList(mMap);
                } else {
                    if (sendList != null) {
                        mSelectCatAdapter.setNewData(sendList);
                        window.showBashOfAnchor(mView, layoutGravity, 0, 0);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.method();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.method();
                int position = tab.getPosition();
                if (position == 5) {
                    if (sendList != null) {
                        window.showBashOfAnchor(mView, layoutGravity, 0, 0);
                    }
                }
            }
        });
    }

    SelectCatAdapter mSelectCatAdapter;

    private void initRecyclerSelect() {
        mRecyclerViewSelect.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mSelectCatAdapter = new SelectCatAdapter(null);
        mRecyclerViewSelect.setAdapter(mSelectCatAdapter);
        mSelectCatAdapter.setOnItemClickListener((adapter, view, position) -> {
            HomeListBean.SendListBean sendListBean = mSelectCatAdapter.getData().get(position);
            String data = sendListBean.name;
            TabLayout.Tab tab = mTabLayout.getTabAt(5);
            if (tab != null) {
                tab.setText(data);
            }
            mMap.put("catId", sendListBean.pid + "");
            mPresenter.homeGoodsList(mMap);
        });
    }

    private void initRecyclerLocal() {
        mRecyclerViewLocal = rootView.findViewById(R.id.recyclerViewLocal);
        mRecyclerViewLocal.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("东昌府区");
        strings.add("临清市");
        strings.add("阳谷县");
        strings.add("莘县");
        strings.add("荏平县");
        strings.add("东阿县");
        strings.add("冠县");
        strings.add("高唐县");
        mLocalSetAdapter = new LocalSetAdapter(strings);
        mRecyclerViewLocal.setAdapter(mLocalSetAdapter);
        mTvLocal = rootView.findViewById(R.id.tvLocal);
        mTvLocal.setOnClickListener(v -> {
            dissmisDilaog();
        });
        rootView.findViewById(R.id.flTouchView).setOnTouchListener((v, event) -> {
            v.performClick();
            if (event.getAction() == MotionEvent.ACTION_DOWN && mRecyclerViewLocal.getVisibility() == View.VISIBLE) {
                fadeOutLocal(mRecyclerViewLocal);
                return true;
            } else {
                return false;
            }
        });
        mLocalSetAdapter.setOnItemClickListener((adapter, view, position) -> {
            String data = mLocalSetAdapter.getData().get(position);
            mTvLocal.setText(data);
            fadeOutLocal(mRecyclerViewLocal);
        });
    }

    private void dissmisDilaog() {
        if (mIsGone) {
            fadeInLocal(mRecyclerViewLocal);
        } else {
            fadeOutLocal(mRecyclerViewLocal);
        }
    }

    private void fadeInLocal(View view) {
        ViewAnimator.animate(view)
                .fadeIn()
                .duration(0)
                .onStart(() -> {
                    view.setVisibility(View.VISIBLE);
                    mIsGone = false;
                })
                .start();
    }

    private void fadeOutLocal(View view) {
        ViewAnimator.animate(view)
                .fadeOut()
                .duration(0)
                .onStop(() -> {
                    mIsGone = true;
                    view.setVisibility(View.GONE);
                })
                .start();
    }

    private void clickToStore(String name, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        ActivityUtils.init().start(this.mActivity, StoreActivity.class, name, bundle);
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.constraint).init();
    }

    List<HomeListBean.SendListBean> sendList;

    public void homeList(HomeListBean homeListBean) {
        sendList = homeListBean.sendList;
        initClassify(homeListBean.list);
    }

    public void homeGoodsList(HomeGoodsList homeGoodsList) {
        mAdapter.setNewData(homeGoodsList.list);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            HomeGoodsList.ListBean bean = mAdapter.getData().get(position);
            clickToStore(bean.sp_name, bean.user_id + "");
        });
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
        mLlShow0.setOnClickListener(v -> {
            clickToStore(homeTopBean.shop.get(0).sp_name, homeTopBean.shop.get(0).user_id + "");
        });
        mLlShop1.setOnClickListener(v -> {
            clickToStore(homeTopBean.shop.get(1).sp_name, homeTopBean.shop.get(1).user_id + "");
        });
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
            mListText.get(index).setOnClickListener(v -> {
                clickToSearch(index + 1);
            });
            mListSrc.get(index).setOnClickListener(v -> {
                clickToSearch(index + 1);
            });
        });
    }

    private void clickToSearch(int index) {
        Bundle bundle = new Bundle();
        bundle.putString("pid", index + "");
        ActivityUtils.init().start(getActivity(), SearchActivity.class, "", bundle);
    }

    private void initBanner(HomeTopBean homeTopBean) {
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {
            HomeTopBean.BannerBean bannerBean = (HomeTopBean.BannerBean) model;
            if (bannerBean != null) {
                Glide.with(mActivity).load(bannerBean.banner_img)
                        .apply(GlideUtils.init().options(R.drawable.placeholder))
                        .into((ImageView) itemView);
                itemView.setOnClickListener(v -> {
                    if ("2".equals(bannerBean.banner_type)) {
                        startWeb("https://www.xueyituanchina.cn/info/article_active.html?id=" + bannerBean.banner_id + "&uid=" + XYTApplication.uid);
                    } else if ("1".equals(bannerBean.banner_type)) {
                        clickToStore("", bannerBean.banner_id);
                    } else {
                        startWeb(bannerBean.banner_id);
                    }
                });
            }

        });
        mBgaBanner.setData(homeTopBean.banner, null);
    }

    private void startWeb(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
    }


    private View getTextImageView(HomeTopBean.AcTopBean listBean) {
        View view = View.inflate(getContext(), R.layout.layout_marquee_text_image, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle0);
        ImageView ivSrc = view.findViewById(R.id.ivSrc);
        TextView tvSubTitle = view.findViewById(R.id.tvTitle1);
        tvTitle.setText(listBean.title);
        tvSubTitle.setText(listBean.sub_title);
        Glide.with(mActivity).load(listBean.sp_img).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivSrc);
        view.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.xueyituanchina.cn/info/article_active.html?id=" + listBean.id + "&uid=" + XYTApplication.uid);
            ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
