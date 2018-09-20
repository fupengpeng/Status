package com.fpp.status.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author fupengpeng
 * @description 日期时间工具类
 * @data 2018/3/8 0008 11:18
 */

public class DateTimeUtil {


    public static String pattern = "yyyy-MM-dd";
    public static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
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
     * 月日    中文年月日格式
     */
    public static final String TIME_CHINESE_MD_FORMAT = "MM月dd日";

    /**
     * 时分     横线时分格式
     */
    public static final String TIME_COLON_HS_FORMAT = "hh:mm";


    private static SimpleDateFormat mSdf = null;
    private static Date mDate = new Date();
    private static Calendar mCalendar = Calendar.getInstance();


    /**
     * 获取指定格式的当前日期时间
     *
     * @param format 指定格式
     * @return 指定格式的当前日期时间
     */
    public static String getDateTime(String format) {
        mSdf = new SimpleDateFormat(format);
        return mSdf.format(getTimestamp01());
    }


    /*******************************  时间戳转日期时间字符串  *******************************/
    /**
     * 根据给定的时间戳获取指定格式的日期时间
     *
     * @param dateTime 指定的时间戳
     * @param format   指定的日期时间格式
     * @return 指定时间戳和日期时间格式的字符串
     */
    public static String timestampOfString(long dateTime, String format) {
        mSdf = new SimpleDateFormat(format);
        return mSdf.format(dateTime);
    }
    /*******************************  时间戳转日期时间字符串  *******************************/


    /*******************************  时间戳转Date、Calendar对象  *******************************/
    /**
     * 时间戳转Date对象
     *
     * @param timestamp
     * @return
     */
    public static Date timestampOfDate(long timestamp) {
        mDate.setTime(timestamp);
        return mDate;
    }

    /**
     * 时间戳转Calendar对象
     *
     * @param timestamp
     * @return
     */
    public static Calendar timestampOfCalendar(long timestamp) {
        mDate.setTime(timestamp);
        mCalendar.setTime(mDate);
        return mCalendar;
    }
    /*******************************  时间戳转Date、Calendar对象  *******************************/


    /*******************************  日期时间字符串转时间戳  *******************************/
    /**
     * 将指定日期时间转换为时间戳
     *
     * @param dateTime 指定日期时间
     * @return 转换成功的时间戳
     * @throws ParseException
     */
    public static long stringOfTimestamp(String dateTime) throws ParseException {
        mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mSdf.parse(dateTime).getTime();
    }
    /*******************************  日期时间字符串转时间戳  *******************************/


    /*******************************  日期时间字符串转Date、Calendar对象  *******************************/
    /**
     * 将指定日期时间转换为Date对象
     *
     * @param dateTime 指定日期时间
     * @return 转换成功的Date对象
     * @throws ParseException
     */
    public static Date stringOfDate(String dateTime) throws ParseException {
        mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mSdf.parse(dateTime);
    }

    /**
     * 将指定日期时间转换为Calendar对象
     *
     * @param dateTime 指定日期时间
     * @return 转换成功的Calendar对象
     * @throws ParseException
     */
    public static Calendar stringOfCalendar(String dateTime) throws ParseException {
        mSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mCalendar.setTime(mSdf.parse(dateTime));
        return mCalendar;
    }
    /*******************************  日期时间字符串转Date、Calendar对象  *******************************/


    /*******************************  Date、Calendar对象转时间戳  *******************************/
    /**
     * 获取时间戳----方式一
     *
     * @return 时间戳
     */
    public static long getTimestamp01() {
        // 效率最高
        long currentTime = System.currentTimeMillis();
        return currentTime;
    }

    /**
     * 获取时间戳----方式二
     *
     * @return 时间戳
     */
    public static long getTimestamp02(Calendar calendar) {
        // 效率最差  因为要处理时区问题
        long currentTime = calendar.getTimeInMillis();
        return currentTime;
    }

    /**
     * 获取时间戳----方式三
     *
     * @return 时间戳
     */
    public static long getTimestamp03(Date date) {
        // 效率次之
        long currentTime = date.getTime();
        return currentTime;
    }

    /*******************************  Date、Calendar对象转时间戳  *******************************/

    /*******************************  Date、Calendar对象转日期时间字符串  *******************************/

    /**
     * 根据给定的Date对象获取指定格式的日期时间
     *
     * @param date   指定的Date对象
     * @param format 指定的日期时间格式
     * @return 指定时间戳和日期时间格式的字符串
     */
    public static String dateOfString(Date date, String format) {
        mSdf = new SimpleDateFormat(format);
        return mSdf.format(date.getTime());
    }

