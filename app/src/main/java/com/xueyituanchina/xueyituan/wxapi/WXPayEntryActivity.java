package com.xueyituanchina.xueyituan.wxapi;

import android.content.Intent;
import android.view.View;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xueyituanchina.xueyituan.XYTApplication;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.ui.activity.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ToastUtils;

public class WXPayEntryActivity extends SuperBaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                EventBus.getDefault().post(new WxPayEvent());
                finish();
            } else {
                ToastUtils.init().showErrorToast(this, "订单支付失败");
                finish();
            }
        }

    }


    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        api = WXAPIFactory.createWXAPI(this, XYTApplication.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected int initRootLayout() {
        return top.jplayer.baseprolibrary.R.layout.fragment_base;
    }

    public class WxPayEvent {
    }
}