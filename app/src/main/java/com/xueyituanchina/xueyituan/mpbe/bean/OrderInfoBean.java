package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderInfoBean extends BaseBean {

    /**
     * record : {"order_id":"2018102311074788427","order_title":"aaa","goods_id":10000,"goods_thumb_img":"xyt/1.jpg","price":1,"amount":1,"total_price":1,"user_id":1,"buyer":6,"pay_type":0,"pay_status":0,"order_status":0,"rp_phone":"17667936541","create_time":"2018-10-23 11:07:48","priceStr":"0.01","totalPriceStr":"0.01","thumbImgList":["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"],"thumbImg":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"}
     */

    public RecordBean record;

    public static class RecordBean {
        /**
         * order_id : 2018102311074788427
         * order_title : aaa
         * goods_id : 10000
         * goods_thumb_img : xyt/1.jpg
         * price : 1
         * amount : 1
         * total_price : 1
         * user_id : 1
         * buyer : 6
         * pay_type : 0
         * pay_status : 0
         * order_status : 0
         * rp_phone : 17667936541
         * create_time : 2018-10-23 11:07:48
         * priceStr : 0.01
         * totalPriceStr : 0.01
         * thumbImgList : ["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg"]
         * thumbImg : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         */

        public String order_id;
        public String order_title;
        public int goods_id;
        public String goods_thumb_img;
        public int price;
        public int amount;
        public int total_price;
        public int user_id;
        public int buyer;
        public int pay_type;
        public int pay_status;
        public int order_status;
        public String rp_phone;
        public String create_time;
        public String priceStr;
        public String totalPriceStr;
        public String thumbImg;
        public List<String> thumbImgList;
    }
}
