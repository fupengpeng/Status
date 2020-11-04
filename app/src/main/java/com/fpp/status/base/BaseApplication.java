package com.fpp.status.base;

import android.app.Application;
import android.content.Context;
<<<<<<< HEAD
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
=======
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.fpp.status.R;
import com.fpp.status.db.DbManager;
import com.fpp.status.greendao.DaoSession;
import com.fpp.status.utils.Constant;
import com.fpp.status.utils.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import androidx.multidex.MultiDex;
=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
import cn.jpush.android.api.JPushInterface;

/**
 * 作者:Jelly
 * 日期:2016/5/30
 * 详情:主要初始化对象以及捕获异常
 */
public class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {

<<<<<<< HEAD
    public static String PATH_DIR;
    public static String PATH_LOG;
    public static Context mContext;
    public static Toast mToast;
=======
    private static Context context;
    private static Toast mToast;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
    // 收集日志信息
    private static Map<String, String> infos = new HashMap<>();
    private final String TAG = this.getClass().getSimpleName();
    // 用于格式化日期,作为日志文件名的一部分

<<<<<<< HEAD
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int H, W;
=======
    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();
    public static int H,W;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
    public static BaseApplication app;

    /**
     * 标识网络是否连接
     */
    private boolean isNetConnected;

    /**
     * Application实例
     */
    private static BaseApplication instance;

    /**
     * 全局的context
     *
     * @return
     */
<<<<<<< HEAD
    public static Context getmContext() {
        return mContext;
=======
    public static Context getContext() {
        return context;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
    }


    // greenDao  session
    public static DaoSession daoSession;


    @Override
    public void onCreate() {
//      Thread.setDefaultUncaughtExceptionHandler(this);
<<<<<<< HEAD
        mContext = getApplicationContext();
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
=======
        context = getApplicationContext();
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
        }
        // 极光推送初始化
        initPush();

<<<<<<< HEAD
        // 添加下面这个
        MultiDex.install(this);
=======
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c


        // 极光推送初始化
        initPush();
        super.onCreate();
        // 初始化实例
        instance = this;
        initApp();

        // 初始化数据库
        initDatabase();


    }
<<<<<<< HEAD

    private void initDatabase() {
        //初始化数据库
        daoSession = DbManager.getDaoSession(mContext);

    }

    private void initApp() {
        app = this;
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
=======
    private void initDatabase() {
        //初始化数据库
        daoSession = DbManager.getDaoSession(context);

    }
    private void initApp() {
        app=this;
        getScreen(this);

    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H=dm.heightPixels;
        W=dm.widthPixels;
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
    }

    /**
     * 获取Application实例
     *
     * @return Application实例
     */
    public static BaseApplication getInstance() {
        return instance;
    }


<<<<<<< HEAD
=======


>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
    /**
     * 极光推送初始化
     */
    private void initPush() {
<<<<<<< HEAD
        SPUtils.put(Constant.MSG_OPEN, true);
=======
        SPUtils.put(Constant.MSG_OPEN,true);
>>>>>>> d4f24dd797a144b906a813cb89a6a7717fd0ec9c
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        if ((Boolean) SPUtils.get(Constant.MSG_OPEN, true)) {
            // 极光推送服务恢复正常工作
            JPushInterface.resumePush(getApplicationContext());
        } else {
            // 极光推送服务停止掉
            JPushInterface.stopPush(getApplicationContext());
        }
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}