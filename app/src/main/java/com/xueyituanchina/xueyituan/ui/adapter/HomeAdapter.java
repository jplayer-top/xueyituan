package com.xueyituanchina.xueyituan.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/8/16.
 * com.xueyituanchina.xueyituan.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeAdapter extends BaseQuickAdapter<HomeGoodsList.ListBean, BaseViewHolder> {


    public HomeAdapter(List<HomeGoodsList.ListBean> data) {
        super(R.layout.adapter_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsList.ListBean item) {
        helper.setVisible(R.id.tvIsGood, item.is_best)
                .setText(R.id.tvShopTitle, item.sp_name)
                .setRating(R.id.ratingBar, (float) item.score)
                .setText(R.id.tvLocal, item.sp_area);
        ImageView ivSrc = helper.itemView.findViewById(R.id.ivSrc);
        RecyclerView recyclerItem = helper.itemView.findViewById(R.id.recyclerItem);
        Glide.with(mContext).load(item.sp_img).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(ivSrc);

        recyclerItem.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        AdapterItemClass adapter = new AdapterItemClass(item.goodslist.subList(0, 2));
        int other = item.other;
        isShowFooter(adapter, other, item.goodslist);
        recyclerItem.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            ToastUtils.init().showQuickToast(position + "---");
        });
    }

    private void isShowFooter(AdapterItemClass adapter, int other, List<HomeGoodsList.ListBean.GoodslistBean> goodslist) {
        if (other > 0) {
            View footerView = View.inflate(mContext, R.layout.adapter_footer, null);
            TextView tvOtherGoods = footerView.findViewById(R.id.tvOtherGoods);
            footerView.setOnClickListener(v -> {
                adapter.setNewData(goodslist);
                adapter.removeAllFooterView();
            });
            tvOtherGoods.setText(String.format(Locale.CHINA, "查看其他%d个团购", other));
            adapter.setFooterView(footerView);
        }
    }
}
