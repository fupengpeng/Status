package com.fpp.status.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.LoudnessEnhancer;

import com.fpp.status.utils.LogUtil;

public class UpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            //Toast.makeText(context, intent.getDataString().substring(8), 2000).show();
            String packName = intent.getDataString().substring(8);
            //packName为所安装的程序的包名
            LogUtil.e("--------------" + packName);
        }
    }
}

