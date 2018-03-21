package com.fpp.status.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.fpp.status.R;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/3/21 0021 16:29
 */

public class TimeCount extends CountDownTimer {
    private TextView tvCode;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tvCode = tv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tvCode.setBackgroundResource(R.drawable.regist_suc);
        tvCode.setTextSize(13);
        tvCode.setText(((int)millisUntilFinished/1000 -1) + "秒");
        tvCode.setClickable(false);
    }

    @Override
    public void onFinish() {
        tvCode.setBackgroundResource(R.drawable.regist_suc);
        tvCode.setTextSize(13);
        tvCode.setText("再次接收");
        tvCode.setClickable(true);
    }
}
