package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/19.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AreaBean extends BaseBean {

    /**
     * areas : {"area_code":"371500","area_name":"聊城市","area_parent":"370000","area_lv":"市","area_ext":"","subs":[{"area_code":"371501","area_name":"市辖区","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371502","area_name":"东昌府区","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371521","area_name":"阳谷县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371522","area_name":"莘　县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371523","area_name":"茌平县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371524","area_name":"东阿县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371525","area_name":"冠　县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371526","area_name":"高唐县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371581","area_name":"临清市","area_parent":"371500","area_lv":"区","area_ext":""}]}
     */

    public AreasBean areas;

    public static class AreasBean {
        /**
         * area_code : 371500
         * area_name : 聊城市
         * area_parent : 370000
         * area_lv : 市
         * area_ext :
         * subs : [{"area_code":"371501","area_name":"市辖区","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371502","area_name":"东昌府区","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371521","area_name":"阳谷县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371522","area_name":"莘　县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371523","area_name":"茌平县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371524","area_name":"东阿县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371525","area_name":"冠　县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371526","area_name":"高唐县","area_parent":"371500","area_lv":"区","area_ext":""},{"area_code":"371581","area_name":"临清市","area_parent":"371500","area_lv":"区","area_ext":""}]
         */

        public String area_code;
        public String area_name;
        public String area_parent;
        public String area_lv;
        public String area_ext;
        public List<SubsBean> subs;

        public static class SubsBean {
            /**
             * area_code : 371501
             * area_name : 市辖区
             * area_parent : 371500
             * area_lv : 区
             * area_ext :
             */

            public String area_code;
            public String area_name;
            public String area_parent;
            public String area_lv;
            public String area_ext;
        }
    }
}
