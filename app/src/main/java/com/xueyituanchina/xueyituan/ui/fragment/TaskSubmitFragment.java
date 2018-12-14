package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskDestoryBean;
import com.xueyituanchina.xueyituan.mpbe.bean.TaskGoodsListBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.TaskFragmentPresenter;
import com.xueyituanchina.xueyituan.ui.activity.PayTaskActivity;
import com.xueyituanchina.xueyituan.ui.adapter.TaskPushAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.PickerUtils;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskSubmitFragment extends SuperBaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.llNums)
    TextView mLlNums;
    @BindView(R.id.llPrice)
    TextView mLlPrice;
    @BindView(R.id.tvGoodsList)
    TextView tvGoodsList;
    @BindView(R.id.btnPush)
    Button mBtnPush;
    @BindView(R.id.llPushTask)
    LinearLayout mLlPushTask;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.llTaskList)
    LinearLayout mLlTaskList;
    private Unbinder mBind;
    private TaskFragmentPresenter mPresenter;
    private Map<String, String> mMap;
    private TaskPushAdapter mAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_task_submit;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        mBind = ButterKnife.bind(this, rootView);
        mPresenter = new TaskFragmentPresenter(this);
        mMap = new HashMap<>();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mLlPushTask.setVisibility(tab.getPosition() == 0 ? View.VISIBLE : View.GONE);
                mLlTaskList.setVisibility(tab.getPosition() == 1 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mAdapter = new TaskPushAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        tvGoodsList.setOnClickListener(v -> {
            mPresenter.taskGoodsList();
        });
        mBtnPush.setOnClickListener(v -> {

            if (mMap.size() < 1) {
                ToastUtils.init().showInfoToast(mActivity, "请选择活动课程");
                return;
            }
            if (StringUtils.init().isEmpty(mTvTitle)) {
                ToastUtils.init().showInfoToast(mActivity, "请输入活动标题");
                return;
            }
            if (mTvTitle.getText().toString().length() > 15) {
                ToastUtils.init().showInfoToast(mActivity, "活动标题限制为15个字符");
                return;
            }
            if (StringUtils.init().isEmpty(mLlNums)) {
                ToastUtils.init().showInfoToast(mActivity, "请输入活动数量");
                return;
            }
            if (StringUtils.init().isEmpty(mLlPrice)) {
                ToastUtils.init().showInfoToast(mActivity, "请输入活动单个价格");
                return;
            }
            if (XYTApplication.merchant != 3) {
                ToastUtils.init().showErrorToast(mActivity, "当前只有商家可以发布任务");
                return;
            }
            mMap.put("goods_subtitle", mTvTitle.getText().toString());
            mMap.put("amount", mLlNums.getText().toString());
            mMap.put("money", mLlPrice.getText().toString());
            mPresenter.taskPush(mMap);
        });
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (XYTApplication.merchant == 3) {
                mPresenter.tasksDestory();
            } else {
                ToastUtils.init().showErrorToast(mActivity, "当前只有商家可以发布任务");
            }
        });
        mPresenter.tasksDestory();
        if (XYTApplication.merchant != 3) {
            ToastUtils.init().showErrorToast(mActivity, "当前只有商家可以发布任务");
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbarPush).init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void goodsList(TaskGoodsListBean bean) {
        PickerUtils pickerUtils = new PickerUtils();
        pickerUtils.setOnClickString(pos -> {
            TaskGoodsListBean.ListBean listBean = bean.list.get(pos);
            mMap.put("goods_id", listBean.goods_id + "");
            tvGoodsList.setText(listBean.goods_title);
        });
        ArrayList<String> strings = new ArrayList<>();
        if (bean.list != null && bean.list.size() > 0) {
            for (TaskGoodsListBean.ListBean listBean : bean.list) {
                strings.add(listBean.goods_title);
            }
            pickerUtils.initStringPicker(strings, 0, mActivity);

        } else {
            ToastUtils.init().showQuickToast("当前未添加课程");
        }
    }

    public void pushTaskOk(PushTaskBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", bean.orderId);
        bundle.putString("totalPrice", bean.totalPrice);
        ActivityUtils.init().start(mActivity, PayTaskActivity.class, "任务支付", bundle);
        mPresenter.tasksDestory();
    }

    public void tesksDestory(PushTaskDestoryBean bean) {
        mSmartRefreshLayout.finishRefresh();
        List<PushTaskDestoryBean.ListBean> list = bean.list;
        if (list != null && list.size() > 0) {
            mMultipleStatusView.showContent();
            mAdapter.setNewData(list);
        } else {
            mMultipleStatusView.showEmpty();
        }
    }

}
