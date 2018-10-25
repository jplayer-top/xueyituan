package com.xueyituanchina.xueyituan.ui.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;

import java.io.File;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by PEO on 2017/2/24.
 * d
 */

public class ShareShowDialog extends BaseCustomDialog {


    private TextView mTvTitle;
    private TextView mTvLocal;
    private TextView mTvTime;
    private TextView mTvDesc;
    private ImageView mIvSrc;
    private TextView mTvPhone;

    public ShareShowDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        mTvTitle = view.findViewById(R.id.tvTitle);
        mTvLocal = view.findViewById(R.id.tvLocal);
        mTvTime = view.findViewById(R.id.tvTime);
        mTvDesc = view.findViewById(R.id.tvDesc);
        mTvPhone = view.findViewById(R.id.tvPhone);
        mIvSrc = view.findViewById(R.id.ivSrc);
    }

    public ShareShowDialog setTvTitle(String string) {
        mTvTitle.setText(string);
        return this;
    }

    public ShareShowDialog setImg(File file) {
        Glide.with(getContext()).load(file).into(mIvSrc);
        return this;
    }

    public ShareShowDialog setTvDesc(String string) {
        mTvDesc.setText(string);
        return this;

    }

    public ShareShowDialog setTvLocal(String string) {
        mTvLocal.setText(String.format(Locale.CHINA, "地址：%s", string));
        return this;

    }

    public ShareShowDialog setTvPhone(String string) {
        mTvPhone.setText(String.format(Locale.CHINA, "电话：%s", string));
        return this;

    }

    public ShareShowDialog setTvTime(String start, String end) {
        mTvTime.setText(String.format(Locale.CHINA, "时间：%s 至 %s", start, end));
        return this;

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
    public int setHeight() {
        return ScreenUtils.getScreenHeight() - ScreenUtils.dp2px(100);

    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(10);
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_share_show;
    }
}
