package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class NearbyAdapter extends BaseQuickAdapter<NearbyActiveBean.ListBean, BaseViewHolder> {
    public NearbyAdapter(List<NearbyActiveBean.ListBean> listBeans) {
        super(R.layout.adapter_nearby, listBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyActiveBean.ListBean item) {
        helper.setText(R.id.tvNearbyTitle, item.title)
                .setText(R.id.tvWhoSend, item.sub_title)
                .setText(R.id.tvWhoLook, String.format(Locale.CHINA, "%d人看过", item.visitor));
        ImageView ivNearbySrc = helper.itemView.findViewById(R.id.ivNearbySrc);
        Glide.with(mContext)
                .load(item.poster)
                .apply(GlideUtils.init().options(R.drawable.placeholder))
                .into(ivNearbySrc);
    }
}
