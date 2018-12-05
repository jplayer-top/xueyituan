package com.xueyituanchina.xueyituan.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.UserTaskListBean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.DateUtils;

/**
 * Created by Obl on 2018/12/5.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserTaskListAdapter extends BaseQuickAdapter<UserTaskListBean.ListBean, BaseViewHolder> {
    public UserTaskListAdapter(List<UserTaskListBean.ListBean> data) {
        super(R.layout.adapter_task_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserTaskListBean.ListBean item) {
        helper.setText(R.id.tvType, item.cat_name)
                .setText(R.id.tvAwardTitle, item.goods_title)
                .setText(R.id.tvAwardShare, String.format(Locale.CHINA, "%s", getStatus(item.status)))
                .setText(R.id.tvAwardSubTitle, item.goods_subtitle)
                .setText(R.id.tvPushName, item.sp_name)
                .setText(R.id.tvPushNum, getDataSub(item.ct))
                .addOnClickListener(R.id.tvAwardShare);
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivPushAvatar);
        Glide.with(this.mContext).load(item.sp_img).into(ivAvatar);
    }

    private String getDataSub(String preDate) {

        try {
            int l = (int) ((new Date().getTime() - DateUtils.getDateByPattern(preDate, "yyyy-MM-dd " + "HH:mm:ss").getTime())) / 1000;
            int dayAll = 3600 * 24;
            int h = (dayAll - l) / 3600;
            int m = (dayAll - l) % 3600 / 60;
            return String.format(Locale.CHINA, "剩余%d时%d分", h, m);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "审核时间";
    }


    private String getStatus(String status) {
        if ("0".equals(status)) {
            return "提交审核";
        } else if ("1".equals(status)) {
            return "审核通过";
        } else if ("2".equals(status)) {
            return "重新提交";
        } else if ("3".equals(status)) {
            return "已过期";
        } else if ("4".equals(status)) {
            return "审核中";
        }
        return "已过期";
    }
}