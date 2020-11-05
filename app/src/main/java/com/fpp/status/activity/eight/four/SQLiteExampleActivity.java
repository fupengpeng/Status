package com.fpp.status.activity.eight.four;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fpp.status.R;
import com.fpp.status.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/19 0019 9:33
 */

public class SQLiteExampleActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    @BindView(R.id.et_atvt_sqlite_example_one)
    EditText etAtvtSqliteExampleOne;
    @BindView(R.id.et_atvt_sqlite_example_two)
    EditText etAtvtSqliteExampleTwo;
    @BindView(R.id.btn_atvt_sqlite_example_one)
    Button btnAtvtSqliteExampleOne;
    @BindView(R.id.et_atvt_sqlite_example_three)
    EditText etAtvtSqliteExampleThree;
    @BindView(R.id.btn_atvt_sqlite_example_two)
    Button btnAtvtSqliteExampleTwo;

    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example);
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

    private void initView() {
        // 创建DatabaseHelper对象，指定数据库版本为1，此处使用相对路径即可
        // 数据库文件会自动保存在程序的数据文件夹的database目录下。
        dbHelper = new DatabaseHelper(this, "muDict.db3", null, 1);

    }

    private void insertData(SQLiteDatabase db, String word, String detail) {
        // 执行插入语句
        String insertSql = "insert into dict values(null,?,?)";
        db.execSQL(insertSql, new String[]{word, detail});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时，关闭DatabaseHelper里面的SQLiteDatabase
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    @OnClick({R.id.btn_atvt_sqlite_example_one, R.id.btn_atvt_sqlite_example_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_atvt_sqlite_example_one:
                // 获取用户输入数据
                String title = etAtvtSqliteExampleOne.getText().toString();
                String content = etAtvtSqliteExampleTwo.getText().toString();


                // 获取用户输入
                String word = etAtvtSqliteExampleOne.getText().toString();
                String detail = etAtvtSqliteExampleTwo.getText().toString();
                // 插入生词记录
                insertData(dbHelper.getReadableDatabase(), word, detail);
                // 显示提示信息
                ToastUtils.showLong(this, "添加生词成功！");

                break;
            case R.id.btn_atvt_sqlite_example_two:
                // 获取用户输入
                String key = etAtvtSqliteExampleThree.getText().toString();
                // 执行查询
                Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from dict where word like ? or detail like ? ",
                        new String[]{"%" + key + "%", "%" + key + "%"});
                // 创建一个Bundle对象
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                // 创建一个Intent
                Intent intent = new Intent(SQLiteExampleActivity.this, ResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);

                break;
        }
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        // 遍历 Cursor 结果集
        while (cursor.moveToNext()) {
            // 将结果集中的数据存入
            Map<String, String> map = new HashMap<String, String>();
            // 取出查询记录中第2列，第3列的值
            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}
