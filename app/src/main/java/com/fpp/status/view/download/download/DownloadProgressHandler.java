package com.fpp.status.view.download.download;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.fpp.status.base.BaseApplication;
import com.fpp.status.view.download.callback.DownloadCallback;
import com.fpp.status.view.download.data.DownloadData;
import com.fpp.status.view.download.utils.Utils;

import java.io.File;

import static com.fpp.status.view.download.data.Consts.CANCEL;
import static com.fpp.status.view.download.data.Consts.DESTROY;
import static com.fpp.status.view.download.data.Consts.ERROR;
import static com.fpp.status.view.download.data.Consts.FINISH;
import static com.fpp.status.view.download.data.Consts.NONE;
import static com.fpp.status.view.download.data.Consts.PAUSE;
import static com.fpp.status.view.download.data.Consts.PROGRESS;
import static com.fpp.status.view.download.data.Consts.START;

public class DownloadProgressHandler {
    private String url;
    private String path;
    private String name;
    private int childTaskCount;

    private Context context;

    private DownloadCallback downloadCallback;
    private DownloadData downloadData;

    private FileTask fileTask;

    private int mCurrentState = NONE;

    //是否支持断点续传
    private boolean isSupportRange;

    //重新开始下载需要先进行取消操作
    private boolean isNeedRestart;

    //记录已经下载的大小
    private int currentLength = 0;
    //记录文件总大小
    private int totalLength = 0;
    //记录已经暂停或取消的线程数
    private int tempChildTaskCount = 0;

    private long lastProgressTime;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int mLastSate = mCurrentState;
            mCurrentState = msg.what;
            downloadData.setStatus(mCurrentState);

