package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeListBean {

    /**
     * code : 000
     * msg : 操作成功
     * list : [{"id":1,"pid":0,"name":"特长","icon":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_inde_call.png","ct":"2018-08-14 09:48:56"},{"id":2,"pid":0,"name":"文化课","icon":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","ct":"2018-08-15 16:52:19"},{"id":3,"pid":0,"name":"早教","icon":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_child.png","ct":"2018-08-15 16:52:20"},{"id":4,"pid":0,"name":"成人教育","icon":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_health.png","ct":"2018-08-15 16:52:22"},{"id":5,"pid":0,"name":"母婴馆","icon":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_setup.png","ct":"2018-08-15 16:52:23"}]
     */

    public String code;
    public String msg;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 1
         * pid : 0
         * name : 特长
         * icon : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_inde_call.png
         * ct : 2018-08-14 09:48:56
         */

        public int id;
        public int pid;
        public String name;
        public String icon;
        public String ct;
    }
}
