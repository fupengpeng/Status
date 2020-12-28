package com.fpp.status;

import android.widget.LinearLayout;

import com.fpp.status.utils.DateTimeUtil;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void splitApdu2() throws Exception {
        String str = ":6C17";
        System.err.println("--" + "" + "--:" + str.contains("6C"));
    }

    @Test
    public void splitApdu() throws Exception {
        String str = "00A40000025202||00B201C400|00B202C400|00B203C400|00B204C400|00B205C400|00B206C400|00B207C400|00B208C400|00B209C400|00B20AC400||805C000204||00B0950000||";
        String[] strings = str.split("\\|\\|");
        for (int i = 0; i < strings.length; i++) {
            System.err.println("--" + i + "--:" + strings[i]);
        }
        for (int i = 0; i < strings.length; i++) {
            System.err.println("--" + i + "--:" + strings[i]);
        }
        String[] recordApdus = strings[1].split("\\|");
        for (int i = 0; i < recordApdus.length; i++) {
            System.err.println("-recordApdus-" + i + "--:" + recordApdus[i]);
        }


    }

    @Test
    public void booleanTest() throws Exception {
        long sys = System.currentTimeMillis();
        long date = new Date().getTime();
        long calendar = Calendar.getInstance().getTimeInMillis();
        // TimeZone.getDefault().getRawOffset()
        boolean a = false;
        System.err.println(" " + a);
        System.err.println(" " + sys + "\n" + date + "\n" + calendar);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        long sys = System.currentTimeMillis();
        long date = new Date().getTime();
        long calendar = Calendar.getInstance().getTimeInMillis();
        // TimeZone.getDefault().getRawOffset()
        System.err.println(" " + sys + "\n" + date + "\n" + calendar);
    }

    @Test
    public void yearMonthLastDate() throws Exception {
        List<Integer> yearList = new ArrayList();
        for (int i = 0; i < 4000; i++) {
            int year = 0;
            yearList.add(year + i);
        }
        List<Integer> monthList = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < yearList.size(); i++) {
            for (int j = 0; j < monthList.size(); j++) {
                Date date = DateTimeUtil.getEndMonthDate(yearList.get(i), monthList.get(j));
                String dateStr = dateFormat.format(date);
                System.err.println("----" + yearList.get(i) + "年" + monthList.get(j) + "月最后一天" + "----  " + dateStr + " ----" + (yearList.get(i) % 4 == 0 ? "闰年" : "平年"));
            }
        }
    }

    @Test
    public void zengXiaoRiQi() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        List<String> currentDayList = DateTimeUtil.getDays("20200101", "20211231", "yyyyMMdd");
        for (int i = 1; i <= currentDayList.size(); i++) {
            String dateStr = DateTimeUtil.zengXiaoRiQi();
            System.err.println("----" + "----  增效日期：" + dateStr + " ----");
        }
    }

    @Test
    public void yuepiaochongzhiyuefen() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        String month = "";
        String[] strings = new String[4];
        strings[0] = sdf.format(calendar.getTime());
        for (int i = 1; i < 4; i++) {
            calendar.add(Calendar.MONTH, 1);
            month = sdf.format(calendar.getTime());
            strings[i] = month;
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println("增加后的月份：" + strings[i]);
        }
    }
}