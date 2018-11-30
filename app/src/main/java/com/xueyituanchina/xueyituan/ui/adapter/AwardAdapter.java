package com.xueyituanchina.xueyituan.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;

import java.util.List;

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

    }
}
