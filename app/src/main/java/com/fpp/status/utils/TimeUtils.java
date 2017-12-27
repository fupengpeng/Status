package com.fpp.status.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前的系統時間
 */
public class TimeUtils {
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        int m = Integer.parseInt(month);
        if (m >=0 && m < 10) {
            month = "0" + month;
        }
        String date = calendar.get(Calendar.DATE) + "";
        int d = Integer.parseInt(date);
        if (d >=0 && d < 10) {
            date = "0" + date;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(month);
        buffer.append(date);
        buffer.append(year);
        return buffer.toString();
    }

    public static String getTime1() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        int m = Integer.parseInt(month);
        if (m >=0 && m < 10) {
            month = "0" + month;
        }
        String date = calendar.get(Calendar.DATE) + "";
        int d = Integer.parseInt(date);
        if (d >=0 && d < 10) {
            date = "0" + date;
        }
        String hour = calendar.get(Calendar.HOUR) + "";
        int h = Integer.parseInt(hour);
        if (h >=0 && h < 10) {
            hour = "0" + hour;
        }
        String minute = calendar.get(Calendar.MINUTE) + "";
        int mi = Integer.parseInt(minute);
        if (mi >=0 && mi < 10) {
            minute = "0" + minute;
        }
        String second = calendar.get(Calendar.SECOND) + "";
        int s = Integer.parseInt(second);
        if (s > 0 && s < 10) {
            second = "0" + second;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(year);
        buffer.append(month);
        buffer.append(date);
        buffer.append(hour);
        buffer.append(minute);
        buffer.append(second);
        return buffer.toString();
    }



    /**
     * 计算时间差
     *
     * @param starTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return 返回时间差
     */
    public String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            timeString = hour1 + "小时" + min1 + "分";
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }

}
