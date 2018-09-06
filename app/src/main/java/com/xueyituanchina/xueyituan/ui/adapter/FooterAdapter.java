package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;

/**
 * Created by Obl on 2018/9/6.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class FooterAdapter extends BaseQuickAdapter<StoreBean.TeacherListBean, BaseViewHolder> {
    public FooterAdapter(List<StoreBean.TeacherListBean> data) {
        super(R.layout.adpter_footer_sotre, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean.TeacherListBean item) {
        helper.setText(R.id.tvTeachName, item.teacher_name)
                .setText(R.id.tvTeachType, item.direction)
                .setText(R.id.tvTeachTime, String.format(Locale.CHINA, "%d年教龄", item.years));
        ImageView ivTeachSrc = helper.itemView.findViewById(R.id.ivTeachSrc);
        Glide.with(mContext).load(item.teacher_avator).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivTeachSrc);
    }
}
