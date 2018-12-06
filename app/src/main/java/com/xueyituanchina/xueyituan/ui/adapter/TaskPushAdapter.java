package com.xueyituanchina.xueyituan.ui.adapter;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskDestoryBean;

import java.util.List;
import java.util.Locale;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskPushAdapter extends BaseQuickAdapter<PushTaskDestoryBean.ListBean, BaseViewHolder> {
    public TaskPushAdapter(List<PushTaskDestoryBean.ListBean> data) {
        super(R.layout.adapter_tasks_destory, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PushTaskDestoryBean.ListBean item) {
        String status = item.task_status;
        helper.setText(R.id.tvAwardTitle, item.goods_title)
                .setText(R.id.tvType, item.cat_name)
                .setBackgroundRes(R.id.tvAwardShare, getStatusColor(status, item.is_on))
                .setText(R.id.tvAwardShare, String.format(Locale.CHINA, "%s", getStatus(status, item.is_on)))
                .setText(R.id.tvAwardSubTitle, item.goods_subtitle)
                .setText(R.id.tvPushName, item.sp_name)
                .setText(R.id.tvPushNum, String.format(Locale.CHINA, "剩余%d/总数%d", item.left_amount, item.amount));
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivPushAvatar);
        helper.itemView.findViewById(R.id.tvPushNum)
                .setVisibility("1".equals(status) && item.is_on ? View.VISIBLE : View.INVISIBLE);
        Glide.with(this.mContext).load(item.sp_img).into(ivAvatar);
    }

    public @DrawableRes
    int getStatusColor(String status, boolean isOn) {
        if ("0".equals(status)) {
            return R.drawable.shape_45_task0;
        } else if ("1".equals(status)) {
            return !isOn ? R.drawable.shape_45_task3 : R.drawable.shape_45_task0;
        } else if ("2".equals(status)) {
            return R.drawable.shape_45_task1;
        } else if ("3".equals(status)) {
            return R.drawable.shape_45_task2;
        }
        return R.drawable.shape_45_task1;
    }

    public String getStatus(String status, boolean isOn) {
        if ("0".equals(status)) {
            return "审核中";
        } else if ("1".equals(status)) {
            return !isOn ? "未上架" : "已上架";
        } else if ("2".equals(status)) {
            return "已驳回";
        } else if ("3".equals(status)) {
            return "已完成";
        }
        return "未上架";
    }

}
