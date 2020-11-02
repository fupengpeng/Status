package com.fpp.status.activity.eight.four;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/19 0019 9:33
 */

public class SQLiteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    @BindView(R.id.et_atvt_sqlite_one)
    EditText etAtvtSqliteOne;
    @BindView(R.id.et_atvt_sqlite_two)
    EditText etAtvtSqliteTwo;
    @BindView(R.id.btn_atvt_sqlite_one)
    Button btnAtvtSqliteOne;
    @BindView(R.id.lv_atvt_sqlite)
    ListView lvAtvtSqlite;
    @BindView(R.id.btn_atvt_sqlite_two)
    Button btnAtvtSqliteTwo;
    private ContentValues values;

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

    }

    private void initTransaction() {
        // 开始事务
        db.beginTransaction();
        try {
            // 执行 DML 语句

            // 调用该方法设置事务成功，否则endTransaction()方法回滚事务
            db.endTransaction();
        } finally {
            // 由事务的标志决定是提交事务还是回滚事务
            db.endTransaction();
        }

    }

    private void initSQLiteOperation() {
        // TODO: 2018/4/20 0020  插入
        values = new ContentValues();
        values.put("name", "张三");
        values.put("age", 500);
        // 返回新添记录的行号，该行号是一个内部值，与主键id无关，发生错误返回-1
        long rowid = db.insert("person_inf", null, values);

        // TODO: 2018/4/20 0020  更新
        values = new ContentValues();
        // 存放更新后的人名
        values.put("name", "李四");
        int updateResult = db.update("person_inf", values, "_id > ?", new String[]{"20"});

        // TODO: 2018/4/20 0020  删除
        int deleteResult = db.delete("person_inf", "person_name like ? ", new String[]{"张"});

        // TODO: 2018/4/20 0020  查询
        Cursor cursor = db.query("person_inf ", new String[]{" _id,name,age "}, " name like ? ",
                new String[]{" 张% "}, null, null, " personid desc ", " 5,10 ");
        // 处理结果集
        cursor.close();

    }

    private void initView() {
        // 创建或者打开数据库
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/my.dp3", null);
        LogUtils.e("数据库路径 = " + this.getFilesDir().toString());
        // 当未指定SQLiteDatabase.CursorFactory参数时，则使用默认工厂

    }

    /**/
    @OnClick({R.id.btn_atvt_sqlite_one, R.id.btn_atvt_sqlite_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_atvt_sqlite_two:
                Intent intent = new Intent(SQLiteActivity.this, SQLiteExampleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_atvt_sqlite_one:
                // 获取用户输入数据
                String title = etAtvtSqliteOne.getText().toString();
                String content = etAtvtSqliteTwo.getText().toString();

                try {
                    insertData(db, title, content);
                    String selectSql = "select * from news_inf";
                    Cursor cursor = db.rawQuery(selectSql, null);
                    inflateList(cursor);
                } catch (SQLiteException e) {
                    // 执行DDL创建数据库表
                    String createSql = "create table news_inf(_id integer primary key autoincrement," +
                            "news_title varchar(255)," +
                            "news_content varchar(255))";
                    db.execSQL(createSql);
                    // 执行insert语句插入数据
                    insertData(db, title, content);
                    // 执行查询
                    String selectSql = "select * from news_inf";
                    Cursor cursor = db.rawQuery(selectSql, null);
                    inflateList(cursor);
                }
                break;
        }
    }

    private void inflateList(Cursor cursor) {
        // 填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLiteActivity.this,
                R.layout.line, cursor,
                new String[]{"news_title", "news_content"},
                new int[]{R.id.tv_lvi_title, R.id.file_name},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 显示数据
        lvAtvtSqlite.setAdapter(adapter);


    }

    private void insertData(SQLiteDatabase db, String title, String content) {
        // 执行插入语句
        String insertSql = "insert into news_inf values(null,?,?)";
        db.execSQL(insertSql, new String[]{title, content});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时，关闭SQLiteDatabase
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

}
