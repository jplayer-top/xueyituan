package com.xueyituanchina.xueyituan.mpbe.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserOrderListBean extends BaseBean {

    public List<ListBean> list;
    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 1;

    public static class ListBean extends AbstractExpandableItem<ListBean.ReMendBean> implements MultiItemEntity {
        /**
         * month : 本月
         * in : 0.00
         * out : 0.00
         * list : []
         */

        public String month;
        public String in;
        public String out;
        public List<ReMendBean> list;

        @Override
        public int getLevel() {
            return LEVEL_0;
        }

        @Override
        public int getItemType() {
            return LEVEL_0;
        }

        public static class ReMendBean implements MultiItemEntity {
            public String money;
            public String remark;
            public String ct;
            public String type;

            @Override
            public int getItemType() {
                return LEVEL_1;
            }
        }
    }

}
