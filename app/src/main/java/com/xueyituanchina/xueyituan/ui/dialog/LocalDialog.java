package com.xueyituanchina.xueyituan.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.LocalBean;
import com.xueyituanchina.xueyituan.mpbe.model.LocalDaoModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;
import top.jplayer.baseprolibrary.widgets.dialog.BaseCustomDialog;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.ui.dialog
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LocalDialog extends BaseCustomDialog {

    private LocalDaoModel mDaoModel;
    @BindView(R.id.ivShopPic)
    ImageView mIvShopPic;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;
    @BindView(R.id.editName)
    EditText mEditName;
    @BindView(R.id.editPhone)
    EditText mEditPhone;
    @BindView(R.id.editDetailLocal)
    EditText mEditDetailLocal;
    @BindView(R.id.btnSure)
    Button mBtnSure;
    @BindView(R.id.ivCancel)
    ImageView mIvCancel;
    private LocalBean mLocalBean;
    private Unbinder mUnbinder;
    private int id;

    public LocalDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        mUnbinder = ButterKnife.bind(this, view);
        mDaoModel = new LocalDaoModel(view.getContext());
        mLocalBean = mDaoModel.querybeanById(1);
        List<LocalBean> localBeans = mDaoModel.queryAllbean();
        mIvCancel.setOnClickListener(v -> cancel());
        if (mLocalBean != null) {
            mEditName.setText(mLocalBean.getName());
            mEditName.setSelection(mLocalBean.getName().length());
            mEditPhone.setText(mLocalBean.getPhone());
            mEditDetailLocal.setText(mLocalBean.getLocal());
        }
    }

    public LocalDialog setTvTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public LocalDialog setPrice(String price) {
        mTvPrice.setText(price);
        return this;

    }

    public LocalDialog setGoodsId(int id) {
        this.id = id;
        return this;

    }

    public LocalDialog setIvShopPic(String url) {
        Glide.with(mIvShopPic.getContext()).load(url).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(mIvShopPic);
        return this;

    }

    @Override
    public void setSureListener(SureListener listener) {
        super.setSureListener(listener);
        LocalBean localBean = new LocalBean(1L, mEditName.getText().toString()
                , mEditPhone.getText().toString(),
                mEditDetailLocal.getText().toString());
        if (mLocalBean == null) {
            mDaoModel.insertUser(localBean);
        } else {
            mDaoModel.updatebean(localBean);
        }
        localBean.goods_id = this.id;
        EventBus.getDefault().post(localBean);
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_local;
    }

    @Override
    public int setSoftInputState() {
        return WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUnbinder.unbind();
    }

    @Override
    public void cancel() {
        KeyboardUtils.init().hideSoftInput((Activity) mWeakReference.get(), mContentView);
        Observable.interval(300, TimeUnit.MILLISECONDS).subscribe(aLong -> super.cancel());
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
        return top.jplayer.baseprolibrary.R.style.AnimBottom;
    }
}
