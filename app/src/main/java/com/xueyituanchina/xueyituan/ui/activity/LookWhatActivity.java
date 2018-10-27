package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.LookWhatBean;
import com.xueyituanchina.xueyituan.mpbe.model.LoookWhatDaoModel;
import com.xueyituanchina.xueyituan.ui.adapter.LookWhatAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LookWhatActivity extends CommonToolBarActivity {

    private LoookWhatDaoModel mDaoModel;
    private LookWhatAdapter mLookWhatAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_look_what;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mDaoModel = new LoookWhatDaoModel(this);
        List<LookWhatBean> lookWhatBeans = mDaoModel.queryAllbean();
        mLookWhatAdapter = new LookWhatAdapter(lookWhatBeans);
        mRecyclerView.setAdapter(mLookWhatAdapter);
        mLookWhatAdapter.setOnItemClickListener((adapter, view, position) -> {
            LookWhatBean listBean = mLookWhatAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.goods_id + "");
            ActivityUtils.init().start(mActivity, ShopItemActivity.class, listBean.goods_title, bundle);
        });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
            LookWhatActivity.this.responseSuccess();
        });
    }
}
