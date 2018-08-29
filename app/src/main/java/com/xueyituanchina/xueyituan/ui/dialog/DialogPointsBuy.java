package com.xueyituanchina.xueyituan.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.xueyituanchina.xueyituan.R;

import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Administrator on 2018/8/29.
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class DialogPointsBuy extends BaseCustomDialog {
    public DialogPointsBuy(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
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
        return R.layout.dialog_points_buy;
    }
}
