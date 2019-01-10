package com.fpp.status.view.download.upload;

import android.os.Handler;
import android.os.Message;

import com.fpp.status.view.download.callback.UploadCallback;
import com.fpp.status.view.download.utils.Utils;

import static com.fpp.status.view.download.data.Consts.ERROR;
import static com.fpp.status.view.download.data.Consts.FINISH;
import static com.fpp.status.view.download.data.Consts.PROGRESS;
import static com.fpp.status.view.download.data.Consts.START;

public class UploadProgressHandler {
    private UploadCallback uploadCallback;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    uploadCallback.onStart();
                    break;
                case PROGRESS:
                    uploadCallback.onProgress(msg.arg1, msg.arg2, Utils.getPercentage(msg.arg1, msg.arg2));
                    break;
                case FINISH:
                    uploadCallback.onFinish(msg.obj.toString());
                    break;
                case ERROR:
                    uploadCallback.onError(msg.obj.toString());
                    break;
            }
        }
    };

    public UploadProgressHandler(UploadCallback uploadCallback) {
        this.uploadCallback = uploadCallback;
    }

    public Handler getHandler() {
        return mHandler;
    }
}