    /**
     * 根据给定的Calendar对象获取指定格式的日期时间
     *
     * @param calendar
     * @param format
     * @return
     */
    public static String calendarOfString(Calendar calendar, String format) {
        mSdf = new SimpleDateFormat(format);
        return mSdf.format(calendar.getTime());
    }
    /*******************************  Date、Calendar对象转日期时间字符串  *******************************/


    /**
     * 本年第一天
     *
     * @param format 输出日期格式
     * @return 返回时间
     */
    public static String getYearFirstDay(String format) {
        mSdf = new SimpleDateFormat(format);
        mCalendar.set(Calendar.DAY_OF_YEAR, 1);
        return mSdf.format(mCalendar.getTime());
    }

    /**
     * 本年最后一天
     *
     * @param format 输出日期格式
     * @return 返回时间
     */
    public static String getYearLastDay(String format) {
        mSdf = new SimpleDateFormat(format);
        mCalendar.roll(Calendar.DAY_OF_YEAR, -1);
        return mSdf.format(mCalendar.getTime());
    }


    /**
     * 获取某天的本年度第一天日期
     *
     * @param dateTime 某天日期字符串
     * @param format   输出日期格式
     * @return
     * @throws ParseException
     */
    public static String getYearFirst(String dateTime, String format) throws ParseException {
        int year = DateTimeUtil.stringOfCalendar(dateTime).get(Calendar.YEAR);
        mCalendar.clear();
        mCalendar.set(Calendar.YEAR, year);
        return calendarOfString(mCalendar, format);
    }

    /**
     * 获取某天的本年度最后一天日期
     *
     * @param dateTime 某天日期字符串
     * @param format   输出日期格式
     * @return
     * @throws ParseException
     */
    public static String getYearLast(String dateTime, String format) throws ParseException {
        int year = DateTimeUtil.stringOfCalendar(dateTime).get(Calendar.YEAR);
        mCalendar.clear();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendarOfString(mCalendar, format);
    }


