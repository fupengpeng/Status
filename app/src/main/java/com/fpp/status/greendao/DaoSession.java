package com.fpp.status.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fpp.status.entity.Student;
import com.fpp.status.entity.TopicBean;

import com.fpp.status.greendao.StudentDao;
import com.fpp.status.greendao.TopicBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig topicBeanDaoConfig;

    private final StudentDao studentDao;
    private final TopicBeanDao topicBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        topicBeanDaoConfig = daoConfigMap.get(TopicBeanDao.class).clone();
        topicBeanDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        topicBeanDao = new TopicBeanDao(topicBeanDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(TopicBean.class, topicBeanDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        topicBeanDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TopicBeanDao getTopicBeanDao() {
        return topicBeanDao;
    }

}
