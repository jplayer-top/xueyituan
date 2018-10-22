package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaBean;
import com.xueyituanchina.xueyituan.mpbe.bean.BrandBBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShopItemBean;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeModel extends BaseModel<XYTServer> {
    public HomeModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<HomeTopBean> homeTop() {
        return mServer.home_top().compose(new IoMainSchedule<>());
    }

    public Observable<HomeListBean> homeList() {
        return mServer.home_list().compose(new IoMainSchedule<>());
    }

    public Observable<AreaBean> area_list() {
        return mServer.area_list("http://123.206.45.111:8080/xytuan/area").compose(new IoMainSchedule<>());
    }


    public Observable<HomeListBean> homeFilter(String pid) {
        return mServer.home_list(pid).compose(new IoMainSchedule<>());
    }

    public Observable<HomeGoodsList> homeGoodsList(Map<String, String> map) {
        return mServer.home_goods_list(map).compose(new IoMainSchedule<>());
    }

    public Observable<StoreBean> storeInfo(String id,String lnglat) {
        return mServer.storeInfo(id,lnglat).compose(new IoMainSchedule<>());
    }

    public Observable<AliPayInfoBean> payAli(String id) {
        return mServer.payAliOrder(id, "1").compose(new IoMainSchedule<>());
    }

    public Observable<WxPayInfoBean> payWx(String id) {
        return mServer.payWxOrder(id, "2").compose(new IoMainSchedule<>());
    }

    public Observable<ShopItemBean> shopInfo(String id,String lnglat) {
        return mServer.shopInfo(id,lnglat).compose(new IoMainSchedule<>());
    }

    public Observable<OrderBean> createOrder(String goodsId, String amount, String phone) {
        return mServer.createOrder(goodsId, amount, phone).compose(new IoMainSchedule<>());
    }

    public Observable<BrandBBean> brandInfo(String id) {
        return mServer.brandInfo(id).compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> collectionType(String favType, String favId) {
        return mServer.collectionType(favType, favId).compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> cancelCollectionType(String favType, String favId) {
        return mServer.cancelCollectionType(favType, favId).compose(new IoMainSchedule<>());
    }
}
