package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PostLocalBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.SearchPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.HomeAdapter;
import com.xueyituanchina.xueyituan.ui.adapter.SelectAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/10/19.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SearchActivity extends SuperBaseActivity {
    HomeAdapter mAdapter;
    SearchPresenter mPresenter;
    HashMap<String, String> mMap;
    @BindView(R.id.tvOrder)
    TextView mTvOrder;
    @BindView(R.id.tvType)
    TextView mTvType;
    @BindView(R.id.tvLocal)
    TextView mTvLocal;
    @BindView(R.id.ivGoBack)
    ImageView ivGoBack;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.recyclerViewSelect)
    RecyclerView mRecyclerViewSelect;
    @BindView(R.id.flTouchView)
    FrameLayout mFlTouchView;
    private Unbinder mUnbinder;
    private boolean mIsGone;
    SelectAdapter mSelectAdapter;
    private ArrayList<String> mStrings;
    private int clickSelect = -1;
    private String mPid;
    private int pagerNumber = 1;
    private ArrayList<PostLocalBean> mPostLocalBeans;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.llTool).init();
    }

    @Override
    protected int initRootLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initRootData(View rootView) {
        super.initRootData(rootView);
        initRefreshStatusView(rootView);
        mUnbinder = ButterKnife.bind(this);
        mAdapter = new HomeAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new SearchPresenter(this);
        String json = (String) SharePreUtil.getData(this, "sel_bean_local", "");
        if (!"".equals(json)) {
            mPostLocalBeans = new Gson().fromJson(json, new TypeToken<ArrayList<PostLocalBean>>() {
            }.getType());
        } else {
            json = " [{\"code\":\"371501\",\"name\":\"市辖区\"},{\"code\":\"371502\",\"name\":\"东昌府区\"}," +
                    "{\"code\":\"371521\",\"name\":\"阳谷县\"},{\"code\":\"371522\",\"name\":\"莘　县\"},{\"code\":\"371523\",\"name\":\"茌平县\"},{\"code\":\"371524\",\"name\":\"东阿县\"},{\"code\":\"371525\",\"name\":\"冠　县\"},{\"code\":\"371526\",\"name\":\"高唐县\"},{\"code\":\"371581\",\"name\":\"临清市\"}]";
            mPostLocalBeans = new Gson().fromJson(json, new TypeToken<ArrayList<PostLocalBean>>() {
            }.getType());
        }
        mMap = new HashMap<>();
        mPid = mBundle.getString("pid");
        mMap.put("orderType", "0");
        mMap.put("pageNumber", "1");
        if (!mPid.equals("0")) {
            mMap.put("catId", mPid);
        }
        mPresenter.homeGoodsList(mMap);
        mPresenter.homeList(mPid);
        initSelect(rootView);
        ivGoBack.setOnClickListener(v -> finish());
        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                KeyboardUtils.init().hideKeyboard(this, editSearch);
                editSearch.clearFocus();
                String string = editSearch.getText().toString();
                mMap.put("name", string);
                mPresenter.homeGoodsList(mMap, true);
            }
            return false;
        });
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (hasMore) {
                mMap.put("pageNumber", ++pagerNumber + "");
                mPresenter.homeGoodsList(mMap);
            } else {
                mSmartRefreshLayout.finishLoadMore(1000);

            }
        });
    }

    //0综合排序 1距离 2优选 3销量 4评分
    private void initSelect(View rootView) {
        mRecyclerViewSelect.setLayoutManager(new LinearLayoutManager(this));
        mStrings = new ArrayList<>();

        mSelectAdapter = new SelectAdapter(mStrings);
        mRecyclerViewSelect.setAdapter(mSelectAdapter);
        initLocalData();
        mTvLocal.setOnClickListener(v -> {
            if (mPostLocalBeans != null) {
                areaList(mPostLocalBeans);
                clickSelect = 2;
                closeDialog();
            }
        });
        mTvType.setOnClickListener(v -> {
            if (listBean != null) {
                homeTypeList(listBean);
                closeDialog();
                clickSelect = 1;
            }
        });
        mTvOrder.setOnClickListener(v -> {
            initOrderData();
            clickSelect = 0;
            closeDialog();
        });
        rootView.findViewById(R.id.flTouchView).setOnTouchListener((v, event) -> {
            v.performClick();
            if (event.getAction() == MotionEvent.ACTION_DOWN && mRecyclerViewSelect.getVisibility() == View.VISIBLE) {
                fadeOutLocal();
                return true;
            } else {
                return false;
            }
        });
        mSelectAdapter.setOnItemClickListener((adapter, view, position) -> {
            String data = mSelectAdapter.getData().get(position);
            if (clickSelect == 0) {
                mMap.put("orderType", position + "");
                mTvOrder.setText(data);
            } else if (clickSelect == 1) {
                mTvType.setText(data);
                for (HomeListBean.ListBean bean : listBean.list) {
                    if (data.equals(bean.name)) {
                        mMap.put("catId", bean.id + "");
                        break;
                    }
                }
            } else if (clickSelect == 2) {
                mTvLocal.setText(data);
                for (PostLocalBean bean : mPostLocalBeans) {
                    if (data.equals(bean.name)) {
                        mMap.put("areaCode", bean.code);
                        break;
                    }

                }
            }
            fadeOutLocal();
            mPresenter.homeGoodsList(mMap, true);
        });
    }

    private void initLocalData() {
        if (mStrings == null) {
            mStrings = new ArrayList<>();
        }
        mStrings.clear();
        mStrings.add("东昌府区");
        mStrings.add("临清市");
        mStrings.add("阳谷县");
        mStrings.add("莘县");
        mStrings.add("荏平县");
        mStrings.add("东阿县");
        mStrings.add("冠县");
        mStrings.add("高唐县");
        mSelectAdapter.setNewData(mStrings);
    }

