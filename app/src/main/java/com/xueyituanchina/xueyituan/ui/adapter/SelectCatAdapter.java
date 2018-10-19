package com.xueyituanchina.xueyituan.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;

import java.util.ArrayList;

/**
 * Created by Obl on 2018/9/11.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SelectCatAdapter extends BaseQuickAdapter<HomeListBean.SendListBean, BaseViewHolder> {
    public SelectCatAdapter(ArrayList<HomeListBean.SendListBean> strings) {
        super(R.layout.adapter_local_set, strings);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean.SendListBean item) {
        helper.setText(R.id.tvLocalName, item.name);
    }
}
