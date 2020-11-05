package com.fpp.status.activity.customview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.fpp.status.R;
import com.fpp.status.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewFourActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private Chronometer chronometer; // 计时组件
    private Button btn_start;
    private Button btn_stop;
    private Button btn_base;
    private Button btn_format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_four);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);// 获取震动服务
        chronometer = (Chronometer) findViewById(R.id.myChronometer);
//        chronometer.setOnChronometerTickListener( new OnChronometerTickListenerImpl()); // 给计时组件设置舰艇对象
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_base = (Button) findViewById(R.id.btn_base);
        btn_format = (Button) findViewById(R.id.btn_format);
        btn_start.setOnClickListener(new ButtonClickListener());
        btn_stop.setOnClickListener(new ButtonClickListener());
        btn_base.setOnClickListener(new ButtonClickListener());
        btn_format.setOnClickListener(new ButtonClickListener());
    }
    public class OnChronometerTickListenerImpl implements // 计时监听事件，随时随地的监听时间的变化
            Chronometer.OnChronometerTickListener {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            String time = chronometer.getText().toString();
            if ("00:05".equals(time)) {// 判断五秒之后，让手机震动
                vibrator.vibrate(new long[] { 1000, 10, 100, 10 }, 0);// 设置震动周期和是否循环震动，如果不想循环震动把0改为-1
            }
        }
    }
    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    chronometer.start();// 开始计时
                    break;
                case R.id.btn_stop:
                    chronometer.stop();// 停止计时
                    break;
                case R.id.btn_base:
                    chronometer.setBase(SystemClock.elapsedRealtime());// 复位键
                    break;
                case R.id.btn_format:
                    chronometer.setFormat("显示时间：%s");// 更改时间显示格式
                    break;
                default:
                    break;
            }
        }
    }


    final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                long createtime = new Date().getTime() - 1000;
                long nowtime = new Date().getTime()/1000;
                long differencetime = nowtime - createtime;
//                LogUtils.e("订单创建时间 = " + createtime +"------当前时间 = " + nowtime + "相差时间 = " + differencetime);
                long hour = differencetime/3600;
                long minute = differencetime%3600/60;
                long second = differencetime%3600%60;
                String hourString;
                if (hour >=0 && hour < 10) {
                    hourString = "0" + hour;
                }else {
                    hourString = String.valueOf(hour);
                }
                String minuteString;
                if (minute >=0 && minute < 10) {
                    minuteString = "0" + minute;
                }else {
                    minuteString = String.valueOf(minute);
                }
                String secondString;
                if (second >=0 && second < 10) {
                    secondString = "0" + second;
                }else {
                    secondString = String.valueOf(second);
                }
//                LogUtils.e("时 = " + hour+"   分 = " + minute + "   秒 = " + second);
                LogUtils.e("订单创建时间 = " + df.format(createtime*1000) +"------当前时间 = " + df.format(nowtime*1000) + "相差时间 = " + hourString +" 时 " + minuteString + " 分 " +   secondString +" 秒 ");
//                String time =  getTimeDifference(df.format(createtime*1000), df.format(new Date().getTime()));
//                LogUtils.e("----时间差---- " + time);
            }
            super.handleMessage(msg);
        }
    };

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    //        timer.schedule(task ,0, 1000);       // timeTask


    // 控件执行自身变化任务

//       holder.tvLvItemServiceOrderServiceTime.postDelayed(new Runnable() {
//        @Override
//        public void run() {
//            holder.tvLvItemServiceOrderServiceTime.postDelayed(this, 1000);
//        }
//    },1000);


}
