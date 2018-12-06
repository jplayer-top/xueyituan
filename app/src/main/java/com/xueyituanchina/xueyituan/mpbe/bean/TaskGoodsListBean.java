package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/12/6.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TaskGoodsListBean extends BaseBean {

    public List<ListBean> list;

    public static class ListBean {
        /**
         * goods_id : 10015
         * goods_title : 我是测试
         */

        public int goods_id;
        public String goods_title;
    }
}
