package com.fpp.status.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringUtil {
    public static boolean isBlank(String s) {
        if (null == s || "".equals(s.trim())) {
            return true;
        }
        return false;
    }

    public static boolean hasBlankValue(String... strings) {
        for (String s : strings) {
            if (isBlank(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param sourceStr  源字符串
     * @param appendChar 待添加字符
     * @param length     处理完成后字符串总长
     * @param type       prefix 代表前缀，suffix 代表后缀
     * @return
     */
    public static String dealString(String sourceStr, char appendChar,
                                    int length, String type) {
        if (sourceStr.length() > length) {
            return "LENGTH ERROR";
        }
        int toAddLen = length - sourceStr.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toAddLen; i++) {
            sb.append(appendChar);
        }
        if ("prefix".equals(type)) {
            return sb.toString() + sourceStr;
        } else if ("suffix".equals(type)) {
            return sourceStr + sb.toString();
        } else {
            return "TYPE ERROR";
        }
    }

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @author runlin.chen
     * @date 2016年7月25日上午10:49:23
     */
    public static String changeF2Y(int amount) throws Exception {
        if (!(amount + "").matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amount + "";
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i,
                        intString.length() - i + 1));
            }
            result.reverse().append(".")
                    .append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 根据逗号分割字符串,并返回一个List
     */
    public static List<String> splitStringWithComma(String str) {
        String[] ss = str.split(",");
        return new ArrayList<String>(Arrays.asList(ss));
    }

    /**
     * 获取指定的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串居中拼接处理
     *
     * @param str        源字符串
     * @param appendChar 需要拼接的字符
     * @param length     拼接后长度
     * @return 拼接后字符串
     * @throws Exception 异常
     */
    public static String appendStrLen(String str, char appendChar, int length) {
        if ("".equals(str) || null == str) {
            return "";
        }
        if (str.length() > length) {
            return str;
        }
        if ("".equals(appendChar)) {
            return str;
        }
        int appendLen = length - str.length();
        int appendLenBefore;
        int appendLenAfter;
        if (appendLen % 2 == 0) {
            appendLenBefore = appendLen / 2;
            appendLenAfter = appendLen / 2;
        } else {
            appendLenBefore = appendLen / 2;
            appendLenAfter = (int) Math.floor(appendLen / 2F) + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < appendLenBefore; i++) {
            sb.append(appendChar);
        }
        sb.append(str);
        for (int i = 0; i < appendLenAfter; i++) {
            sb.append(appendChar);
        }
        return sb.toString();
    }

}
