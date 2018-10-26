package com.xueyituanchina.xueyituan.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.xueyituanchina.xueyituan.R;

import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Administrator on 2018/8/29.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogPayBuyTest extends BaseCustomDialog {

    private EditText mEditPhone;

    public DialogPayBuyTest(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        mEditPhone = view.findViewById(R.id.editPhone);
        String login_phone = (String) SharePreUtil.getData(getContext(), "login_phone", "");
        if (login_phone != null && !"".equals(login_phone)) {
            mEditPhone.setText(login_phone);
        }
    }

    @Override
    public void setSureListener(SureListener listener) {
        listener.onSure(mEditPhone);
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(10);
    }

    @Override
    public int setGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int setAnim() {
        return R.style.AnimBottom;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_pay_buy_test;
    }
}
