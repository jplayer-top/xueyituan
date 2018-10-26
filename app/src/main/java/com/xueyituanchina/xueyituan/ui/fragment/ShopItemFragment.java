package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.FavListBean;
import com.xueyituanchina.xueyituan.mpbe.model.HomeModel;
import com.xueyituanchina.xueyituan.ui.activity.ShopItemActivity;
import com.xueyituanchina.xueyituan.ui.adapter.ShopItemAdapter;

import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopItemFragment extends SuperBaseFragment {

    private ShopItemAdapter mAdapter;
    private String mLnglat;

    @Override
    public int initLayout() {
        return R.layout.fragment_item_fav;
    }

    @Override
    protected void initData(View rootView) {
        mLnglat = (String) SharePreUtil.getData(mActivity, "lnglat", "115.98530,36.45702");
        initRefreshStatusView(rootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new ShopItemAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        loaddingFav();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            FavListBean.ListBean listBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("id", listBean.goods_id + "");
            ActivityUtils.init().start(mActivity, ShopItemActivity.class, listBean.goods_title, bundle);
        });
    }

    private void loaddingFav() {
        new HomeModel(XYTServer.class)
                .favList("2", mLnglat)
                .compose(new IoMainSchedule<>())
                .subscribe(new NetCallBackObserver<FavListBean>() {
                    @Override
                    public void responseSuccess(FavListBean favListBean) {
                        ShopItemFragment.this.responseSuccess();
                        mAdapter.setNewData(favListBean.list);
                    }

                    @Override
                    public void responseFail(FavListBean favListBean) {

                    }
                });
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        loaddingFav();
    }
}
