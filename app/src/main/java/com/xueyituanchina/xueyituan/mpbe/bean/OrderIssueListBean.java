package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class OrderIssueListBean extends BaseBean{

    /**
     * nick : 1234how
     * avator : https://xueyituan.oss-cn-beijing.aliyuncs.com/avatar/4433fa11f5ab4830995cff1b115e4546.jpg
     * list : [{"descd":"说一下","score":5,"goods_title":"aaa","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sp_name":"松子儿舞蹈学院","cat_name":"特长","ct":"2018-10-27 11:55:44"},{"descd":"挺好的，我喜欢","score":3,"goods_title":"aaa","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sp_name":"松子儿舞蹈学院","cat_name":"特长","ct":"2018-10-27 11:55:33"},{"descd":"asdasdadas","score":3,"goods_title":"aaa","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sp_name":"松子儿舞蹈学院","cat_name":"特长","ct":"2018-10-26 18:03:31"},{"descd":"太好了","score":4.1,"goods_title":"aaa","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg","sp_name":"松子儿舞蹈学院","cat_name":"特长","ct":"2018-09-05 15:19:08"}]
     */

    public String nick;
    public String avator;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * descd : 说一下
         * score : 5.0
         * goods_title : aaa
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
         * sp_name : 松子儿舞蹈学院
         * cat_name : 特长
         * ct : 2018-10-27 11:55:44
         */

        public String descd;
        public double score;
        public String goods_title;
        public String goods_thumb_img;
        public String sp_name;
        public String cat_name;
        public String ct;
    }
}
