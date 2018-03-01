package com.fpp.status.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 获取当前的系統時間
 */
public class TimeUtils {

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        int m = Integer.parseInt(month);
        if (m >= 0 && m < 10) {
            month = "0" + month;
        }
        String date = calendar.get(Calendar.DATE) + "";
        int d = Integer.parseInt(date);
        if (d >= 0 && d < 10) {
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
        if (m >= 0 && m < 10) {
            month = "0" + month;
        }
        String date = calendar.get(Calendar.DATE) + "";
        int d = Integer.parseInt(date);
        if (d >= 0 && d < 10) {
            date = "0" + date;
        }
        String hour = calendar.get(Calendar.HOUR) + "";
        int h = Integer.parseInt(hour);
        if (h >= 0 && h < 10) {
            hour = "0" + hour;
        }
        String minute = calendar.get(Calendar.MINUTE) + "";
        int mi = Integer.parseInt(minute);
        if (mi >= 0 && mi < 10) {
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
     * @param starTime 开始时间
     * @param endTime  结束时间
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


    /**
     * 默认格式
     */
    public static final String TIME_DEFAULT_FORMAT = "yyyy-MM-dd hh:mm:ss";
    /**
     * 年月日    默认格式
     */
    public static final String TIME_DEFAULT_YTD_FORMAT = "yyyy-MM-dd";
    /**
     * 年月日    没有横线格式
     */
    public static final String TIME_NO_LINE_YTD_FORMAT = "yyyyMMdd";
    /**
     * 年月日    中文年月日格式
     */
    public static final String TIME_CHINESE_YTD_FORMAT = "yyyy年MM月dd日";
    /**
     * 年月日    斜杠格式
     */
    public static final String TIME_SLASH_YTD_FORMAT = "yyyy/MM/dd";
    /**
     * 年月日    冒号格式
     */
    public static final String TIME_COLON_YTD_FORMAT = "yyyy:MM:dd";
    /**
     * 月日    中文年月日格式
     */
    public static final String TIME_CHINESE_MD_FORMAT = "MM月dd日";
    /**
     * 月日     横线月日格式
     */
    public static final String TIME_LINE_MD_FORMAT = "MM-dd";
    /**
     * 时分     横线时分格式
     */
    public static final String TIME_COLON_HS_FORMAT = "hh:mm";
    /**
     * 月     横线时分格式
     */
    public static final String TIME_ONLY_CHINESE_M_FORMAT = "MM月";

    /**
     * 获取最近日期       默认
     *
     * @param i 偏离当前日期天数      -1:昨天   0:今天      1:明天
     * @return 返回日期
     */
    public static String getRecentTime(int i) {
        return getRecentTime(i, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 获取最近日期
     *
     * @param i 偏离当前日期天数      -1:昨天   0:今天      1:明天
     * @return 返回日期
     */
    public static String getRecentTime(int i, String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String dateString = "";
        try {
            calendar.add(calendar.DATE, i);//把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime(); //这个时间就是日期往后推一天的结果
            dateString = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /************************本年第一天*********************************************************************************
     /**
     * 本年第一天  默认
     * @return 返回时间
     */
    public static String getYearFirstDay() {
        return getYearFirstDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * * 本年第一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getYearFirstDay(String format) {
//        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_YEAR, 1);
        return getDate(format, Calendar.DAY_OF_YEAR, 1);
    }

    /**
     * 本年第一天     默认
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getYearFirstDay(TextView view) {
        getYearFirstDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本年第一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getYearFirstDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_YEAR, 1));
    }

    /************************本年最后一天*********************************************************************************
     /**
     * 本年最后一天
     * @return 返回时间
     */
    public static String getYearLastDay() {
        return getYearLastDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本年最后一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getYearLastDay(String format) {
        return getDate(format, Calendar.DAY_OF_YEAR, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR));
    }

    /**
     * 本年最后一天
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getYearLastDay(TextView view) {
        getYearLastDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本年最后一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getYearLastDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_YEAR, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)));
    }

    /************************本月第一天*********************************************************************************
     /**
     * 本月第一天  默认
     * @return 返回时间
     */
    public static String getMonthFirstDay() {
        return getMonthFirstDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * * 本月第一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getMonthFirstDay(String format) {
        return getDate(format, Calendar.DAY_OF_MONTH, 1);
    }

    /**
     * 本月第一天     默认
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getMonthFirstDay(TextView view) {
        getMonthFirstDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本月第一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getMonthFirstDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_MONTH, 1));
    }

    /************************本月最后一天*********************************************************************************
     /**
     * 本月最后一天
     * @return 返回时间
     */
    public static String getMonthLastDay() {
        return getMonthLastDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本月最后一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getMonthLastDay(String format) {
        return getDate(format, Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 本月最后一天
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getMonthLastDay(TextView view) {
        getMonthLastDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本月最后一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getMonthLastDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)));
    }

    /************************本周第一天*********************************************************************************
     /**
     * 本周第一天  默认
     * @return 返回时间
     */
    public static String getWeekFirstDay() {
        return getMonthFirstDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * * 本周第一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getWeekFirstDay(String format) {
        return getDate(format, Calendar.DAY_OF_WEEK, 1);
    }

    /**
     * 本周第一天     默认
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getWeekFirstDay(TextView view) {
        getMonthFirstDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本周第一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getWeekFirstDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_WEEK, 1));
    }

    /************************本周最后一天*********************************************************************************
     /**
     * 本周最后一天
     * @return 返回时间
     */
    public static String getWeekLastDay() {
        return getMonthLastDay(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本周最后一天
     *
     * @param format 格式
     * @return 返回时间
     */
    public static String getWeekLastDay(String format) {
        return getDate(format, Calendar.DAY_OF_WEEK, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_WEEK));
    }

    /**
     * 本周最后一天
     *
     * @param view 需要显示的控件（Button,TextView）
     */
    public static void getWeekLastDay(TextView view) {
        getMonthLastDay(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 本周最后一天
     *
     * @param view   需要显示的控件（Button,TextView）
     * @param format 格式
     */
    public static void getWeekLastDay(TextView view, String format) {
        view.setText(getDate(format, Calendar.DAY_OF_WEEK, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_WEEK)));
    }

    /************************当天*********************************************************************************
     * 获取当天        默认
     * @return 当天时间
     */
    public static String getToday() {
        return getToday(TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @param format 格式
     * @return 当天时间
     */
    public static String getToday(String format) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当天
     *
     * @return 当天时间
     */
    public static void getToday(TextView view) {
        getToday(view, TIME_DEFAULT_YTD_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @param view   控件
     * @param format 格式
     */
    public static void getToday(TextView view, String format) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        view.setText(dateFormater.format(cal.getTime()));
    }

    /**
     * 获取指定日期
     *
     * @param format   格式
     * @param calendar 日历
     * @param day      第几天
     * @return 返回日期
     */
    public static String getDate(String format, int calendar, int day) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.set(calendar, day);
        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取指定日期
     *
     * @param view     控件
     * @param format   格式
     * @param calendar 日历
     * @param day      第几天
     * @return 返回日期
     */
    public static void getDate(TextView view, String format, int calendar, int day) {
        view.setText(getDate(format, calendar, day));
    }
/***********************************时间格式转换*********************************************************************/
    /**
     * 时间转换  输入date 输出String
     *
     * @param date 日期
     * @return 返回日期
     */
    public static String timeFormatAlter(Date date, String format) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
        return dateFormater.format(date);
        // SimpleDateFormat formatter = new SimpleDateFormat(TimesUtils.TIME_DEFAULT_YTD_FORMAT);
        // time= formatter.format(data.getYearmonthday());
    }

    /**
     * 时间转换  输入date 输出String
     *
     * @param time 日期
     * @return 返回日期
     */
    public static String timeFormatAlter(String time, String format) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        Date date = new Date(time);
        cal.setTime(date);
        return dateFormater.format(date);
    }

    public static Calendar timeFormatAlter(String time) {
//    SimpleDateFormat dateFormater = new SimpleDateFormat(TIME_DEFAULT_YTD_FORMAT);
        SimpleDateFormat fmt = new SimpleDateFormat(TIME_DEFAULT_YTD_FORMAT);
        Calendar cal = Calendar.getInstance();
        LogUtils.e("---time-", time);
        try {
            Date date = fmt.parse(time);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.e("---cal-", cal.getTime() + "");
        return cal;
    }
/**********************************时间戳**************************************************************/
    /**
     * 调用此方法输入所要转换的时间戳输入
     *
     * @param time 时间戳
     * @return
     */
    public static String timestampShiftTime(long time, String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format);
//        @SuppressWarnings("unused")
//        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(time * 1000L));
        return times;

    }


    /**
     * 显示当前时间
     *
     * @param textView 要显示时间的控件
     */
    public static void setTime(Context context, final TextView textView) {
        final Calendar mCalendar = Calendar.getInstance();
        int mHour = mCalendar.get(Calendar.HOUR);
        int mMinuts = mCalendar.get(Calendar.MINUTE);

        String tvTime = (String) textView.getText();
        int hour;
        int minuts;
        if (TextUtils.isEmpty(tvTime)) {
            hour = mCalendar.get(Calendar.HOUR);
            minuts = mCalendar.get(Calendar.MINUTE);
        } else {
            String[] split = tvTime.split(":");
            hour = Integer.parseInt(split[0]);
            minuts = Integer.parseInt(split[split.length - 1]);
        }

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(hourOfDay, minute);//获取时间
                textView.setText(DateFormat.format("hh:ss", mCalendar));//显示时间
            }
        }, hour, minuts, true);
        timePickerDialog.show();

    }

    private static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(TIME_DEFAULT_YTD_FORMAT);
        return format.format(date);
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateTimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取当前日期时间
     *
     * @return 2018-02-28 13:54:55
     */
    public static String getDateTime01() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);

    }

    /**
     * 获取当前日期时间
     *
     * @return 2018-02-28 13:54:55
     */
    public static String getDateTime02() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(System.currentTimeMillis());
    }

    /**
     * 获取当前日期时间
     *
     * @return 2018-02-28 13:54:55
     */
    public static String getDateTime03() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }

    /**
     * 获取当前日期时间
     *
     * @return 2018-02-28 13:54:55
     */
    public static String getDateTime04() {
        Date date = new Date();
        String year = String.format("%tY", date);
        String month = String.format("%tB", date);
        String day = String.format("%te", date);
        return "今天是：" + year + "-" + month + "-" + day;

    }


    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    /**
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay + " 天 " + strHour + " 小时 " + strMinute + " 分钟 " + strSecond + " 秒";
    }

    /**
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }


//            Math.ceil(millisUntilFinished/1000/60/60/24);   //  向上取整：只要有小数都+1
//            Math.floor(millisUntilFinished/1000/60/60/24);   //  向下取整: 不取小数
//            Math.round(millisUntilFinished/1000/60/60/24);  //   四舍五入:四舍五入


    /**
     * 开始倒计时
     *
     * @param time
     */
    public static String  restart(Long time) {

        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = time / dd;
        Long hour = (time - day * dd) / hh;
        Long minute = (time - day * dd - hour * hh) / mi;
        Long second = (time - day * dd - hour * hh - minute * mi) / ss;
//            Long milliSecond = time - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day >= 0) {
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
//            if (milliSecond > 0) {
//                sb.append(milliSecond + "毫秒");
//            }

        return sb.toString();
    }



}
