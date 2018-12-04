package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;

import java.util.List;
import java.util.Locale;

/**
 * Created by Obl on 2018/11/30.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardAdapter extends BaseQuickAdapter<AwardBean.TaskListBean, BaseViewHolder> {
    public AwardAdapter(List<AwardBean.TaskListBean> data) {
        super(R.layout.adapter_task, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AwardBean.TaskListBean item) {
        helper.setText(R.id.tvType, item.cat_name)
                .setText(R.id.tvAwardTitle, item.goods_title)
                .setText(R.id.tvAwardSubTitle, item.goods_subtitle)
                .setText(R.id.tvPushName, item.sp_name)
                .setBackgroundRes(R.id.tvAwardShare,item.shared?R.drawable.shape_45_gray:R.drawable.award_share)
                .setText(R.id.tvPushNum, String.format(Locale.CHINA, "已分享%d人", item.amount))
                .addOnClickListener(R.id.tvAwardShare);
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivPushAvatar);
        Glide.with(this.mContext).load(item.sp_img).into(ivAvatar);
    }
}
