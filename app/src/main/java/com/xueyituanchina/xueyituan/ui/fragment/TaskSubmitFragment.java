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
import com.xueyituanchina.xueyituan.mpbe.event.AliPayOkEvent;
import com.xueyituanchina.xueyituan.mpbe.event.NoPayBackEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.TaskFragmentPresenter;
import com.xueyituanchina.xueyituan.ui.activity.PayTaskActivity;
import com.xueyituanchina.xueyituan.ui.activity.RechargeActivity;
import com.xueyituanchina.xueyituan.ui.activity.ShopCreateActivity;
import com.xueyituanchina.xueyituan.ui.adapter.TaskPushAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.DialogTaskPush;
import com.xueyituanchina.xueyituan.wxapi.WXPayEntryActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.dialog.DialogLogout;
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
    private DialogTaskPush mDialogTaskPush;

    @Override
    public int initLayout() {
        return R.layout.fragment_task_submit;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        mBind = ButterKnife.bind(this, rootView);
        mPresenter = new TaskFragmentPresenter(this);
        EventBus.getDefault().register(this);
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
                toCreateStore();
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
                toCreateStore();
            }
        });
        mPresenter.tasksDestory();
        if (XYTApplication.merchant != 3) {
            toCreateStore();
        }
    }

    private void toCreateStore() {
        mDialogTaskPush = new DialogTaskPush(mActivity);
        mDialogTaskPush.show(R.id.btnSure, view -> {
            if (0 == (XYTApplication.merchant)) {
                ActivityUtils.init().start(mActivity, ShopCreateActivity.class, "商家入驻");
            } else if (1 == (XYTApplication.merchant)) {
                DialogLogout dialogLogout = new DialogLogout(mActivity);
                dialogLogout
                        .setTitle("温馨提示")
                        .setSubTitle("当前已提交入驻信息\n请提交五百元审核金")
                        .show(R.id.btnSure, view1 -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("recharge", "500.00");
                            ActivityUtils.init().start(this.getActivity(), RechargeActivity.class, "商铺审核金", bundle);
                            dialogLogout.dismiss();
                        });
            } else if (2 == (XYTApplication.merchant)) {
                DialogLogout dialogLogout = new DialogLogout(mActivity);
                dialogLogout
                        .setTitle("温馨提示")
                        .setSubTitle("当前已认缴五百元审核金\n请耐心等待审核")
                        .show(R.id.btnSure, view1 -> {
                            dialogLogout.dismiss();
                        });
            } else if (4 == (XYTApplication.merchant)) {
                ActivityUtils.init().start(mActivity, ShopCreateActivity.class, "商家入驻");
            }
            mDialogTaskPush.dismiss();
        });
    }


    @Subscribe
    public void onEvent(WXPayEntryActivity.WxPayEvent event) {
        XYTApplication.merchant = 2;
    }

    @Subscribe
    public void onEvent(NoPayBackEvent event) {
        XYTApplication.merchant = 1;
    }

    @Subscribe
    public void onEvent(AliPayOkEvent event) {
        XYTApplication.merchant = 2;
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
        EventBus.getDefault().unregister(this);
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
