package com.fpp.status.activity.ten.one;

import android.content.Context;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.v7.app.AppCompatActivity;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.fpp.status.R;
import com.fpp.status.db.StudentDaoHelper;
import com.fpp.status.db.TopicBeanDaoHelper;
import com.fpp.status.entity.Student;
import com.fpp.status.entity.TopicBean;
import com.fpp.status.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;
=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/2  14:49
 */

public class GreenDaoActivity extends AppCompatActivity {

    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.updata)
    Button updata;
    @BindView(R.id.check)
    Button check;
    @BindView(R.id.deleteAll)
    Button deleteAll;
    @BindView(R.id.check_id)
    Button checkId;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private Context mContext;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        mContext = this;
        initData();
        initListener();

    }

    private List<Student> studentList = new ArrayList<>();

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 20; i++) {
            student = new Student((long) i, "huang" + i, 25);
            studentList.add(student);
        }

    }

    private void initListener() {
        /**
         *增
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDaoHelper.insertData(mContext, studentList);
            }
        });
        /**
         * 删
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student((long) 5, "haung" + 5, 25);
                /**
                 * 根据特定的对象删除
                 */
                StudentDaoHelper.deleteData(mContext, student);
                /**
                 * 根据主键删除
                 */
                StudentDaoHelper.deleteByKeyData(mContext, 7);
            }
        });
        /**
         *删除所有
         */
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDaoHelper.deleteAllData(mContext);
            }
        });
        /**
         * 更新
         */
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student((long) 2, "haungxiaoguo", 16516);
                StudentDaoHelper.updateData(mContext, student);
            }
        });
        /**
         * 查询全部
         */
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = StudentDaoHelper.queryAll(mContext);
                for (int i = 0; i < students.size(); i++) {
                  LogUtil.e("Log", students.get(i).getName());
                }
            }
        });
        /**
         * 根据id查询
         */
        checkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = StudentDaoHelper.queryForId(mContext, 10);
                for (int i = 0; i < students.size(); i++) {
                    LogUtil.e("Log", students.get(i).getName());
                }
            }
        });
    }
}
