package com.xueyituanchina.xueyituan.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.event.FileSelect;
import com.xueyituanchina.xueyituan.ui.fragment.GiftFragment;
import com.xueyituanchina.xueyituan.ui.fragment.HomeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.MeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.NearbyFragment;
import com.xueyituanchina.xueyituan.ui.fragment.ShareFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import io.rong.imkit.plugin.location.AMapLocationInfo;
import io.rong.imkit.plugin.location.IMyLocationChangedListener;
import io.rong.imkit.plugin.location.LocationManager;
import io.rong.imlib.model.Conversation;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.QuickNavigationBar;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

public class MainActivity extends SuperBaseActivity implements IMyLocationChangedListener {
    public MainActivity mMainActivity;
    private File mFile;

    @Override
    protected int initRootLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        mMainActivity = this;
        NavigationTabBar navigationBar = view.findViewById(R.id.navigationBar);
        new QuickNavigationBar(this)
                .idRes(R.id.flRoot)
                .dataList(initBarList())
                .create(navigationBar);
        LocationManager.getInstance().bindConversation(mActivity, Conversation.ConversationType.PRIVATE, "10000");
        LocationManager.getInstance().setMyLocationChangedListener(this);
        String lnglat = (String) SharePreUtil.getData(this, "lnglat", "");
        if ("".equals(lnglat)) {
            SharePreUtil.saveData(this, "lnglat", "115.9853071091,36.4570202778");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                mFile = new File(path);
            }
            EventBus.getDefault().post(new FileSelect(mFile));
        }
    }

    /**
     * 设置底部栏数据图片
     *
     * @return list
     */
    @NonNull
    private List<QuickNavigationBar.NavihationInfo> initBarList() {
        List<QuickNavigationBar.NavihationInfo> list = new ArrayList<>();
        list.add(new QuickNavigationBar.NavihationInfo("首页", R.drawable.main_home, new HomeFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("活动", R.drawable.main_act, new NearbyFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("商城", R.drawable.main_shop, new GiftFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("发布", R.drawable.main_send, new ShareFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("我的", R.drawable.main_me, new MeFragment()));
        return list;
    }

    @Override
    public void onMyLocationChanged(AMapLocationInfo aMapLocationInfo) {
        String lnglat = aMapLocationInfo.getLng() + "," + aMapLocationInfo.getLat();
        XYTApplication.lnglat = lnglat;
        SharePreUtil.saveData(this, "lnglat", lnglat);
    }

    public void setOnClick() {
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(permissions -> CameraUtil.getInstance().openSingalCamerNoCrop(this))
                .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                .start();
    }
}
