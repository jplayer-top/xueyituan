package com.xueyituanchina.xueyituan.mpbe.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Obl on 2018/9/4.
 * com.xueyituanchina.xueyituan.mpbe.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@Entity
public class LocalBean {
    @Id(autoincrement = true)
    private Long id;
    public String name;
    public String phone;
    public String local;
    @Transient
    public int goods_id;
    @Generated(hash = 380193353)
    public LocalBean(Long id, String name, String phone, String local) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.local = local;
    }
    @Generated(hash = 140313795)
    public LocalBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLocal() {
        return this.local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
   
}