    /**
     * 获取当前日期是本年的第几天
     *
     * @param dateTime 输入日期字符串
     * @return
     * @throws ParseException
     */
    public static int getDayiy(String dateTime) throws ParseException {

        mCalendar.setTime(stringOfDate(dateTime));//实例化一个日期
        return mCalendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取当前日期是本年的第几周
     *
     * @param dateTime 输入日期字符串
     * @return
     * @throws ParseException
     */
    public static int getWeekOfYear(String dateTime) throws ParseException {
        mCalendar.setTime(stringOfDate(dateTime));//实例化一个日期
        return mCalendar.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 日期大小对比
     *
     * @param DATE1 开始日期
     * @param DATE2 结束日期
     * @return 返回值：0、两个日期相同；-1、date1在date2之前；1、date1在date2之后
     */
    public static int compare_date(String DATE1, String DATE2) {
        mSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = mSdf.parse(DATE1);
            Date dt2 = mSdf.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期大小对比
     *
     * @param date1 开始日期
     * @param date2 结束日期
     * @return 返回值：0、两个日期相同；-1、date1在date2之前；1、date1在date2之后
     */
    public static int compareDate(String date1, String date2) {
        int comparetoDate = 0;
        mSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date startDate = mSdf.parse(date1);
            Date endDate = mSdf.parse(date2);
            comparetoDate = startDate.compareTo(endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return comparetoDate;
    }


    /**
     * 判断time1是否在time2的n天之内
     *
     * @param time1 输入Data
     * @param time2
     * @param n     正数表示在条件时间n天之后，负数表示在条件时间n天之前
     * @return
     */
    public static boolean belongDate(Date time1, Date time2, int n) {
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(time2);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, n);
        Date before7days = calendar.getTime();   //得到n前的时间
        if (before7days.getTime() < time1.getTime()) {
            return true;
        } else {
            return false;
        }

//        System.out.println(belongDate(time,now,-2));//2天前
//        System.out.println(belongDate(time,now,2));//2天后
//        System.out.println(belongDate(time,now,-6));//6天前
    }

    /**
     * 判断time1是否在time2，time3之间
     *
     * @param time1 指定日期
     * @param time2 开始日期
     * @param time3 结束日期
     * @return
     */
    public static boolean belongCalendar(Date time1, Date time2, Date time3) {
        Calendar date = Calendar.getInstance();
        date.setTime(time1);

        Calendar after = Calendar.getInstance();
        after.setTime(time2);

        Calendar before = Calendar.getInstance();
        before.setTime(time3);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断给定时间与当前时间相差多少天
     *
     * @param date 给定时间
     * @return
     */
    public static long getDistanceDays(String date) {
        mSdf = new SimpleDateFormat("yyyy-MM-dd");
        long days = 0;
        try {
            Date time = mSdf.parse(date);//String转Date
            Date now = new Date();//获取当前时间
            long time1 = time.getTime();
            long time2 = now.getTime();
            long diff = time1 - time2;
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;//正数表示在当前时间之后，负数表示在当前时间之前
    }

    /**
     * 判断两个日期是否是否在同一周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);//西方周日为一周的第一天，咱得将周一设为一周第一天
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.setTime(d1);
        cal2.setTime(d2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear == 0)// subYear==0,说明是同一年
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) //subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)//subYear==-1,说明cal比cal2小一年
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 时间格式化
     *
     * @param time 传入的时间毫秒值
     * @return 格式化后的时间
     */
    public static String getFormatTime(long time) {

        long second = time / 1000;
        long day = second / 60 / 60 / 24;
        long hour = (second - day * 60 * 60 * 24) / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;

        String rDay = "";
        String rHour = "";
        String rMin = "";
        String rSs = "";
        // 天
        if (day < 10) {
            rDay = "0" + day;
        } else {
            rDay = day + "";
        }
        // 时
        if (hour < 10) {
            rHour = "0" + hour;
        } else {
            rHour = hour + "";
        }
        // 分
        if (minute < 10) {
            rMin = "0" + minute;
        } else {
            rMin = minute + "";
        }
        // 秒
        if (sec < 10) {
            rSs = "0" + sec;
        } else {
            rSs = sec + "";
        }

        // return day + "天" + hour + "小时" + minute + "分钟" + sec + "秒";
        return rDay + " " + rHour + ":" + rMin + ":" + rSs;

    }


    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) throws ParseException {

        /**
         * 详细设计： 1.被400整除是闰年，否则：
         *            2.不能被4整除则不是闰年
         *            3.能被4整除同时不能被100整除则是闰年
         *            4.能被4整除同时能被100整除则不是闰年
         */
        Date d = DateTimeUtil.stringOfDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        return week;
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

    public static String formatDateToMD(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    public static String formatDateToYMD(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }
/* ---------------------------------------- 测试分界 ---------------------------------------- */


    /**
     * 倒计时处理类
     */
    public class CountDownUtil extends CountDownTimer {
        /**
         * 设置显示倒计时的TextView
         */
        private TextView mTextView;

        /**
         * 构造函数
         *
         * @param millisInFuture    倒计时时间
         * @param countDownInterval 间隔
         * @param textView          控件
         */
        public CountDownUtil(long millisInFuture, long countDownInterval,
                             TextView textView) {
            super(millisInFuture, countDownInterval);
            this.mTextView = textView;
        }

        @SuppressLint("NewApi")
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText(getFormatTime(millisUntilFinished));//设置倒计时格式化好的时间
        }

        @SuppressLint("NewApi")
        @Override
        public void onFinish() {

        }
    }


    /**
     * 得到一个时间延后或前移几天的时间,nowdate(yyyy-mm-dd)为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            String mdate = "";
            Date d = DateTimeUtil.stringOfDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = formatter.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) throws ParseException {
        // 再转换为时间
        Date date = DateTimeUtil.stringOfDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    //获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    //获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayStartTime(cal.getTime());
    }

    //获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    // TODO: 2018/3/21 0021  两个日期相减得到的天数
    public static int getDiffDays(Date beginDate, Date endDate) {

        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }

        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);

        int days = new Long(diff).intValue();

        return days;
    }

    // TODO: 2018/3/21 0021 两个日期相减得到的毫秒数 
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    // TODO: 2018/3/21 0021  获取两个日期中的最大日期
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    // TODO: 2018/3/21 0021 获取两个日期中的最小日期
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    //返回某月该季度的第一个月
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    //返回某个日期下几天的日期
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    //返回某个日期前几天的日期
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    //获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int endYear,
                                   int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));

            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    //获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    //获取某年某月的第一天日期
    public static Date getStartMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    //获取某年某月的最后一天日期
    public static Date getEndMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
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
        mSdf = new SimpleDateFormat(format);
        mCalendar.set(calendar, day);
        return mSdf.format(mCalendar.getTime());
    }


    // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
    public Date getdate(int i, String format) {
        Date date = null;

        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);

        date = cd.getTime();
        mSdf = new SimpleDateFormat(format);
        Timestamp timestamp = Timestamp.valueOf(mSdf.format(date));
        return timestamp;
    }

    /**
     * 获取某周的最后一天日期
     *
     * @param week
     * @return
     */
    public static String weekLastDay(int week) {
        Calendar c1 = Calendar.getInstance();
        int dow = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DATE, -dow - 7 * (week - 1) + 1);
        String d1 = new SimpleDateFormat("yyyy-MM-dd").format(c1.getTime());
        return d1 + " 23:59:59";
    }



    /*手机号处理*/

    /**
     * 给手机加密中间四位加星号
     */

    /*邮箱加密处理*/

    /**
     * 给邮箱加密加星号
     */


}