            switch (mCurrentState) {
                case START:
                    Bundle bundle = msg.getData();
                    totalLength = bundle.getInt("totalLength");
                    currentLength = bundle.getInt("currentLength");
                    String lastModify = bundle.getString("lastModify");
                    isSupportRange = bundle.getBoolean("isSupportRange");

                    if (!isSupportRange) {
                        childTaskCount = 1;
                    } else if (currentLength == 0) {
                        DownloadData downloadData = BaseApplication.daoSession.getDownloadDataDao().queryBuilder()
                                .where(DownloadDataDao.Properties.Url.eq(url)).unique();
                        if (downloadData != null){
                            downloadData.setUrl(url);
                            downloadData.setPath(path);
                            downloadData.setChildTaskCount(childTaskCount);
                            downloadData.setName(name);
                            downloadData.setCurrentLength(currentLength);
                            downloadData.setTotalLength(totalLength);
                            downloadData.setLastModify(lastModify);
                            downloadData.setDate(System.currentTimeMillis());
                            BaseApplication.daoSession.getDownloadDataDao().update(downloadData);
                        }else {
                            BaseApplication.daoSession.getDownloadDataDao().insert(
                                    new DownloadData(url, path, childTaskCount, name
                                            , currentLength, totalLength, lastModify, System.currentTimeMillis()));
                        }

                    }
                    if (downloadCallback != null) {
                        downloadCallback.onStart(currentLength, totalLength, Utils.getPercentage(currentLength, totalLength));
                    }
                    break;
                case PROGRESS:
                    synchronized (this) {
                        currentLength += msg.arg1;

                        downloadData.setPercentage(Utils.getPercentage(currentLength, totalLength));

                        if (downloadCallback != null && (System.currentTimeMillis() - lastProgressTime >= 20 || currentLength == totalLength)) {
                            downloadCallback.onProgress(currentLength, totalLength, Utils.getPercentage(currentLength, totalLength));
                            lastProgressTime = System.currentTimeMillis();
                        }

                        if (currentLength == totalLength) {
                            sendEmptyMessage(FINISH);
                        }
                    }
                    break;
                case CANCEL:
                    synchronized (this) {
                        tempChildTaskCount++;
                        if (tempChildTaskCount == childTaskCount || mLastSate == PAUSE || mLastSate == ERROR) {
                            tempChildTaskCount = 0;
                            if (downloadCallback != null) {
                                downloadCallback.onProgress(0, totalLength, 0);
                            }
                            currentLength = 0;
                            if (isSupportRange) {
                                // TODO: 2018/7/28 取消下载时，更改数据状态，移除下载任务相关配置
                                DownloadManger.getInstance(context).getFileTaskMap().remove(url);
                                DownloadManger.getInstance(context).getCallbackMap().remove(url);
                                DownloadManger.getInstance(context).getDownloadDataMap().remove(url);
                                DownloadManger.getInstance(context).getProgressHandlerMap().remove(url);

                                DownloadData downloadData = BaseApplication.daoSession.getDownloadDataDao().queryBuilder()
                                        .where(DownloadDataDao.Properties.Url.eq(url)).unique();
                                downloadData.setStatus(NONE);
                                BaseApplication.daoSession.getDownloadDataDao().update(downloadData);

                                Utils.deleteFile(new File(path, name + ".temp"));
                            }
                            Utils.deleteFile(new File(path, name));
                            if (downloadCallback != null) {
                                downloadCallback.onCancel();
                            }

                            if (isNeedRestart) {
                                isNeedRestart = false;
                                DownloadManger.getInstance(context).innerRestart(url);
                            }
                        }
                    }
                    break;
                case PAUSE:
                    synchronized (this) {
                        if (isSupportRange) {
                            DownloadData downloadData =
                                    BaseApplication.daoSession.getDownloadDataDao()
                                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url)).unique();
                            downloadData.setCurrentLength(currentLength);
                            downloadData.setPercentage( Utils.getPercentage(currentLength, totalLength));
                            downloadData.setStatus(PAUSE);
                            BaseApplication.daoSession.getDownloadDataDao().update(downloadData);
                        }
                        tempChildTaskCount++;
                        if (tempChildTaskCount == childTaskCount) {
                            if (downloadCallback != null) {
                                downloadCallback.onPause();
                            }
                            tempChildTaskCount = 0;
                        }
                    }
                    break;
                case FINISH:
                    if (isSupportRange) {
                        Utils.deleteFile(new File(path, name + ".temp"));
                        // TODO: 2018/7/28 下载完成后，更改数据状态，移除相关配置

                        DownloadManger.getInstance(context).getFileTaskMap().remove(url);
                        DownloadManger.getInstance(context).getCallbackMap().remove(url);
                        DownloadManger.getInstance(context).getDownloadDataMap().remove(url);
                        DownloadManger.getInstance(context).getProgressHandlerMap().remove(url);
                        DownloadData downloadData = BaseApplication.daoSession.getDownloadDataDao().queryBuilder()
                                .where(DownloadDataDao.Properties.Url.eq(url)).unique();
                        downloadData.setStatus(FINISH);
                        BaseApplication.daoSession.getDownloadDataDao().update(downloadData);

                    }
                    if (downloadCallback != null) {
                        downloadCallback.onFinish(new File(path, name));
                    }
                    break;
                case DESTROY:
                    synchronized (this) {
                        if (isSupportRange) {

                            DownloadData downloadData =
                                    BaseApplication.daoSession.getDownloadDataDao()
                                            .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url)).unique();
                            downloadData.setCurrentLength(currentLength);
                            downloadData.setPercentage(Utils.getPercentage(currentLength, totalLength));
                            downloadData.setStatus(DESTROY);
                            BaseApplication.daoSession.getDownloadDataDao().update(downloadData);
                        }
                    }
                    break;
                case ERROR:
                    if (isSupportRange) {
                        DownloadData downloadData =
                                BaseApplication.daoSession.getDownloadDataDao()
                                        .queryBuilder().where(DownloadDataDao.Properties.Url.eq(url)).unique();
                        downloadData.setCurrentLength(currentLength);
                        downloadData.setPercentage( Utils.getPercentage(currentLength, totalLength));
                        downloadData.setStatus(ERROR);
                        BaseApplication.daoSession.getDownloadDataDao().update(downloadData);
                    }
                    if (downloadCallback != null) {
                        downloadCallback.onError((String) msg.obj);
                    }
                    break;
            }
        }
    };

    public DownloadProgressHandler(Context context, DownloadData downloadData, DownloadCallback downloadCallback) {
        this.context = context;
        this.downloadCallback = downloadCallback;

        this.url = downloadData.getUrl();
        this.path = downloadData.getPath();
        this.name = downloadData.getName();
        this.childTaskCount = downloadData.getChildTaskCount();

        DownloadData dbData =
                        BaseApplication.daoSession.getDownloadDataDao().queryBuilder()
                                .where(DownloadDataDao.Properties.Url.eq(url)).unique();
        this.downloadData = dbData == null ? downloadData : dbData;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    public DownloadData getDownloadData() {
        return downloadData;
    }

    public void setFileTask(FileTask fileTask) {
        this.fileTask = fileTask;
    }

    /**
     * 下载中退出时保存数据、释放资源
     */
    public void destroy() {
        if (mCurrentState == CANCEL || mCurrentState == PAUSE) {
            return;
        }
        fileTask.destroy();
    }

    /**
     * 暂停（正在下载才可以暂停）
     * 如果文件不支持断点续传则不能进行暂停操作
     */
    public void pause() {
        if (mCurrentState == PROGRESS) {
            fileTask.pause();
        }
    }

    /**
     * 取消（已经被取消、下载结束则不可取消）
     */
    public void cancel(boolean isNeedRestart) {
        this.isNeedRestart = isNeedRestart;
        if (mCurrentState == PROGRESS) {
            fileTask.cancel();
        } else if (mCurrentState == PAUSE || mCurrentState == ERROR) {
            mHandler.sendEmptyMessage(CANCEL);
        }
    }
}
