package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.event.PayPasInputEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.ApplySignPresenter;
import com.xueyituanchina.xueyituan.ui.dialog.DialogApply;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TXActivity extends CommonToolBarActivity {
    @BindView(R.id.tvCanTx)
    TextView mTvCanTx;
    @BindView(R.id.tvBank)
    TextView mTvBank;
    @BindView(R.id.tvTxMoney)
    EditText mTvTxMoney;
    @BindView(R.id.btnToTx)
    Button mBtnToTx;
    private Unbinder mBind;
    private String mCantx;
    private ApplySignPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_tx;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        String bank = mBundle.getString("bank");
        EventBus.getDefault().register(this);
        mPresenter = new ApplySignPresenter(this);
        mCantx = mBundle.getString("cantx");
        if (bank != null) {
            String bankStr = bank.substring(0, 5) + "**********" + bank.substring(bank.length() - 4, bank.length());
            mTvBank.setText(bankStr);
            mTvCanTx.setText(mCantx);
        }
        mBtnToTx.setOnClickListener(v -> {
            toTx();
        });
    }

    private void toTx() {
        String string = mTvTxMoney.getText().toString();
        if ("".equals(string)) {
            ToastUtils.init().showInfoToast(this, "请输入兑换金额");
            return;
        }
        if (Float.valueOf(string) > Float.valueOf(mCantx)) {
            ToastUtils.init().showInfoToast(this, "超出可兑换金额");
            return;
        }
        if (Float.valueOf(string) < 100 || Float.valueOf(string) % 100 != 0) {
            ToastUtils.init().showInfoToast(this, "奖学金兑换金额需要≥100，且为100的整数倍");
            return;
        }
        if (!XYTApplication.isVip) {
            ToastUtils.init().showInfoToast(this, "成为会员即可兑换");
            return;
        }
        new DialogApply(this).setMoney(string).show();
//        mPresenter.applySign("");
    }

    @Subscribe

    public void onEvent(PayPasInputEvent event) {
        mPresenter.applySign(event.pw);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void signOk() {
        mPresenter.apply(mTvTxMoney.getText().toString());
    }
}
