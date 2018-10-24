package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.presenter.ShopCreatePresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/10/22.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopCreateActivity extends CommonToolBarActivity {
    @BindView(R.id.edSpName)
    EditText mEdSpName;
    @BindView(R.id.edSpLocal)
    TextView mEdSpLocal;
    @BindView(R.id.edSpAddr)
    EditText mEdSpAddr;
    @BindView(R.id.edSpUserName)
    EditText mEdSpUserName;
    @BindView(R.id.edSpPhone)
    EditText mEdSpPhone;
    @BindView(R.id.ivShopLic)
    ImageView mIvShopLic;
    @BindView(R.id.ivShopImg)
    ImageView mIvShopImg;
    @BindView(R.id.btnCreate)
    Button mBtnCreate;
    @BindView(R.id.tvInfo)
    TextView mTvInfo;
    private Unbinder mBind;
    private int isLic = -1;
    private File fileLic;
    private File fileImg;
    private ShopCreatePresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_shop_create;
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
        mIvShopLic.setOnClickListener(v -> {
            setOnClick(0);
        });
        mIvShopImg.setOnClickListener(v -> {
            setOnClick(1);
        });
        mPresenter = new ShopCreatePresenter(this);
        mPresenter.areaLocal();
        mPresenter.area();
        mBtnCreate.setOnClickListener(v -> {
            if (fileLic == null) {
                ToastUtils.init().showQuickToast(mActivity, "请上传营业执照");
                return;
            }
            if (fileImg == null) {
                ToastUtils.init().showQuickToast(mActivity, "请上传店铺照片");
                return;
            }
            if (StringUtils.init().isEmpty(mEdSpName)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入店铺名称");
                return;
            }
            if (StringUtils.init().isEmpty(mEdSpAddr)) {
                ToastUtils.init().showQuickToast(mActivity, "请选择店铺地址");
                return;
            }
            if (StringUtils.init().isEmpty(mEdSpAddr)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入店铺详细地址");
                return;
            }
            if (StringUtils.init().isEmpty(mEdSpUserName)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入法人姓名");
                return;
            }
            if (StringUtils.init().isEmpty(mEdSpPhone)) {
                ToastUtils.init().showQuickToast(mActivity, "请输入手机号码");
                return;
            }
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("sp_name", mEdSpName.getText().toString())
                    .addFormDataPart("sp_province", "")
                    .addFormDataPart("sp_city", "")
                    .addFormDataPart("sp_area", "")
                    .addFormDataPart("sp_addr", mEdSpAddr.getText().toString())
                    .addFormDataPart("sp_link_name", mEdSpUserName.getText().toString())
                    .addFormDataPart("sp_link_phone", mEdSpPhone.getText().toString())
                    .addFormDataPart("sp_img", fileImg.getName(), RequestBody.create(MediaType.parse("image/*"), fileImg))
                    .addFormDataPart("sp_lic", fileLic.getName(), RequestBody.create(MediaType.parse("image/*"), fileLic));
            RequestBody requestBody = builder.build();
            mPresenter.createShop(requestBody);
        });
    }

    public void setOnClick(int isLic) {
        this.isLic = isLic;
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(permissions -> CameraUtil.getInstance().openSingalCamerNoCrop(this))
                .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            String s = pathList.get(0);
            if (this.isLic == 0) {
                fileLic = new File(s);
                Glide.with(this).load(fileLic).into(mIvShopLic);
            } else if (this.isLic == 1) {
                fileImg = new File(s);
                Glide.with(this).load(fileImg).into(mIvShopImg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void createSuccess() {

    }
}
