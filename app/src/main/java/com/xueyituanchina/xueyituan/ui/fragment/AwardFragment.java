package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.event.ShareAwardAllEvent;
import com.xueyituanchina.xueyituan.mpbe.event.ShareAwardOneEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.AwardPresenter;
import com.xueyituanchina.xueyituan.ui.activity.AwardActivity;
import com.xueyituanchina.xueyituan.ui.activity.MyShareActivity;
import com.xueyituanchina.xueyituan.ui.adapter.AwardAdapter;
import com.xueyituanchina.xueyituan.ui.dialog.ShareAwardDialog;
import com.xueyituanchina.xueyituan.wxapi.WXShare;
import com.xueyituanchina.xueyituan.wxapi.WXShareBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;
import java.util.Locale;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.BitmapUtil;
import top.jplayer.baseprolibrary.utils.FileUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/11/30.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardFragment extends SuperBaseFragment {

    private AwardAdapter mAdapter;
    private View mHeader;
    private BGABanner mBgaBanner;
    private AwardPresenter mPresenter;
    private ViewFlipper mViewFlipper;
    private TextView mTvCounts;
    private TextView mTvNums;
    private TextView mTvAwardAll;
    private ShareAwardDialog mAwardDialog;

    @Override
    public int initLayout() {
        return R.layout.fragment_award;
    }

    @Override
    protected void initData(View rootView) {
        initImmersionBar();
        initRefreshStatusView(rootView);
        EventBus.getDefault().register(this);
        mAdapter = new AwardAdapter(null);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (mAdapter.getData().get(position).shared) {
                ToastUtils.init().showInfoToast(mActivity, "该任务已经分享过");
                return false;
            }
            mAwardDialog = new ShareAwardDialog(mActivity);
            mAwardDialog.show();
            cPos = position;
            return false;
        });
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new AwardPresenter(this);
        mPresenter.awardList();
        initHeader();
    }

    public int cPos = 0;

    @Subscribe
    public void onEvent(ShareAwardOneEvent event) {
        Observable.just(0).subscribeOn(Schedulers.io()).map(integer -> {
            File file = Glide.with(mActivity).asFile().load(mAdapter.getData().get(cPos)
                    .share_img).submit().get();
            return FileUtil.copyFile(file, mActivity.getExternalCacheDir(), "award.png");
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(file ->
                new WXShare(mActivity).shareImage(file.getAbsolutePath(),
                        BitmapUtil.adjustBitmap(file.getAbsolutePath()),
                        SendMessageToWX.Req.WXSceneSession));
    }

    @Subscribe
    public void onEvent(ShareAwardAllEvent event) {
        Observable.just(0).subscribeOn(Schedulers.io()).map(integer -> {
            File file = Glide.with(mActivity).asFile().load(mAdapter.getData().get(cPos)
                    .share_img).submit().get();
            return FileUtil.copyFile(file, mActivity.getExternalCacheDir(), "award.png");
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(file ->
                new WXShare(mActivity).shareImage(file.getAbsolutePath(),
                        BitmapUtil.adjustBitmap(file.getAbsolutePath()),
                        SendMessageToWX.Req.WXSceneTimeline));
    }

    @Subscribe
    public void onEvent(WXShareBean event) {
        mPresenter.shareOk(mAdapter.getData().get(cPos).task_id);
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.awardList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initHeader() {
        mHeader = View.inflate(getContext(), R.layout.layout_header_award, null);
        mAdapter.addHeaderView(mHeader);
        mViewFlipper = mHeader.findViewById(R.id.viewFlipper);
        mBgaBanner = mHeader.findViewById(R.id.bgaBanner);
        mTvCounts = mHeader.findViewById(R.id.tvCounts);
        mTvNums = mHeader.findViewById(R.id.tvNums);
        mTvAwardAll = mHeader.findViewById(R.id.tvAwardAll);
        mHeader.findViewById(R.id.tvShareInvit).setOnClickListener(v -> {
            ActivityUtils.init().start(mActivity, MyShareActivity.class, "邀请好友");
        });
        mTvAwardAll.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("beans", mAwardBean.taskList);
            ActivityUtils.init().start(mActivity, AwardActivity.class, "全部任务", bundle);
        });
    }

    private void initViewFlipper(List<String> broadMsgList) {
        Observable.fromIterable(broadMsgList).subscribe(str -> mViewFlipper.addView(getTextImageView(str)));
    }

    private View getTextImageView(String string) {
        View view = View.inflate(getContext(), R.layout.layout_marquee_text, null);
        TextView tvTitle = view.findViewById(R.id.tvTip);
        tvTitle.setText(string);
        return view;
    }

    private void initBanner(AwardBean bean) {
        if (bean.bannerList != null) {
            mBgaBanner.setAdapter((banner, itemView, model, position) -> {
                AwardBean.BannerBean bannerBean = (AwardBean.BannerBean) model;
                if (model != null) {
                    Glide.with(mActivity).load(((AwardBean.BannerBean) model).img)
                            .apply(GlideUtils.init().options(R.drawable.placeholder))
                            .into((ImageView) itemView);
                    itemView.setOnClickListener(v -> {
                        startWeb(bannerBean.url);
                    });
                }

            });
            mBgaBanner.setData(bean.bannerList, null);
        }
    }

    private void startWeb(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        ActivityUtils.init().start(getActivity(), WebViewActivity.class, "", bundle);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarAward).init();
    }

    private AwardBean mAwardBean;

    public void awardList(AwardBean bean) {
        mSmartRefreshLayout.finishRefresh();
        mMultipleStatusView.showContent();
        mAwardBean = bean;
        List<AwardBean.TaskListBean> taskList = bean.taskList;
        initBanner(bean);
        if (taskList != null) {
            mAdapter.setNewData(taskList.subList(0, taskList.size() > 5 ? 5 : taskList.size()));
        }
        initViewFlipper(bean.broadMsgList);
        mTvNums.setText(String.format(Locale.CHINA, "%d", bean.taskNum));
        mTvCounts.setText(String.format(Locale.CHINA, "%d", bean.totalShared));
    }

    public void shareOk(BaseBean bean) {
        mPresenter.awardList();
    }
}
