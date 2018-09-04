package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PointDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PointRecodeBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/23.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class GiftDetailModel extends BaseModel<XYTServer> {
    public GiftDetailModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<GiftDetailBean> requestGiftDetail() {
        return mServer.gift_detail().compose(new IoMainSchedule<>());
    }

    public Observable<PointDetailBean> requestPointDetail() {
        return mServer.billsList().compose(new IoMainSchedule<>());
    }

    public Observable<PointRecodeBean> requestPointRecode() {
        return mServer.billsOrders().compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> createBills(Map<String, String> map) {
        return mServer.createBills(map).compose(new IoMainSchedule<>());
    }
}
