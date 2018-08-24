package com.xueyituanchina.xueyituan.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;

import java.util.List;
import java.util.Locale;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

class AdapterItemClass extends BaseQuickAdapter<HomeGoodsList.ListBean.GoodslistBean, BaseViewHolder> {
    public AdapterItemClass(List<HomeGoodsList.ListBean.GoodslistBean> data) {
        super(R.layout.adapter_item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsList.ListBean.GoodslistBean item) {
        helper.setText(R.id.tvGoodsName, item.goods_title)
                .setText(R.id.tvGift, item.goods_subtitle)
                .setText(R.id.tvGoodsPrice, String.format(Locale.CHINA, "￥%s", item.goodsBestPriceStr))
                .setText(R.id.tvLocalGoodsPrice, String.format(Locale.CHINA, "门市价：￥%s", item.goodsOrgPriceStr));

    }
}
