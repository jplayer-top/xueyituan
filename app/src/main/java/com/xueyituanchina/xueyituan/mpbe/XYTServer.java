package com.xueyituanchina.xueyituan.mpbe;

import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

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


    @POST("user/register?")
    Observable<BaseBean> register(@QueryMap() Map<String, String> map);

    @POST("user/resetpw?")
    Observable<BaseBean> forget(@QueryMap() Map<String, String> map);

    @POST("user/verfiysmcode?")
    Observable<LoginBean> verfiyCode(@Query("phone") String phone, @Query("smCode") String password);

    @POST("user/smcode?")
    Observable<LoginBean> getSmsBean(@QueryMap() Map<String, String> map);

    @POST("user/login?")
    Observable<LoginBean> getLoginBean(@Query("username") String phone, @Query("password") String password);

    @GET("my/index?")
    Observable<MyInfoBean> myInfo();
}
