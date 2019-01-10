package com.fpp.status.view.download;

import android.content.Context;

import com.fpp.status.view.download.download.DBuilder;
import com.fpp.status.view.download.upload.DirectUploadBuilder;
import com.fpp.status.view.download.upload.FormUploadBuilder;


public class DUtil {

    /**
     * 下载
     *
     * @param context
     * @return
     */
    public static DBuilder init(Context context) {
        return new DBuilder(context);
    }

    /**
     * 表单式文件上传
     *
     * @return
     */
    public static FormUploadBuilder initFormUpload() {
        return new FormUploadBuilder();
    }

    /**
     * 将文件作为请求体上传
     *
     * @return
     */
    public static DirectUploadBuilder initUpload() {
        return new DirectUploadBuilder();
    }
}
