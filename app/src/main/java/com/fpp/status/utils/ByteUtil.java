package com.fpp.status.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class ByteUtil {


    private static byte[] mTempData;

    public static byte[] String2Byte(String str) {
        str = str + "0";
        byte[] bytes = str.getBytes();
        bytes[bytes.length - 1] = 0;
        return bytes;
    }


    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(byte[] a) {

        String hs = "";
        String stmp;
        for (int n = 0; n < a.length; n++) {
            stmp = (Integer.toHexString(a[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(int[] a) {

        String hs = "";
        String stmp;
        for (int n = 0; n < a.length; n++) {
            stmp = (Integer.toHexString(a[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static String append20ForMessage(String msg, int len) {
        for (int i = 0; i < len; i++) {
            msg = msg + " ";
        }
        return msg;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @param len
     * @return
     */
    public static String appendLengthForMessage(String msg, int len) {
        if (TextUtils.isEmpty(msg)) {
            return String.format("%0" + len + "d", 0);
        }
        if (len <= msg.length()) {
            return msg;
        }
        int length = len - msg.length();
        return String.format("%0" + length + "d%s", 0, msg);
    }

    /**
     * 16进制字符串转换成int数组
     *
     * @param str
     * @return
     */
    public static byte[] hexStr(String str) {

        byte[] bt = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            bt[i] = (byte) parseInt(str.substring(i * 2, 2 * (i + 1)),
                    16);
        }
        return bt;
    }

    /**
     * 公交卡号后八位转换
     *
     * @param asc
     * @return
     */
    public static String AscToHex(String asc) {
        if (TextUtils.isEmpty(asc)) return null;
        long[] r = new long[4];

        for (int i = 0; i < 4; i += 1) {
            String ss = asc.substring(i * 2, 2 + (i * 2));
            r[i] = pasInt(ss);
        }
        long j = r[0] * 256 * 256 * 256 + r[1] * 256 * 256 + r[2] * 256 + r[3];
        return String.valueOf(j);
    }

    /**
     * 发送数组包头
     *
     * @param cmdHeader
     * @return
     */
    public static byte[] getTempByte(byte cmdHeader) {
        if (mTempData == null || cmdHeader != mTempData[0]) {
            mTempData = new byte[20];
            mTempData[0] = cmdHeader;
            mTempData[1] = 1;
        }
        return mTempData;
    }

    /**
     * @param @param  msg
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: pasInt
     * @Description: TODO(16进制在字符转10进制)
     */
    public static long pasInt(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return 0;
        }
        long a = Long.parseLong(msg, 16);
        return a;
    }

    public static int crcTable(byte[] bytes) {
        int[] table = {
                0x0000, 0x1189, 0x2312, 0x329B, 0x4624, 0x57AD, 0x6536, 0x74BF,

                0x8C48, 0x9DC1, 0xAF5A, 0xBED3, 0xCA6C, 0xDBE5, 0xE97E, 0xF8F7,

                0x1081, 0x0108, 0x3393, 0x221A, 0x56A5, 0x472C, 0x75B7, 0x643E,

                0x9CC9, 0x8D40, 0xBFDB, 0xAE52, 0xDAED, 0xCB64, 0xF9FF, 0xE876,

                0x2102, 0x308B, 0x0210, 0x1399, 0x6726, 0x76AF, 0x4434, 0x55BD,

                0xAD4A, 0xBCC3, 0x8E58, 0x9FD1, 0xEB6E, 0xFAE7, 0xC87C, 0xD9F5,

                0x3183, 0x200A, 0x1291, 0x0318, 0x77A7, 0x662E, 0x54B5, 0x453C,

                0xBDCB, 0xAC42, 0x9ED9, 0x8F50, 0xFBEF, 0xEA66, 0xD8FD, 0xC974,

                0x4204, 0x538D, 0x6116, 0x709F, 0x0420, 0x15A9, 0x2732, 0x36BB,

                0xCE4C, 0xDFC5, 0xED5E, 0xFCD7, 0x8868, 0x99E1, 0xAB7A, 0xBAF3,

                0x5285, 0x430C, 0x7197, 0x601E, 0x14A1, 0x0528, 0x37B3, 0x263A,

                0xDECD, 0xCF44, 0xFDDF, 0xEC56, 0x98E9, 0x8960, 0xBBFB, 0xAA72,

                0x6306, 0x728F, 0x4014, 0x519D, 0x2522, 0x34AB, 0x0630, 0x17B9,

                0xEF4E, 0xFEC7, 0xCC5C, 0xDDD5, 0xA96A, 0xB8E3, 0x8A78, 0x9BF1,

                0x7387, 0x620E, 0x5095, 0x411C, 0x35A3, 0x242A, 0x16B1, 0x0738,

                0xFFCF, 0xEE46, 0xDCDD, 0xCD54, 0xB9EB, 0xA862, 0x9AF9, 0x8B70,

                0x8408, 0x9581, 0xA71A, 0xB693, 0xC22C, 0xD3A5, 0xE13E, 0xF0B7,

                0x0840, 0x19C9, 0x2B52, 0x3ADB, 0x4E64, 0x5FED, 0x6D76, 0x7CFF,

                0x9489, 0x8500, 0xB79B, 0xA612, 0xD2AD, 0xC324, 0xF1BF, 0xE036,

                0x18C1, 0x0948, 0x3BD3, 0x2A5A, 0x5EE5, 0x4F6C, 0x7DF7, 0x6C7E,

                0xA50A, 0xB483, 0x8618, 0x9791, 0xE32E, 0xF2A7, 0xC03C, 0xD1B5,

                0x2942, 0x38CB, 0x0A50, 0x1BD9, 0x6F66, 0x7EEF, 0x4C74, 0x5DFD,

                0xB58B, 0xA402, 0x9699, 0x8710, 0xF3AF, 0xE226, 0xD0BD, 0xC134,

                0x39C3, 0x284A, 0x1AD1, 0x0B58, 0x7FE7, 0x6E6E, 0x5CF5, 0x4D7C,

                0xC60C, 0xD785, 0xE51E, 0xF497, 0x8028, 0x91A1, 0xA33A, 0xB2B3,

                0x4A44, 0x5BCD, 0x6956, 0x78DF, 0x0C60, 0x1DE9, 0x2F72, 0x3EFB,

                0xD68D, 0xC704, 0xF59F, 0xE416, 0x90A9, 0x8120, 0xB3BB, 0xA232,

                0x5AC5, 0x4B4C, 0x79D7, 0x685E, 0x1CE1, 0x0D68, 0x3FF3, 0x2E7A,

                0xE70E, 0xF687, 0xC41C, 0xD595, 0xA12A, 0xB0A3, 0x8238, 0x93B1,

                0x6B46, 0x7ACF, 0x4854, 0x59DD, 0x2D62, 0x3CEB, 0x0E70, 0x1FF9,

                0xF78F, 0xE606, 0xD49D, 0xC514, 0xB1AB, 0xA022, 0x92B9, 0x8330,

                0x7BC7, 0x6A4E, 0x58D5, 0x495C, 0x3DE3, 0x2C6A, 0x1EF1, 0x0F78
        };

        int crc = 0xFFFF;

        for (byte b : bytes) {
            crc = (crc >> 8) ^ table[(crc ^ b) & 0xff];
        }
        return crc;
    }

    /**
     * 16进制转2进制字符串
     *
     * @param hexString
     * @return
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static String toAmountString(float value) {
        return String.format("%.2f", value);
    }

    private final static String[] binaryArray = {
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    /**
     * bytes转换成十六进制字符串
     */
    public static String bytes2HexString(byte[] a) {
        if (a == null) {
            return null;
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < a.length; n++) {
            stmp = (Integer.toHexString(a[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * 16进制字符串转换成byte数组
     *
     * @param str
     * @return
     */
    public static byte[] hexString2Bytes(String str) {
        byte[] bt = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            bt[i] = (byte) Integer.parseInt(str.substring(i * 2, 2 * (i + 1)),
                    16);
        }
        return bt;
    }

    //字符串转换成十六进制字符串
    public static String string2HexString(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    //十六进制字符串转换字符串
    public static String hexString2String(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    //位图信息（字节码）解析成二进制字符串
    @SuppressWarnings("unused")
    private static String bytes2BinaryStr(byte[] bArray) {
        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            // 高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            // 低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;
    }

    //单个字节压缩成BCD
    public static byte ASCII_To_BCD(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte) (asc - 'a' + 10);
        else
            bcd = (byte) (asc - 48);
        return bcd;
    }

    //压缩成BCD码
    public static byte[] ASCII_To_BCD(byte[] ascii) {
        int asc_len = ascii.length;
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = ASCII_To_BCD(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : ASCII_To_BCD(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    //BCD码解压缩成string
    public static String BCD_To_Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * 根据加入list的顺序将字节数组拼接起来
     *
     * @param list
     * @return
     */
    public static byte[] sumList(List<byte[]> list) {
        int length = 0;
        for (byte[] b : list) {
            length += b.length;
        }
        byte[] bb = new byte[length];
        int index = 0;
        for (byte[] b : list) {
            System.arraycopy(b, 0, bb, index, b.length);
            index += b.length;
        }
        return bb;
    }

    /**
     * 16进制在字符转10进制
     *
     * @param msg
     * @return
     */
    public static long hexStringToLong(String msg) {
        long a = 0;
        for (int i = 0; i < msg.length(); i++) {
            a = a + Integer.parseInt(msg.substring(i, i + 1), 16)
                    * ((long) Math.pow(16, (msg.length() - (i + 1))));
        }
        return a;
    }

    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (TextUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }


    /**
     * crc 校验
     *
     * @param crc
     * @param bytes
     * @param len
     * @return
     */
    public static int crc16(int crc, byte[] bytes, int len) {
        int table[] = {
                0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50A5, 0x60C6, 0x70E7,
                0x8108, 0x9129, 0xA14A, 0xB16B, 0xC18C, 0xD1AD, 0xE1CE, 0xF1EF,
                0x1231, 0x0210, 0x3273, 0x2252, 0x52B5, 0x4294, 0x72F7, 0x62D6,
                0x9339, 0x8318, 0xB37B, 0xA35A, 0xD3BD, 0xC39C, 0xF3FF, 0xE3DE,
                0x2462, 0x3443, 0x0420, 0x1401, 0x64E6, 0x74C7, 0x44A4, 0x5485,
                0xA56A, 0xB54B, 0x8528, 0x9509, 0xE5EE, 0xF5CF, 0xC5AC, 0xD58D,
                0x3653, 0x2672, 0x1611, 0x0630, 0x76D7, 0x66F6, 0x5695, 0x46B4,
                0xB75B, 0xA77A, 0x9719, 0x8738, 0xF7DF, 0xE7FE, 0xD79D, 0xC7BC,
                0x48C4, 0x58E5, 0x6886, 0x78A7, 0x0840, 0x1861, 0x2802, 0x3823,
                0xC9CC, 0xD9ED, 0xE98E, 0xF9AF, 0x8948, 0x9969, 0xA90A, 0xB92B,
                0x5AF5, 0x4AD4, 0x7AB7, 0x6A96, 0x1A71, 0x0A50, 0x3A33, 0x2A12,
                0xDBFD, 0xCBDC, 0xFBBF, 0xEB9E, 0x9B79, 0x8B58, 0xBB3B, 0xAB1A,
                0x6CA6, 0x7C87, 0x4CE4, 0x5CC5, 0x2C22, 0x3C03, 0x0C60, 0x1C41,
                0xEDAE, 0xFD8F, 0xCDEC, 0xDDCD, 0xAD2A, 0xBD0B, 0x8D68, 0x9D49,
                0x7E97, 0x6EB6, 0x5ED5, 0x4EF4, 0x3E13, 0x2E32, 0x1E51, 0x0E70,
                0xFF9F, 0xEFBE, 0xDFDD, 0xCFFC, 0xBF1B, 0xAF3A, 0x9F59, 0x8F78,
                0x9188, 0x81A9, 0xB1CA, 0xA1EB, 0xD10C, 0xC12D, 0xF14E, 0xE16F,
                0x1080, 0x00A1, 0x30C2, 0x20E3, 0x5004, 0x4025, 0x7046, 0x6067,
                0x83B9, 0x9398, 0xA3FB, 0xB3DA, 0xC33D, 0xD31C, 0xE37F, 0xF35E,
                0x02B1, 0x1290, 0x22F3, 0x32D2, 0x4235, 0x5214, 0x6277, 0x7256,
                0xB5EA, 0xA5CB, 0x95A8, 0x8589, 0xF56E, 0xE54F, 0xD52C, 0xC50D,
                0x34E2, 0x24C3, 0x14A0, 0x0481, 0x7466, 0x6447, 0x5424, 0x4405,
                0xA7DB, 0xB7FA, 0x8799, 0x97B8, 0xE75F, 0xF77E, 0xC71D, 0xD73C,
                0x26D3, 0x36F2, 0x0691, 0x16B0, 0x6657, 0x7676, 0x4615, 0x5634,
                0xD94C, 0xC96D, 0xF90E, 0xE92F, 0x99C8, 0x89E9, 0xB98A, 0xA9AB,
                0x5844, 0x4865, 0x7806, 0x6827, 0x18C0, 0x08E1, 0x3882, 0x28A3,
                0xCB7D, 0xDB5C, 0xEB3F, 0xFB1E, 0x8BF9, 0x9BD8, 0xABBB, 0xBB9A,
                0x4A75, 0x5A54, 0x6A37, 0x7A16, 0x0AF1, 0x1AD0, 0x2AB3, 0x3A92,
                0xFD2E, 0xED0F, 0xDD6C, 0xCD4D, 0xBDAA, 0xAD8B, 0x9DE8, 0x8DC9,
                0x7C26, 0x6C07, 0x5C64, 0x4C45, 0x3CA2, 0x2C83, 0x1CE0, 0x0CC1,
                0xEF1F, 0xFF3E, 0xCF5D, 0xDF7C, 0xAF9B, 0xBFBA, 0x8FD9, 0x9FF8,
                0x6E17, 0x7E36, 0x4E55, 0x5E74, 0x2E93, 0x3EB2, 0x0ED1, 0x1EF0
        };
        int tmp = 0;
        int i = 0;
        while (len-- > 0) {
            crc = table[(crc >> 8 ^ bytes[i]) & 0xff] ^ (crc << 8);
            i = i + 1;
        }
        tmp = crc >> 8;
        tmp |= (crc & 0xff) << 8;
        return tmp;
    }


    /**
     * 汉字转换为十六进制ASCII码字符串
     *
     * @param s 汉字
     * @return 十六进制ASCII码字符串
     */
    public static String stringToHexASCIIString(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            sb.append(Integer.toHexString((int) chars[i]));
        }
        return sb.toString().toUpperCase();
    }


    /**
     * GBK码转汉字字符串
     *
     * @param nameEncoded GBK码
     * @return 汉字字符串
     */
    public static String GBKToStringChinese(String nameEncoded) {
        if (TextUtils.isEmpty(nameEncoded)) {
            return "";
        }
        byte[] ints1 = new byte[nameEncoded.length() / 2];
        for (int i = 0; i < ints1.length; i++) {
            String s = nameEncoded.substring(i * 2, (i + 1) * 2);
            ints1[i] = (byte) Integer.parseInt(s, 16);
        }
        String chinese = null;
        try {
            chinese = new String(ints1, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return chinese;
    }


    /**
     * 十六进制ASCII码字符串转换为汉字
     *
     * @param hexASCIIString 十六进制ASCII码字符串
     * @return 汉字
     */
    public static String hexASCIIStringToString(String hexASCIIString) {
//        int[] ints1 = new int[hexASCIIString.length() / 4];
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < ints1.length; i++) {
//            sb.append((char) Integer.parseInt(hexASCIIString.substring(i * 4, (i + 1) * 4), 16));
//        }
//        return sb.toString();

        int[] ints1 = new int[hexASCIIString.length() / 4];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ints1.length; i++) {
            String s = hexASCIIString.substring(i * 4, (i + 1) * 4);
            char c = (char) Integer.parseInt(s, 16);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 十六进制ASC码数字转字符串
     *
     * @param s
     * @return
     */
    public static String hexASCIINumToString(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    //需要使用4字节表示b
    public static String numToHex32(int b) {
        return String.format("%04x", b);
    }

    /**
     * byte[] 转int,高位在前
     *
     * @param bytes 字节数组
     * @return 返回 int 值
     */
    public static int bytes2Int(byte[] bytes) {
        int result = 0;
        String string = new String(bytes);
        try {
            result = Integer.valueOf(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 两个字节数组按位进行异或
     *
     * @param a 字节数组 a
     * @param b 字节数组 b
     * @return rb 返回处理后的字节数组
     */
    public static byte[] xor(byte[] a, byte[] b) {
        byte[] rb = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            rb[i] = (byte) (a[i] ^ b[i]);
        }
        return rb;
    }

    /**
     * 将b字节数组拼接到a字节数组末尾,生成大的字节数组
     *
     * @throws
     */
    public static byte[] joinByteBToBytesAEnd(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }


    /**
     * 16进制字符串转换成int数组
     *
     * @param hexStr
     * @return
     */
    public static int[] hexString2Ints(String hexStr) {

        int[] bt = new int[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            bt[i] = Integer.parseInt(hexStr.substring(i * 2, 2 * (i + 1)),
                    16);
        }
        return bt;
    }

    /**
     * 2 大小端位置转换
     *
     * @param str 字符串
     * @return 返回字符串
     */
    public static String changeStringModel2(String str) {
        if ("".equals(str) || null == str) {
            return "";
        }
        int len = str.length() / 2;
        StringBuffer sb = new StringBuffer();
        for (int i = len; i > 0; i--) {
            sb.append(str.substring(i * 2 - 2, i * 2));
        }
        return sb.toString();
    }


    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(int[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length / 2; i++) {
            char letter = (char) (bytes[2 * i + 1] * 256 + bytes[2 * i]);
            //16进制pare整形字符串。
            buffer.append(new Character(letter).toString());
        }
        return buffer.toString();
    }

//    /**
//     * 16进制直接转换成为字符串(无需Unicode解码)
//     * @param hexStr
//     * @return
//     */
//    public static String hexStr2Str(String hexStr) {
//        String str = "0123456789ABCDEF";
//        char[] hexs = hexStr.toCharArray();
//        byte[] bytes = new byte[hexStr.length() / 2];
//        int n;
//        for (int i = 0; i < bytes.length; i++) {
//            n = str.indexOf(hexs[2 * i]) * 16;
//            n += str.indexOf(hexs[2 * i + 1]);
//            bytes[i] = (byte) (n & 0xff);
//        }
//        return new String(bytes);
//    }

}
