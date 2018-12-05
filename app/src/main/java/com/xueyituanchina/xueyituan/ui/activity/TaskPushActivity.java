package com.xueyituanchina.xueyituan.ui.activity;

import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaiky.imagespickers.ImageSelectorActivity;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.event.PushTaskEvent;
import com.xueyituanchina.xueyituan.mpbe.presenter.TaskPushPresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;
import top.jplayer.baseprolibrary.utils.CameraUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/12/5.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskPushActivity extends CommonToolBarActivity {
    private File fileLic;
    private ImageView mIvShopLic;
    private TaskPushPresenter mPresenter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_task_push;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        String rules = mBundle.getString("rules");
        mPresenter = new TaskPushPresenter(this);
        TextView tvRules = rootView.findViewById(R.id.tvRules);
        tvRules.setText(rules);
        mIvShopLic = rootView.findViewById(R.id.ivShopLic);
        rootView.findViewById(R.id.btnPush).setOnClickListener(v -> {
            if (fileLic != null) {
                mPresenter.updatePoster(fileLic);
            }
        });
        mIvShopLic.setOnClickListener(v -> {
            AndPermission.with(this)
                    .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE)
                    .onGranted(permissions -> {
                        CameraUtil.getInstance().openSingalCamerNoCrop(this);
                    })
                    .onDenied(permissions -> AndPermission.hasAlwaysDeniedPermission(mActivity, permissions))
                    .start();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            String s = pathList.get(0);
            fileLic = new File(s);
            Glide.with(this).load(fileLic).into(mIvShopLic);
        }
    }

    public void upSuccess(UpdateUrlBean bean) {
        mPresenter.pushShare(mBundle.getString("id"), bean.url);
    }

    public void pushShare(BaseBean bean) {
        ToastUtils.init().showSuccessToast(this, "提交成功");
        EventBus.getDefault().post(new PushTaskEvent());
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> finish());
    }
}
