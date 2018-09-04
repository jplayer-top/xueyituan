package com.xueyituanchina.xueyituan.mpbe.model;

import android.content.Context;

import com.xueyituanchina.xueyituan.greendao.DaoManager;
import com.xueyituanchina.xueyituan.greendao.LocalBeanDao;
import com.xueyituanchina.xueyituan.mpbe.bean.LocalBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import top.jplayer.baseprolibrary.utils.LogUtil;


/**
 * Created by Obl on 2018/8/10.
 * top.jplayer.quick_test.mvp.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LocalDaoModel {
    private DaoManager mManager;

    public LocalDaoModel(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成bean记录的插入，如果表未创建，先创建bean表
     *
     * @param bean
     * @return
     */
    public boolean insertUser(LocalBean bean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getLocalBeanDao().insert(bean) != -1;
        LogUtil.e(bean);
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     */
    public boolean insertMultUser(final List<LocalBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (LocalBean bean : beanList) {
                        mManager.getDaoSession().insertOrReplace(bean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @return
     */
    public boolean updatebean(LocalBean bean) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param bean
     * @return
     */
    public boolean deleteUserBean(LocalBean bean) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(LocalBean.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<LocalBean> queryAllbean() {
        return mManager.getDaoSession().loadAll(LocalBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public LocalBean querybeanById(long key) {
        return mManager.getDaoSession().load(LocalBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<LocalBean> querybeanByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(LocalBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     */
    public List<LocalBean> querybeanByQueryBuilder(long id) {
        QueryBuilder<LocalBean> queryBuilder = mManager.getDaoSession().queryBuilder(LocalBean.class);
        return queryBuilder.where(LocalBeanDao.Properties.Id.eq(id)).list();
    }
}
