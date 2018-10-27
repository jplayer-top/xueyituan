package com.xueyituanchina.xueyituan.mpbe.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Obl on 2018/10/27.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@Entity
public class LookWhatBean {
    @Id(autoincrement = true)
    private Long id;
    public int goods_id;
    public String goods_title;
    public String goods_thumb_img;
    public String goods_subtitle;
    public String goods_best_price;
    public String goods_org_price;
    public String dist;
    @Generated(hash = 71011075)
    public LookWhatBean(Long id, int goods_id, String goods_title,
            String goods_thumb_img, String goods_subtitle, String goods_best_price,
            String goods_org_price, String dist) {
        this.id = id;
        this.goods_id = goods_id;
        this.goods_title = goods_title;
        this.goods_thumb_img = goods_thumb_img;
        this.goods_subtitle = goods_subtitle;
        this.goods_best_price = goods_best_price;
        this.goods_org_price = goods_org_price;
        this.dist = dist;
    }
    @Generated(hash = 269344934)
    public LookWhatBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getGoods_id() {
        return this.goods_id;
    }
    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }
    public String getGoods_title() {
        return this.goods_title;
    }
    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }
    public String getGoods_thumb_img() {
        return this.goods_thumb_img;
    }
    public void setGoods_thumb_img(String goods_thumb_img) {
        this.goods_thumb_img = goods_thumb_img;
    }
    public String getGoods_subtitle() {
        return this.goods_subtitle;
    }
    public void setGoods_subtitle(String goods_subtitle) {
        this.goods_subtitle = goods_subtitle;
    }
    public String getGoods_best_price() {
        return this.goods_best_price;
    }
    public void setGoods_best_price(String goods_best_price) {
        this.goods_best_price = goods_best_price;
    }
    public String getGoods_org_price() {
        return this.goods_org_price;
    }
    public void setGoods_org_price(String goods_org_price) {
        this.goods_org_price = goods_org_price;
    }
    public String getDist() {
        return this.dist;
    }
    public void setDist(String dist) {
        this.dist = dist;
    }
}
