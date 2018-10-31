package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/19.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ShopItemBean extends BaseBean {

    /**
     * shop : {"spId":2,"sp_name":"小蝶书画学校","score":2.5,"sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","best":true,"sales":0,"addr":"东昌府区柳园南路200号","phone":"13212321254"}
     * goods : {"goods_id":10002,"goods_title":"bbb_001","goods_subtitle":"11111","goods_desc":"胜多负少的","goodsOrgPriceStr":"0.01","goodsBestPriceStr":"0.01","sales":0,"score":4.6,"goods_thumb_img":["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"],"goods_desc_img":["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"]}
     * students : 0
     * commentsList : []
     * rate : 100%
     * hightRate : 25.00%
     * keep : false
     */

    public ShopBean shop;
    public GoodsBean goods;
    public int students;
    public String rate;
    public String hightRate;
    public boolean keep;
    public List<CommentsListBean> commentsList;

    public static class ShopBean {
        /**
         * spId : 2
         * sp_name : 小蝶书画学校
         * score : 2.5
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         * best : true
         * sales : 0
         * addr : 东昌府区柳园南路200号
         * phone : 13212321254
         */

        public int spId;
        public String sp_name;
        public double score;
        public String sp_img;
        public boolean best;
        public int sales;
        public String addr;
        public String phone;
    }

    public static class GoodsBean {
        /**
         * goods_id : 10002
         * goods_title : bbb_001
         * goods_subtitle : 11111
         * goods_desc : 胜多负少的
         * goodsOrgPriceStr : 0.01
         * goodsBestPriceStr : 0.01
         * sales : 0
         * score : 4.6
         * goods_thumb_img : ["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"]
         * goods_desc_img : ["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"]
         */

        public int goods_id;
        public String goods_title;
        public String goods_subtitle;
        public String goods_desc;
        public String goodsOrgPriceStr;
        public String goodsBestPriceStr;
        public int sales;
        public double score;
        public List<String> goods_thumb_img;
        public List<String> goods_desc_img;
    }

    public static class CommentsListBean {
        /**
         * descd : 太好了
         * score : 4.1
         * goods_title : aaa_1111
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         * sp_name : 松子儿舞蹈学院
         * cat_name : 特长
         * ct : 2018-09-05 15:19:08
         */

        public String descd;
        public double score;
        public String goods_title;
        public String goods_thumb_img;
        public String sp_name;
        public String cat_name;
        public String ct;
        public String nick;
        public boolean anonymous;
    }
}
