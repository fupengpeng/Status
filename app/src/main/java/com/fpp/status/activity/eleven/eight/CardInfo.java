package com.fpp.status.activity.eleven.eight;

import java.io.Serializable;

/**
 * @Description 卡片信息
 * @Author fpp
 * @Date 2020/3/5 0005 11:12
 */
public class CardInfo implements Serializable {


    // 3F01 15 文件数据
    // 544B 3500 0000 FFFF 01 01 3500 3500000100003133 20181107 20730810 FFFF 02 00 08 20730810 01 00 0000 00000000000000 9000
    // 544B35000000FFFF0101350035000001000031332018110720730810FFFF02000820730810 01000000000000000000009000
    private String cardIssuerLogo;          // 发卡机构标识  发卡方代码
    private String cityCode;                // 城市代码
    private String tradeCode;               // 行业代码
    private String rfu;                     // Rfu
    private String appTypeIdentification;   // 应用类型标识
    private String appVersionOrganization;  // 发卡机构应用版本  应用版本
    private String cityOrPost;              // 互联互通标识（参与互通城市的标识） 城市代码or邮编
    private String cardNo;                  // 卡号 应用序列号
    private String startTime;               // 开卡时间 应用启动日期
    private String endTime;                 // 卡失效时间 应用有效日期
    private String fciData;                 // 发卡机构自定义FCI数据 预留
    private String cardSType;               // 主卡类型
    private String cardMType;               // 子卡类型
    private String cardAppTag;              // 卡应用标志
    private String yearCheckDate;           // 年检日期
    private String enabled;                 // 应用启用标志
    private String discountRate;            // 卡片折扣率
    private String indate;                  // 有效天数

    private boolean isEnabled;              // 是否启用

    private String balance;   // 余额
    private String balanceHex;   // 余额

    // 3F02 15 文件
    private String appVersionOrganization3F02;  //   应用版本（保留）
    private String enabled3F02;                 // 应用启用标志 00：未启用 01：启用
    private String startTime3F02;               // 计次启用日期
    private String endTime3F02;                 // 计次截至日期
    private String cardinalNumber3F02;          // 计次基数
    private String yearCheckDate3F02;           // 年检截止日期
    private String monthWalletTag3F02;          // 月票钱包标识   0:标准月票    1:钱包月票
    private String monthWallet0013F02;          // 月票钱包1有效月份  YYMM
    private String monthWallet001CN3F02;        // 月票钱包1计费基数  HEX	（单位分最大65535分）
    private String monthWallet0023F02;          // 月票钱包2有效月份
    private String monthWallet002CN3F02;        // 月票钱包2计费基数
    private String monthWallet0033F02;          // 月票钱包3有效月份
    private String monthWallet003CN3F02;        // 月票钱包3计费基数
    private String monthWallet0043F02;          // 月票钱包4有效月份
    private String monthWallet004CN3F02;        // 月票钱包4计费基数
    private String monthWallet0053F02;          // 月票钱包复充月份
    private String monthWallet005Count3F02;     // 月票钱包复充次数 复充月份不同时从0重新开始

    // 3F00 05文件
    private String deposit;                     // 押金

    private String name;                        // 姓名
    private String id;                          // 证件号码
    private String idType;                      // 证件类型


    private String endDate3F02;                 // 老年卡年检日期or月票启用信息or月票应用有效期
    private int cardType;                       // 1:交通部 2:住建部
    private String random;                      // 随机数
    private String yearCheck62;                  // 年检  62 域数据


    private String cardSeq;   //计数器
    private String cardTradeNo;    //交易序号
    private String keyVer;    //密钥版本号
    private String alglnd;    //算法标识
    private String cardRand;  //随机数
    private String qcMac;
    private String messageDateTime;

    private String tac;
    private boolean isUse; //是否启用
    private String monthExpiryDate;         // 月票有效期

    private boolean sameCard;


    private String orderType;

    public String getYearCheck62() {
        return yearCheck62;
    }

