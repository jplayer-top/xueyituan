package com.xueyituanchina.xueyituan.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.UserTaskListBean;
import com.xueyituanchina.xueyituan.mpbe.event.PushTaskEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.UserTaskListPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.UserTaskListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/12/5.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserTaskListActivity extends CommonToolBarActivity {

    private UserTaskListAdapter mAdapter;
    private UserTaskListPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_tasklist;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mPresenter = new UserTaskListPresenter(this);
        mPresenter.taskList();
        mAdapter = new UserTaskListAdapter(null);
        EventBus.getDefault().register(this);
        mRecyclerView.setAdapter(mAdapter);
        rootView.findViewById(R.id.tvCopyTo).setOnClickListener(v -> {
            ToastUtils.init().showSuccessToast(this, "已成功复制客服微信");
            ClipboardManager tvCopy = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            tvCopy.setText("xueyika01");
            Observable.timer(500, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
                getWechatApi();
            });
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            UserTaskListBean.ListBean listBean = mAdapter.getData().get(position);
            String status = listBean.status;
            if ("0".equals(status) || "2".equals(status)) {
                Bundle bundle = new Bundle();
                bundle.putString("rules", rules);
                bundle.putString("id", listBean.id + "");
                ActivityUtils.init().start(this, TaskPushActivity.class, "提交审核", bundle);
            }
            return false;
        });
    }

    @Subscribe
    public void onEvent(PushTaskEvent event) {
        mPresenter.taskList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.taskList();
    }

    private String rules;

    public void taskList(UserTaskListBean bean) {
        rules = bean.rule;
        mSmartRefreshLayout.finishRefresh();
        mAdapter.setNewData(bean.list);
    }

    /**
     * 跳转到微信
     */
    private void getWechatApi() {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.init().showQuickToast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }
}
