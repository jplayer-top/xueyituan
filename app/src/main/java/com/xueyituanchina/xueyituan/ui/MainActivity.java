package com.xueyituanchina.xueyituan.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.event.FileSelect;
import com.xueyituanchina.xueyituan.ui.fragment.AwardFragment;
import com.xueyituanchina.xueyituan.ui.fragment.HomeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.MeFragment;
import com.xueyituanchina.xueyituan.ui.fragment.NearbyFragment;
import com.xueyituanchina.xueyituan.ui.fragment.TaskSubmitFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.QuickNavigationBar;
import top.jplayer.baseprolibrary.utils.SharePreUtil;

public class MainActivity extends SuperBaseActivity {
    public MainActivity mMainActivity;
    public File mFile;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = aMapLocation -> {
        String lnglat = aMapLocation.getLongitude() + "," + aMapLocation.getLatitude();
        XYTApplication.lnglat = lnglat;
        SharePreUtil.saveData(this, "lnglat", lnglat);
        LogUtil.str(aMapLocation);
    };

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
        String lnglat = (String) SharePreUtil.getData(this, "lnglat", "");
        if ("".equals(lnglat)) {
            SharePreUtil.saveData(this, "lnglat", "115.9853071091,36.4570202778");
        }
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.ACCESS_COARSE_LOCATION)
                .onGranted(permissions -> {
                    if (null != mLocationClient) {
                        mLocationClient.setLocationOption(mLocationOption);
                        mLocationClient.stopLocation();
                        mLocationClient.startLocation();
                        mLocationClient.setLocationListener(mLocationListener);
                    }
                })
                .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                .start();
        isCheckKeyboard = false;
        initLocation();
    }

    private void initLocation() {
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(this);
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setInterval(60000);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
            mLocationClient.setLocationListener(mLocationListener);
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
        list.add(new QuickNavigationBar.NavihationInfo("首页", R.drawable.main_home, new AwardFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("商家", R.drawable.main_store, new HomeFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("发布", R.drawable.main_shop, new TaskSubmitFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("活动", R.drawable.main_act, new NearbyFragment()));
        list.add(new QuickNavigationBar.NavihationInfo("我的", R.drawable.main_me, new MeFragment()));
        return list;
    }

//    @Override
//    public void onMyLocationChanged(AMapLocationInfo aMapLocationInfo) {
//        String lnglat = aMapLocationInfo.getLng() + "," + aMapLocationInfo.getLat();
//        XYTApplication.lnglat = lnglat;
//        SharePreUtil.saveData(this, "lnglat", lnglat);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
    }

    public void setOnClick() {
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(permissions -> CameraUtil.getInstance().openSingalCamerNoCrop(this))
                .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                .start();
    }
}
