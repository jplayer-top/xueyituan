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

public class StoreItemAdapter extends BaseQuickAdapter<FavListBean.ListBean, BaseViewHolder> {
    public StoreItemAdapter(List<FavListBean.ListBean> o) {
        super(R.layout.adapter_store_fav, o);
    }

    @Override
    protected void convert(BaseViewHolder helper, FavListBean.ListBean item) {
        helper.setText(R.id.tvTitle, item.sp_name)
                .setText(R.id.tvLocalLen, "距离" + item.dist)
                .setRating(R.id.ratingBar, (float) (item.score / 10))
                .setText(R.id.tvLocal, item.sp_city + "  " + item.sp_area);
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        Glide.with(mContext).load(item.sp_img).apply(GlideUtils.init().options(R.drawable.placeholder)).into(ivSrc);
    }
}
