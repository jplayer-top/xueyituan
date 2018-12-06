package com.xueyituanchina.xueyituan.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;

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
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Unbinder mBind;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
