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
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    //主页
    @GET("home/top")
    Observable<HomeTopBean> home_top();

    @GET("home/list")
    Observable<HomeListBean> home_list();

    @GET("home/list")
    Observable<HomeListBean> home_list(@Query("pid") String pid);

    @GET("home/goodslist")
    Observable<HomeGoodsList> home_goods_list(@QueryMap() Map<String, String> map);

    //积分
    @GET("points/index")
    Observable<GiftDetailBean> gift_detail();

    @GET("points/orders?")
    Observable<BaseBean> billsOrders();

    @POST("points/create?")
    Observable<BaseBean> createBills(@QueryMap Map<String, String> map);

    @GET("points/bills?")
    Observable<BaseBean> billsList();

    //活动
    @GET("activity/nearList")
    Observable<NearbyActiveBean> active_nearby_list();

    //我的
    @GET("my/index?")
    Observable<MyInfoBean> myInfo();

    //个人信息
    @POST("user/register?")
    Observable<BaseBean> register(@QueryMap() Map<String, String> map);

    @POST("user/resetpw?")
    Observable<BaseBean> forget(@QueryMap() Map<String, String> map);

    @GET("user/logout?")
    Observable<BaseBean> logout();

    @POST("user/verfiysmcode?")
    Observable<LoginBean> verfiyCode(@Query("phone") String phone, @Query("smCode") String password);

    @POST("user/smcode?")
    Observable<LoginBean> getSmsBean(@QueryMap() Map<String, String> map);

    @POST("user/login?")
    Observable<LoginBean> getLoginBean(@Query("username") String phone, @Query("password") String password);

    @POST("user/updatenick?")
    Observable<BaseBean> updateNick(@Query("nick") String nick);

    @Multipart
    @POST("user/updateavatar?")
    Observable<MyInfoBean> updateAvatar(@Part MultipartBody.Part file);

    @POST("user/updatepw?")
    Observable<BaseBean> updatePw(@Query("opw") String opw, @Query("npw") String npw);

    @POST("user/verifypw?")
    Observable<BaseBean> verifyPw(@Query("pw") String pw);
}
