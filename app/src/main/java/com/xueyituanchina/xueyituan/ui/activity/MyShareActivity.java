package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInviteBean;
import com.xueyituanchina.xueyituan.mpbe.event.ShareAllEvent;
import com.xueyituanchina.xueyituan.mpbe.event.ShareOneEvent;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;
import com.xueyituanchina.xueyituan.ui.dialog.ShareDialog;
import com.xueyituanchina.xueyituan.wxapi.WXShare;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.DialogLoading;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.adapter.BaseViewPagerViewAdapter;
import top.jplayer.baseprolibrary.utils.BitmapUtil;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.CardTransformer;

/**
 * Created by Obl on 2018/11/20.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MyShareActivity extends CommonToolBarActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private Unbinder mUnbinder;
    private ImageView mIvShareSrc;
    private String mInvImg;
    private String mName;
    private String mInvUrl;
    private Bitmap mBitmap;
    private String mSaveBitmap;
    private DialogLoading mLoading;
    private View mView;
    private ImageView mIvXuanZhe;

    @Override
    public int initAddLayout() {
        return R.layout.activity_my_share;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this);
        toolRightVisible(mTvToolRight, "分享");
        EventBus.getDefault().register(this);
        if (mBundle != null) {
            mName = mBundle.getString("name");
            mInvImg = mBundle.getString("invImg");
            mInvUrl = mBundle.getString("invUrl");
            initViewPager(null);

        } else {
            getInfo();
        }
    }

    private void initViewPager(MyInviteBean bean) {
        if (bean != null) {
            mName = bean.name;
            mInvImg = bean.invImg;
            mInvUrl = bean.invUrl;
        }
        List<String> strings = new ArrayList<>();
        strings.add(mName);
        strings.add(mName);
        mViewPager.setOffscreenPageLimit(strings.size());
        mViewPager.setPageTransformer(true, new CardTransformer(35));
        mViewPager.setAdapter(new BaseViewPagerViewAdapter<String>(strings, R.layout.layout_share_pager) {
            @Override
            public void bindView(View view, String s, int position) {
                mIvShareSrc = view.findViewById(R.id.ivShareSrc);
                mIvXuanZhe = view.findViewById(R.id.ivXuanZhe);
                TextView tvName = view.findViewById(R.id.tvName);
                tvName.setText(String.format(Locale.CHINA, "%s", s));
                ImageView ivQCode = view.findViewById(R.id.ivQCode);
                String textContent = mInvUrl;
                if (TextUtils.isEmpty(textContent)) {
                    ToastUtils.init().showQuickToast(textContent);
                    return;
                }
                int px = ScreenUtils.dp2px(100);
                Bitmap bitmap = CodeUtils.createImage(textContent, px, px, null);
                ivQCode.setImageBitmap(bitmap);

                if (position == 0) {
                    mIvXuanZhe.setVisibility(View.GONE);
                    Glide.with(mActivity).load(mInvImg).into(mIvShareSrc);
                } else {
                    mIvXuanZhe.setVisibility(View.VISIBLE);
                    mIvShareSrc.setOnClickListener(v -> {
                        AndPermission.with(mActivity)
                                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                                .onGranted(permissions -> CameraUtil.getInstance().openSingalCamer(mActivity, 540, 540))
                                .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                                .start();
                    });
                }
            }
        });
    }

    private void getInfo() {
        new MeModel(XYTServer.class).inviteList().subscribe(new NetCallBackObserver<MyInviteBean>() {
            @Override
            public void responseSuccess(MyInviteBean bean) {
                inviteList(bean);
            }

            @Override
            public void responseFail(MyInviteBean bean) {

            }
        });
    }

    private void inviteList(MyInviteBean bean) {
        initViewPager(bean);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            ImageView view = mViewPager.getChildAt(mViewPager.getCurrentItem()).findViewById(R.id.ivShareSrc);
            Glide.with(mActivity).load(pathList.get(0)).into(view);
            mIvXuanZhe.setVisibility(View.GONE);
        }
    }

    @Override
    public void toolRightBtn(View v) {
        super.toolRightBtn(v);
        mLoading = new DialogLoading(this);
        mLoading.show();
        mView = mViewPager.getChildAt(mViewPager.getCurrentItem()).findViewById(R.id.clSharePic);
        Observable.just(1).subscribeOn(Schedulers.io())
                .map(aLong -> {
                    mBitmap = BitmapUtil.screenShotView(mView);
                    mSaveBitmap = BitmapUtil.saveBitmap(mBitmap);
                    return aLong;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong
                        -> {
                    if (mLoading != null) {
                        mLoading.dismiss();
                        new ShareDialog(mActivity).show();
                    }
                });
    }

    @Subscribe
    public void shareEvent(ShareOneEvent event) {
        if (mSaveBitmap != null && mBitmap != null) {
            new WXShare(this).shareImage(mSaveBitmap, mBitmap, SendMessageToWX.Req.WXSceneSession);
        }
    }

    @Subscribe
    public void shareEvent(ShareAllEvent event) {
        if (mSaveBitmap != null && mBitmap != null) {
            new WXShare(this).shareImage(mSaveBitmap, mBitmap, SendMessageToWX.Req.WXSceneTimeline);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}

