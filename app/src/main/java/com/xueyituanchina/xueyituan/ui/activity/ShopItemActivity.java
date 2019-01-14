package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.bean.HasIssueBean;
import com.xueyituanchina.xueyituan.mpbe.bean.LookWhatBean;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShopItemBean;
import com.xueyituanchina.xueyituan.mpbe.event.ShareAllEvent;
import com.xueyituanchina.xueyituan.mpbe.event.ShareOneEvent;
import com.xueyituanchina.xueyituan.mpbe.event.ShareOtherEvent;
import com.xueyituanchina.xueyituan.mpbe.model.LoookWhatDaoModel;
import com.xueyituanchina.xueyituan.mpbe.presenter.ShopPresenter;
import com.xueyituanchina.xueyituan.ui.adapter.ShopAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.ShareDialog;
import com.xueyituanchina.xueyituan.wxapi.WXShare;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.dialog.DialogLogout;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

import static com.xueyituanchina.xueyituan.XYTApplication.assert2Login;
import static top.jplayer.baseprolibrary.BaseInitApplication.getContext;

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
    private TextView mTvGiftTip;
    private WXShare mWxShare;
    private boolean keep;
    private LoookWhatDaoModel mDaoModel;
    private LookWhatBean mWhatBean;
    private TextView mTvScore;

    @Override
    public int initAddLayout() {
        return R.layout.activity_shopitem;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        EventBus.getDefault().register(this);
        mAdapter = new ShopAdapter(null);
        mPresenter = new ShopPresenter(this);
        mRecyclerView.setAdapter(mAdapter);
        mId = mBundle.getString("id");
        mPresenter.shopInfo(mId);
        initHeaderView();
        initFooterView();
        mBtnPay = rootView.findViewById(R.id.btnPay);

        toolRightVisible(mIvToolRight, R.drawable.collection);
        mIvToolRightLeft.setImageResource(R.drawable.share);
        mWxShare = new WXShare(this);
        findViewById(R.id.tvChat).setOnClickListener(v -> {
            if (assert2Login(mActivity)) {
                ToastUtils.init().showQuickToast("请联系客服电话:0635-8091618");
//                RongIM.getInstance().startConversation(mActivity, Conversation.ConversationType.PRIVATE,
//                        "u_" + XYTApplication.cuid, "客服");
            }
        });
        findViewById(R.id.tvXYTCall).setOnClickListener(v -> {
            dialPhoneNumber("0635-8091618");
        });
        mDaoModel = new LoookWhatDaoModel(this);
        List<LookWhatBean> lookWhatBeans = mDaoModel.queryAllbean();

        for (LookWhatBean lookWhatBean : lookWhatBeans) {
            if (mId.equals(lookWhatBean.goods_id + "")) {
                mWhatBean = lookWhatBean;
                break;
            }
            mWhatBean = null;
        }
    }

    @Override
    public void toolRightLeft(int isVisible, View.OnClickListener listener) {
        super.toolRightLeft(View.VISIBLE, v -> {
            EventBus.getDefault().post(new ShareOtherEvent());
            new ShareDialog(mActivity).show();
        });
    }

    @Override
    public void toolRightBtn(View v) {
        super.toolRightBtn(v);
        if (this.keep) {
            mPresenter.favUnKeep("2", mId);
        } else {
            mPresenter.favKeep("2", mId);
        }
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
        mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTvWasPay = mHeader.findViewById(R.id.tvWasPay);
        mTvStoreName = mHeader.findViewById(R.id.tvStoreName);
        mTvStoreLocal = mHeader.findViewById(R.id.tvStoreLocal);
        mTvStoreLocalLen = mHeader.findViewById(R.id.tvStoreLocalLen);
        mTvGiftTip = mHeader.findViewById(R.id.tvGiftTip);
        mTvScore = mHeader.findViewById(R.id.tvScore);
    }

    public void shopInfo(ShopItemBean bean) {
        this.keep = bean.keep;
        ShopItemBean.GoodsBean goods = bean.goods;
        if (mWhatBean == null) {
            mWhatBean = new LookWhatBean(null,
                    goods.goods_id,
                    goods.goods_title,
                    goods.goods_thumb_img != null && goods.goods_thumb_img.size() > 0 ? goods.goods_thumb_img.get(0) : "",
                    goods.goods_subtitle,
                    goods.goodsBestPriceStr,
                    goods.goodsOrgPriceStr,
                    "");
            mDaoModel.insertUser(mWhatBean);
        }

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
        mTvGiftTip.setText(goodsBean.goods_subtitle);
        mTvStoreName.setText(shopBean.sp_name);
        mTvStoreLocalLen.setText("暂无");
        mTvOldPrice.setText(String.format(Locale.CHINA, "门市价：%s", goodsBean.goodsOrgPriceStr));
        mTvChatTip.setText(String.format(Locale.CHINA, "用户评论（%d）", bean.commentsList.size()));
        mTvScore.setText(String.format(Locale.CHINA, "%s分", bean.goods.score + ""));
        mTvChatTip.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Gson gson = new Gson();
            String toJson = gson.toJson(bean.commentsList);
            ArrayList<HasIssueBean> json = gson.fromJson(toJson, new TypeToken<List<HasIssueBean>>() {
            }.getType());
            bundle.putParcelableArrayList("issue", json);
            ActivityUtils.init().start(this, IssueHasListActivity.class, "课程评价", bundle);
        });
        mTvWasPay.setText(String.format(Locale.CHINA, "已售 %d", goodsBean.sales));
        mHeader.findViewById(R.id.ivToCall).setOnClickListener(v -> dialPhoneNumber(shopBean.phone));
        mBtnPay.setText(String.format(Locale.CHINA, "%s元换购", goodsBean.goodsBestPriceStr));

        if (XYTApplication.isVip && goodsBean.vip == 1) {
            mBtnPay.setText("会员0元专享");
            mBtnPay.setOnClickListener(v -> {
                DialogLogout dialogLogout = new DialogLogout(mActivity);
                dialogLogout
                        .setTitle("提示")
                        .setSubTitle("是否确定购买会员0元专享课程")
                        .show(R.id.btnSure, view1 -> {
                            String login_phone = (String) SharePreUtil.getData(this, "login_phone", "");
                            mPresenter.createOrder(mId, "1", login_phone, true);
                            dialogLogout.dismiss();
                        });
            });
        } else {
            mBtnPay.setOnClickListener(v -> {
                String login_phone = (String) SharePreUtil.getData(this, "login_phone", "");
                mPresenter.createOrder(mId, "1", login_phone);
            });
        }

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

    @Subscribe
    public void shareEvent(ShareOneEvent event) {
        if (mWxShare.checkWX()) {
            String lnglat = (String) SharePreUtil.getData(this, "lnglat", "");
            if ("".equals(lnglat)) {
                lnglat = "115.9853071091,36.4570202778";
            }
            String url = String.format(Locale.CHINA, "https://www.xueyituanchina.cn/info/shop_item" +
                            ".html?id=%s&lnglat=%s",
                    mId, lnglat);
            Bitmap thumb = BitmapFactory
                    .decodeResource(getResources(), R.mipmap.ic_launcher);
            mWxShare.shareUrl(url, mTvToolTitle.getText().toString(), thumb,
                    mTvShopSubName.getText().toString(), SendMessageToWX.Req.WXSceneSession);
        } else {
            ToastUtils.init().showInfoToast(getContext(), "请先安装微信");
        }
    }

    @Subscribe
    public void shareEvent(ShareAllEvent event) {
        if (mWxShare.checkWX()) {
            String lnglat = (String) SharePreUtil.getData(this, "lnglat", "");
            if ("".equals(lnglat)) {
                lnglat = "115.9853071091,36.4570202778";
            }
            String url = String.format(Locale.CHINA, "https://www.xueyituanchina.cn/info/shop_item" +
                            ".html?id=%s&lnglat=%s",
                    mId, lnglat);
            Bitmap thumb = BitmapFactory
                    .decodeResource(getResources(), R.mipmap.ic_launcher);
            mWxShare.shareUrl(url, mTvToolTitle.getText().toString(), thumb,
                    mTvShopSubName.getText().toString(), SendMessageToWX.Req.WXSceneTimeline);
        } else {
            ToastUtils.init().showInfoToast(getContext(), "请先安装微信");
        }
    }

    public void vipPay() {
        ToastUtils.init().showSuccessToast(this, "已成功购买会员0元专享课程");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void createOrder(OrderBean orderBean, boolean isVip) {
        mPresenter.vipPay(orderBean.orderId);
    }

    public void createOrder(OrderBean orderBean) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderBean.orderId);
        bundle.putString("totalPrice", orderBean.totalPrice);
        bundle.putString("title", mTvToolTitle.getText().toString());
        bundle.putString("price", mTvNewPrice.getText().toString());
        ActivityUtils.init().start(this, OrderPrePayActivity.class, "订单信息", bundle);
    }


}
