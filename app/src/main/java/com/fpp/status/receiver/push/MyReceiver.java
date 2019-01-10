package com.fpp.status.receiver.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

//import com.huazhong.car.drivingjiang.ui.CommontActivity;
//import com.huazhong.car.drivingjiang.ui.me.appointment.MyAppointmentFragment;
//import com.huazhong.car.drivingjiang.utils.Constant;
//import com.huazhong.car.drivingjiang.utils.LogUtil;
//import com.huazhong.car.drivingjiang.utils.PushUtil;
//import com.huazhong.car.drivingjiang.utils.SPUtils;

import com.fpp.status.MainActivity;
import com.fpp.status.utils.Constant;
import com.fpp.status.utils.LogUtil;
import com.fpp.status.utils.SPUtils;
import com.fpp.status.utils.UIUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;


/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-推送演示----";

    /**
     * 接收消息
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
                    try {
                        Bundle bundle = intent.getExtras();
                        SPUtils.put(Constant.REGISTRATION_ID, JPushInterface.getRegistrationID(context));
                        LogUtil.e(TAG, "[MyReceiver] onReceive - " + intent.getAction()
                                + ", extras: " + printBundle(bundle) +
                                "   RegistrationID = " + JPushInterface.getRegistrationID(context)
                                + "    " + SPUtils.get(Constant.REGISTRATION_ID, "regittrationid"));
                        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                            LogUtil.e(TAG, "[MyReceiver] 获取RegistrationID: "
//                        + bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID));
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                LogUtil.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: "
                        + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                LogUtil.e(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                LogUtil.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
                receivingNotification( context,  bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                LogUtil.e(TAG, "[MyReceiver] 接收到推送的通知后，点击打开了通知");
                //打开自定义的Activity
                // TODO: 2018/5/9   推送消息待分类处理
                Intent i = new Intent(context, MainActivity.class);
                i.putExtras(bundle);
                i.putExtra("key", "key");
                i.putExtra("position","1");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // 跳转至我的消息界面
//                context.startActivity(CommontActivity.init(MessageFragment.newInstance(0), i));
                // 跳转至我的预约----已完成界面
                context.startActivity(i);

                openNotification( context,  bundle);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {

                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);

            } else {

            }
        } catch (Exception e) {

        }

    }

    // 显示消息
    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        LogUtil.e(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        LogUtil.e(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtil.e(TAG, "extras : " + extras);
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LogUtil.e(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtil.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("myKey");
            LogUtil.e(TAG,  "value = " + myValue);
        } catch (Exception e) {
            LogUtil.e(TAG, "-----" + e);
            return;
        }

    }

    //发送消息给mainactivity
    private void processCustomMessage(Context context, Bundle bundle) {

            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            if (!UIUtil.isEmpty(extras)) {
                try {
                    JSONObject extraJson = new JSONObject(extras);
                    if (extraJson.length() > 0) {
                        // TODO: 2018/5/10 推送消息数据处理
                    }
                } catch (JSONException e) {

                }

            }

    }
}
