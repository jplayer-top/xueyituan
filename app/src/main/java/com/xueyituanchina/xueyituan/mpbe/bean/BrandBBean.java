package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/9/7.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BrandBBean extends BaseBean {
    /**
     * brand : {"user_id":1,"brand":"松子儿舞蹈学院","slogan":"乐享国学，传承经典","story":"都市生活节奏太快，渐渐丢了自己，每个人都在寻找属于自己的\u201c一亩三分地\u201d。那里没有喧嚣和烦恼，听得见心跳，看得见灵魂。或许，你要找的就是它\u2014\u2014\u201c壹亩叁份地儿\u201d。\r\n\r\n \r\n\r\n假如生活是一亩田，将这片田分为三份，第一份种着一颗永远不老的文艺心，第二份种着充满青春气息的风尚之心，第三份种着对传统曲艺的热爱之心，生活之外的生活圣地\u201c壹亩叁份地儿\u201d应运而生。","feature":"一对一专业老师","logo":"https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/1e8741e8e74345b7b611fb51e5dd536d.jpg","envirmt":"shop/1e8741e8e74345b7b611fb51e5dd536d.jpg","envirmtList":["https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/1e8741e8e74345b7b611fb51e5dd536d.jpg"]}
     */

    public BrandBean brand;

    public static class BrandBean {
        /**
         * user_id : 1
         * brand : 松子儿舞蹈学院
         * slogan : 乐享国学，传承经典
         * story : 都市生活节奏太快，渐渐丢了自己，每个人都在寻找属于自己的“一亩三分地”。那里没有喧嚣和烦恼，听得见心跳，看得见灵魂。或许，你要找的就是它——“壹亩叁份地儿”。
         * <p>
         * <p>
         * <p>
         * 假如生活是一亩田，将这片田分为三份，第一份种着一颗永远不老的文艺心，第二份种着充满青春气息的风尚之心，第三份种着对传统曲艺的热爱之心，生活之外的生活圣地“壹亩叁份地儿”应运而生。
         * feature : 一对一专业老师
         * logo : https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/1e8741e8e74345b7b611fb51e5dd536d.jpg
         * envirmt : shop/1e8741e8e74345b7b611fb51e5dd536d.jpg
         * envirmtList : ["https://xueyituan.oss-cn-beijing.aliyuncs.com/shop/1e8741e8e74345b7b611fb51e5dd536d.jpg"]
         */

        public int user_id;
        public String brand;
        public String slogan;
        public String story;
        public String feature;
        public String logo;
        public String envirmt;
        public List<String> envirmtList;
    }
}
