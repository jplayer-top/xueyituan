package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.PointRecodeBean;

import java.util.List;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointRecodeAdapter extends BaseQuickAdapter<PointRecodeBean.OrderListBean, BaseViewHolder> {
    public PointRecodeAdapter(List<PointRecodeBean.OrderListBean> data) {
        super(R.layout.adapter_point_recode, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PointRecodeBean.OrderListBean item) {
        helper.setText(R.id.tvPointTitle, item.points_order_title)
                .setText(R.id.tvPoint, "" + item.points)
                .setText(R.id.tvTime, item.ct);
        ImageView ivThumbImg = helper.itemView.findViewById(R.id.ivThumbImg);
        Glide.with(mContext).load(R.mipmap.ic_launcher).into(ivThumbImg);
    }
}
