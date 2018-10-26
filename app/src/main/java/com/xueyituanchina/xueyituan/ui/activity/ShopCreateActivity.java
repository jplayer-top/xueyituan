package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaAllBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PostLocalBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.presenter.ShopCreatePresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.ui.activity.WebViewActivity;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
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
    @BindView(R.id.tvUserAgent)
    TextView tvUserAgent;
    @BindView(R.id.checkbox)
    CheckBox mCheckBox;
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
        initPicker();
        mEdSpLocal.setOnClickListener(v -> {
            if (mLocalBean != null) {
                setLocalBean(mLocalBean);
            } else {
                mPresenter.areaLocal();
            }
        });
        tvUserAgent.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.xueyituanchina.cn/info/shopagent.html");
            ActivityUtils.init().start(mActivity, WebViewActivity.class, "", bundle);
        });
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
            if (!mCheckBox.isChecked()) {
                ToastUtils.init().showQuickToast(mActivity, "请阅读并同意商家协议");
                return;
            }
            mPresenter.updateLic(fileLic);
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

    private ArrayList<PostLocalBean> optionsLocalSItems = new ArrayList<>();
    private ArrayList<ArrayList<PostLocalBean>> optionsLocalXItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<PostLocalBean>>> optionsQItems = new ArrayList<>();
    private OptionsPickerView mLocalPickerView;
    private AreaAllBean mLocalBean;

    public void setLocalBean(AreaAllBean localBean) {
        mLocalBean = localBean;
        if (localBean != null && localBean.areas.size() > 0) {
            for (int i = 0; i < localBean.areas.size(); i++) {//省
                ArrayList<ArrayList<PostLocalBean>> minLocalItems = new ArrayList<>();
                ArrayList<PostLocalBean> subsString = new ArrayList<>();
                for (int j = 0; j < localBean.areas.get(i).subs.size(); j++) {
                    String area_nameS = localBean.areas.get(i).subs.get(j).area_name;
                    String area_codeS = localBean.areas.get(i).subs.get(j).area_code;
                    PostLocalBean postLocalBeanS = new PostLocalBean();
                    postLocalBeanS.name = area_nameS;
                    postLocalBeanS.code = area_codeS;
                    subsString.add(postLocalBeanS);
                    ArrayList<PostLocalBean> subsXString = new ArrayList<>();
                    for (int k = 0; k < localBean.areas.get(i).subs.get(j).subs.size(); k++) {
                        String area_nameX = localBean.areas.get(i).subs.get(j).subs.get(k).area_name;
                        String area_codeX = localBean.areas.get(i).subs.get(j).subs.get(k).area_code;
                        PostLocalBean postLocalBeanX = new PostLocalBean();
                        postLocalBeanX.name = area_nameX;
                        postLocalBeanX.code = area_codeX;
                        subsXString.add(postLocalBeanX);
                    }
                    minLocalItems.add(subsXString);
                }
                optionsLocalXItems.add(subsString);
                optionsQItems.add(minLocalItems);
                String area_name = localBean.areas.get(i).area_name;
                String area_code = localBean.areas.get(i).area_code;
                PostLocalBean postLocalBean = new PostLocalBean();
                postLocalBean.code = area_code;
                postLocalBean.name = area_name;
                optionsLocalSItems.add(postLocalBean);
            }
            if (optionsLocalSItems.size() != 0 & optionsLocalSItems != null) {
                mLocalPickerView.setPicker(optionsLocalSItems, optionsLocalXItems, optionsQItems);
            }
        }
        if (!mLocalPickerView.isShowing()) {
            mLocalPickerView.show();
        }
    }

    private void initPicker() {
        //地区
        mLocalPickerView = new OptionsPickerView.Builder(this, (options1, option2, options3, v) -> {
            mProvince_name = optionsLocalSItems.get(options1).name + "";
            mProvince_code = optionsLocalSItems.get(options1).code;
            PostLocalBean localBean = optionsLocalXItems.get(options1).get(option2);
            mCity_name = (localBean.name) == null ? "" : (localBean.name + "");
            mCity_code = localBean.code;
            PostLocalBean areaBean = optionsQItems.get(options1).get(option2).get(options3);
            mArea_code = areaBean.code;
            mArea_name = (areaBean.name) == null ? "" : areaBean.name + "";
            mEdSpLocal.setText(String.format(Locale.CHINA, "%s%s%s", mProvince_name, mCity_name, mArea_name));
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setSubmitColor(getResources().getColor(R.color.colorBlackGold))
                .setCancelColor(getResources().getColor(R.color.grey))
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();
    }

    private String mProvince_code;
    private String mCity_code;
    private String mArea_code;
    private String mProvince_name;
    private String mCity_name;
    private String mArea_name;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void createSuccess() {

    }

    public void upLicSuccess(UpdateUrlBean bean) {
        mPresenter.updateImg(fileImg, bean.url);
    }

    public void upSuccess(String url, String url1) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("sp_name", mEdSpName.getText().toString())
                .addFormDataPart("sp_province", "")
                .addFormDataPart("sp_city", "")
                .addFormDataPart("sp_area", "")
                .addFormDataPart("sp_addr", mEdSpAddr.getText().toString())
                .addFormDataPart("sp_link_name", mEdSpUserName.getText().toString())
                .addFormDataPart("sp_link_phone", mEdSpPhone.getText().toString())
                .addFormDataPart("sp_img", url)
                .addFormDataPart("lnglat", url)
                .addFormDataPart("sp_lic", url1);
        RequestBody requestBody = builder.build();
        mPresenter.createShop(requestBody);
    }
}
