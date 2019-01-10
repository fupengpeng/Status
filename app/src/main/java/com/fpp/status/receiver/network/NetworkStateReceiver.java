package com.fpp.status.receiver.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import com.fpp.status.utils.NetworkUtils;

import java.util.ArrayList;

/**
 * 网络状态广播接收器
 * 使用步骤：
 * 0：加权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * 1：注册网络状态广播接收器 NetworkStateReceiver.registerNetworkStateReceiver(context)
 * 2: 注册网络连接状态观察者 NetworkStateReceiver.registerObserver(observer)
 * 3: 检查网络状态(可选) NetworkStateReceiver.checkNetworkState(context)
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    /**
     * 标识网络是否可用
     */
    private static Boolean networkAvailable = Boolean.valueOf(false);
    /**
     * 网络连接类型
     */
    private static NetworkUtils.netType netType;
    /**
     * 网络状态观察者集合
     */
    private static ArrayList<NetworkChangeObserver> observers = new ArrayList<>();
    /**
     * 广播接收器
     */
    private static BroadcastReceiver receiver;
    /**
     * Action：当网络状态改变时，Android系统发出的广播
     */
    private static final String ACTION_ANDROID_NET_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    /**
     * Action：用于检查网络状态
     */
    public static final String ACTION_CHECK_NETWORK_STATE = "android.net.conn.CHECK_NETWORK_STATE";

    /**
     * 检查网络状态
     *
     * @param context context
     */
    public static void checkNetworkState(Context context) {
        // 发送广播检查网络状态
        context.sendBroadcast(new Intent(ACTION_CHECK_NETWORK_STATE));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        receiver = this;
        // action
        String action = intent.getAction();
        // 判断广播类型
        if (ACTION_CHECK_NETWORK_STATE.equalsIgnoreCase(action)
                || ACTION_ANDROID_NET_CHANGE.equalsIgnoreCase(action)) {
            // 判断网络是否连接
            if (!NetworkUtils.isConnected(context)) { // 未连接
                networkAvailable = false;
            } else { // 已连接
                // 获取连接类型
                netType = NetworkUtils.getAPNType(context);
                networkAvailable = true;
            }
            notifyObservers();
        }
    }

    /**
     * 通知观察者
     */
    private void notifyObservers() {
        for (NetworkChangeObserver observer : observers) {
            if (observer == null) {
                continue;
            }
            // 判断网络是否可用
            if (isNetworkAvailable()) {
                observer.onConnect(netType);
            } else {
                observer.onDisConnect();
            }
        }
    }

    /**
     * 注册网络状态广播接收器
     *
     * @param context context
     */
    public static void registerNetworkStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ANDROID_NET_CHANGE);
        filter.addAction(ACTION_CHECK_NETWORK_STATE);
        context.registerReceiver(getReceiver(), filter);
    }

    /**
     * 获取网络状态广播接收器对象
     *
     * @return 网络状态广播接收器对象
     */
    private static BroadcastReceiver getReceiver() {
        if (receiver == null) {
            receiver = new NetworkStateReceiver();
        }
        return receiver;
    }

    /**
     * 注销网络状态广播接收器
     *
     * @param context context
     */
    public static void unRegisterNetworkStateReceiver(Context context) {
        if (receiver != null) {
            try {
                context.unregisterReceiver(receiver);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取网络连接类型
     *
     * @return 网络连接类型
     */
    public static NetworkUtils.netType getAPNType() {
        return netType;
    }

    /**
     * 判断当前网络是否可用
     *
     * @return true:可用；false:不可用；
     */
    public static Boolean isNetworkAvailable() {
        return networkAvailable;
    }

    /**
     * 注册网络连接状态观察者
     *
     * @param observer 网络连接状态观察者
     */
    public static void registerObserver(NetworkChangeObserver observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    /**
     * 移除网络连接状态观察者
     *
     * @param observer 网络连接状态观察者
     */
    public static void removeRegisterObserver(NetworkChangeObserver observer) {
        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * 网络连接状态观察者
     */
    public interface NetworkChangeObserver {
        /**
         * 当网络连接时被调用
         *
         * @param networkType 网络连接类型
         */
        void onConnect(NetworkUtils.netType networkType);

        /**
         * 当网络断开连接时被调用
         */
        void onDisConnect();
    }
}
