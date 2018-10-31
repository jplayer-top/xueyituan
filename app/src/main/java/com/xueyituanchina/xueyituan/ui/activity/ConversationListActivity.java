package com.xueyituanchina.xueyituan.ui.activity;


import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;

import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/8/3.
 * com.modiwu.mah.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ConversationListActivity extends CommonToolBarActivity {

    @Override
    public int initAddLayout() {
        return  R.layout.activity_conversation;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mTvToolTitle.setText("客服列表");
    }
}
