package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.XYTApplication;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.event.ApplyEvent;
import com.xueyituanchina.xueyituan.mpbe.model.MeModel;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.NetCallBackObserver;
import top.jplayer.baseprolibrary.net.tip.PostImplTip;
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

    @Override
    public int initAddLayout() {
        return R.layout.activity_tx;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        String bank = mBundle.getString("bank");
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
            ToastUtils.init().showInfoToast(this, "请输入提现金额");
            return;
        }
        if (Float.valueOf(string) > Float.valueOf(mCantx)) {
            ToastUtils.init().showInfoToast(this, "超出可提现金额");
            return;
        }
        if (Float.valueOf(string) < 200) {
            ToastUtils.init().showInfoToast(this, "提现金额需大于200元");
            return;
        }
        if (!XYTApplication.isVip) {
            ToastUtils.init().showInfoToast(this, "成为会员，可以提现");
            return;
        }
        new MeModel(XYTServer.class).apply(string).subscribe(new NetCallBackObserver<BaseBean>(new
                PostImplTip(this)) {
            @Override
            public void responseSuccess(BaseBean bean) {
                EventBus.getDefault().post(new ApplyEvent());
                Observable.timer(1, TimeUnit.SECONDS).subscribe(a -> finish());
            }

            @Override
            public void responseFail(BaseBean bean) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
