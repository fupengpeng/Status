package com.fpp.status.activity.eight.four;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/19 0019 9:33
 */

public class SharePreferencesActivity extends AppCompatActivity {


    @BindView(R.id.et_atvt_share_preferences_one)
    EditText etAtvtSharePreferencesOne;
    @BindView(R.id.et_atvt_share_preferences_two)
    EditText etAtvtSharePreferencesTwo;
    @BindView(R.id.btn_atvt_share_preferences_one)
    Button btnAtvtSharePreferencesOne;
    @BindView(R.id.btn_atvt_share_preferences_two)
    Button btnAtvtSharePreferencesTwo;
    @BindView(R.id.et_atvt_share_preferences_three)
    EditText etAtvtSharePreferencesThree;
    @BindView(R.id.et_atvt_share_preferences_four)
    EditText etAtvtSharePreferencesFour;
    @BindView(R.id.btn_atvt_share_preferences_three)
    Button btnAtvtSharePreferencesThree;
    @BindView(R.id.btn_atvt_share_preferences_four)
    Button btnAtvtSharePreferencesFour;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
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
        // 获取只能被本应用读写的SharedPreferences对象
        sharedPreferences = getSharedPreferences("default_sp", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void write(View resource) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + " hh:mm:ss ");
        // 存入当前时间
        editor.putString("time", sdf.format(new Date()));
        // 存入一个随机数
        editor.putInt("random", (int) (Math.random() * 100));
        // 提交所有存入的数据
        editor.commit();
    }

    public void read(View resource) {
        // 读取字符串数据
        String time = sharedPreferences.getString("time", null);
        // 读取int型数据
        int randNum = sharedPreferences.getInt("random", 0);
        String result = time == null ? "你暂时还未写入数据" : "写入时间为：" + time + "  \n 上次生成的随机数为 ： " + randNum;
        ToastUtils.showLong(this, result);
        LogUtils.e(result);

    }

    @OnClick({R.id.btn_atvt_share_preferences_one, R.id.btn_atvt_share_preferences_two,
            R.id.btn_atvt_share_preferences_three, R.id.btn_atvt_share_preferences_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_atvt_share_preferences_one:
                // 将输入的文字内容写入文件中
                try {
                    // 以追加方式打开文件输出流
                    FileOutputStream fos = openFileOutput("default_sp.bin", MODE_APPEND);
                    // 将FileOutputStream包装成PrintStream
                    PrintStream ps = new PrintStream(fos);
                    // 输出文件内容
                    ps.println(etAtvtSharePreferencesOne.getText().toString());
                    // 关闭文件输出流
                    ps.close();
                    etAtvtSharePreferencesOne.setText("");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_atvt_share_preferences_two:
                try {
                    // 打开文件输入流
                    FileInputStream fis = openFileInput("default_sp.bin");
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    StringBuilder sb = new StringBuilder("");

                    // 读取文件内容
                    while ((hasRead = fis.read(buff)) > 0) {
                        sb.append(new String(buff, 0, hasRead));
                    }
                    // 关闭文件输入流
                    fis.close();
                    etAtvtSharePreferencesTwo.setText(sb.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_atvt_share_preferences_three:
                try {
                    // 如果手机插入了SD卡，且应用具有SD卡访问权限
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        // 获取SD卡对应的存储目录
                        File sdCardDir = Environment.getExternalStorageDirectory();
                        File targetFile = new File(sdCardDir.getCanonicalPath() + "/default_sp.bin");
                        // 以指定文件创建 RandomAccessFile对象
                        RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                        // 将文件记录指针移动到最后
                        raf.seek(targetFile.length());
                        // 输出文件内容
                        raf.write(etAtvtSharePreferencesThree.getText().toString().getBytes());
                        // 关闭RandomAccessFile
                        raf.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_atvt_share_preferences_four:

                try {
                    // 如果手机插入了SD卡，且应用具有SD卡访问权限
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        // 获取SD卡对应的存储目录
                        File sdCardDir = Environment.getExternalStorageDirectory();
                        // 获取指定文件对应的输入流
                        FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + "/default_sp.bin");
                        // 将指定的输入流包装成BufferedReader
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                        StringBuilder sb = new StringBuilder("");
                        String line = null;

                        // 读取文件内容
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        // 关闭文件输入流
                        br.close();
                        etAtvtSharePreferencesFour.setText(sb.toString());
                        etAtvtSharePreferencesThree.setText("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
