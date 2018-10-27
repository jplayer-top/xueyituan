package com.xueyituanchina.xueyituan.ui.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.DialogIssueEvent;

import org.greenrobot.eventbus.EventBus;

import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by PEO on 2017/2/24.
 * d
 */

public class OrderIssueDialog extends BaseCustomDialog {


    private EditText mEdInput;
    private RatingBar mRatingBar;
    private CheckBox mCheckbox;

    public OrderIssueDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        mEdInput = view.findViewById(R.id.et_input);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        mCheckbox = view.findViewById(R.id.checkbox);
        mRatingBar = view.findViewById(R.id.ratingBar);
        btnSubmit.setOnClickListener(v -> {
            if (StringUtils.init().isEmpty(mEdInput)) {
                ToastUtils.init().showInfoToast(getContext(), "请输入评论内容");
                return;
            }
            EventBus.getDefault().post(new DialogIssueEvent(mEdInput.getText().toString(), mRatingBar.getRating(),
                    mCheckbox.isChecked()));
            dismiss();
        });
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
    public int setWidth(int i) {
        return super.setWidth(10);
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_order_issue;
    }
}
