package com.fpp.status;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

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
}