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

public class ActivityItemAdapter extends BaseQuickAdapter<FavListBean.ListBean, BaseViewHolder> {
    public ActivityItemAdapter(List<FavListBean.ListBean> o) {
        super(R.layout.adapter_activity_fav, o);
    }

    @Override
    protected void convert(BaseViewHolder helper, FavListBean.ListBean item) {
        helper.setText(R.id.tvTitle, item.title)
                .setText(R.id.tvOldPrice, item.sp_name);
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        Glide.with(mContext).load(item.poster).apply(GlideUtils.init().options(R.drawable.placeholder)).into(ivSrc);
    }
}
