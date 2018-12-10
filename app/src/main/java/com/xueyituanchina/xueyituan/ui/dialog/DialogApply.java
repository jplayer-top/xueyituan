package com.xueyituanchina.xueyituan.ui.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.PayPasInputEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;
import top.jplayer.baseprolibrary.widgets.PayPsdInputView;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogApply extends BaseCustomDialog {

    private PayPsdInputView mEditPassword;
    private TextView mTvMoney;

    public DialogApply(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> cancel());
        mEditPassword = (PayPsdInputView) view.findViewById(R.id.editPassword);
        mEditPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6) {
                    KeyboardUtils.init().hideSoftInput(getOwnerActivity());
                    Observable.timer(300, TimeUnit.MILLISECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
                        EventBus.getDefault().post(new PayPasInputEvent(s.toString()));
                        dismiss();
                    });
                }
            }
        });
        mTvMoney = view.findViewById(R.id.tvMoney);
    }

    public DialogApply setMoney(String money) {
        mTvMoney.setText(String.format(Locale.CHINA, "￥%s", money));
        return this;
    }

    @Override
    public int setSoftInputState() {
        return WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(9);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_apply;
    }
}
