package com.xueyituanchina.xueyituan.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInviteBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.MyInvListPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.InvListAdapter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeInvActivity extends CommonToolBarActivity {
    @BindView(R.id.tvToInv)
    TextView mTvToInv;
    @BindView(R.id.tvInvCounts)
    TextView tvInvCounts;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Unbinder mBind;
    private InvListAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_me_inv;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        mTvToInv.setOnClickListener(v -> {
            ActivityUtils.init().start(this, MyShareActivity.class, "邀请好友");
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InvListAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        new MyInvListPresenter(this).inviteList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void inviteList(MyInviteBean bean) {
        mAdapter.setNewData(bean.invList);
        boolean b = bean.invList != null;
        String format = String.format(Locale.CHINA, "成功邀请%d人", b ? bean.invList.size() : 0);
        tvInvCounts.setText(format);
    }
}
