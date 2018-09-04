package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointRecodeBean extends BaseBean {
    public List<OrderListBean> orderList;

    public static class OrderListBean {
        /**
         * points_order_id : pt2018090417010962466
         * points_goods_id : 1
         * points_order_title : aaaaaa
         * points : 1000
         * amount : 1
         * total_points : 1000
         * status : 0
         * rp_name : 1123123
         * rp_phone : 1
         * rp_addr : 1
         * user_id : 6
         * ct : 2018-09-04 17:01:09
         */

        public String points_order_id;
        public int points_goods_id;
        public String points_order_title;
        public int points;
        public int amount;
        public int total_points;
        public int status;
        public String rp_name;
        public String rp_phone;
        public String rp_addr;
        public int user_id;
        public String ct;
    }
}
