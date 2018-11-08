package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreInfoBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/8/28.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreOrderAdapter extends BaseQuickAdapter<StoreInfoBean.OrderListBean, BaseViewHolder> {
    public StoreOrderAdapter(List<StoreInfoBean.OrderListBean> data) {
        super(R.layout.adapter_store_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreInfoBean.OrderListBean item) {
        ImageView ivOrderSrc = helper.itemView.findViewById(R.id.ivOrderSrc);
        Glide.with(mContext).load(item.thumbImg)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivOrderSrc);
        helper.setText(R.id.tvPrice, String.format(Locale.CHINA, "￥%s", item.priceStr))
                .setText(R.id.tvShopName, item.order_title)
                .setText(R.id.tvOrderPhone, String.format(Locale.CHINA, "家长电话：%s", item.rp_phone))
                .setText(R.id.tvOrderStatus, String.format(Locale.CHINA, "支付状态：%s", item.pay_status == 1 ? "已支付" :
                        "未支付"));
    }
}
