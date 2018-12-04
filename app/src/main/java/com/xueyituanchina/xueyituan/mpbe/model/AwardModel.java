package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/12/3.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardModel extends BaseModel<XYTServer> {
    public AwardModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<AwardBean> awardList() {
        return mServer.awardList().compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> shareOk(String taskId) {
        return mServer.shareOk(taskId).compose(new IoMainSchedule<>());
    }
}
