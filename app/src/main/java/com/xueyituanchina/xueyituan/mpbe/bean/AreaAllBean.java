package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/24.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AreaAllBean extends BaseBean{

    public List<AreasBean> areas;

    public static class AreasBean {
        /**
         * area_code : 110000
         * area_name : 北京市
         * area_parent : 000000
         * area_lv : 省
         * area_ext :
         * subs : [{"area_code":"110100","area_name":"市辖区","area_parent":"110000","area_lv":"市","area_ext":"","subs":[{"area_code":"110101","area_name":"东城区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110102","area_name":"西城区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110103","area_name":"崇文区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110104","area_name":"宣武区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110105","area_name":"朝阳区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110106","area_name":"丰台区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110107","area_name":"石景山区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110108","area_name":"海淀区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110109","area_name":"门头沟区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110111","area_name":"房山区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110112","area_name":"通州区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110113","area_name":"顺义区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110114","area_name":"昌平区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110115","area_name":"大兴区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110116","area_name":"怀柔区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110117","area_name":"平谷区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]}]},{"area_code":"110200","area_name":"县","area_parent":"110000","area_lv":"市","area_ext":"","subs":[{"area_code":"110228","area_name":"密云县","area_parent":"110200","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110229","area_name":"延庆县","area_parent":"110200","area_lv":"区","area_ext":"","subs":[]}]}]
         */

        public String area_code;
        public String area_name;
        public String area_parent;
        public String area_lv;
        public String area_ext;
        public List<SubsBeanX> subs;

        public static class SubsBeanX {
            /**
             * area_code : 110100
             * area_name : 市辖区
             * area_parent : 110000
             * area_lv : 市
             * area_ext :
             * subs : [{"area_code":"110101","area_name":"东城区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110102","area_name":"西城区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110103","area_name":"崇文区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110104","area_name":"宣武区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110105","area_name":"朝阳区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110106","area_name":"丰台区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110107","area_name":"石景山区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110108","area_name":"海淀区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110109","area_name":"门头沟区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110111","area_name":"房山区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110112","area_name":"通州区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110113","area_name":"顺义区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110114","area_name":"昌平区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110115","area_name":"大兴区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110116","area_name":"怀柔区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]},{"area_code":"110117","area_name":"平谷区","area_parent":"110100","area_lv":"区","area_ext":"","subs":[]}]
             */

            public String area_code;
            public String area_name;
            public String area_parent;
            public String area_lv;
            public String area_ext;
            public List<SubsBean> subs;

            public static class SubsBean {
                /**
                 * area_code : 110101
                 * area_name : 东城区
                 * area_parent : 110100
                 * area_lv : 区
                 * area_ext :
                 * subs : []
                 */

                public String area_code;
                public String area_name;
                public String area_parent;
                public String area_lv;
                public String area_ext;
                public List<?> subs;
            }
        }
    }
}
