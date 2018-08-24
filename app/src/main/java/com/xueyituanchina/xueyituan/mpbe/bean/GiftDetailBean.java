package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/23.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class GiftDetailBean extends BaseBean {

    /**
     * goodsList : [{"points_goods_id":3,"title":"cccccc","points":10,"thumb_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","desc_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","stock":100,"is_on":1,"ct":"2018-08-20 17:05:59","ut":"2018-08-21 10:00:01"},{"points_goods_id":1,"title":"aaaaaa","points":100,"thumb_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","desc_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","stock":100,"is_on":1,"ct":"2018-08-20 17:05:59","ut":"2018-08-21 10:41:50"},{"points_goods_id":2,"title":"bbbbbb","points":1000,"thumb_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","desc_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","stock":100,"is_on":1,"ct":"2018-08-20 17:05:59","ut":"2018-08-21 10:41:53"}]
     * advertList : [{"advert_id":1,"advert_title":"sdfsdf","advert_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","advert_url":"https://item.taobao.com/item.htm?id=573916402308","begin_time":"2018-08-20 18:13:50","end_time":"2018-08-25 18:13:52"},{"advert_id":2,"advert_title":"dfdsdf","advert_img":"https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png","advert_url":"https://item.taobao.com/item.htm?id=563061649881","begin_time":"2018-08-20 18:13:55","end_time":"2018-08-25 18:13:57"}]
     * balance : 0
     */

    public int balance;
    public List<GoodsListBean> goodsList;
    public List<AdvertListBean> advertList;

    public static class GoodsListBean {
        /**
         * points_goods_id : 3
         * title : cccccc
         * points : 10
         * thumb_img : https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png
         * desc_img : https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png
         * stock : 100
         * is_on : 1
         * ct : 2018-08-20 17:05:59
         * ut : 2018-08-21 10:00:01
         */

        public int points_goods_id;
        public String title;
        public int points;
        public String thumb_img;
        public String desc_img;
        public int stock;
        public int is_on;
        public String ct;
        public String ut;
    }

    public static class AdvertListBean {
        /**
         * advert_id : 1
         * advert_title : sdfsdf
         * advert_img : https://xiaoi-img.oss-cn-qingdao.aliyuncs.com/assets/image/customer_avatar.png
         * advert_url : https://item.taobao.com/item.htm?id=573916402308
         * begin_time : 2018-08-20 18:13:50
         * end_time : 2018-08-25 18:13:52
         */

        public int advert_id;
        public String advert_title;
        public String advert_img;
        public String advert_url;
        public String begin_time;
        public String end_time;
    }
}
