package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/8/20.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HomeTopBean extends BaseBean {
    /**
     * banner : [{"banner_type":"1","banner_id":1,"banner_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/timg.jpg"}]
     * acTop : [{"id":4,"title":"松子儿舞蹈暑假开课了！！！","sub_title":"松子儿舞蹈暑假开课了","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"},{"id":5,"title":"松子儿舞蹈暑假开课了！！！","sub_title":"松子儿舞蹈暑假开课了","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"},{"id":2,"title":"松子儿舞蹈暑假开课了！！！","sub_title":"松子儿舞蹈暑假开课了","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"},{"id":3,"title":"松子儿舞蹈暑假开课了！！！","sub_title":"松子儿舞蹈暑假开课了","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"},{"id":1,"title":"松子儿舞蹈暑假开课了！！！","sub_title":"松子儿舞蹈暑假开课了","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png"}]
     * acfamily : {"title":"本周推荐济南动物园","avator":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/timg.jpg"}
     * shop : [{"user_id":1,"sp_name":"松子儿舞蹈学院","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","recommend_title":"让孩子潮起来"},{"user_id":2,"sp_name":"小蝶书画学校","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","recommend_title":"只需10元这个假期学画画"}]
     */

    public AcfamilyBean acfamily;
    public List<BannerBean> banner;
    public List<AcTopBean> acTop;
    public List<ShopBean> shop;

    public static class AcfamilyBean {
        /**
         * title : 本周推荐济南动物园
         * avator : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/timg.jpg
         */

        public String title;
        public String avator;
    }

    public static class BannerBean {
        /**
         * banner_type : 1
         * banner_id : 1
         * banner_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/timg.jpg
         */

        public String banner_type;
        public int banner_id;
        public String banner_img;
    }

    public static class AcTopBean {
        /**
         * id : 4
         * title : 松子儿舞蹈暑假开课了！！！
         * sub_title : 松子儿舞蹈暑假开课了
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         */

        public int id;
        public String title;
        public String sub_title;
        public String sp_img;
    }

    public static class ShopBean {
        /**
         * user_id : 1
         * sp_name : 松子儿舞蹈学院
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png
         * recommend_title : 让孩子潮起来
         */

        public int user_id;
        public String sp_name;
        public String sp_img;
        public String recommend_title;
    }
}
