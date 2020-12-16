package com.fpp.status.utils;

public class Bytes {
	
	private final static String[] binaryArray = {
		"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", 
		"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};


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
    public static byte ASCII_To_BCD(byte asc)
    {
        byte bcd;
        
        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte)(asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte)(asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte)(asc - 'a' + 10);
        else
            bcd = (byte)(asc - 48);
        return bcd;
    }
    
    //压缩成BCD码
    public static byte[] ASCII_To_BCD(byte[] ascii)
    {
    	int asc_len = ascii.length;
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++)
        {
            bcd[i] = ASCII_To_BCD(ascii[j++]);
            bcd[i] = (byte)(((j >= asc_len) ? 0x00 : ASCII_To_BCD(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }
    
    //BCD码解压缩成string
    public static String BCD_To_Str(byte[] bytes)
    {
        char temp[] = new char[bytes.length * 2], val;
        
        for (int i = 0; i < bytes.length; i++)
        {
            val = (char)(((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
            
            val = (char)(bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
}
