package com.xueyituanchina.xueyituan.mpbe.model;


import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.LoginBean;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;


/**
 * Created by Obl on 2018/1/30.
 * com.modiwu.mah.mvp.model
 */

public class LoginModel extends BaseModel<XYTServer> {

    public LoginModel(Class<XYTServer> t) {
        super(t);
    }
    public Observable<MyInfoBean> requestMyInfo() {
        return mServer.myInfo().compose(new IoMainSchedule<>());
    }
    public Observable<LoginBean> requestLogin(String phone, String password) {
        return mServer.getLoginBean(phone, password)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestSms(Map<String, String> map) {
        return mServer.getSmsBean(map)
                .compose(new IoMainSchedule<>());
    }


    public Observable<BaseBean> requestVerfiyCode(String phone, String smCode) {
        return mServer.verfiyCode(phone, smCode)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestRegister(Map<String, String> map) {
        return mServer.register(map)
                .compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> requestForget(Map<String, String> map) {
        return mServer.forget(map)
                .compose(new IoMainSchedule<>());
    }

}
