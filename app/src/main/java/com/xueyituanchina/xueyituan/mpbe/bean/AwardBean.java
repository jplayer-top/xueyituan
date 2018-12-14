package com.xueyituanchina.xueyituan.mpbe.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/11/30.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardBean extends BaseBean {

    /**
     * bannerList : []
     * broadMsgList : ["用户正则领取了59.00元奖励金","用户圆圆领取了2.00元奖励金"]
     * taskList : [{"task_id":"gtask2018112616050419241","goods_title":"我是测试","goods_subtitle":"f","amount":401,"share_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/share/5ad6bc17aef34e4994070a69d407d33a.jpg","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当","shared":false},{"task_id":"gtask2018112616042285637","goods_title":"我是测试","goods_subtitle":"dd","amount":400,"share_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/share/b619bb30c2c24e428d13e54322441e97.jpg","cat_name":"文化课","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png","sp_name":"当当","shared":false},{"task_id":"gtask2018112615383392440","goods_title":"胜多负少的","goods_subtitle":"dd","amount":351,"share_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/share/686ca982df7649cd95aff8357f56ffcd.jpg","cat_name":"特长","goods_thumb_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/1137de5e7bc24967aa7f893d29064784.jpg","sp_img":"https://xueyituan.oss-cn-beijing.aliyuncs.com/icon/tv_index_album.png","sp_name":"松子儿舞蹈学院","shared":false}]
     * taskNum : 3
     * totalShared : 403
     */

    public int taskNum;
    public int totalShared;
    public List<BannerBean> bannerList;
    public List<String> broadMsgList;
    public ArrayList<TaskListBean> taskList;

    public static class BannerBean {
        public String img;
        public String url;
    }

    public static class TaskListBean implements Parcelable {
        /**
         * task_id : gtask2018112616050419241
         * goods_title : 我是测试
         * goods_subtitle : f
         * amount : 401
         * share_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/share/5ad6bc17aef34e4994070a69d407d33a.jpg
         * cat_name : 文化课
         * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png
         * sp_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png
         * sp_name : 当当
         * shared : false
         */

        public String task_id;
        public String goods_title;
        public String goods_subtitle;
        public int amount;
        public int goods_id;
        public String share_img;
        public String cat_name;
        public String goods_thumb_img;
        public String sp_img;
        public String sp_name;
        public boolean shared;

        protected TaskListBean(Parcel in) {
            task_id = in.readString();
            goods_title = in.readString();
            goods_subtitle = in.readString();
            amount = in.readInt();
            share_img = in.readString();
            cat_name = in.readString();
            goods_thumb_img = in.readString();
            sp_img = in.readString();
            sp_name = in.readString();
            shared = in.readByte() != 0;
        }

        public static final Creator<TaskListBean> CREATOR = new Creator<TaskListBean>() {
            @Override
            public TaskListBean createFromParcel(Parcel in) {
                return new TaskListBean(in);
            }

            @Override
            public TaskListBean[] newArray(int size) {
                return new TaskListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(task_id);
            dest.writeString(goods_title);
            dest.writeString(goods_subtitle);
            dest.writeInt(amount);
            dest.writeString(share_img);
            dest.writeString(cat_name);
            dest.writeString(goods_thumb_img);
            dest.writeString(sp_img);
            dest.writeString(sp_name);
            dest.writeByte((byte) (shared ? 1 : 0));
        }
    }
}
