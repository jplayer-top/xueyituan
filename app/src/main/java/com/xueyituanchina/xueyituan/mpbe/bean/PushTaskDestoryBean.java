package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PushTaskDestoryBean extends BaseBean {

    public List<ListBean> list;
    public List<DataBean> data;

    public static class ListBean {
        /**
         * task_id : gtask2018112616091519457
         * goods_title : 我是测试
         * goods_subtitle : q
         * amount : 0
         * left_amount : 0
         * is_on : true
         * task_status : 3
         * cat_name : 文化课
         * sp_name : 当当
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png
         */

        public String task_id;
        public String goods_title;
        public String goods_subtitle;
        public int amount;
        public int left_amount;
        public boolean is_on;
        public String task_status;
        public String cat_name;
        public String sp_name;
        public String sp_img;
    }

    public static class DataBean {
        /**
         * title : 11-07
         * num : 0
         */

        public String title;
        public int num;
    }
}
