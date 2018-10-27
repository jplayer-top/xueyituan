package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderIssueListBean;

import java.util.List;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class IssueAdapter extends BaseQuickAdapter<OrderIssueListBean.ListBean, BaseViewHolder> {
    public IssueAdapter(List<OrderIssueListBean.ListBean> o) {
        super(R.layout.adapter_issue, o);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderIssueListBean.ListBean item) {
        helper.setText(R.id.tvName, XYTApplication.login_name)
                .setText(R.id.tvTime, item.ct)
                .setText(R.id.tvDesc, item.descd)
                .setText(R.id.tvSpName, item.sp_name)
                .setText(R.id.tvGoodsName, item.goods_title)
                .setRating(R.id.ratingBar, (float) (item.score))
                .setText(R.id.tvCat, "课程分类：" + item.cat_name);
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        Glide.with(mContext).load(item.goods_thumb_img).apply(GlideUtils.init().options(R.drawable.placeholder)).into(ivSrc);
    }
}
