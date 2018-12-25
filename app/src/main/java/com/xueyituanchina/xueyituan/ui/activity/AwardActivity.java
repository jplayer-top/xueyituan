package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareImgBean;
import com.xueyituanchina.xueyituan.mpbe.event.ShareOtherEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.AwardActivityPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.AwardAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.ShareAwardDialog;
import com.xueyituanchina.xueyituan.wxapi.WXShareBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/12/3.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardActivity extends CommonToolBarActivity {

    private AwardAdapter mAdapter;
    private ShareAwardDialog mAwardDialog;
    public int cPos = -1;
    private AwardActivityPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.layout_refresh_white_nofoot;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        ArrayList<AwardBean.TaskListBean> beans = mBundle.getParcelableArrayList("beans");
        EventBus.getDefault().register(this);
        mPresenter = new AwardActivityPresenter(this);
        mAdapter = new AwardAdapter(beans);
        mAdapter.setEmptyView(View.inflate(this, R.layout.layout_empty_view_award_2, null));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (XYTApplication.assertNoLogin(mActivity)) {
                if (mAdapter.getData().get(position).shared) {
                    ToastUtils.init().showInfoToast(mActivity, "该任务已经分享过");
                    return false;
                }
                mPresenter.shareCan(mAdapter.getData().get(position).task_id, position);
            } else {
                ToastUtils.init().showInfoToast(mActivity, "请先登录");
            }
            return false;
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            AwardBean.TaskListBean taskListBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", taskListBean.goods_id + "");
            ActivityUtils.init().start(mActivity, ShopItemActivity.class, taskListBean.goods_title, bundle);
        });
    }

    @Subscribe
    public void onEvent(ShareOtherEvent event) {
        cPos = -1;
    }

    @Subscribe
    public void onEvent(WXShareBean event) {
        if (cPos != -1) {
            mPresenter.shareOk(mAdapter.getData().get(cPos).task_id);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.awardList();
    }

    public void awardList(AwardBean bean) {
        mSmartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mAdapter.setNewData(bean.taskList);
    }


    public void shareOk(BaseBean bean) {
        cPos = -1;
        mPresenter.awardList();
    }

    ShareImgBean shareBean;

    public void shareImg(ShareImgBean bean, int position) {
        shareBean = bean;
        cPos = position;
        mAwardDialog = new ShareAwardDialog(mActivity);
        mAwardDialog.setUrl(bean.shareImg);
        mAwardDialog.show();
        mAwardDialog.setOnDismissListener(dialog -> {
            LogUtil.str("-----");
            cPos = -1;
        });
    }

    public void shareCan(String taskId, int position) {
        mPresenter.shareImg(taskId, position);
    }
}
