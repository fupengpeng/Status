package com.example.jpushdemo;

import android.util.Log;

/**
 * Log管理类
 */
public class LogUtils {

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "jdlx";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.i(TAG, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.d(TAG, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(TAG, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.v(TAG, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.i(tag, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.d(tag, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(tag, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.v(tag, msg + " (" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
        }
    }

    /**
     * 获取LogUtils的使用位置信息
     *
     * @return LogUtils的使用位置信息
     */
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}