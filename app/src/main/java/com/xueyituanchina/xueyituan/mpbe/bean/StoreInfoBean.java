package com.xueyituanchina.xueyituan.mpbe.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class StoreInfoBean extends BaseBean {


    /**
     * spName : 当当
     * spImg : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/shop/e1febb62e64b4edb82a10fd351ee6faa.png
     * wallet : 0.00
     * orderList : [{"order_id":"2018102714381664701","order_title":"我是测试","goods_id":10015,"goods_thumb_img":"xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png","price":1,"amount":1,"total_price":1,"user_id":6,"buyer":6,"pay_type":2,"pay_status":1,"trans_num":"4200000204201810277999601859","order_status":1,"rp_phone":"17667936541","create_time":"2018-10-27 14:38:17","pay_time":"2018-10-27 14:38:28","priceStr":"0.01","totalPriceStr":"0.01","thumbImgList":["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png"],"thumbImg":"https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png"}]
     * todayOrder : 0
     * todayPend : 0
     * todayIncome : 0.00
     * totalIncome : 0.01
     * totalUser : 1
     * totalOrder : 1
     * payOrder : 1
     * rate : 100.0
     */

    public String spName;
    public String spImg;
    public String wallet;
    public int todayOrder;
    public int todayPend;
    public String todayIncome;
    public String totalIncome;
    public int totalUser;
    public int totalOrder;
    public int payOrder;
    public double rate;
    public List<OrderListBean> orderList;

    public static class OrderListBean implements Parcelable {
        /**
         * order_id : 2018102714381664701
         * order_title : 我是测试
         * goods_id : 10015
         * goods_thumb_img : xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png
         * price : 1
         * amount : 1
         * total_price : 1
         * user_id : 6
         * buyer : 6
         * pay_type : 2
         * pay_status : 1
         * trans_num : 4200000204201810277999601859
         * order_status : 1
         * rp_phone : 17667936541
         * create_time : 2018-10-27 14:38:17
         * pay_time : 2018-10-27 14:38:28
         * priceStr : 0.01
         * totalPriceStr : 0.01
         * thumbImgList : ["https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png"]
         * thumbImg : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/goods/8b2d54c99a404b068ade1ac3facfaf9b.png
         */

        public String order_id;
        public String order_title;
        public int goods_id;
        public String goods_thumb_img;
        public int price;
        public int amount;
        public int total_price;
        public int user_id;
        public int buyer;
        public int pay_type;
        public int pay_status;
        public String trans_num;
        public int order_status;
        public String rp_phone;
        public String create_time;
        public String pay_time;
        public String priceStr;
        public String totalPriceStr;
        public String thumbImg;
        public List<String> thumbImgList;

        protected OrderListBean(Parcel in) {
            order_id = in.readString();
            order_title = in.readString();
            goods_id = in.readInt();
            goods_thumb_img = in.readString();
            price = in.readInt();
            amount = in.readInt();
            total_price = in.readInt();
            user_id = in.readInt();
            buyer = in.readInt();
            pay_type = in.readInt();
            pay_status = in.readInt();
            trans_num = in.readString();
            order_status = in.readInt();
            rp_phone = in.readString();
            create_time = in.readString();
            pay_time = in.readString();
            priceStr = in.readString();
            totalPriceStr = in.readString();
            thumbImg = in.readString();
            thumbImgList = in.createStringArrayList();
        }

        public static final Creator<OrderListBean> CREATOR = new Creator<OrderListBean>() {
            @Override
            public OrderListBean createFromParcel(Parcel in) {
                return new OrderListBean(in);
            }

            @Override
            public OrderListBean[] newArray(int size) {
                return new OrderListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(order_id);
            dest.writeString(order_title);
            dest.writeInt(goods_id);
            dest.writeString(goods_thumb_img);
            dest.writeInt(price);
            dest.writeInt(amount);
            dest.writeInt(total_price);
            dest.writeInt(user_id);
            dest.writeInt(buyer);
            dest.writeInt(pay_type);
            dest.writeInt(pay_status);
            dest.writeString(trans_num);
            dest.writeInt(order_status);
            dest.writeString(rp_phone);
            dest.writeString(create_time);
            dest.writeString(pay_time);
            dest.writeString(priceStr);
            dest.writeString(totalPriceStr);
            dest.writeString(thumbImg);
            dest.writeStringList(thumbImgList);
        }
    }
}
