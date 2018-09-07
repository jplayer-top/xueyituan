package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/7.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BrandInfoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BrandInfoAdapter(List<String> data) {
        super(R.layout.adapter_brand_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivBrandSrc = helper.itemView.findViewById(R.id.ivBrandSrc);
        Glide.with(mContext).load(item).apply(GlideUtils.init().options(R.drawable.placeholder)).into(ivBrandSrc);
    }
}
