package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeGoodsList extends BaseBean {

    /**
     * list : [{"user_id":1,"sp_name":"松子儿舞蹈学院","is_best":false,"score":4.5,"sp_area":"东昌府区","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","goodslist":[{"goods_id":10000,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10001,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"}],"other":0},{"user_id":2,"sp_name":"小蝶书画学校","is_best":false,"score":5,"sp_area":"东昌府区","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","goodslist":[{"goods_id":10002,"goods_title":"bbb_001","goods_subtitle":"11111","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10003,"goods_title":"bbb_002","goods_subtitle":"2222","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10004,"goods_title":"bbb_004","goods_subtitle":"33333","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10005,"goods_title":"bbb_004","goods_subtitle":"44444","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10006,"goods_title":"bbb_004","goods_subtitle":"55555","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10007,"goods_title":"bbb_004","goods_subtitle":"6666","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"}],"other":4}]
     * more : false
     */

    public boolean more;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * user_id : 1
         * sp_name : 松子儿舞蹈学院
         * is_best : false
         * score : 4.5
         * sp_area : 东昌府区
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         * goodslist : [{"goods_id":10000,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"},{"goods_id":10001,"goods_title":"aaa_1111","goods_subtitle":"dsfsdfsd","goods_org_price":1,"goods_best_price":1,"goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01"}]
         * other : 0
         */

        public int user_id;
        public String sp_name;
        public boolean is_best;
        public double score;
        public String sp_area;
        public String sp_img;
        public int other;
        public List<GoodslistBean> goodslist;

        public static class GoodslistBean {
            /**
             * goods_id : 10000
             * goods_title : aaa_1111
             * goods_subtitle : dsfsdfsd
             * goods_org_price : 1
             * goods_best_price : 1
             * goodsOrgPriceStr : 0.01
             * goodsBestPriceStr : 0.01
             */

            public int goods_id;
            public String goods_title;
            public String goods_subtitle;
            public int goods_org_price;
            public int goods_best_price;
            public String goodsOrgPriceStr;
            public String goodsBestPriceStr;
        }
    }
}
