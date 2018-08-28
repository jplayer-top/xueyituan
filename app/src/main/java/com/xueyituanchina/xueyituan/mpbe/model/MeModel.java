package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.MyInfoBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/27.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class MeModel extends BaseModel<XYTServer> {

    public MeModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<MyInfoBean> requestMyInfo() {
        return mServer.myInfo().compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> updateNick(String nick) {
        return mServer.updateNick(nick).compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> verifyPw(String pw) {
        return mServer.verifyPw(pw).compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> updatePw(String opw, String npw) {
        return mServer.updatePw(opw, npw).compose(new IoMainSchedule<>());
    }

    public Observable<MyInfoBean> updateAvatar(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return mServer.updateAvatar(body)
                .compose(new IoMainSchedule<>());
    }
}
