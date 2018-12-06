package com.xueyituanchina.xueyituan.ui.adapter;

import android.support.annotation.DrawableRes;
import android.view.View;
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
        String status = item.status;

        helper.setText(R.id.tvType, item.cat_name)
                .setText(R.id.tvAwardTitle, item.goods_title)
                .setBackgroundRes(R.id.tvAwardShare, getStatusColor(status))
                .setText(R.id.tvAwardShare, String.format(Locale.CHINA, "%s", getStatus(status)))
                .setText(R.id.tvAwardSubTitle, item.goods_subtitle)
                .setText(R.id.tvPushName, item.sp_name)
                .setText(R.id.tvPushNum, getDataSub(item.ct, status))
                .addOnClickListener(R.id.tvAwardShare);
        ImageView ivAvatar = helper.itemView.findViewById(R.id.ivPushAvatar);
        helper.itemView.findViewById(R.id.tvPushNum)
                .setVisibility("0".equals(status) || "2".equals(status) ? View.VISIBLE : View.INVISIBLE);
        Glide.with(this.mContext).load(item.sp_img).into(ivAvatar);
    }

    private String getDataSub(String preDate, String status) {
        if ("0".equals(status) || "2".equals(status)) {
            try {
                int l = (int) ((new Date().getTime() - DateUtils.getDateByPattern(preDate, "yyyy-MM-dd " + "HH:mm:ss").getTime())) / 1000;
                int dayAll = 3600 * 24;
                int h = (dayAll - l) / 3600;
                int m = (dayAll - l) % 3600 / 60;
                if (h < 0) {
                    return "";
                }
                return String.format(Locale.CHINA, "剩余%d时%d分", h, m);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return "";
    }


    public String getStatus(String status) {
        if ("0".equals(status)) {
            return "上传审核";
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

    public @DrawableRes
    int getStatusColor(String status) {
        if ("0".equals(status)) {
            return R.drawable.shape_45_task0;
        } else if ("1".equals(status)) {
            return R.drawable.shape_45_task2;
        } else if ("2".equals(status)) {
            return R.drawable.shape_45_task1;
        } else if ("3".equals(status)) {
            return R.drawable.shape_45_task3;
        } else if ("4".equals(status)) {
            return R.drawable.shape_45_task1;
        }
        return R.drawable.shape_45_task1;
    }
}
