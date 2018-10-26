package com.xueyituanchina.xueyituan.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.event.AliPayOkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.RechargePresenter;
import com.xueyituanchina.xueyituan.wxapi.WXPayEntryActivity;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class RechargeActivity extends CommonToolBarActivity {

    @BindView(R.id.checkbox1)
    CheckBox mCheckbox1;
    @BindView(R.id.checkbox2)
    CheckBox mCheckbox2;
    @BindView(R.id.tv2Pay)
    TextView mTv2Pay;
    @BindView(R.id.tvRecharge)
    TextView mTvRecharge;
    private Unbinder mUnbinder;
    private String mRecharge;
    private RechargePresenter mPresenter;
    private boolean mIsVip;

    @Override
    public int initAddLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this);
        mRecharge = mBundle.getString("recharge");
        EventBus.getDefault().register(this);
        String string = mTvToolTitle.getText().toString();
        mIsVip = string.contains("会员");
        mTvRecharge.setText(mRecharge);
        mCheckbox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox1.setChecked(isChecked);
            mCheckbox2.setChecked(!isChecked);
        });
        mCheckbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox2.setChecked(isChecked);
            mCheckbox1.setChecked(!isChecked);
        });
        mPresenter = new RechargePresenter(this);
        mTv2Pay.setOnClickListener(v -> {
            boolean checked = mCheckbox1.isChecked();
            if (checked) {
                if (mIsVip) {
                    mPresenter.wxPay(mRecharge);
                } else {
                    mPresenter.wxPayShop(mRecharge);
                }
            } else {
                if (mIsVip) {
                    mPresenter.aliPay(mRecharge);
                } else {
                    mPresenter.aliPayShop(mRecharge);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyboardUtils.init().hideSoftInput(this);
    }

    IWXAPI api;

    public void wxPay(WxPayInfoBean response) {
        WxPayInfoBean.OrderStrBean orderStrBean = response.orderStr;
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, response.orderStr.appid, true);
        }
        if (!checkWX()) {
            ToastUtils.init().showQuickToast(this, "您手机尚未安装微信，请安装后再登录");
            return;
        }
        api.registerApp(response.orderStr.appid);
        PayReq request = new PayReq();
        request.appId = orderStrBean.appid;
        request.partnerId = orderStrBean.partnerid;
        request.prepayId = orderStrBean.prepayid;
        request.packageValue = orderStrBean.packageX;
        request.nonceStr = orderStrBean.noncestr;
        request.timeStamp = orderStrBean.timestamp;
        request.sign = orderStrBean.sign;
        api.sendReq(request);
    }

    // 检查微信是否安装
    private boolean checkWX() {
        boolean bErr = false;
        try {
            if (!api.isWXAppInstalled()) {
                bErr = true;
            }
        } catch (Exception e) {
            bErr = true;
            e.printStackTrace();
        }
        if (!bErr) {
            return true;
        }
        return false;
    }

    public void aliPay(final AliPayInfoBean response) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(response.orderStr, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Subscribe
    public void wxPayOk(WXPayEntryActivity.WxPayEvent event) {
        ToastUtils.init().showSuccessToast(this, "充值成功");
        finish();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            Map<String, String> result = (Map<String, String>) msg.obj;
            if (result.get("resultStatus").equals("9000")) {
                //支付成功
                orderPayOk();
            } else if (result.get("resultStatus").equals("8000")) {
                //支付处理中
                ToastUtils.init().showErrorToast(mActivity, "支付处理中，请稍后");
            } else {
                ToastUtils.init().showErrorToast(mActivity, "订单支付失败");
            }
        }
    };

    private void orderPayOk() {
        payOk();
    }

    private void payOk() {
        EventBus.getDefault().post(new AliPayOkEvent());
        ToastUtils.init().showSuccessToast(this, "充值成功");
        finish();
    }

    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


}