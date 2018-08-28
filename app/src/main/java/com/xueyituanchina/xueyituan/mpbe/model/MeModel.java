package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeModel extends BaseModel<XYTServer> {

    public MeModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<MyInfoBean> requestMyInfo() {
        return mServer.myInfo().compose(new IoMainSchedule<>());
    }
}
