package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.event.PayOKStateEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.PayPresenter;
import com.xueyituanchina.xueyituan.wxapi.WXPayEntryActivity;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/10/20.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PayActivity extends CommonToolBarActivity {

    private PayPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_pay;
    }

    @BindView(R.id.tvPayMoney)
    TextView mTvPayMoney;
    @BindView(R.id.tvOrderId)
    TextView tvOrderId;
    @BindView(R.id.checkbox1)
    CheckBox mCheckbox1;
    @BindView(R.id.checkbox2)
    CheckBox mCheckbox2;
    @BindView(R.id.tv2Pay)
    TextView tv2Pay;
    @BindView(R.id.llPayOk)
    LinearLayout llPayOk;
    private Unbinder mUnbinder;

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mUnbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        final String orderId = mBundle.getString("orderId");
        String totalPrice = mBundle.getString("totalPrice");
        llPayOk.setVisibility(View.GONE);
        mTvPayMoney.setText(String.format(Locale.CHINA, "付款总额：%s", totalPrice));
        tvOrderId.setText(String.format(Locale.CHINA, "订单编号：%s", orderId));
        mCheckbox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox1.setChecked(isChecked);
            mCheckbox2.setChecked(!isChecked);
        });
        mCheckbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCheckbox2.setChecked(isChecked);
            mCheckbox1.setChecked(!isChecked);
        });
        mPresenter = new PayPresenter(this);

        tv2Pay.setOnClickListener(v -> {
            boolean checked = mCheckbox1.isChecked();
            if (checked) {
                mPresenter.payWxOrder(orderId);
            } else {

                mPresenter.payAliOrder(orderId);

            }
        });
    }

    IWXAPI api;

    // 检查微信是否安装
    private boolean checkWX() {
        boolean bErr = false;
        try {
            if (!api.isWXAppInstalled() || !api.isWXAppSupportAPI()) {
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

    private void payAliOk() {
        next4PayOk();
    }

    private void next4PayOk() {
        llPayOk.setVisibility(View.VISIBLE);
        EventBus.getDefault().post(new PayOKStateEvent());
    }

    @Subscribe
    public void payWxOk(WXPayEntryActivity.WxPayEvent event) {
        next4PayOk();
    }

    public void payAliOrder(AliPayInfoBean response) {
        Observable.just(response).subscribeOn(Schedulers.io())
                .map(aliPayInfoBean -> {
                    PayTask alipay = new PayTask(mActivity);
                    return alipay.payV2(response.orderStr, true);
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.get("resultStatus").equals("9000")) {
                        //支付成功
                        payAliOk();
                    } else if (result.get("resultStatus").equals("8000")) {
                        //支付处理中
                        ToastUtils.init().showErrorToast(mActivity, "支付处理中，请稍后");
                    } else {
                        ToastUtils.init().showErrorToast(mActivity, "订单支付失败");
                    }
                });
    }

    public void payWxOrder(WxPayInfoBean response) {
        WxPayInfoBean.OrderStrBean orderStrBean = response.orderStr;
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, response.orderStr.appid, true);
        }
        if (!checkWX()) {
            ToastUtils.init().showInfoToast(this, "您手机尚未安装微信，请安装后再登录");
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

    @Override

    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
