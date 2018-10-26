package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.FavListBean;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopItemAdapter extends BaseQuickAdapter<FavListBean.ListBean, BaseViewHolder> {
    public ShopItemAdapter(List<FavListBean.ListBean> o) {
        super(R.layout.adapter_shop_fav, o);
    }

    @Override
    protected void convert(BaseViewHolder helper, FavListBean.ListBean item) {
        helper.setText(R.id.tvTitle, item.goods_title)
                .setText(R.id.tvLocalLen, item.dist)
                .setText(R.id.tvNewPrice, item.goods_best_price)
                .setText(R.id.tvSubTitle, item.goods_subtitle)
                .setText(R.id.tvOldPrice, item.goods_org_price);
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        Glide.with(mContext).load(item.goods_thumb_img).apply(GlideUtils.init().options(R.drawable.placeholder)).into(ivSrc);
    }
}
