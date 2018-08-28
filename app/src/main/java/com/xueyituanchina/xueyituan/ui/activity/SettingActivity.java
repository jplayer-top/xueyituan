package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.MessageEvent;
import com.xueyituanchina.xueyituan.mpbe.event.MessageOkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.SettingPresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.dialog.DialogEdit;
import top.jplayer.baseprolibrary.ui.dialog.DialogLogout;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/8/28.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SettingActivity extends CommonToolBarActivity {

    @BindView(R.id.ivMeAvatar)
    ImageView mIvMeAvatar;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.tvPhone)
    TextView mTvPhone;
    @BindView(R.id.tvPassword)
    TextView mTvPasswrod;
    @BindView(R.id.tvPoint)
    TextView mTvPoint;
    @BindView(R.id.btnLogout)
    Button mBtnLogout;
    private Unbinder mUnbinder;
    private File mFile;
    private SettingPresenter mPresenter;
    private String nick;
    private String opw;

    @Override
    public int initAddLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this, rootView);
        mPresenter = new SettingPresenter(this);
        EventBus.getDefault().register(this);
        nick = mBundle.getString("nick");
        String points = mBundle.getString("points");
        String avatar = mBundle.getString("avatar");
        mIvMeAvatar.setOnClickListener(v -> {
            AndPermission.with(this)
                    .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                    .onGranted(permissions -> CameraUtil.getInstance().openSingalCamer(this.mActivity))
                    .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                    .start();
        });
        Glide.with(this).load(avatar).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(mIvMeAvatar);
        mTvName.setText(nick);
        mTvPoint.setText(points);
        String phone = (String) SharePreUtil.getData(this, "login_phone", "");
        mTvPhone.setText(phone);
        mBtnLogout.setOnClickListener(v -> {
            new DialogLogout(this).setSubTitle("退出后将无法享受优质服务\n确认退出吗?").show(R.id.btnSure, view -> {
//                mPresenter.logout();
            });
        });
        mTvName.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("key", "昵称");
            bundle.putString("value", nick);
            ActivityUtils.init().start(this, ChangeMsgActivity.class, "修改昵称", bundle);
        });
        mTvPasswrod.setOnClickListener(view -> {
            new DialogEdit(this)
                    .setSubTitle("请输入原密码")
                    .show(R.id.btnSure, view1 -> {
                        EditText editText = (EditText) view1;
                        opw = editText.getText().toString();
                        mPresenter.verifyPw(opw);
                    });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                mFile = new File(path);
            }
            mPresenter.updateAvatar(mFile);
        }
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        if ("昵称".equals(event.key)) {
            mPresenter.updateNick(event.preText);
        } else {
            mPresenter.updatePw(opw, event.preText);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void responseAvatar() {
        Glide.with(this).load(mFile).apply(GlideUtils.init().options(R.drawable.placeholder)).into(mIvMeAvatar);
        EventBus.getDefault().post(new MessageOkEvent());
    }

    public void responseNick(String nick) {
        mTvName.setText(nick);
        EventBus.getDefault().post(new MessageOkEvent());
    }

    public void verifyPw(String pw) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "密码");
        bundle.putString("value", pw);
        ActivityUtils.init().start(this, ChangeMsgActivity.class, "修改密码", bundle);
    }

    public void updatePw() {

    }
}
