package com.xueyituanchina.xueyituan.ui.activity;


import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;

import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by PEO on 2017/2/22.
 * 单聊界面
 */

public class ConversationOneActivity extends CommonToolBarActivity {


    @Override
    public int initAddLayout() {
        return R.layout.conversation;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mTvToolTitle.setText("客服");
    }
}
