package com.fpp.status.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author fupengpeng
 * @description 倒计时类
 * @data 2018/3/21 0021 15:49
 */

public class TimeCountDown extends TextView {

    private static final String TAG = TimeCountDown.class.getSimpleName();
    private static final int COUNT_DOWN_START = 1;//开始计时
    private static final int COUNT_DOWN_LOADING = 2;//计数中
    private static final int COUNT_DOWN_FINISH = 3;//计数完成
    private static final int COUNT_DOWN_ERROR = 4;//计数出错
    /**
     * 倒计时时间为60 s
     */
    private int countTime = 60;

    /**
     * 变量，用来计数当前倒计时的时间
     */
    private int mCount;

    /**
     * 倒计时之前显示的文字
     */
    private String beforeCount = "获取验证码";

    /**
     * 倒计时之后显示的文字
     */
    private String afterCount = "秒";

    /**
     * handler
     */
    private Handler mHandler;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private OnTimerCountDownListener onTimerCountDownListener;

    public TimeCountDown(Context context) {
        super(context);
        init();
    }

    public TimeCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        mCount = countTime;
        setText(beforeCount);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case COUNT_DOWN_START:
                        if (null != onTimerCountDownListener) {
                            onTimerCountDownListener.onCountDownStart();
                        }
                        mCount--;
                        break;
                    case COUNT_DOWN_LOADING:
                        if (null != onTimerCountDownListener) {
                            onTimerCountDownListener.onCountDownLoading(mCount);
                        }
                        // TODO: 2018/3/21 0021 设置倒计时时所展示内容
                        setText("重新发送(" + msg.arg1 + afterCount + ")");
                        mCount--;
                        break;
                    case COUNT_DOWN_FINISH:
                        if (null != onTimerCountDownListener) {
                            onTimerCountDownListener.onCountDownFinish();
                        }
                        if (mCount < 0) {
                            //当倒计时时间小于0后，取消计时任务
                            mTimer.cancel();
                            setText("获取验证码");
                            mCount = countTime;
                        }
                        break;
                    case COUNT_DOWN_ERROR:
                        if (null != onTimerCountDownListener) {
                            onTimerCountDownListener.onCountDownError();
                        }
                        break;
                }
            }
        };
    }

    public void initTimer() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "当前count:" + mCount);
                switch (mCount) {
                    case 60:
                        sendMessage(COUNT_DOWN_START, 0);
                        break;
                    case -1:
                        sendMessage(COUNT_DOWN_FINISH, 0);
                        break;
                    default:
                        sendMessage(COUNT_DOWN_LOADING, mCount);
                        break;
                }
            }
        };
        //每隔1秒发送一次空消息
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private void sendMessage(int flag, int factor) {
        Message msg = new Message();
        switch (flag) {
            case COUNT_DOWN_ERROR:
            case COUNT_DOWN_FINISH:
            case COUNT_DOWN_START:
                break;
            case COUNT_DOWN_LOADING:// 计数中
                msg.arg1 = factor;
                break;
            default:
                break;
        }
        msg.what = flag;
        mHandler.sendMessage(msg);
    }

    //这个必须要写，在退出界面时，要取消计时任务，不然，会一直在计时，直到为0.
    public void cancel() {
        mTimer.cancel();
    }


    /**
     * 暂停当前计时
     */
    public void stopCountDown() {
        cancel();
    }

    /**
     * 重新开始计时
     */
    public void restartCountDown() {
        mCount = countTime;
        setText(beforeCount);
        cancel();
        initTimer();
    }





    public void setOnTimerCountDownListener(OnTimerCountDownListener listener) {
        this.onTimerCountDownListener = listener;
    }

    public interface OnTimerCountDownListener {
        /**
         * 计时开始
         */
        public void onCountDownStart();

        /**
         * 计时中
         */
        public void onCountDownLoading(int currentCount);

        /**
         * 计时错误失败
         */
        public void onCountDownError();

        /**
         * 计时完成
         */
        public void onCountDownFinish();
    }
}
