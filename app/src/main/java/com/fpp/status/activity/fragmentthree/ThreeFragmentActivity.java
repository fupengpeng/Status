package com.fpp.status.activity.fragmentthree;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreeFragmentActivity extends AppCompatActivity {

    private static long _TEN_THOUSAND=10000;

    @BindView(R.id.tv_time)
    TextView tvTime;
    SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");

    @BindView(R.id.tv_01)
    TextView tv01;
    @BindView(R.id.tv_02)
    TextView tv02;
    @BindView(R.id.tv_03)
    TextView tv03;
    @BindView(R.id.tv_04)
    TextView tv04;
    @BindView(R.id.tv_05)
    TextView tv05;
    @BindView(R.id.tv_06)
    TextView tv06;
    @BindView(R.id.tv_07)
    TextView tv07;
    @BindView(R.id.tv_08)
    TextView tv08;
    @BindView(R.id.tv_09)
    TextView tv09;


    private int i = 0;
    private int TIME = 1000;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                tvTime.setText(Integer.toString(i++));
            }
            super.handleMessage(msg);
        }

        ;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_three);
        ButterKnife.bind(this);
        initView();
        initTime();
    }

    private void initTime() {
        tv01.setText( Calendar.getInstance().getTime().getTime() + "");
        tv02.setText( new Date().getTime()+"");
        tv03.setText(System.currentTimeMillis() + "");

        tv04.setText(DateTimeUtil.getYearFirstDay("yyyy-MM-dd HH:mm:ss"));
        tv05.setText(DateTimeUtil.getYearLastDay("yyyy-MM-dd HH:mm:ss"));

        tv06.setText( "");
        tv07.setText("");




//        long times=1000*_TEN_THOUSAND;
//        long t1=System.currentTimeMillis();
//        testSystem(times);
//        long t2=System.currentTimeMillis();
//        System.out.println(t2-t1);
//
//        testCalander(times);
//        long t3=System.currentTimeMillis();
//        System.out.println(t3-t2);
//
//        testDate(times);
//        long t4=System.currentTimeMillis();
//        System.out.println(t4-t3);
    }

//    public static void testSystem(long times){//use 188
//        for(int i=0;i<times;i++){
//            long currentTime=System.currentTimeMillis();
//        }
//    }
//
//    public static void testCalander(long times){//use 6299
//        for(int i=0;i<times;i++){
//            long currentTime=Calendar.getInstance().getTimeInMillis();
//        }
//    }
//
//    public static void testDate(long times){
//        for(int i=0;i<times;i++){
//            long currentTime=new Date().getTime();
//        }
//    }




    private void initView() {
        timer.schedule(task, 0, 1000);
    }


}