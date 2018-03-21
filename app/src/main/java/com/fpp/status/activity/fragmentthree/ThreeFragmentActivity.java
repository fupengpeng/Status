package com.fpp.status.activity.fragmentthree;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fpp.status.R;
import com.fpp.status.utils.DateTimeUtil;
import com.fpp.status.utils.TimeCount;
import com.fpp.status.utils.TimeCountDown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreeFragmentActivity extends AppCompatActivity implements TimeCountDown.OnTimerCountDownListener{

    private static long _TEN_THOUSAND = 10000;

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
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.tv_13)
    TextView tv13;
    @BindView(R.id.tv_14)
    TextView tv14;
    @BindView(R.id.tv_15)
    TextView tv15;
    @BindView(R.id.tv_16)
    TextView tv16;
    @BindView(R.id.tv_17)
    TextView tv17;
    @BindView(R.id.tv_18)
    TextView tv18;
    @BindView(R.id.tv_19)
    TextView tv19;
    @BindView(R.id.tv_20)
    TextView tv20;
    @BindView(R.id.tv_21)
    TextView tv21;
    @BindView(R.id.tv_22)
    TextView tv22;
    @BindView(R.id.tv_23)
    TextView tv23;
    @BindView(R.id.tv_24)
    TextView tv24;
    @BindView(R.id.tv_25)
    TextView tv25;
    @BindView(R.id.tv_26)
    TextView tv26;
    @BindView(R.id.tv_27)
    TextView tv27;
    @BindView(R.id.tv_28)
    TextView tv28;
    @BindView(R.id.tv_29)
    TextView tv29;
    @BindView(R.id.tv_30)
    TextView tv30;
    @BindView(R.id.tv_31)
    TextView tv31;
    @BindView(R.id.tv_32)
    TextView tv32;
    @BindView(R.id.tv_33)
    TextView tv33;
    @BindView(R.id.tv_34)
    TextView tv34;
    @BindView(R.id.tv_35)
    TextView tv35;
    @BindView(R.id.tv_36)
    TextView tv36;
    @BindView(R.id.tv_37)
    TextView tv37;
    @BindView(R.id.tv_38)
    TextView tv38;
    @BindView(R.id.tv_39)
    TextView tv39;
    @BindView(R.id.tv_40)
    TextView tv40;
    @BindView(R.id.tv_41)
    TimeCountDown tv41;


    private int i = 0;
    private int TIME = 1000;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                long time = i++;
                DateTimeUtil.getFormatTime(time);
                tvTime.setText(DateTimeUtil.getFormatTime(time) + "");
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
    private TimeCount time;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_three);
        ButterKnife.bind(this);
        initView();
        try {
            initTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    String yyMMddhhmmss = "yyyy-MM-dd hh:mm:ss";
    String datetime1 = "2018-12-15 12:15:12";

    String datetime2 = "2018-12-10 12:15:12";

    String datetime3 = "2018-12-20 12:15:12";

    private void initTime() throws ParseException {
        tv01.setText(DateTimeUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
        tv02.setText(DateTimeUtil.getYearFirstDay("yyyy-MM-dd hh:mm:ss"));
        tv03.setText(DateTimeUtil.getYearLastDay(yyMMddhhmmss));

        tv04.setText(DateTimeUtil.getYearFirst(datetime1, yyMMddhhmmss));
        tv05.setText(DateTimeUtil.getYearLast(datetime1, yyMMddhhmmss));

        tv06.setText(DateTimeUtil.getDayiy(datetime1) + "");
        tv07.setText(DateTimeUtil.getWeekOfYear(datetime1) + "");


        tv08.setText(" 前天 " + DateTimeUtil.getRecentTime(-2, yyMMddhhmmss));
        tv09.setText(" 昨天 " + DateTimeUtil.getRecentTime(-1, yyMMddhhmmss));
        tv10.setText(" 今天 " + DateTimeUtil.getRecentTime(0, yyMMddhhmmss));
        tv11.setText(" 明天 " + DateTimeUtil.getRecentTime(1, yyMMddhhmmss));
        tv12.setText(" 后天 " + DateTimeUtil.getRecentTime(2, yyMMddhhmmss));
        tv13.setText("  " + DateTimeUtil.compareDate(datetime1, datetime2) + "");
        tv14.setText(DateTimeUtil.compare_date(datetime1, datetime2) + "");
        tv15.setText(DateTimeUtil.belongDate(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2), 10) + "");
        tv16.setText(DateTimeUtil.belongCalendar(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2),
                DateTimeUtil.stringOfDate(datetime3)) + "");
        tv17.setText(DateTimeUtil.getDistanceDays(datetime1) + "");
        tv18.setText(DateTimeUtil.isSameDate(datetime1, datetime2) + "");
        tv19.setText(DateTimeUtil.isLeapYear(datetime1) + "");
        tv20.setText(DateTimeUtil.getSeqWeek() + "");
        tv21.setText("两个日期相减得到的天数 = " + DateTimeUtil.getDiffDays(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2)) + "");
        tv22.setText("两个日期相减得到的毫秒数  = " + DateTimeUtil.dateDiff(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2)) + "");
        tv23.setText("获取两个日期中的最大日期 = " + DateTimeUtil.dateOfString(
                DateTimeUtil.max(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2)), yyMMddhhmmss) + "");
        tv24.setText("获取两个日期中的最小日期 = " + DateTimeUtil.dateOfString(
                DateTimeUtil.min(DateTimeUtil.stringOfDate(datetime1), DateTimeUtil.stringOfDate(datetime2)), yyMMddhhmmss) + "");
        tv25.setText(" 点击倒计时开始 ");
        /**
         * 倒计时60秒，一次1秒
         */
        time = new TimeCount(60000 + 1050,1000,tv25);

        tv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.start();
            }
        });

        tv41.setOnTimerCountDownListener(this);

        tv41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv41.initTimer();
//                onBackPressed();
            }
        });


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




    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv41.cancel();
    }


    @Override
    public void onCountDownStart() {

    }

    @Override
    public void onCountDownLoading(int currentCount) {

    }

    @Override
    public void onCountDownError() {

    }

    @Override
    public void onCountDownFinish() {

    }
}