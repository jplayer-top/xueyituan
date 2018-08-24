package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ActiveModel extends BaseModel<XYTServer> {
    public ActiveModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<NearbyActiveBean> nearbyList() {
        return mServer.active_nearby_list().compose(new IoMainSchedule<>());
    }
}
