package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.UserOrderListPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.UserOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserOrderListActivity extends CommonToolBarActivity {

    private UserOrderListPresenter mPresenter;
    private UserOrderListAdapter mAdapter;
    private ArrayList<MultiItemEntity> mEntities;

    @Override
    public int initAddLayout() {
        return R.layout.layout_refresh_white_nofoot;
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        mPresenter = new UserOrderListPresenter(this);
        mPresenter.bill();
        mAdapter = new UserOrderListAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void bill(UserOrderListBean bean) {

        generateData(bean.list);

        mAdapter.setNewData(mEntities);
    }

    private void generateData(List<UserOrderListBean.ListBean> listBeans) {
        if (mEntities != null) {
            mEntities.clear();
        } else {
            mEntities = new ArrayList<>();
        }
        Observable.fromIterable(listBeans).subscribe(listBean -> {
            mEntities.add(listBean);
            Observable.fromIterable(listBean.list).subscribe(reMendBean -> {
                mEntities.add(reMendBean);
            });
        });
    }

}