//    private void initTypeData() {
//        if (mStrings == null) {
//            mStrings = new ArrayList<>();
//        }
//        mStrings.clear();
//        mStrings.add("特长");
//        mStrings.add("文化课");
//        mStrings.add("早教");
//        mStrings.add("成人教育");
//        mStrings.add("母婴馆");
//        mSelectAdapter.setNewData(mStrings);
//
//    }

    private void initOrderData() {
        if (mStrings == null) {
            mStrings = new ArrayList<>();
        }
        mStrings.clear();
        mStrings.add("综合排序");
        mStrings.add("距离");
        mStrings.add("优选");
        mStrings.add("销售");
        mStrings.add("评分");
        mSelectAdapter.setNewData(mStrings);

    }

    private void closeDialog() {
        mIsGone = mRecyclerViewSelect.getVisibility() == View.GONE;
        if (mIsGone) {
            fadeInLocal();
        } else {
            fadeOutLocal();
        }
    }

    private boolean hasMore;

    public void homeGoodsList(HomeGoodsList bean) {
        hasMore = bean.more;
        if (pagerNumber > 1) {
            mSmartRefreshLayout.finishLoadMore();
            mAdapter.addData(bean.list);
        } else {
            mAdapter.setNewData(bean.list);
        }
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            HomeGoodsList.ListBean listBean = mAdapter.getData().get(position);
            clickToStore(listBean.sp_name, listBean.user_id + "");
        });
    }

    private void clickToStore(String name, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        ActivityUtils.init().start(this.mActivity, StoreActivity.class, name, bundle);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mMap.clear();
        mTvOrder.setText("智能排序");
        mTvType.setText("全部分类");
        mTvLocal.setText("全城");
        editSearch.setText("");
        mMap.put("pageNumber", "1");
        pagerNumber = 1;
        mPresenter.homeGoodsList(mMap);
    }

    private void fadeInLocal() {
        ViewAnimator.animate(mRecyclerViewSelect)
                .fadeIn()
                .duration(300)
                .onStart(() -> {
                    if (mRecyclerViewSelect.getVisibility() == View.GONE) {
                        mRecyclerViewSelect.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    private void fadeOutLocal() {
        ViewAnimator.animate(mRecyclerViewSelect)
                .fadeOut()
                .duration(300)
                .onStop(() -> {
                    if (mRecyclerViewSelect.getVisibility() == View.VISIBLE) {
                        mRecyclerViewSelect.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private HomeListBean listBean;

    public void homeTypeList(HomeListBean listBean) {
        if (mStrings == null) {
            mStrings = new ArrayList<>();
        }
        mStrings.clear();
        this.listBean = listBean;
        Observable.fromIterable(listBean.list).subscribe(listBean1 -> {
            mStrings.add(listBean1.name);
        });
        mSelectAdapter.setNewData(mStrings);
    }


    public void areaList(ArrayList<PostLocalBean> listBean) {
        if (mStrings == null) {
            mStrings = new ArrayList<>();
        }
        mStrings.clear();
        Observable.fromIterable(listBean).subscribe(bean -> mStrings.add(bean.name));
        mSelectAdapter.setNewData(mStrings);
    }
}
