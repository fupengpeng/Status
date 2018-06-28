package com.fpp.status.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 */
public class NetworkUtils {

    /**
     * 网络连接类型
     * 枚举取值：wifi, CMNET, CMWAP, noneNet
     */
    public static enum netType {
        wifi, CMNET, CMWAP, noneNet;
    }

    private NetworkUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        // 获取连接管理器
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // 判断是否获取到连接管理器
        if (null != connectivity) {
            // 获取活动的网络信息
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            // 判断网络是否连接
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 获取网络连接类型
     *
     * @param context context
     * @return 网络连接类型
     */
    public static netType getAPNType(Context context) {
        // 获取连接管理器
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断是否获取到连接管理器
        if (null == connectivity) {
            return netType.noneNet;
        }

        // 获取活动的网络信息
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType.noneNet;
        }

        // 获取连接类型
        int type = networkInfo.getType();
        // 判读是否是移动流量
        if (type == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                return netType.CMNET;
            }
            return netType.CMWAP;
        }
        // 判断是否是WIFI
        if (type == ConnectivityManager.TYPE_WIFI) {
            return netType.wifi;
        }

        return netType.noneNet;
    }
}