package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class NearbyActiveBean extends BaseBean {
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 1
         * title : 八成的第三方
         * sub_title : 到饭点
         * description : dfdsf
         * dsfsdfsdfdsfsdf
         * sdfsdfsdfsdfsdfsd发
         * poster : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/timg.jpg
         * visitor : 0
         * user_id : 1
         * puber_id : 0
         * puber_name : 小白安
         * ct : 2018-08-20 15:40:08
         */

        public int id;
        public String title;
        public String sub_title;
        public String description;
        public String poster;
        public int visitor;
        public int user_id;
        public int puber_id;
        public String puber_name;
        public String ct;
    }
}
