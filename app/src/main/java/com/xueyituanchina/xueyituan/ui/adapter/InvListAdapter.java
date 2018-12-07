package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInviteBean;

import java.util.List;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class InvListAdapter extends BaseQuickAdapter<MyInviteBean.InvListBean, BaseViewHolder> {
    public InvListAdapter(List<MyInviteBean.InvListBean> data) {
        super(R.layout.adapter_inv_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInviteBean.InvListBean item) {
        helper.setText(R.id.tvName, item.nick)
                .setText(R.id.tvType, item.status == 0 ? "已注册" : "成为VIP");
        ImageView imageView = helper.itemView.findViewById(R.id.ivAvatar);
        Glide.with(mContext).load(item.avatar).into(imageView);
    }
}
