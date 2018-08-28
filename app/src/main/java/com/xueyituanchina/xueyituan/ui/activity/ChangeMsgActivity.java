package com.xueyituanchina.xueyituan.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2018/8/28.
 * 修改 信息
 */

public class ChangeMsgActivity extends CommonToolBarActivity {
    @BindView(R.id.editText01)
    EditText editText01;
    @BindView(R.id.editText02)
    EditText editText02;
    @BindView(R.id.viewLine2)
    View viewLine2;
    private Unbinder unbinder;
    private String key;
    private String value;

    @Override
    public int initAddLayout() {
        return R.layout.activity_changemsg;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        unbinder = ButterKnife.bind(this, rootView);
        key = mBundle.getString("key");
        value = mBundle.getString("value");
        toolRightVisible(mTvToolRight, "保存");
        if (!key.contains("密码")) {
            viewLine2.setVisibility(View.INVISIBLE);
            editText02.setVisibility(View.INVISIBLE);
            editText01.setHint("请输入昵称");
            if (value != null && !"".equals(value)) {
                editText01.setText(value);
                editText01.setSelection(value.length());
            }
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    public void toolRightBtn(View v) {
        super.toolRightBtn(v);
        String preText = editText01.getText().toString();
        String atfText = editText02.getText().toString();
        if ("".equals(preText)) {
            ToastUtils.init().showQuickToast("请输入要修改的密码");
            return;
        }
        if (key.contains("密码") && !atfText.equals(preText)) {
            ToastUtils.init().showQuickToast("两次输入密码不一致");
            return;
        }
        if (value != null && !"".equals(value) && value.equals(preText)) {
            ToastUtils.init().showQuickToast("不可与上次相同");
            return;

        }
        EventBus.getDefault().post(new MessageEvent(preText, key));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
