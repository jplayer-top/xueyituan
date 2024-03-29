package com.xueyituanchina.xueyituan.mpbe;

import com.xueyituanchina.xueyituan.aliapi.AliPayInfoBean;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaAllBean;
import com.xueyituanchina.xueyituan.mpbe.bean.AreaBean;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.bean.BrandBBean;
import com.xueyituanchina.xueyituan.mpbe.bean.EquityBean;
import com.xueyituanchina.xueyituan.mpbe.bean.FavListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.GiftDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeGoodsList;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.HomeTopBean;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInviteBean;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderBean;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderInfoBean;
import com.xueyituanchina.xueyituan.mpbe.bean.OrderIssueListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PointDetailBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PointRecodeBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ProPertyBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskDestoryBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareImgBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShopItemBean;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreBean;
import com.xueyituanchina.xueyituan.mpbe.bean.StoreInfoBean;
import com.xueyituanchina.xueyituan.mpbe.bean.TaskGoodsListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UserOrderListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UserTaskListBean;
import com.xueyituanchina.xueyituan.wxapi.WxPayInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
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

    @GET()
    Observable<AreaBean> area_list(@Url String url);

    @GET()
    Observable<AreaAllBean> area(@Url String url);


    @POST("task/sharelist")
    Observable<UserTaskListBean> taskList();


    @GET("home2/index")
    Observable<AwardBean> awardList();

    @GET("my/equity")
    Observable<EquityBean> equity();

    @GET("swlt/property")
    Observable<ProPertyBean> property();

    @GET("swlt/bill")
    Observable<UserOrderListBean> bill();

    @POST("task/goodslist")
    Observable<TaskGoodsListBean> taskGoodsList();

    @POST("invite/my")
    Observable<MyInviteBean> inviteList();

    @POST("task/pubhis")
    Observable<PushTaskDestoryBean> tasksDestory();

    @POST("task/pub")
    Observable<PushTaskBean> taskPub(@QueryMap() Map<String, String> map);

    @POST("user/savebankinfo")
    Observable<BaseBean> bankinfo(@QueryMap() Map<String, String> map);

    @POST("task/share")
    Observable<BaseBean> shareOk(@Query("taskId") String taskId);

    @POST("task/beforeshare")
    Observable<BaseBean> shareCan(@Query("taskId") String taskId);

    @POST("task/shareimg")
    Observable<ShareImgBean> shareImg(@Query("taskId") String taskId);

    @POST("home/vippay")
    Observable<BaseBean> vipPay(@Query("orderId") String orderId);

    @POST("swlt/pwd")
    Observable<BaseBean> applySign(@Query("pw") String pw);

    @POST("swlt/apply")
    Observable<BaseBean> apply(@Query("money") String money);

    @POST("task/beforeshare")
    Observable<BaseBean> canShare(@Query("taskId") String taskId);

    @POST("task/submit")
    Observable<BaseBean> pushShare(@Query("id") String taskId, @Query("url") String url);

    @GET("home/goodsinfo?")
    Observable<ShopItemBean> shopInfo(@Query("id") String id, @Query("lnglat") String lnglat);

    @GET("home/goodslist")
    Observable<HomeGoodsList> home_goods_list(@QueryMap() Map<String, String> map);

    @GET("home/shopInfo")
    Observable<StoreBean> storeInfo(@Query("id") String id, @Query("lnglat") String lnglat);

    @GET("home/brand")
    Observable<BrandBBean> brandInfo(@Query("id") String id);

    //类型 1商家 2商品 3内容
    @GET("my/fav?")
    Observable<BaseBean> collectionType(@Query("favType") String favType, @Query("favId") String favId, @Query("uid")
            String uid);

    @GET("my/cancelfav")
    Observable<BaseBean> cancelCollectionType(@Query("favType") String favType, @Query("favId") String favId, @Query("uid")
            String uid);

    //积分
    @GET("points/index")
    Observable<GiftDetailBean> gift_detail();

    @GET("points/orders?")
    Observable<PointRecodeBean> billsOrders();

    @POST("points/create?")
    Observable<BaseBean> createBills(@QueryMap Map<String, String> map);

    @GET("home/bespoke?")
    Observable<BaseBean> storeJoin(@Query("goodsId") String goodsId, @Query("phone") String phone);

    @POST("home/pay")
    Observable<AliPayInfoBean> payAliOrder(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("home/pay")
    Observable<WxPayInfoBean> payWxOrder(@Query("orderId") String orderId, @Query("payType") String payType);

    @POST("home/rpay")
    Observable<BaseBean> payUserOrder(@Query("orderId") String orderId);

    @POST("home/createorder?")
    Observable<OrderBean> createOrder(@Query("goodsId") String goodsId,
                                      @Query("amount") String amount,
                                      @Query("phone") String phone);

    @GET("points/bills?")
    Observable<PointDetailBean> billsList();

    //活动
    @GET("activity/nearList")
    Observable<NearbyActiveBean> active_nearby_list();

    //活动
    @GET("home/weekList")
    Observable<NearbyActiveBean> week_list();

    //我的
    @GET("my/index?")
    Observable<MyInfoBean> myInfo();

    //我的
    @GET("home/info?")
    Observable<OrderInfoBean> orderInfo(@Query("orderId") String orderId);

    //个人信息
    @POST("user/register?")
    Observable<BaseBean> register(@QueryMap() Map<String, String> map);

    @POST("user/resetpw?")
    Observable<BaseBean> forget(@QueryMap() Map<String, String> map);

    @GET("user/logout?")
    Observable<BaseBean> logout();

    @GET("my/favlist")
    Observable<FavListBean> favList(@Query("favType") String favType, @Query("lnglat") String lnglat);

    @POST("user/verfiysmcode?")
    Observable<LoginBean> verfiyCode(@Query("phone") String phone, @Query("smCode") String password);

    @POST("my/comment?")
    Observable<BaseBean> orderIssue(@Query("orderId") String orderId,
                                    @Query("descd") String descd,
                                    @Query("anonymous") String anonymous,
                                    @Query("score") String score);

    @GET("my/comments")
    Observable<OrderIssueListBean> orderIssueList();

    @GET("my/merchant")
    Observable<StoreInfoBean> storeInfo();

    @POST("user/smcode?")
    Observable<LoginBean> getSmsBean(@QueryMap() Map<String, String> map);

    @POST("my/viprecharge")
    Observable<WxPayInfoBean> wallWXRecharge(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("my/viprecharge")
    Observable<AliPayInfoBean> wallAliRecharge(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("my/viprecharge")
    Observable<BaseBean> payVipR(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("task/pay")
    Observable<WxPayInfoBean> payTaskWx(@Query("orderId") String orderId, @Query("payType") String pay_type);

    @POST("task/pay")
    Observable<AliPayInfoBean> payTaskAli(@Query("orderId") String orderId, @Query("payType") String pay_type);

    @POST("my/signrecharge")
    Observable<WxPayInfoBean> wallWxShop(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("my/walletrecharge")
    Observable<WxPayInfoBean> wallWxWall(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("my/walletrecharge")
    Observable<AliPayInfoBean> wallAliWall(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("my/signrecharge")
    Observable<AliPayInfoBean> wallAliShop(@Query("money") String money, @Query("pay_type") String pay_type);

    @POST("user/login?")
    Observable<LoginBean> getLoginBean(@Query("username") String phone, @Query("password") String password);

    @POST("user/updatenick?")
    Observable<BaseBean> updateNick(@Query("nick") String nick);

    @Multipart
    @POST("user/updateavatar?")
    Observable<MyInfoBean> updateAvatar(@Part MultipartBody.Part file);

    @Multipart
    @POST("uploadposter")
    Observable<UpdateUrlBean> updatePoster(@Part MultipartBody.Part file);

    @Multipart
    @POST("uploadscreens")
    Observable<UpdateUrlBean> updateScreen(@Part MultipartBody.Part file);

    @Multipart
    @POST("upload")
    Observable<UpdateUrlBean> updateLic(@Part MultipartBody.Part file);

    @POST("user/updatepw?")
    Observable<BaseBean> updatePw(@Query("opw") String opw, @Query("npw") String npw);

    @POST("user/updatecashpw?")
    Observable<BaseBean> updateTxPw(@Query("opw") String opw, @Query("npw") String npw);

    @POST("user/verifypw?")
    Observable<BaseBean> verifyPw(@Query("pw") String pw);

    @POST("user/verifycashpw?")
    Observable<BaseBean> verifyTxPw(@Query("pw") String pw);

    @POST("activity/pub")
    Observable<ShareBean> pubActivity(@Body RequestBody Body);

    @POST("my/mctentry")
    Observable<BaseBean> shopCreate(@Body RequestBody Body);

    @Headers("download:download")
    @Streaming //IO 大文件下载处理
    @GET
    Observable<ResponseBody> imgLoad(@Url String url);
}
