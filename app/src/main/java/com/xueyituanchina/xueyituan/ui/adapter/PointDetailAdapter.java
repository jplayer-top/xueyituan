package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.PointDetailBean;

import java.util.List;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointDetailAdapter extends BaseQuickAdapter<PointDetailBean.OrderListBean, BaseViewHolder> {
    public PointDetailAdapter(List<PointDetailBean.OrderListBean> data) {
        super(R.layout.adapter_point_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PointDetailBean.OrderListBean item) {
        helper.setText(R.id.tvType, item.remark)
                .setText(R.id.tvPoint, item.type + item.changed)
                .setText(R.id.tvTime, item.ct);
        ImageView ivPointInOut = helper.itemView.findViewById(R.id.ivPointInOut);
        Glide.with(mContext).load("-".equals(item.type) ? R.drawable.point_out : R.drawable.point_in).into(ivPointInOut);
    }
}
