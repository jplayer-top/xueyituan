package com.xueyituanchina.xueyituan.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xueyituanchina.xueyituan.ui.fragment.ActivityItemFragment;
import com.xueyituanchina.xueyituan.ui.fragment.ShopItemFragment;
import com.xueyituanchina.xueyituan.ui.fragment.StoreItemFragment;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerFragmentAdapter;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AdapterPagerTab extends BaseViewPagerFragmentAdapter<String> {
    public AdapterPagerTab(FragmentManager supportFragmentManager, ArrayList<String> strings) {
        super(supportFragmentManager, strings);
        mFragmentList = new ArrayList<>();

        mFragmentList.add(new StoreItemFragment());
        mFragmentList.add(new ShopItemFragment());
        mFragmentList.add(new ActivityItemFragment());

    }

    public List<SuperBaseFragment> mFragmentList = null;


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}