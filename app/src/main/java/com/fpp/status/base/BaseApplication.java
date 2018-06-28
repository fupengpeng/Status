package com.fpp.status.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.fpp.status.utils.Constant;
import com.fpp.status.utils.SPUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者:Jelly
 * 日期:2016/5/30
 * 详情:主要初始化对象以及捕获异常
 */
public class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {

    private static Context context;
    private static Toast mToast;
    // 收集日志信息
    private static Map<String, String> infos = new HashMap<>();
    private final String TAG = this.getClass().getSimpleName();
    // 用于格式化日期,作为日志文件名的一部分



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
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
//      Thread.setDefaultUncaughtExceptionHandler(this);
        context = getApplicationContext();
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        // 极光推送初始化
        initPush();



        // 极光推送初始化
        initPush();
        super.onCreate();
        // 初始化实例
        instance = this;
    }

    /**
     * 获取Application实例
     *
     * @return Application实例
     */
    public static BaseApplication getInstance() {
        return instance;
    }




    /**
     * 极光推送初始化
     */
    private void initPush() {
        SPUtils.put(Constant.MSG_OPEN,true);
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