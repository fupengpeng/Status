package com.fpp.status.activity.eight.four;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fpp.status.R;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/19 0019 9:33
 */

public class SQLiteActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
        initViewCount();

    }




    private void initViewCount() {

    }

    private void initView() {
        //
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/mnt/db/temp.dp3",null);
        // 当未指定SQLiteDatabase.CursorFactory参数时，则使用默认工厂

        // 定义建表语句
        String sql = "create table user_inf(user_id integer primary key , user_name varchar(255) , user_pass varchar(255))";
        // 执行SQL语句
        db.execSQL(sql);


    }

    public void write(View resource) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + " hh:mm:ss ");
        //
    }

    public void read(View resource) {
        //

    }




}
