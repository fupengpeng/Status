package com.fpp.status.db;

import android.content.Context;


import com.fpp.status.entity.TopicBean;
import com.fpp.status.greendao.TopicBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Description:  题目数据处理工具类
 * Author: fpp
 * Date: 2018/7/2  13:55
 */

public class TopicBeanDaoHelper {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param topicBean
     */
    public static void insertData(Context context, TopicBean topicBean) {

        DbManager.getDaoSession(context).getTopicBeanDao().insert(topicBean);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<TopicBean> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getTopicBeanDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param topicBean
     */
    public static void saveData(Context context, TopicBean topicBean) {
        DbManager.getDaoSession(context).getTopicBeanDao().save(topicBean);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param topicBean 删除具体内容
     */
    public static void deleteData(Context context, TopicBean topicBean) {
        DbManager.getDaoSession(context).getTopicBeanDao().delete(topicBean);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getTopicBeanDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getTopicBeanDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param topicBean
     */
    public static void updateData(Context context, TopicBean topicBean) {
        DbManager.getDaoSession(context).getTopicBeanDao().update(topicBean);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<TopicBean> queryAll(Context context) {
        QueryBuilder<TopicBean> builder = DbManager.getDaoSession(context).getTopicBeanDao().queryBuilder();

        return builder.build().list();
    }

    /**
     * 根据id，其他的字段类似
     *
     * @param context
     * @param id
     * @return
     */
    public static List<TopicBean> queryForId(Context context, long id) {
        QueryBuilder<TopicBean> builder = DbManager.getDaoSession(context).getTopicBeanDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(TopicBeanDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<TopicBean> build = builder.where(TopicBeanDao.Properties.Id.eq(id)).build();
        // List<TopicBean> list = build.list();
        return builder.where(TopicBeanDao.Properties.Id.eq(id)).list();
    }
}

