package com.xueyituanchina.xueyituan.mpbe;

import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mp
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public interface XYTServer {
    @GET("home/top")
    Observable<HomeTopBean> home_top();

    @GET("home/list")
    Observable<HomeListBean> home_list();

    @GET("points/index")
    Observable<GiftDetailBean> gift_detail();

    @GET("home/list")
    Observable<HomeListBean> home_list(@Query("pid") String pid);

    @GET("home/goodslist")
    Observable<HomeGoodsList> home_goods_list(@QueryMap() Map<String, String> map);

    @GET("activity/nearList")
    Observable<NearbyActiveBean> active_nearby_list();
}
