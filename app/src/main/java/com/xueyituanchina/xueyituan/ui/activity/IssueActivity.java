package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderIssueListBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.adapter.IssueAdapter;

import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/12.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class IssueActivity extends CommonToolBarActivity {

    private IssueAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_issue_list;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mAdapter = new IssueAdapter(null);
        getOrderIssueList();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getOrderIssueList() {
        new HomeModel(XYTServer.class).orderIssueList().subscribe(new NetCallBackObserver<OrderIssueListBean>() {
            @Override
            public void responseSuccess(OrderIssueListBean orderIssueListBean) {
                IssueActivity.this.responseSuccess();
                mAdapter.setNewData(orderIssueListBean.list);

            }

            @Override
            public void responseFail(OrderIssueListBean orderIssueListBean) {

            }
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        getOrderIssueList();
    }
}
