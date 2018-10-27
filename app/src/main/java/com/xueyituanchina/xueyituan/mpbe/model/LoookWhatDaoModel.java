package com.xueyituanchina.xueyituan.mpbe.model;

import android.content.Context;

import com.xueyituanchina.xueyituan.greendao.DaoManager;
import com.xueyituanchina.xueyituan.greendao.LookWhatBeanDao;
import com.xueyituanchina.xueyituan.mpbe.bean.LookWhatBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import top.jplayer.baseprolibrary.utils.LogUtil;


/**
 * Created by Obl on 2018/8/10.
 * top.jplayer.quick_test.mvp.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LoookWhatDaoModel {
    private DaoManager mManager;

    public LoookWhatDaoModel(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成bean记录的插入，如果表未创建，先创建bean表
     *
     * @param bean
     * @return
     */
    public boolean insertUser(LookWhatBean bean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getLookWhatBeanDao().insert(bean) != -1;
        LogUtil.e(bean);
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     */
    public boolean insertMultUser(final List<LookWhatBean> beanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (LookWhatBean bean : beanList) {
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
    public boolean updatebean(LookWhatBean bean) {
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
    public boolean deleteUserBean(LookWhatBean bean) {
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
            mManager.getDaoSession().deleteAll(LookWhatBean.class);
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
    public List<LookWhatBean> queryAllbean() {
        return mManager.getDaoSession().loadAll(LookWhatBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public LookWhatBean querybeanById(long key) {
        return mManager.getDaoSession().load(LookWhatBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<LookWhatBean> querybeanByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(LookWhatBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     */
    public List<LookWhatBean> querybeanByQueryBuilder(long id) {
        QueryBuilder<LookWhatBean> queryBuilder = mManager.getDaoSession().queryBuilder(LookWhatBean.class);
        return queryBuilder.where(LookWhatBeanDao.Properties.Id.eq(id)).list();
    }
}
