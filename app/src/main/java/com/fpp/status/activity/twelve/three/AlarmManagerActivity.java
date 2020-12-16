package com.fpp.status.activity.twelve.three;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.os.Bundle;

import com.fpp.status.R;

import java.util.Calendar;
import java.util.TimeZone;

public class AlarmManagerActivity extends AppCompatActivity {
    public static final long TIMER_24_HOUR = 24 * 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void sendBroadcast(int hour, int minute, int second) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 设置首次发送广播时间
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, second);
                long firstTime = calendar.getTimeInMillis();
                long systemTime = System.currentTimeMillis();
                if (systemTime > firstTime) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    firstTime = calendar.getTimeInMillis();
                }

//                Intent intent = new Intent(this, RebootReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                        AlarmManagerActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, TIMER_24_HOUR, pendingIntent);
            }
        }.start();

    }


//    public void sendBroadcast() {
//        Intent stateChangeIntent = createStateChangeIntent(context,
//                ALARM_MANAGER_TAG, instance, newState);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
//                instance.hashCode(), stateChangeIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager) context
//                .getSystemService(Context.ALARM_SERVICE);
//
//        if (Utils.isKitKatOrLater()) {   //这里需要做个判断，android 4.4之后接口有变化
//            am.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
//        } else {
//            am.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
//        }
//    }
//
//    public void sendBroadcast(int hour, int minute, int second) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                // 设置首次发送广播时间
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//                calendar.set(Calendar.HOUR_OF_DAY, hour);
//                calendar.set(Calendar.MINUTE, minute);
//                calendar.set(Calendar.SECOND, second);
//                long firstTime = calendar.getTimeInMillis();
//                long systemTime = System.currentTimeMillis();
//                if (systemTime > firstTime) {
//                    calendar.add(Calendar.DAY_OF_MONTH, 1);
//                    firstTime = calendar.getTimeInMillis();
//                }
//
//                Intent intent = new Intent(this, RebootReceiver.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                        HomeActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, Constant.TIMER_24_HOUR, pendingIntent);
//            }
//        }.start();
//
//    }

}
