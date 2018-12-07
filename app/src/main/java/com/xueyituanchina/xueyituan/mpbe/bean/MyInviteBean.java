package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/12/7.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MyInviteBean extends BaseBean {

    /**
     * invImg : https://xueyituan.oss-cn-beijing.aliyuncs.com/invite/38c70dbe32cf4522add6ee59ec14edba.png
     * ruleImg : https://xueyituan.oss-cn-beijing.aliyuncs.com/invite/38c70dbe32cf4522add6ee59ec14edba.png
     * invUrl : https://www.xueyituanchina.cn/info/regiest.html?invcode=SA3XP&phone=null&name=18654539238
     * name : yuan
     * invList : [{"nick":"1234h","avatar":"https://xueyituan.oss-cn-beijing.aliyuncs.com/avatar/4433fa11f5ab4830995cff1b115e4546.jpg","status":0,"ct":"2018-11-20 16:24:33"},{"nick":"ypqe","avatar":"https://xueyituan.oss-cn-beijing.aliyuncs.com/avatar/21093bdbe7504152a85d5a54d050e742.jpg","status":0,"ct":"2018-11-20 16:24:06"}]
     * invNum : 2
     */

    public String invImg;
    public String ruleImg;
    public String invUrl;
    public String name;
    public int invNum;
    public List<InvListBean> invList;

    public static class InvListBean {
        /**
         * nick : 1234h
         * avatar : https://xueyituan.oss-cn-beijing.aliyuncs.com/avatar/4433fa11f5ab4830995cff1b115e4546.jpg
         * status : 0
         * ct : 2018-11-20 16:24:33
         */

        public String nick;
        public String avatar;
        public int status;
        public String ct;
    }
}
