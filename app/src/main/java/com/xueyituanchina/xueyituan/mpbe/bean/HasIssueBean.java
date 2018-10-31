package com.xueyituanchina.xueyituan.mpbe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HasIssueBean implements Parcelable {
    /**
     * descd : 太好了
     * score : 4.1
     * goods_title : aaa_1111
     * goods_thumb_img : https://xueyituan.oss-cn-beijing.aliyuncs.com/xyt/1.jpg
     * sp_name : 松子儿舞蹈学院
     * cat_name : 特长
     * ct : 2018-09-05 15:19:08
     */

    public String descd;
    public double score;
    public String goods_title;
    public String goods_thumb_img;
    public String sp_name;
    public String cat_name;
    public String ct;
    public String nick;
    public boolean anonymous;


    protected HasIssueBean(Parcel in) {
        descd = in.readString();
        score = in.readDouble();
        goods_title = in.readString();
        goods_thumb_img = in.readString();
        sp_name = in.readString();
        cat_name = in.readString();
        ct = in.readString();
        nick = in.readString();
        anonymous = in.readByte() != 0;
    }

    public static final Creator<HasIssueBean> CREATOR = new Creator<HasIssueBean>() {
        @Override
        public HasIssueBean createFromParcel(Parcel in) {
            return new HasIssueBean(in);
        }

        @Override
        public HasIssueBean[] newArray(int size) {
            return new HasIssueBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descd);
        dest.writeDouble(score);
        dest.writeString(goods_title);
        dest.writeString(goods_thumb_img);
        dest.writeString(sp_name);
        dest.writeString(cat_name);
        dest.writeString(ct);
        dest.writeString(nick);
        dest.writeByte((byte) (anonymous ? 1 : 0));
    }
}
