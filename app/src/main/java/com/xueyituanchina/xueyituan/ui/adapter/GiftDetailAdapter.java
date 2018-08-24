package com.xueyituanchina.xueyituan.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ScreenUtils;

/**
 * Created by Obl on 2018/8/23.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class GiftDetailAdapter extends BaseQuickAdapter<GiftDetailBean.GoodsListBean, BaseViewHolder> {
    public GiftDetailAdapter(List<GiftDetailBean.GoodsListBean> data) {
        super(R.layout.adapter_gift, data);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = super.onCreateDefViewHolder(parent, viewType);
        ImageView ivSrc = holder.itemView.findViewById(R.id.ivSrc);
        int i = ScreenUtils.getWidthOfScreen(mContext, 30, 2);
        ivSrc.getLayoutParams().width = i;
        ivSrc.getLayoutParams().height = i;
        return holder;
    }

    @Override
    protected void convert(BaseViewHolder helper, GiftDetailBean.GoodsListBean item) {
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        Glide.with(ivSrc).load(item.thumb_img).into(ivSrc);
        helper.setText(R.id.tvTip, item.title)
                .setText(R.id.tvPrice, String.format(Locale.CHINA, "%d", item.points));
    }
}
