package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/8/28.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeOrderAdapter extends BaseQuickAdapter<MyInfoBean.OrderListBean, BaseViewHolder> {
    public MeOrderAdapter(List<MyInfoBean.OrderListBean> data) {
        super(R.layout.adapter_me_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInfoBean.OrderListBean item) {
        ImageView ivOrderSrc = helper.itemView.findViewById(R.id.ivOrderSrc);
        Glide.with(mContext).load(item.goods_thumb_img)
                .apply(GlideUtils.init().options(R.mipmap.ic_launcher))
                .into(ivOrderSrc);
        helper.setText(R.id.tvPrice, String.format(Locale.CHINA, "￥%s", item.totalpricestr))
                .setText(R.id.tvShopName, item.sp_name)
                .setText(R.id.tvOrderName, item.order_title);
    }
}
