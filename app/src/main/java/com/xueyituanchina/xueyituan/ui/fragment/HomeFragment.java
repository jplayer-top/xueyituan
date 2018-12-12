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

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.gson.Gson;
import com.kingja.magicmirror.MagicMirrorView;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaAllBean;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PostLocalBean;
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
import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
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
    private View mFooter;
    private int pageNumber = 1;

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        mFooter = View.inflate(mActivity, R.layout.layout_empty_view_card, null);
        TextView emptyText = mFooter.findViewById(R.id.empty_retry_view);
        emptyText.setText("暂无相关商家及课程");
        mAdapter = new HomeAdapter(new ArrayList<>());
        initHeader();
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new HomePresenter(this);
        mPresenter.homeTop();
        mPresenter.homeList();

        mMap = new HashMap<>();
        mMap.put("orderType", "0");
        mMap.put("pageNumber", "1");
        String sel_areaCode = (String) SharePreUtil.getData(getActivity(), "sel_areaCode", "");
        String sel_local = (String) SharePreUtil.getData(getActivity(), "sel_local", "");
        mTvLocal.setText("".equals(sel_local) ? "山东省" : sel_local);
        if (!"".equals(sel_areaCode)) {
            mMap.put("areaCode", sel_areaCode);
        }
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
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.ALIGN_LEFT |
                CommonPopupWindow.LayoutGravity.TO_BOTTOM);
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (hasMore) {
                mMap.put("pageNumber", ++pageNumber + "");
                mPresenter.homeGoodsList(mMap);
            } else {
                mSmartRefreshLayout.finishLoadMore(1000);

            }
        });
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
                    mMap.put("pageNumber", "1");
                    pageNumber = 1;
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
            window.getPopupWindow().dismiss();
            mMap.put("pageNumber", "1");
            pageNumber = 1;
            mPresenter.homeGoodsList(mMap);
        });

    }

    private void initRecyclerLocal() {
        mRecyclerViewLocal = rootView.findViewById(R.id.recyclerViewLocal);
        mRecyclerViewLocal.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mLocalSetAdapter = new LocalSetAdapter(null);
        mRecyclerViewLocal.setAdapter(mLocalSetAdapter);
        mTvLocal = rootView.findViewById(R.id.tvLocal);

        initPicker();

        mTvLocal.setOnClickListener(v -> {
            if (mLocalBean != null) {
                areaSD(mLocalBean);
            } else {
                mPresenter.areaSD();
            }
        });
//        mTvLocal.setOnClickListener(v -> {
//            dissmisDilaog();
//        });
        rootView.findViewById(R.id.flTouchView).setOnTouchListener((v, event) -> {
            v.performClick();
            if (event.getAction() == MotionEvent.ACTION_DOWN && mRecyclerViewLocal.getVisibility() == View.VISIBLE) {
                fadeOutLocal(mRecyclerViewLocal);
                return true;
            } else {
                return false;
            }
        });
//        mLocalSetAdapter.setOnItemClickListener((adapter, view, position) -> {
//            mData = mLocalSetAdapter.getData().get(position);
//            mTvLocal.setText(mData);
//            fadeOutLocal(mRecyclerViewLocal);
//            SharePreUtil.saveData(mActivity, "sel_local", mData);
//            mMap.put("areaCode", mData);
//            mMap.put("pageNumber", "1");
//            pageNumber = 1;
//            mPresenter.homeGoodsList(mMap);
//        });
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

    private boolean hasMore;

    public void homeGoodsList(HomeGoodsList homeGoodsList) {
        hasMore = homeGoodsList.more;
        if (pageNumber > 1) {
            mSmartRefreshLayout.finishLoadMore();
            mAdapter.addData(homeGoodsList.list);
        } else {
            mAdapter.removeAllFooterView();
            if (homeGoodsList.list == null || homeGoodsList.list.size() < 1) {
                mAdapter.addFooterView(mFooter);
            }
            mAdapter.setNewData(homeGoodsList.list);
        }
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
        mMap.put("catId", "");
        TabLayout.Tab tab = mTabLayout.getTabAt(5);
        if (tab != null) {
            tab.setText("筛选");
        }
        mMap.put("pageNumber", "1");
        pageNumber = 1;
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
            bundle.putString("url", "https://www.xueyituanchina.cn/info/topinfo.html?id=" + listBean.id + "&uid=" +
                    XYTApplication.uid);
            ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    List<String> mListLocalString;
    AreaBean mAreaBean;

    public void areaList(AreaBean listBean) {
        if (mListLocalString == null) {
            mListLocalString = new ArrayList<>();
        }
        mListLocalString.clear();
        mAreaBean = listBean;
        Observable.fromIterable(listBean.areas.subs).subscribe(bean -> mListLocalString.add(bean.area_name));
        mLocalSetAdapter.setNewData(mListLocalString);
    }

    private String mProvince_code;
    private String mCity_code;
    private String mArea_code;
    private String mProvince_name;
    private String mCity_name;
    private String mArea_name;

    private ArrayList<PostLocalBean> optionsLocalSItems = new ArrayList<>();
    private ArrayList<ArrayList<PostLocalBean>> optionsLocalXItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<PostLocalBean>>> optionsQItems = new ArrayList<>();
    private OptionsPickerView mLocalPickerView;
    private AreaAllBean mLocalBean;

    private void initPicker() {
        //地区
        mLocalPickerView = new OptionsPickerView.Builder(getActivity(), (options1, option2, options3, v) -> {
            mProvince_name = optionsLocalSItems.get(options1).name + "";
            mProvince_code = optionsLocalSItems.get(options1).code;
            PostLocalBean localBean = optionsLocalXItems.get(options1).get(option2);
            mCity_name = (localBean.name) == null ? "" : (localBean.name + "");
            mCity_code = localBean.code;
            ArrayList<PostLocalBean> postLocalBeans = optionsQItems.get(options1).get(option2);
            PostLocalBean areaBean = postLocalBeans.get(options3);
            mArea_code = areaBean.code;
            mArea_name = (areaBean.name) == null ? "" : areaBean.name + "";
            mTvLocal.setText(String.format(Locale.CHINA, "%s", mArea_name));
            LogUtil.str(String.format(Locale.CHINA, "%s%s%s", mProvince_name, mCity_name, mArea_name));

            SharePreUtil.saveData(mActivity, "sel_local", mArea_name);
            SharePreUtil.saveData(mActivity, "sel_areaCode", mArea_code);
            SharePreUtil.saveData(mActivity, "sel_bean_local", new Gson().toJson(postLocalBeans));

            LogUtil.str(postLocalBeans);
            mMap.put("areaCode", mArea_code);
            mMap.put("pageNumber", "1");
            pageNumber = 1;
            mPresenter.homeGoodsList(mMap);
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
    }

    public void areaSD(AreaAllBean localBean) {
        mLocalBean = localBean;
        if (optionsLocalSItems == null || optionsLocalSItems.size() < 1) {
            if (localBean != null && localBean.areas.size() > 0) {
                for (int i = 0; i < localBean.areas.size(); i++) {//省
                    ArrayList<ArrayList<PostLocalBean>> minLocalItems = new ArrayList<>();
                    ArrayList<PostLocalBean> subsString = new ArrayList<>();
                    for (int j = 0; j < localBean.areas.get(i).subs.size(); j++) {
                        String area_nameS = localBean.areas.get(i).subs.get(j).area_name;
                        String area_codeS = localBean.areas.get(i).subs.get(j).area_code;
                        PostLocalBean postLocalBeanS = new PostLocalBean();
                        postLocalBeanS.name = area_nameS;
                        postLocalBeanS.code = area_codeS;
                        subsString.add(postLocalBeanS);
                        ArrayList<PostLocalBean> subsXString = new ArrayList<>();
                        for (int k = 0; k < localBean.areas.get(i).subs.get(j).subs.size(); k++) {
                            String area_nameX = localBean.areas.get(i).subs.get(j).subs.get(k).area_name;
                            String area_codeX = localBean.areas.get(i).subs.get(j).subs.get(k).area_code;
                            PostLocalBean postLocalBeanX = new PostLocalBean();
                            postLocalBeanX.name = area_nameX;
                            postLocalBeanX.code = area_codeX;
                            subsXString.add(postLocalBeanX);
                        }
                        minLocalItems.add(subsXString);
                    }
                    optionsLocalXItems.add(subsString);
                    optionsQItems.add(minLocalItems);
                    String area_name = localBean.areas.get(i).area_name;
                    String area_code = localBean.areas.get(i).area_code;
                    PostLocalBean postLocalBean = new PostLocalBean();
                    postLocalBean.code = area_code;
                    postLocalBean.name = area_name;
                    optionsLocalSItems.add(postLocalBean);
                }
                if (optionsLocalSItems.size() != 0 & optionsLocalSItems != null) {
                    mLocalPickerView.setPicker(optionsLocalSItems, optionsLocalXItems, optionsQItems);
                }
            }

        }
        if (!mLocalPickerView.isShowing()) {
            mLocalPickerView.show();
        }
    }
}
