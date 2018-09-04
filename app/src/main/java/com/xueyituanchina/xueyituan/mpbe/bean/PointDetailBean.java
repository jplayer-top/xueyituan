package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PointDetailBean extends BaseBean {
    public List<OrderListBean> orderList;

    public static class OrderListBean {
        /**
         * bill_id : 3
         * user_id : 6
         * type : -
         * changed : 1000
         * balance : 97900
         * order_id : pt2018090417010962466
         * remark : 支出
         * ct : 2018-09-04 17:01:09
         */

        public int bill_id;
        public int user_id;
        public String type;
        public int changed;
        public int balance;
        public String order_id;
        public String remark;
        public String ct;
    }
}
