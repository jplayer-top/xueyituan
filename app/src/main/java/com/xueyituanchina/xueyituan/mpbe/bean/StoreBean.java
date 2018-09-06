package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/5.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreBean  extends BaseBean{

    /**
     * shop : {"spId":1,"sp_name":"松子儿舞蹈学院","score":4.5,"sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","best":true,"sales":0,"addr":"东昌府区柳园南路200号","phone":"13186970101","dist":"150米"}
     * teacherList : [{"id":1,"user_id":1,"teacher_name":"丹丹老师","teacher_avator":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","years":4,"direction":"音乐","experience":"毕业于山东艺术学院"},{"id":2,"user_id":1,"teacher_name":"萍萍老师","teacher_avator":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/2.jpg","years":8,"direction":"音乐","experience":"毕业于山东大学"},{"id":5,"user_id":1,"teacher_name":"assfds","teacher_avator":"https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/4b1d792f08144e29a597ea688ca80a07.jpg","years":4,"direction":"美术","experience":"山东美元"},{"id":6,"user_id":1,"teacher_name":"托尼","teacher_avator":"https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/ad02a3196f5746588dc3f799066ae6b6.jpg","years":1,"direction":"美术","experience":"山东美院"}]
     * goodsList : [{"goods_id":10011,"goods_title":"胜多负少的","goods_subtitle":"胜多负少的","goods_org_price":100,"goods_best_price":100,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/5c3d24b7d1564637a938105a14a66dd8.png","sales":0,"goodsOrgPriceStr":"1.00","goodsBestPriceStr":"1.00"},{"goods_id":10008,"goods_title":"121","goods_subtitle":"121","goods_org_price":0,"goods_best_price":0,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/f2dc3d27ab7f4d37a7d6c7c2acb81361.jpg","sales":0,"goodsOrgPriceStr":"0.00","goodsBestPriceStr":"0.00"},{"goods_id":10001,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sales":0,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10000,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sales":0,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"}]
     * slogan : 乐享国学，传承经典
     */

    public ShopBean shop;
    public String slogan;
    public List<TeacherListBean> teacherList;
    public List<GoodsListBean> goodsList;

    public static class ShopBean {
        /**
         * spId : 1
         * sp_name : 松子儿舞蹈学院
         * score : 4.5
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         * best : true
         * sales : 0
         * addr : 东昌府区柳园南路200号
         * phone : 13186970101
         * dist : 150米
         */

        public int spId;
        public String sp_name;
        public double score;
        public String sp_img;
        public boolean best;
        public int sales;
        public String addr;
        public String phone;
        public String dist;
    }

    public static class TeacherListBean {
        /**
         * id : 1
         * user_id : 1
         * teacher_name : 丹丹老师
         * teacher_avator : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         * years : 4
         * direction : 音乐
         * experience : 毕业于山东艺术学院
         */

        public int id;
        public int user_id;
        public String teacher_name;
        public String teacher_avator;
        public int years;
        public String direction;
        public String experience;
    }

    public static class GoodsListBean {
        /**
         * goods_id : 10011
         * goods_title : 胜多负少的
         * goods_subtitle : 胜多负少的
         * goods_org_price : 100
         * goods_best_price : 100
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/5c3d24b7d1564637a938105a14a66dd8.png
         * sales : 0
         * goodsOrgPriceStr : 1.00
         * goodsBestPriceStr : 1.00
         */

        public int goods_id;
        public String goods_title;
        public String goods_subtitle;
        public int goods_org_price;
        public int goods_best_price;
        public String goods_thumb_img;
        public int sales;
        public String goodsOrgPriceStr;
        public String goodsBestPriceStr;
    }
}
