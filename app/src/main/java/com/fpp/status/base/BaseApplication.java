package com.fpp.status.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.fpp.status.db.DbManager;
import com.fpp.status.greendao.DaoSession;
import com.fpp.status.utils.Constant;
import com.fpp.status.utils.CrashHandler;
import com.fpp.status.utils.SPUtils;

import java.io.File;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;


/**
 * 作者:Jelly
 * 日期:2016/5/30
 * 详情:主要初始化对象以及捕获异常
 */
public class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {

    public static String PATH_DIR;
    public static String PATH_LOG;
    public static Context mContext;
    public static Toast mToast;

    // 收集日志信息
    private static Map<String, String> infos = new HashMap<>();
    private final String TAG = this.getClass().getSimpleName();
    // 用于格式化日期,作为日志文件名的一部分

    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int H, W;

    /**
     * 标识网络是否连接
     */
    private boolean isNetConnected;


    /**
     * 全局的context
     *
     * @return
     */
    public static Context getmContext() {
        return mContext;
    }

    // greenDao  session
    public static DaoSession daoSession;


    @Override
    public void onCreate() {
//      Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = getApplicationContext();
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
            // 添加下面这个
            MultiDex.install(this);
            super.onCreate();
            initApp();
            // 初始化数据库
            initDatabase();

        }
    }

    private void initDatabase() {
        //初始化数据库
        daoSession = DbManager.getDaoSession(mContext);

    }

    private void initApp() {
        getScreen(this);
        initFile();
        // 异常捕捉
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(mContext);

    }

    /**
     * 创建应用文件夹
     *
     * @return
     */
    public static String initFile() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {// 优先保存到SD卡中
            PATH_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "A_TEST";
            PATH_LOG = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "A_TEST" + File.separator + "LogFiles";
        } else {// 如果SD卡不存在，就保存到本应用的目录下
            PATH_DIR = mContext.getFilesDir().getAbsolutePath() + File.separator + "A_TEST";
            PATH_LOG = mContext.getFilesDir().getAbsolutePath() + File.separator + "A_TEST" + File.separator + "LogFiles";
        }
        File mFile = null;
        try {
            mFile = new File(PATH_DIR);
            if (!mFile.exists()) {
                mFile.mkdir();
            }
            mFile = new File(PATH_LOG);
            if (!mFile.exists()) {
                mFile.mkdir();
            }

            return mFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
    }


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

    }
}