    public void setYearCheck62(String yearCheck62) {
        this.yearCheck62 = yearCheck62;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getEndDate3F02() {
        return endDate3F02;
    }

    public void setEndDate3F02(String endDate3F02) {
        this.endDate3F02 = endDate3F02;
    }

    public String getAppVersionOrganization3F02() {
        return appVersionOrganization3F02;
    }

    public void setAppVersionOrganization3F02(String appVersionOrganization3F02) {
        this.appVersionOrganization3F02 = appVersionOrganization3F02;
    }

    public String getEnabled3F02() {
        return enabled3F02;
    }

    public void setEnabled3F02(String enabled3F02) {
        this.enabled3F02 = enabled3F02;
    }

    public String getStartTime3F02() {
        return startTime3F02;
    }

    public void setStartTime3F02(String startTime3F02) {
        this.startTime3F02 = startTime3F02;
    }

    public String getEndTime3F02() {
        return endTime3F02;
    }

    public void setEndTime3F02(String endTime3F02) {
        this.endTime3F02 = endTime3F02;
    }

    public String getCardinalNumber3F02() {
        return cardinalNumber3F02;
    }

    public void setCardinalNumber3F02(String cardinalNumber3F02) {
        this.cardinalNumber3F02 = cardinalNumber3F02;
    }

    public String getYearCheckDate3F02() {
        return yearCheckDate3F02;
    }

    public void setYearCheckDate3F02(String yearCheckDate3F02) {
        this.yearCheckDate3F02 = yearCheckDate3F02;
    }

    public String getMonthWalletTag3F02() {
        return monthWalletTag3F02;
    }

    public void setMonthWalletTag3F02(String monthWalletTag3F02) {
        this.monthWalletTag3F02 = monthWalletTag3F02;
    }

    public String getMonthWallet0013F02() {
        return monthWallet0013F02;
    }

    public void setMonthWallet0013F02(String monthWallet0013F02) {
        this.monthWallet0013F02 = monthWallet0013F02;
    }

    public String getMonthWallet001CN3F02() {
        return monthWallet001CN3F02;
    }

    public void setMonthWallet001CN3F02(String monthWallet001CN3F02) {
        this.monthWallet001CN3F02 = monthWallet001CN3F02;
    }

    public String getMonthWallet0023F02() {
        return monthWallet0023F02;
    }

    public void setMonthWallet0023F02(String monthWallet0023F02) {
        this.monthWallet0023F02 = monthWallet0023F02;
    }

    public String getMonthWallet002CN3F02() {
        return monthWallet002CN3F02;
    }

    public void setMonthWallet002CN3F02(String monthWallet002CN3F02) {
        this.monthWallet002CN3F02 = monthWallet002CN3F02;
    }

    public String getMonthWallet0033F02() {
        return monthWallet0033F02;
    }

    public void setMonthWallet0033F02(String monthWallet0033F02) {
        this.monthWallet0033F02 = monthWallet0033F02;
    }

    public String getMonthWallet003CN3F02() {
        return monthWallet003CN3F02;
    }

    public void setMonthWallet003CN3F02(String monthWallet003CN3F02) {
        this.monthWallet003CN3F02 = monthWallet003CN3F02;
    }

    public String getMonthWallet0043F02() {
        return monthWallet0043F02;
    }

    public void setMonthWallet0043F02(String monthWallet0043F02) {
        this.monthWallet0043F02 = monthWallet0043F02;
    }

    public String getMonthWallet004CN3F02() {
        return monthWallet004CN3F02;
    }

    public void setMonthWallet004CN3F02(String monthWallet004CN3F02) {
        this.monthWallet004CN3F02 = monthWallet004CN3F02;
    }

    public String getMonthWallet0053F02() {
        return monthWallet0053F02;
    }

    public void setMonthWallet0053F02(String monthWallet0053F02) {
        this.monthWallet0053F02 = monthWallet0053F02;
    }

    public String getMonthWallet005Count3F02() {
        return monthWallet005Count3F02;
    }

    public void setMonthWallet005Count3F02(String monthWallet005Count3F02) {
        this.monthWallet005Count3F02 = monthWallet005Count3F02;
    }

    public String getBalanceHex() {
        return balanceHex;
    }

    public void setBalanceHex(String balanceHex) {
        this.balanceHex = balanceHex;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getCardIssuerLogo() {
        return cardIssuerLogo;
    }

    public void setCardIssuerLogo(String cardIssuerLogo) {
        this.cardIssuerLogo = cardIssuerLogo;
    }

    public String getAppTypeIdentification() {
        return appTypeIdentification;
    }

    public void setAppTypeIdentification(String appTypeIdentification) {
        this.appTypeIdentification = appTypeIdentification;
    }

    public String getAppVersionOrganization() {
        return appVersionOrganization;
    }

    public void setAppVersionOrganization(String appVersionOrganization) {
        this.appVersionOrganization = appVersionOrganization;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFciData() {
        return fciData;
    }

    public void setFciData(String fciData) {
        this.fciData = fciData;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCardSeq() {
        return cardSeq;
    }

    public void setCardSeq(String cardSeq) {
        this.cardSeq = cardSeq;
    }

    public String getCardTradeNo() {
        return cardTradeNo;
    }

    public void setCardTradeNo(String cardTradeNo) {
        this.cardTradeNo = cardTradeNo;
    }

    public String getKeyVer() {
        return keyVer;
    }

    public void setKeyVer(String keyVer) {
        this.keyVer = keyVer;
    }

    public String getAlglnd() {
        return alglnd;
    }

    public void setAlglnd(String alglnd) {
        this.alglnd = alglnd;
    }

    public String getCardRand() {
        return cardRand;
    }

    public void setCardRand(String cardRand) {
        this.cardRand = cardRand;
    }

    public String getQcMac() {
        return qcMac;
    }

    public void setQcMac(String qcMac) {
        this.qcMac = qcMac;
    }

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getCardSType() {
        return cardSType;
    }

    public void setCardSType(String cardSType) {
        this.cardSType = cardSType;
    }

    public String getCardMType() {
        return cardMType;
    }

    public void setCardMType(String cardMType) {
        this.cardMType = cardMType;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public String getMonthExpiryDate() {
        return monthExpiryDate;
    }

    public void setMonthExpiryDate(String monthExpiryDate) {
        this.monthExpiryDate = monthExpiryDate;
    }

    public boolean isSameCard() {
        return sameCard;
    }

    public void setSameCard(boolean sameCard) {
        this.sameCard = sameCard;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getRfu() {
        return rfu;
    }

    public void setRfu(String rfu) {
        this.rfu = rfu;
    }

    public String getCityOrPost() {
        return cityOrPost;
    }

    public void setCityOrPost(String cityOrPost) {
        this.cityOrPost = cityOrPost;
    }

    public String getCardAppTag() {
        return cardAppTag;
    }

    public void setCardAppTag(String cardAppTag) {
        this.cardAppTag = cardAppTag;
    }

    public String getYearCheckDate() {
        return yearCheckDate;
    }

    public void setYearCheckDate(String yearCheckDate) {
        this.yearCheckDate = yearCheckDate;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }
}
