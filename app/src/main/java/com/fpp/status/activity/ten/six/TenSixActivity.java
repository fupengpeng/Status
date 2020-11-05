package com.fpp.status.activity.ten.six;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fpp.status.R;
import com.othershe.dutil.callback.DownloadCallback;
import com.othershe.dutil.data.DownloadData;
import com.othershe.dutil.download.DownloadManger;
import com.othershe.dutil.utils.Utils;

import java.io.File;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import static com.othershe.dutil.data.Consts.NONE;

/**
 * Description:
 * Author: fpp
 * Date: 2018/7/27  15:47
 */

public class TenSixActivity  extends AppCompatActivity {

    /**
     * http://1.198.5.23/imtt.dd.qq.com/16891/B8723A0DB2F2702C04D801D9FD19822C.apk //阴阳师
     * http://1.82.215.170/imtt.dd.qq.com/16891/85B6221DE84C466310575D9FBCA453A8.apk  //天天酷跑
     * http://1.198.5.22/imtt.dd.qq.com/16891/8EEC7D8996760973B5CEA15ECA1700E3.apk  //消消乐
     */

    private TextView mTip;
    private TextView mProgress;
    private TextView mStart;
    private TextView mPause;
    private TextView mResume;
    private TextView mCancel;
    private TextView mRestart;
    private ProgressBar progressBar;

    private Context mContext;

    private String url;

    private DownloadManger downloadManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        mContext = this;

        mTip = (TextView) findViewById(R.id.tip);
        mProgress = (TextView) findViewById(R.id.progress);
        mStart = (TextView) findViewById(R.id.start);
        mPause = (TextView) findViewById(R.id.pause);
        mResume = (TextView) findViewById(R.id.resume);
        mCancel = (TextView) findViewById(R.id.cancel);
        mRestart = (TextView) findViewById(R.id.restart);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()

        final String name = "侧方停车";
        url = "http://img.xiaojiangjiakao.com/20180627/7podaodingdiantingchejiaocheng.mp4";

//        downloadManger = DUtil.init(mContext)
//                .url(url)
//                .path(Environment.getExternalStorageDirectory() + "/DUtil/")
//                .name(name + ".mp4")
//                .childTaskCount(3)
//                .build();
//                .start(new DownloadCallback() {
//
//            @Override
//            public void onStart(long currentSize, long totalSize, float progress) {
//                mTip.setText(name + "：准备下载中...");
//                Toast.makeText(TenSixActivity.this,"开始下载",Toast.LENGTH_LONG).show();
//                progressBar.setProgress((int) progress);
//                mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
//            }
//
//            @Override
//            public void onProgress(long currentSize, long totalSize, float progress) {
//                mTip.setText(name + "：下载中...");
//                progressBar.setProgress((int) progress);
//                mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
//            }
//
//            @Override
//            public void onPause() {
//                mTip.setText(name + "：暂停中...");
//            }
//
//            @Override
//            public void onCancel() {
//                mTip.setText(name + "：已取消...");
//            }
//
//            @Override
//            public void onFinish(File file) {
//                mTip.setText(name + "：下载完成...");
//                Toast.makeText(TenSixActivity.this,"下载完成",Toast.LENGTH_LONG).show();
//                Uri uri = Uri.fromFile(file);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setDataAndType(uri, "application/vnd.android.package-archive");
////                        startActivity(intent);
//            }
//
//            @Override
//            public void onWait() {
//
//            }
//
//            @Override
//            public void onError(String error) {
//                mTip.setText(name + "：下载出错...");
//            }
//        });


        DownloadData downloadData = new DownloadData();
        downloadData.setUrl(url);
        downloadData.setPath(Environment.getExternalStorageDirectory() + "/DUtil/");
        downloadData.setName(name + ".mp4");
        downloadData.setCurrentLength(3);
        downloadData.setPercentage(4);
        downloadData.setStatus(NONE);
        downloadData.setChildTaskCount(3);
        downloadData.setDate(new Date().getDate());
        downloadData.setLastModify("");


        downloadManger = DownloadManger.getInstance(this);
        downloadManger.setOnDownloadCallback(downloadData, new DownloadCallback() {

            @Override
            public void onStart(long currentSize, long totalSize, float progress) {
                mTip.setText(name + "：准备下载中...");
                progressBar.setProgress((int) progress);
                mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
            }

            @Override
            public void onProgress(long currentSize, long totalSize, float progress) {
                mTip.setText(name + "：下载中...");
                progressBar.setProgress((int) progress);
                mProgress.setText(Utils.formatSize(currentSize) + " / " + Utils.formatSize(totalSize) + "--------" + progress + "%");
            }

            @Override
            public void onPause() {
                mTip.setText(name + "：暂停中...");
            }

            @Override
            public void onCancel() {
                mTip.setText(name + "：已取消...");
            }

            @Override
            public void onFinish(File file) {
                mTip.setText(name + "：下载完成...");
                Uri uri = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
//                startActivity(intent);
            }

            @Override
            public void onWait() {

            }

            @Override
            public void onError(String error) {
                mTip.setText(name + "：下载出错...");
            }
        });
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.start(url);
                Toast.makeText(TenSixActivity.this,"开始",Toast.LENGTH_LONG).show();
            }
        });
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.pause(url);
                Toast.makeText(TenSixActivity.this,"暂停",Toast.LENGTH_LONG).show();
            }
        });

        mResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.resume(url);
                Toast.makeText(TenSixActivity.this,"继续",Toast.LENGTH_LONG).show();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.cancel(url);
                Toast.makeText(TenSixActivity.this,"取消",Toast.LENGTH_LONG).show();
            }
        });

        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManger.restart(url);
                Toast.makeText(TenSixActivity.this,"重新开始",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        downloadManger.destroy(url);
        super.onDestroy();
    }
}
