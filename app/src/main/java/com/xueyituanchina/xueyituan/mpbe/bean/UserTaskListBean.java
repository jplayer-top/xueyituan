package com.xueyituanchina.xueyituan.mpbe.bean;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/12/4.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class UserTaskListBean extends BaseBean {

    /**
     * list : [{"id":16,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:23:47","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":17,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:30:01","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":18,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:30:20","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":19,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:40:09","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":20,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:40:22","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":21,"goods_title":"我是测试","goods_subtitle":"dd","status":"0","ct":"2018-12-04 17:40:52","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":22,"goods_title":"胜多负少的","goods_subtitle":"dd","status":"0","ct":"2018-12-04 17:49:40","cat_name":"特长","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/1137de5e7bc24967aa7f893d29064784.jpg","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","sp_name":"松子儿舞蹈学院"},{"id":23,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:49:40","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"},{"id":24,"goods_title":"胜多负少的","goods_subtitle":"dd","status":"0","ct":"2018-12-04 17:49:42","cat_name":"特长","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/1137de5e7bc24967aa7f893d29064784.jpg","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","sp_name":"松子儿舞蹈学院"},{"id":25,"goods_title":"我是测试","goods_subtitle":"f","status":"0","ct":"2018-12-04 17:49:42","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当"}]
     * rule : 1.用户每天可分享5-10条朋友圈课程图片或任务
     2.不得屏蔽微信好友查看您的朋友圈权限
     3.上传图片必须是朋友圈全屏截图
     4.朋友圈需要转发至少两小时后才可进行截图审核
     */

    public String rule;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 16
         * goods_title : 我是测试
         * goods_subtitle : f
         * status : 0 //status 0提交审核 1审核通过 2重新提交 3过期 4审核中
         * ct : 2018-12-04 17:23:47
         * cat_name : 文化课
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png
         * sp_name : 当当
         */

        public int id;
        public String goods_title;
        public String goods_subtitle;
        public String status;
        public String ct;
        public String cat_name;
        public String goods_thumb_img;
        public String sp_img;
        public String sp_name;
    }
}
