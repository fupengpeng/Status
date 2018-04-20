package com.fpp.status.activity.eight.four;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpp.status.utils.LogUtils;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/20 0020 13:14
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL = "create table dict(_id integer primary key autoincrement,word,detail)";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // 第一次使用数据库建表
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.e("--------onUpgrade Called---------" + oldVersion + "-------->" + newVersion);
    }
}
