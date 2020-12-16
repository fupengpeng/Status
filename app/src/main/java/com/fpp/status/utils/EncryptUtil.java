package com.fpp.status.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtil {

    private static final String ALGORITHM = "DES";

    private byte asc_to_bcd(byte asc) {
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

    public byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    public String bcd2Str(byte[] bytes) {
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
     * 把by按照8字节数组进行拆分
     */
    public static byte[] distributeBytes(byte[] by, int j) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = by[i + j * 8];
        }
        return b;
    }

    /**
     * 3des解密
     */
    public byte[] tripleUndes(byte[] keySet, byte[] keyBcd) {
        // 解密密钥BCD码前8位
        byte[] keyBcdOne = distributeBytes(keyBcd, 0);
        // 解密密钥BCD码后8位
        byte[] keyBcdTwo = distributeBytes(keyBcd, 1);

        byte[] tempStr1 = decrypt(keySet, keyBcdOne);
        byte[] tempStr2 = encrypt(tempStr1, keyBcdTwo);
        byte[] tempStr3 = decrypt(tempStr2, keyBcdOne);

        return tempStr3;
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public byte[] encrypt(byte[] datasource, byte[] password) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(datasource);
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public byte[] decrypt(byte[] src, byte[] password) {
        byte[] a = {};
        Cipher cipher = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(src);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 真正开始解密操作
        try {
            a = cipher.doFinal(src);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e1) {
            e1.printStackTrace();
        }
        return a;
    }

    public byte[] combineBytes(byte[] b1, byte[] b2) {
        int length = b1.length + b2.length;
        byte[] nbs = new byte[length];
        for (int i = 0; i < b1.length; i++) {
            nbs[i] = b1[i];
        }
        for (int i = 0; i < b2.length; i++) {
            nbs[b1.length + i] = b2[i];
        }
        return nbs;
    }

    /**
     * 将a与b进行按位异或
     */
    public static byte[] xor(byte[] a, byte[] b, int j) {
        byte[] rb = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            rb[i] = (byte) (a[i] ^ b[j * 8 + i]);
        }
        return rb;
    }

    /**
     * 3des加密
     */
    public byte[] tripledesForCardReader(byte[] keySet, byte[] keyBcd) {
        // 解密密钥BCD码前8位
        byte[] keyBcdOne = distributeBytes(keyBcd, 0);
        // 解密密钥BCD码后8位
        byte[] keyBcdTwo = distributeBytes(keyBcd, 1);

        byte[] tempStr1 = encrypt(keySet, keyBcdOne);
        byte[] tempStr2 = decrypt(tempStr1, keyBcdTwo);
        byte[] tempStr3 = encrypt(tempStr2, keyBcdOne);

        return tempStr3;
    }

    /**
     * 使用工作密钥(key)加密源数据 <功能详细描述>
     *
     * @param sourceData
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String generateMacKey(String sourceData, String key, int times) {
        EncryptUtil encryptUtil = new EncryptUtil();
        // 加密密钥BCD码
        byte[] macKeyBcd = encryptUtil.ASCII_To_BCD(key.getBytes(),
                key.length());
        // 加密密钥前8位
        byte[] encryptKey = encryptUtil.distributeBytes(macKeyBcd, 0);
        // 加密密钥后8位
        byte[] decodeKey = encryptUtil.distributeBytes(macKeyBcd, 1);
        // 待加密数据明文对应的BCD码
        byte[] sourceDataBcd = encryptUtil.ASCII_To_BCD(sourceData.getBytes(),
                sourceData.length());
        byte[] initData = {0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < times; i++) {
            initData = encryptUtil.xor(initData, sourceDataBcd, i);
            initData = encryptUtil.encrypt(initData, encryptKey);
            initData = encryptUtil.decrypt(initData, decodeKey);
            initData = encryptUtil.encrypt(initData, encryptKey);
        }

        String outString = encryptUtil.bcd2Str(initData);

        return outString;
    }

    /**
     * 计算MAC算法
     *
     * @param paramsMap 源字符串
     * @param key       密钥
     * @return
     */
    public static String calculateMac(HashMap<String, String> paramsMap, String key) {
        String source = "";
        Iterator<String> iter = paramsMap.values().iterator();
        while (iter.hasNext()) {
            source = source + iter.next();
        }
        if (StringUtil.hasBlankValue(source, key)) {
            return "source data or key data can't be null!";
        }
        if (32 != key.length()) {
            return "key length error! need:32, actural:" + key.length();
        }
		/*if (0 != (source.length() % 16)) {
			return "source length error!";
		}*/
        source = complete80ForMac(source);
        System.out.println("工具计算mac字符串:" + source);
        // 待加密数据明文对应的BCD码
        byte[] sourceBt = Bytes.ASCII_To_BCD(source.getBytes());
        // 加密密钥BCD码
        byte[] keyBt = Bytes.ASCII_To_BCD(key.getBytes());

        return calculateMac(sourceBt, keyBt);
    }

    /**
     * 计算MAC算法
     *
     * @param source 源字符串
     * @param key    密钥
     * @return
     */
    public static String calculateMac(String source, String key) {
        if (StringUtil.hasBlankValue(source, key)) {
            return "source data or key data can't be null!";
        }
        if (32 != key.length()) {
            return "key length error! need:32, actural:" + key.length();
        }
		/*if (0 != (source.length() % 16)) {
			return "source length error!";
		}*/
        source = complete80ForMac(source);
        System.out.println("工具计算mac字符串:" + source);
        // 待加密数据明文对应的BCD码
        byte[] sourceBt = Bytes.ASCII_To_BCD(source.getBytes());
        // 加密密钥BCD码
        byte[] keyBt = Bytes.ASCII_To_BCD(key.getBytes());

        return calculateMac(sourceBt, keyBt);
    }

    /**
     * 计算mac的str补80
     *
     * @param str
     * @return
     */
    public static String complete80ForMac(String str) {
        int num = str.length() % 16;
        if (num == 0) {
            return str;
        }
        String returnStr = "8000000000000000".substring(0, 16 - num);
        return str + returnStr;
    }

    /**
     * 计算MAC算法
     *
     * @param source 源字符串
     * @param key    密钥
     * @return
     */
    public static String calculateMac(byte[] source, byte[] key) {
        if (16 != key.length) {
            return "key length error! need:32, actural:" + key.length;
        }
        if (0 != (source.length % 8)) {
            return "source length error!";
        }

        int times = source.length / 8;
        // 加密密钥前8位
        byte[] keyLeft = EncryptUtil.distributeBytes(key, 0);
        // 加密密钥后8位
        byte[] keyRight = EncryptUtil.distributeBytes(key, 1);
        byte[] initData = {0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < times; i++) {
            initData = EncryptUtil.xor(initData, source, i);
            initData = EncryptUtil.ecbEncrypt(initData, keyLeft);
        }
        initData = EncryptUtil.ecbDecrypt(initData, keyRight);
        initData = EncryptUtil.ecbEncrypt(initData, keyLeft);

        String outString = Bytes.BCD_To_Str(initData);

        return outString.substring(0, 8);
    }

    /**
     * ECB加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] ecbEncrypt(byte[] datasource, byte[] password) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(datasource);
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ECB解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] ecbDecrypt(byte[] src, byte[] password) {
        byte[] a = {};
        Cipher cipher = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(src);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 真正开始解密操作
        try {
            a = cipher.doFinal(src);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e1) {
            e1.printStackTrace();
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(calculateMac("18629518283", "46F795B0D33B87311F8B8A378C3A3964"));
        System.out.println(calculateMac("2150999999990003530000a", "096473137898B5B133B86F33AF8AD498"));
    }
}
