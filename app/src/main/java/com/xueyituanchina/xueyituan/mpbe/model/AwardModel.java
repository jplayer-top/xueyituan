package com.xueyituanchina.xueyituan.mpbe.model;

import com.xueyituanchina.xueyituan.mpbe.XYTServer;
import com.xueyituanchina.xueyituan.mpbe.bean.AwardBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskBean;
import com.xueyituanchina.xueyituan.mpbe.bean.PushTaskDestoryBean;
import com.xueyituanchina.xueyituan.mpbe.bean.TaskGoodsListBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UpdateUrlBean;
import com.xueyituanchina.xueyituan.mpbe.bean.UserTaskListBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.net.retrofit.IoMainSchedule;

/**
 * Created by Obl on 2018/12/3.
 * com.xueyituanchina.xueyituan.mpbe.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AwardModel extends BaseModel<XYTServer> {
    public AwardModel(Class<XYTServer> t) {
        super(t);
    }

    public Observable<AwardBean> awardList() {
        return mServer.awardList().compose(new IoMainSchedule<>());
    }

    public Observable<UserTaskListBean> taskList() {
        return mServer.taskList().compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> shareOk(String taskId) {
        return mServer.shareOk(taskId).compose(new IoMainSchedule<>());
    }

    public Observable<BaseBean> pushShare(String taskId, String url) {
        return mServer.pushShare(taskId, url).compose(new IoMainSchedule<>());
    }

    public Observable<TaskGoodsListBean> taskGoodsList() {
        return mServer.taskGoodsList().compose(new IoMainSchedule<>());
    }

    public Observable<PushTaskDestoryBean> tasksDestory() {
        return mServer.tasksDestory().compose(new IoMainSchedule<>());
    }

    public Observable<PushTaskBean> taskPub(Map<String, String> map) {
        return mServer.taskPub(map).compose(new IoMainSchedule<>());
    }

    public Observable<UpdateUrlBean> updateScreen(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return mServer.updateScreen(body)
                .compose(new IoMainSchedule<>());
    }
}
