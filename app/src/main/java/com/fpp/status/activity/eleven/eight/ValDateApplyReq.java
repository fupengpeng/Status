package com.fpp.status.activity.eleven.eight;

public class ValDateApplyReq {
    private String cardId;              // String 是 卡号 16长度
    private String messageDateTime;     // String 是 售卡时间 14长度
    private String deviceId;            // String 是 设备号
    private String asn;                 // String 是 应用序列号 20长度
    private String uid;                 // String 是 芯片号 8长度
    private String random;              // String 是 卡片随机数 8长度
    private String macCode;             // String 是 验签码

    public ValDateApplyReq() {
    }

    public ValDateApplyReq(CardInfo mCardInfo, String random) {
        this.cardId = mCardInfo.getCard_id();
        this.messageDateTime = mCardInfo.getMessageDateTime();
        this.asn = mCardInfo.getCard_asn();
        this.uid = mCardInfo.getUid();
        this.random = random;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }
}
