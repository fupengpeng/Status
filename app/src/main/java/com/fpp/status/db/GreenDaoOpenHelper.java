package com.fpp.status.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.fpp.status.greendao.DaoMaster;
import com.fpp.status.greendao.StudentDao;
import com.fpp.status.greendao.TopicBeanDao;

import org.greenrobot.greendao.database.Database;


/**
 * Description:
 * Author: fpp
 * Date: 2018/7/2  12:00
 */

public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {

    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db
                , TopicBeanDao.class
                , StudentDao.class
        );
    }


}




