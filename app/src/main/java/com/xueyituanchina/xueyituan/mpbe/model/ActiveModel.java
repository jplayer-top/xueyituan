package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.NearbyActiveBean;
import com.xueyituanchina.xueyituan.mpbe.bean.ShareBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/8/21.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ActiveModel extends BaseModel<XYTServer> {
    public ActiveModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<NearbyActiveBean> nearbyList() {
        return mServer.active_nearby_list().compose(new IoMainSchedule<>());
    }

    public Observable<NearbyActiveBean> weekList() {
        return mServer.week_list().compose(new IoMainSchedule<>());
    }

    public Observable<ShareBean> pubActivity(RequestBody body) {
        return mServer.pubActivity(body)
                .compose(new IoMainSchedule<>());
    }

    public Observable<UpdateUrlBean> updatePoster(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return mServer.updatePoster(body)
                .compose(new IoMainSchedule<>());
    }
}
