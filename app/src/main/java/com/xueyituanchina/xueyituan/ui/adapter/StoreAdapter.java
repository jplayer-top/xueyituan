package com.xueyituanchina.xueyituan.ui.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/5.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreAdapter extends BaseQuickAdapter<StoreBean.GoodsListBean, BaseViewHolder> {
    public StoreAdapter(List<StoreBean.GoodsListBean> data) {
        super(R.layout.adapter_store, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean.GoodsListBean item) {
        TextView tvLocalGoodsPrice = helper.itemView.findViewById(R.id.tvOldPrice);
        tvLocalGoodsPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.tvTitle, item.goods_title)
                .setText(R.id.tvSubTitle, item.goods_subtitle)
                .setText(R.id.tvBuyNum,  String.format(Locale.CHINA, "已售出（%d）", item.sales))
                .setText(R.id.tvNewPrice, String.format(Locale.CHINA, "￥%s", item.goodsBestPriceStr))
                .setText(R.id.tvOldPrice, String.format(Locale.CHINA, "门市价 %s", item.goodsOrgPriceStr));
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivShopSrc);
        Glide.with(mContext).load(item.goods_thumb_img).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivSrc);
    }
}
