package com.xueyituanchina.xueyituan.ui.adapter;

import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.SharePreUtil;

import static com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean.LEVEL_0;
import static com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean.LEVEL_1;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserOrderListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public UserOrderListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_0, R.layout.adapter_header_order);
        addItemType(LEVEL_1, R.layout.adapter_item_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case LEVEL_0:
                UserOrderListBean.ListBean listBean = (UserOrderListBean.ListBean) item;
                helper.setText(R.id.tvM, listBean.month)
                        .setText(R.id.tvMoney,
                                String.format(Locale.CHINA, "收入 ￥%s  支出 ￥%s", listBean.in, listBean.out));
                break;
            case LEVEL_1:
                UserOrderListBean.ListBean.ReMendBean itemBean = (UserOrderListBean.ListBean.ReMendBean) item;
                ImageView ivAvatar = helper.itemView.findViewById(R.id.ivAvatar);
                Glide.with(mContext).load(SharePreUtil.getData(mContext, "login_avatar", "")).into(ivAvatar);
                helper.setText(R.id.tvTime, itemBean.ct)
                        .setText(R.id.tvTitle, itemBean.remark)
                        .setTextColor(R.id.tvMoneyHow, "+".equals(itemBean.type) ? Color.parseColor("#EC7B62") : Color.parseColor("#1BCDCF"))
                        .setText(R.id.tvMoneyHow, String.format(Locale.CHINA, "%s%s", itemBean.type, itemBean.money));
                break;
        }
    }
}
