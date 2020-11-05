package com.fpp.status.nfc;

import android.nfc.tech.IsoDep;


import com.fpp.status.utils.ByteUtil;
import com.fpp.status.utils.LogUtil;

import java.io.IOException;
import java.util.Arrays;

/**
 * Description    :
 * CreateAuthor: Cannan
 * CreateTime   : 2018/9/22     18:53
 * Project          : TestNFC
 * PackageName :  com.lansent.testnfc;
 */

public class NFC {

    // 声明ISO-DEP协议的Tag操作实例
    private static IsoDep tag;

    public NFC(IsoDep tag) throws IOException {
        // 初始化ISO-DEP协议的Tag操作类实例
        this.tag = tag;
        tag.setTimeout(5000);
        tag.connect();
    }

    private String printByte(byte[] data) {
        StringBuffer bf = new StringBuffer();
        for (byte b : data) {
            bf.append(Integer.toHexString(b & 0xFF));
            bf.append(",");
        }
        return bf.toString();
    }

    public String sendApdu(String apdu) throws IOException {
        byte[] apduBytes = ByteUtil.hexString2Bytes(apdu);
        LogUtil.e("APDU REQ:" + apdu);
        byte[] resp = tag.transceive(apduBytes);   //1. 在“COS命令框”输入“00A40000023F00”，然后点击“发送命令”，进入主目录。
        String respStr = ByteUtil.bytes2HexString(resp);
        LogUtil.e("APDU REP:" + respStr);
        return respStr;
    }

}
