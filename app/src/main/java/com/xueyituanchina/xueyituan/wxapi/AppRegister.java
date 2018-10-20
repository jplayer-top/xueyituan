package com.xueyituanchina.xueyituan.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xueyituanchina.xueyituan.XYTApplication;

/**
 * Created by Obl on 2018/3/1.
 * com.modiwu.mah.wxapi
 */

public class AppRegister extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);

        // 将该app注册到微信
        api.registerApp(XYTApplication.APP_ID);
    }
}
