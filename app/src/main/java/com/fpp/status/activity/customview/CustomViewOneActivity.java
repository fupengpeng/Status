package com.fpp.status.activity.customview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;
import com.fpp.status.utils.TimeUtils;

import java.text.ParseException;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewOneActivity extends AppCompatActivity {


    private TextView mTvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_one);


        initView();

        initTime();


    }

    private void initTime() {
        mTvShow = (TextView) findViewById(R.id.show);

    }

    private void initView() {

        LogUtils.e("当前系统时间  = "  + TimeUtils.getTime());

        LogUtils.e("当前时间01 = " + TimeUtils.getDateTime01());
        LogUtils.e("当前时间02 = " + TimeUtils.getDateTime02());
        LogUtils.e("当前时间03 = " + TimeUtils.getDateTime03());
        LogUtils.e("当前时间04 = " + TimeUtils.getDateTime04());
        LogUtils.e("当前时间06 = " + new Date());
        LogUtils.e("当前时间07 = " + new Date().getTime());
        try {
            LogUtils.e("当前时间05 = " + TimeUtils.dateToStamp(TimeUtils.getDateTime02()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            LogUtils.e("当前时间08 1518415200000 = " + TimeUtils.dateToStamp("2018-02-12 14:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }




        LogUtils.e("日期时间转时间戳 = " + TimeUtils.dateTimeStamp("2018-02-12 14:00","yyyy-MM-dd HH:mm"));


    }




    /**
     * 取消倒计时
     * @param v
     */
    public void oncancel(View v) {
        timer.cancel();
    }

    /**
     * 开始倒计时
     * @param v
     */
    Long time = 61216466L;
    public void restart(View v) {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(time, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisUntilFinished / dd;
            Long hour = (millisUntilFinished - day * dd) / hh;
            Long minute = (millisUntilFinished - day * dd - hour * hh) / mi;
            Long second = (millisUntilFinished - day * dd - hour * hh - minute * mi) / ss;

            StringBuffer sb = new StringBuffer();
            if (day > 0) {
                sb.append(day + "天");
            }
            if (hour >= 0) {
                sb.append(hour + "小时");
            }
            if (minute >= 0) {
                sb.append(minute + "分");
            }
            if (second >= 0) {
                sb.append(second + "秒");
            }
            mTvShow.setText( sb.toString());
        }

        @Override
        public void onFinish() {
            mTvShow.setEnabled(true);
            mTvShow.setText("00 天 00 时 00 分 00 秒");
        }
    };


}
