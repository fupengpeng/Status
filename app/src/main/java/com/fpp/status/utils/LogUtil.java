package com.fpp.status.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.fpp.status.R;
import com.fpp.status.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log工具
 * author fpp
 * date 2019/3/26 14:38
 */
public class LogUtil {

    private static final String TAG = BaseApplication.mContext.getPackageName().substring(9);

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    @SuppressLint("StaticFieldLeak")
    private static LogUtil INSTANCE = null;
    private LogDumper mLogDumper = null;
    private int mPId;
    private Context mContext = BaseApplication.mContext;
    private String pkgName = mContext.getPackageName();

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private LogUtil(Context context) {
        init(context);
        mPId = android.os.Process.myPid();
    }

    public static LogUtil getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LogUtil(context);
        }
        return INSTANCE;
    }


    /**
     * 初始化目录
     *
     * @param context context
     */
    private void init(Context context) {
    }


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
     * 获取LogUtil的使用位置信息
     *
     * @return LogUtil的使用位置信息
     */
    private static StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtil.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }


    public void start() {
        if (mLogDumper == null)
            mLogDumper = new LogDumper(String.valueOf(mPId), BaseApplication.PATH_LOG);
        mLogDumper.start();
    }

    public void stop() {
        if (mLogDumper != null) {
            mLogDumper.stopLogs();
            mLogDumper = null;
        }
    }

    private class LogDumper extends Thread {

        private Process logcatProc;
        private BufferedReader mReader = null;
        private boolean mRunning = true;
        String cmds = null;
        private String mPID;
        private FileOutputStream out = null;


        LogDumper(String pid, String dir) {
            mPID = pid;
            try {
                out = new FileOutputStream(new File(dir, BaseApplication.mContext.getString(R.string.app_name) + "_"
                        + getFileName() + "" + ".log"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
            // cmds = "logcat  | grep \"(" + mPID + ")\"";//打印所有日志信息
            // cmds = "logcat -s way";//打印标签过滤信息
//            cmds = "logcat -v time *:e *:i | grep \"(" + mPID + ")\"";
            cmds = "logcat -v time | grep \"(" + mPID + ")\"";
            // cmds = "logcat -v time";
        }

        void stopLogs() {
            mRunning = false;
        }

        @Override
        public void run() {
            try {
                logcatProc = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(
                        logcatProc.getInputStream()), 1024);
                String line;
                while (mRunning && (line = mReader.readLine()) != null) {
                    if (!mRunning) {
                        break;
                    }
                    if (line.length() == 0) {
                        continue;
                    }
                    if (out != null && line.contains(mPID)) {
                        out.write((line + "\n").getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (logcatProc != null) {
                    logcatProc.destroy();
                    logcatProc = null;
                }
                if (mReader != null) {
                    try {
                        mReader.close();
                        mReader = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    out = null;
                }
            }
        }
    }

    private String getFileName() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return timeFormat.format(new Date(System.currentTimeMillis()));
    }


}