package com.fpp.status.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者:Jelly
 * 日期:2016/5/30
 * 详情:主要初始化对象以及捕获异常
 */
public class BaseApplication implements Thread.UncaughtExceptionHandler {

    private static Context context;
    private static Toast mToast;
    // 用于格式化日期,作为日志文件名的一部分

    /**
     * 全局的context
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    public void onCreate() {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }

    }






    /**
     * 处理异常
     *
     * @param thread 当前线程
     * @param ex     异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

    }

}
