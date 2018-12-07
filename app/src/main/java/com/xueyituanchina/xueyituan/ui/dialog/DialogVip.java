package com.xueyituanchina.xueyituan.ui.dialog;

import android.content.Context;
import android.view.View;

import com.xueyituanchina.xueyituan.R;

import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogVip extends BaseCustomDialog {

    public DialogVip(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> cancel());
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(8);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_vip;
    }
}
