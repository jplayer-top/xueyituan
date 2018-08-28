package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.presenter.SettingPresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

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

    @Override
    public int initAddLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this, rootView);
        String nick = mBundle.getString("nick");
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                mFile = new File(path);
            }
            new SettingPresenter(this).updateAvatar(mFile);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public void responseAvatar() {
        Glide.with(this).load(mFile).apply(GlideUtils.init().options(R.drawable.placeholder)).into(mIvMeAvatar);
    }
}
