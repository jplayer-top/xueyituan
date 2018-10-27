package com.xueyituanchina.xueyituan.ui.activity;

import android.widget.FrameLayout;

import com.xueyituanchina.xueyituan.R;
import com.xueyituanchina.xueyituan.mpbe.bean.HasIssueBean;
import com.xueyituanchina.xueyituan.ui.adapter.IssueHasAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.activity.CommonToolBarActivity;

/**
 * Created by Obl on 2018/9/12.
 * com.xueyituanchina.xueyituan.ui.activity
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class IssueHasListActivity extends CommonToolBarActivity {

     IssueHasAdapter mAdapter;

    @Override
    public int initAddLayout() {
        return R.layout.activity_issue_list;
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        ArrayList<HasIssueBean> issue = mBundle.getParcelableArrayList("issue");
        mAdapter = new IssueHasAdapter(issue);
        if (issue == null || issue.size() < 1) {
            mMultipleStatusView.showEmpty();
        } else {
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    @Override
    public void refreshStart() {
        super.refreshStart();
        Observable.timer(1, TimeUnit.SECONDS).compose(new IoMainSchedule<>()).subscribe(aLong -> {
            IssueHasListActivity.this.responseSuccess();
        });
    }
}
