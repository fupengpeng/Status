package com.fpp.status.utils;

import android.os.Environment;



/**
 * 作者：Jelly
 * 详情：常量
 */
public class Constant {

    public static final String APP_ID = "wxd3dde3a672fe58df";
    /**
     * 用户信息
     */
    public static final String USER_INFO = "USER_INFO";

    public static final String PRIVINCE_INFO = "PRIVINCE_INFO";
    /**
     * 拍照片使用的
     */
    public static final int ACTION_IMAGE_CAPTURE = 10;
    public static final int ACTION_PICK = 11;

    /**
     * 刷新   主页的action
     */
    public static final String REFRESH_ACTION = "REFRESH_ACTION";
    public static final String FIRST_IN = "FIRST_IN";
    public static boolean NEED_REFRESH_MAINACTIVITY =false;
    public static boolean NEED_REFRESH_KEMU3 =false;
    public static boolean NEED_REFRESH_TOUXIANG =false;
    public static boolean NEED_REFRESH_USER_INFO =false;
    public static boolean NEED_REFRESH_YUYUE =false;
    public static final String REFRESH_DATA = "REFRESH_DATA";
    /**
     * 登录方式
     */
    public static final String LOGIN_FOR_PSW = "LOGIN_FOR_PSW";
    /**
     * 图片路径
     */
    public static final String TOUXIANG_PATH ="TOUXIANG_PATH";

    private static String picturePath=null;



    /**
     * 更换城市用户选择的城市id
     */
    public static final String CACHE_CHOOSE_CITY = "CACHE_CHOOSE_CITY";
    public static final String CACHE_CHOOSE_CITY_ID = "CACHE_CHOOSE_CITY_ID";


    public static final String CACHE_CHOOSE_SCHOOL_ID = "CACHE_CHOOSE_SCHOOL_ID";

    public static final String NEED_UPDATA_CITY_DATA = "NEED_UPDATA_CITY_DATA";

    public static final String YUYUE_KEMU2_AM = "YUYUE_KEMU2_AM";
    public static final String YUYUE_KEMU2_PM = "YUYUE_KEMU2_PM";
    public static final String YUYUE_KEMU3_AM = "YUYUE_KEMU3_AM";
    public static final String YUYUE_KEMU3_PM = "YUYUE_KEMU3_PM";

    //当前城市
    public static final String LOCAL_CITY = "LOCAL_CITY";

    public static final String DEVICE_TYPE = "android";

    public static boolean FROM_QRCODE = false;

    public static String APPOINTMENT_ID = "APPOINTMENT_ID";
    //退出
    public static String EXIT_APP = "EXIT_APP";
    //h5页面title
    public static final String WEB_TITLE = "WEB_TITLE";

    // 推送消息开关
    public static String MSG_OPEN = "msg_open";
    // 极光推送设备id
    public static String REGISTRATION_ID = "registrationid";

    /**
     * 文件下载基路径
     */
    public static final String DOWNLOAD_BASE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/";
    /**
     * APK文件名
     */
    public static final String APK_NAME = "xjjk.apk";

    /**
     * 位置信息
     */
    public static final String LOCATION_INFO = "LOCATION_INFO";
    /**
     * 事件类型
     */
    public class DataEventType {
        /**
         * 网络连接可用
         */
        public static final int NET_CONNECTION_ACCESS = 101;
        /**
         * 网络连接断开
         */
        public static final int NET_CONNECTION_BREAK = 102;

        /**
         * 最新版本
         */
        public static final int VERSION_NEW = 201;
        /**
         * 版本更新
         */
        public static final int VERSION_UPDATE = 202;
        /**
         * 版本更新取消
         */
        public static final int VERSION_UPDATE_CANCEL = 203;
        /**
         * APK下载失败
         */
        public static final int VERSION_DOWNLOAD_ERROR = 204;
        /**
         * APK下载进度
         */
        public static final int VERSION_DOWNLOAD_PROGRESS = 205;
        /**
         * APK下载完成
         */
        public static final int VERSION_DOWNLOAD_FINISH = 206;
    }

    /**
     * 强制更新
     */
    public static final int UPDATE_COERCE = 10003;
    /**
     * 提示更新
     */
    public static final int UPDATE_HINT = 10002;


}
