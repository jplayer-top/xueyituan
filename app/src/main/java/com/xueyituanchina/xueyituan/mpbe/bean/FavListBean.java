package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/26.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class FavListBean extends BaseBean {

    public List<ListBean> list;

    public static class ListBean {
        /**
         * cnt_id : 5
         * title : 八成的第三方5
         * poster : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/activity/c3cc6c44cb52417493c90929feec0d1a.png
         * sp_name : 小蝶书画学校
         */
        /**
         * user_id : 1
         * sp_name : 松子儿舞蹈学院
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         * score : 45
         * sp_city : 聊城市
         * sp_area : 东昌府区
         * sp_lnglat : 121.121,23.1555
         * dist : 1441.4966382km
         */
        /**
         * goods_id : 10000
         * goods_title : aaa
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         * goods_subtitle : dsfsdfsd
         * goods_best_price : 0.01
         * goods_org_price : 0.01
         * sp_lnglat : 121.121,23.1555
         * user_id : 1
         * dist : 1432.7215428km
         */

        public int goods_id;
        public String goods_title;
        public String goods_thumb_img;
        public String goods_subtitle;
        public String goods_best_price;
        public String goods_org_price;
        public int user_id;
        public String sp_img;
        public int score;
        public String sp_city;
        public String sp_area;
        public String sp_lnglat;
        public String dist;
        public int cnt_id;
        public String title;
        public String poster;
        public String sp_name;
    }


}
