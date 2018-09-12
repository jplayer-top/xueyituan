package com.xueyituanchina.xueyituan.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.ui.adapter.BrandInfoAdapter;

import java.util.Locale;

import top.jplayer.baseprolibrary.glide.GlideUtils;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/7.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TeacherInfoActivity extends CommonToolBarActivity {

    private BrandInfoAdapter mAdapter;
    private ImageView mIvAvatarSrc;
    private TextView mTvTitle;
    private TextView mTvSol;
    private TextView mTvBrandBestText;
    private TextView mTvBrandText;
    private StoreBean.TeacherListBean mBean;

    @Override
    public int initAddLayout() {
        return R.layout.activity_teacher_detail;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mBean = mBundle.getParcelable("bean");
        if (mBean != null) {
            mAdapter = new BrandInfoAdapter(mBean.mienList);
            mRecyclerView.setAdapter(mAdapter);
            View view = View.inflate(this, R.layout.adapter_header_teacher, null);
            mIvAvatarSrc = view.findViewById(R.id.ivAvatarSrc);
            mTvTitle = view.findViewById(R.id.tvTitle);
            mTvSol = view.findViewById(R.id.tvSol);
            mTvBrandBestText = view.findViewById(R.id.tvBrandBestText);
            mTvBrandText = view.findViewById(R.id.tvBrandText);
            mAdapter.addHeaderView(view);
            brandInfo(mBean);
        }
    }

    public void brandInfo(StoreBean.TeacherListBean bean) {
        Glide.with(this).load(bean.teacher_avator).apply(GlideUtils.init().options(R.mipmap.ic_launcher)).into(mIvAvatarSrc);
        mTvTitle.setText(bean.teacher_name);
        mTvSol.setText(String.format(Locale.CHINA, "任职%d年", bean.years));
        mTvBrandBestText.setText(bean.direction);
        mTvBrandText.setText(bean.experience);
    }
}
