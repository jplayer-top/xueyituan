package com.xueyituanchina.xueyituan.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.FileSelect;
import com.xueyituanchina.xueyituan.mpbe.presenter.SharePresenter;
import com.xueyituanchina.xueyituan.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.ui.fragment.SuperBaseFragment;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.ShSwitchView;

/**
 * Created by Obl on 2018/8/24.
 * com.xueyituanchina.xueyituan.ui.fragment
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShareFragment extends SuperBaseFragment {
    @BindView(R.id.tvToolTitle)
    TextView mTvToolTitle;
    @BindView(R.id.tvToolRightLeft)
    TextView mTvToolRightLeft;
    @BindView(R.id.tvToolRight)
    TextView mTvToolRight;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ivBigSrc)
    ImageView mIvBigSrc;
    @BindView(R.id.switchView)
    ShSwitchView mSwitchView;
    @BindView(R.id.edTitle)
    EditText mEdTitle;
    @BindView(R.id.edStartTime)
    EditText mEdStartTime;
    @BindView(R.id.edEndTime)
    EditText mEdEndTime;
    @BindView(R.id.edLocal)
    EditText mEdLocal;
    @BindView(R.id.edDesc)
    EditText mEdDesc;
    @BindView(R.id.edPhone)
    EditText mEdPhone;
    @BindView(R.id.tvAdPlan)
    TextView tvAdPlan;
    @BindView(R.id.tvUserAgent)
    TextView tvUserAgent;
    private Unbinder mUnbinder;
    private SharePresenter mPresenter;
    private File mFile;

    @Override
    public int initLayout() {
        return R.layout.fragment_share;
    }

    @Override
    protected void initData(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
        initImmersionBar();
        mPresenter = new SharePresenter(this);
        EventBus.getDefault().register(this);
        mIvBigSrc.setOnClickListener(v -> {
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.setOnClick();
            }
        });
        mSwitchView.setOn(true);
        mTvToolRight.setOnClickListener(v -> {
            if (mFile == null) {
                ToastUtils.init().showQuickToast(mActivity, "请选择活动海报");
                return;
            }
            if (StringUtils.init().isEmpty(mEdTitle)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入活动标题");
                return;
            }
            if (StringUtils.init().isEmpty(mEdStartTime)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入活动开始时间");
                return;
            }
            if (StringUtils.init().isEmpty(mEdEndTime)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入活动结束时间");
                return;
            }
            if (StringUtils.init().isEmpty(mEdLocal)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入活动举办地址");
                return;
            }
            if (StringUtils.init().isEmpty(mEdPhone)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入手机号码");
                return;
            }
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("title", mEdTitle.getText().toString())
                    .addFormDataPart("description", mEdDesc.getText().toString())
                    .addFormDataPart("beginTime", mEdStartTime.getText().toString())
                    .addFormDataPart("endTime", mEdEndTime.getText().toString())
                    .addFormDataPart("addr", mEdLocal.getText().toString())
                    .addFormDataPart("plan", mSwitchView.isOn() ? "1" : "0")
                    .addFormDataPart("poster", mFile.getName(), RequestBody.create(MediaType.parse("image/*"), mFile));
            RequestBody requestBody = builder.build();
            mPresenter.pubActivity(requestBody);
        });
        tvAdPlan.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.xueyituanchina.cn/info/adplan.html");
            ActivityUtils.init().start(mActivity, WebViewActivity.class, "", bundle);
        });
        tvUserAgent.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.xueyituanchina.cn/info/useagreement.html");
            ActivityUtils.init().start(mActivity, WebViewActivity.class, "", bundle);
        });
    }

    @Subscribe

    public void onEvent(FileSelect event) {
        mFile = event.mFile;
        Glide.with(mActivity).load(mFile).into(mIvBigSrc);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.statusBarShare).init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void pubSuccess() {

    }
}
