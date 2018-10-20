package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShopItemBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.ShopPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.ShopAdapter;

import java.util.List;
import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

/**
 * Created by Obl on 2018/10/19.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopItemActivity extends CommonToolBarActivity {

    private ShopPresenter mPresenter;
    private String mId;
    private Button mBtnPay;
    private ShopAdapter mAdapter;
    private View mHeader;
    private BGABanner mBgaBanner;
    private TextView mTvShopName;
    private TextView mTvShopSubName;
    private TextView mTvNewPrice;
    private TextView mTvOldPrice;
    private TextView mTvWasPay;
    private TextView mTvStoreName;
    private TextView mTvStoreLocal;
    private TextView mTvStoreLocalLen;
    private View mFooter;
    private TextView mTvChatTip;

    @Override
    public int initAddLayout() {
        return R.layout.activity_shopitem;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mAdapter = new ShopAdapter(null);
        mPresenter = new ShopPresenter(this);
        mRecyclerView.setAdapter(mAdapter);
        mId = mBundle.getString("id");
        mPresenter.shopInfo(mId);
        showLoading();
        initHeaderView();
        initFooterView();
        mBtnPay = rootView.findViewById(R.id.btnPay);
        mBtnPay.setOnClickListener(v -> {
            String login_phone = (String) SharePreUtil.getData(this, "login_phone", "");
            mPresenter.createOrder(mId, "1", login_phone);
        });
    }

    private void initFooterView() {
        mFooter = View.inflate(this, R.layout.layout_footer_shop, null);
        mTvChatTip = mFooter.findViewById(R.id.tvChatTip);
        mAdapter.addFooterView(mFooter);
    }

    private void initHeaderView() {
        mHeader = View.inflate(this, R.layout.layout_header_shop, null);
        mAdapter.addHeaderView(mHeader);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);
        mTvShopName = mHeader.findViewById(R.id.tvShopName);
        mTvShopSubName = mHeader.findViewById(R.id.tvShopSubTitle);
        mTvNewPrice = mHeader.findViewById(R.id.tvNewPrice);
        mTvOldPrice = mHeader.findViewById(R.id.tvOldPrice);
        mTvWasPay = mHeader.findViewById(R.id.tvWasPay);
        mTvStoreName = mHeader.findViewById(R.id.tvStoreName);
        mTvStoreLocal = mHeader.findViewById(R.id.tvStoreLocal);
        mTvStoreLocalLen = mHeader.findViewById(R.id.tvStoreLocalLen);
    }

    public void shopInfo(ShopItemBean bean) {
        mAdapter.setNewData(bean.goods.goods_desc_img);
        initBanner(bean.goods.goods_thumb_img);
        initHeaderData(bean);
    }

    private void initHeaderData(ShopItemBean bean) {
        ShopItemBean.GoodsBean goodsBean = bean.goods;
        ShopItemBean.ShopBean shopBean = bean.shop;
        mTvShopName.setText(goodsBean.goods_title);
        mTvShopSubName.setText(goodsBean.goods_desc);
        mTvNewPrice.setText(goodsBean.goodsBestPriceStr);
        mTvStoreLocal.setText(shopBean.addr);
        mTvStoreName.setText(shopBean.sp_name);
        mTvStoreLocalLen.setText("暂无");
        mTvOldPrice.setText(String.format(Locale.CHINA, "门市价：%s", goodsBean.goodsOrgPriceStr));
        mTvChatTip.setText(String.format(Locale.CHINA, "用户评论（%d）", bean.commentsList.size()));
        mTvWasPay.setText(String.format(Locale.CHINA, "已售 %d", goodsBean.sales));
        mHeader.findViewById(R.id.ivToCall).setOnClickListener(v -> dialPhoneNumber(shopBean.phone));
        mBtnPay.setText(String.format(Locale.CHINA, "%s元试课", goodsBean.goodsBestPriceStr));
    }

    private void initBanner(List<String> bean) {
        mBgaBanner.setAdapter((banner, itemView, model, position) -> {

            Glide.with(mActivity).load(model)
                    .apply(GlideUtils.init().options(R.drawable.placeholder))
                    .into((ImageView) itemView);
        });
        mBgaBanner.setData(bean, null);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.shopInfo(mId);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void createOrder(OrderBean orderBean) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderBean.orderId);
        bundle.putString("totalPrice", orderBean.totalPrice);
        bundle.putString("title", mTvToolTitle.getText().toString());
        bundle.putString("price", mTvNewPrice.getText().toString());
        ActivityUtils.init().start(this, OrderActivity.class, "提交订单", bundle);
    }
}
