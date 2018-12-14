package com.xueyituanchina.xueyituan.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.event.BankInfoOkEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.UserSignPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserSignActivity extends CommonToolBarActivity {
    @BindView(R.id.tvName)
    EditText mTvName;
    @BindView(R.id.tvPhone)
    EditText mTvPhone;
    @BindView(R.id.tvId)
    EditText mTvId;
    @BindView(R.id.tvBankCard)
    EditText mTvBankCard;
    @BindView(R.id.tvPWD)
    EditText mTvPWD;
    @BindView(R.id.btnToTx)
    Button mBtnToTx;
    private Unbinder mBind;
    private UserSignPresenter mPresenter;
    private ArrayMap<String, String> mMap;

    @Override
    public int initAddLayout() {
        return R.layout.activity_user_sign;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBind = ButterKnife.bind(this);
        mPresenter = new UserSignPresenter(this);
        mMap = new ArrayMap<>();
        isCheckKeyboard = false;
        mTvPWD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lenLength(mTvPWD, s, 6, "您输入的密码已超出范围");
            }
        });
        mBtnToTx.setOnClickListener(v -> {
            if (StringUtils.init().isEmpty(mTvName)) {
                ToastUtils.init().showInfoToast(this, "请输入提款账户姓名");
                return;
            }
            if (StringUtils.init().isEmpty(mTvId)) {
                ToastUtils.init().showInfoToast(this, "请输入提款账户身份证号");
                return;
            }
            if (StringUtils.init().isEmpty(mTvPhone)) {
                ToastUtils.init().showInfoToast(this, "请输入提款账户预留手机号");
                return;
            }
            if (StringUtils.init().isEmpty(mTvBankCard)) {
                ToastUtils.init().showInfoToast(this, "请输入提款账户银行卡号");
                return;
            }
            boolean b = StringUtils.init().tipEditTextLength(mTvPWD, 5, 7);
            if (b) {
                ToastUtils.init().showInfoToast(this, "请六位数提现密码");
                return;
            }
            mMap.put("bank_card", mTvBankCard.getText().toString());
            mMap.put("bank_card_name", mTvName.getText().toString());
            mMap.put("bank_card_phone", mTvPhone.getText().toString());
            mMap.put("idcard", mTvId.getText().toString());
            mMap.put("pwd", mTvPWD.getText().toString());
            mPresenter.bankinfo(mMap);
        });
    }

    private void lenLength(EditText editText, Editable s, int i, String ms) {
        if (editText.hasFocus()) {
            if (s.length() > i) {
                ToastUtils.init().showInfoToast(this, ms);
                editText.setText(s.toString().substring(0, i));
                editText.setSelection(i);
            }
        }
    }

    public void bankinfo(BaseBean bean) {
        EventBus.getDefault().post(new BankInfoOkEvent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }


}
