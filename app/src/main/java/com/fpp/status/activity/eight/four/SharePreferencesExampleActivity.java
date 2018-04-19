package com.fpp.status.activity.eight.four;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/19 0019 9:33
 */

public class SharePreferencesExampleActivity extends AppCompatActivity {


    @BindView(R.id.path)
    TextView path;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.parent)
    Button parent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // 记录当前的父文件夹
    File currentParent;
    // 记录当前路径下的所有文件的文件数组
    File[] currentFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences_example);
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
        sharedPreferences = getSharedPreferences("count", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("count", 0);
        ToastUtils.showLong(this, "程序以前被使用了 " + count + " 次！");
        LogUtils.e("程序以前被使用了 " + count + " 次！");
        editor.putInt("count", ++count);
        editor.commit();
    }

    private void initView() {
        // 获取系统的sd卡目录
        File root = new File("/mnt/sdcard");
        // 如果sd卡存在
        if (root.exists()){
            currentParent = root;
            currentFiles = root.listFiles();
            // 使用当前目录下的全部文件、文件夹来填充ListView
            inflateListView(currentFiles);
        }
        // 为listview列表项单击事件添加监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 用户单击了文件，直接返回，不作任何处理
                if (currentFiles[position].isFile()){
                    return;
                }
                // 获取用户单击的文件夹下所有文件
                File[] tmp = currentFiles[position].listFiles();
                if (tmp == null || tmp.length == 0){
                    ToastUtils.showLong(SharePreferencesExampleActivity.this,"当前路径不可访问或该路径下没有文件");
                    LogUtils.e("当前路径不可访问或该路径下没有文件");
                }else {
                    // 获取用户单击的列表项对应的文件夹，设为当前的父文件夹
                    currentParent = currentFiles[position];
                    // 保存当前父文件夹内的全部文件和文件夹
                    currentFiles = tmp;
                    // 再次刷新listview
                    inflateListView(currentFiles);
                }

            }
        });

    }

    private void inflateListView(File[] files) {
        // 创建一个List 集合 ，List 集合的元素是Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String,Object> listItem = new HashMap<String, Object>();
            // 如果当前File是文件夹，使用folder图标，否则使用file图标
            if (files[i].isDirectory()){
                listItem.put("icon",R.drawable.shilipic);
            }else {
                listItem.put("icon",R.drawable.meinv02);
            }
            listItem.put("fileName",files[i].getName());
            // 添加List项
            listItems.add(listItem);
        }

        // 创建一个SimpleAdapter对象
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.line,new String[]{"icon","fileName"},
                new int[]{R.id.icon,R.id.file_name});

        // 为listview设置Adapter
        list.setAdapter(simpleAdapter);


        try {
            path.setText("当前路径为：" + currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @OnClick({R.id.path, R.id.parent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.path:

                try {
                    if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")){
                        // 获取上一级目录
                        currentParent = currentParent.getParentFile();
                        // 列出当前目录下的所有文件
                        currentFiles = currentParent.listFiles();
                        // 再次更新listview
                        inflateListView(currentFiles);

                    }
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case R.id.parent:
                break;
        }
    }
}
