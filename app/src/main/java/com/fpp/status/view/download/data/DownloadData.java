package com.fpp.status.view.download.data;

import android.os.Parcel;
import android.os.Parcelable;


import com.othershe.dutil.data.*;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import static com.fpp.status.view.download.data.Consts.START;


/**
 * Description:  下载数据及信息
 * Author: fpp
 * Date: 2018/7/27  11:14
 */
@Entity
public class DownloadData implements Parcelable {
    static final long serialVersionUID = 49L;

    @Id(autoincrement = true)
    private Long id;
    private Long postId;  // 文章id
    private String showName; // 显示名称
    private String name;  // 文件名
    private String imgUrl;  // 图片链接
    /**
     * 视频状态
     * 0：未下载； 1：下载中； 2：暂停中；  3：下载完成；
     */
    private int status = Consts.NONE;  // 下载状态
    private int totalLength;   // 文件大小
    private int currentLength;  // 进度大小
    private String url;  // 下载url
    private String path;  // 下载文件保存地址
    private Boolean select;  // 是否选定
    private int taskId;
    private float percentage;  // 百分比
    private int childTaskCount;  // 任务数量
    private long date;  // 日期
    private String lastModify;

    @Generated(hash = 664927048)
    public DownloadData() {

    }

    public DownloadData(String url, String path, String name) {
        this.url = url;
        this.path = path;
        this.name = name;
    }

    public DownloadData(String url, String path, String name, int childTaskCount) {
        this.url = url;
        this.path = path;
        this.name = name;
        this.childTaskCount = childTaskCount;
    }

    public DownloadData(String url, String path, int childTaskCount, String name
            , int currentLength, int totalLength, String lastModify, long date) {
        this.url = url;
        this.path = path;
        this.childTaskCount = childTaskCount;
        this.currentLength = currentLength;
        this.status = START;
        this.name = name;
        this.totalLength = totalLength;
        this.lastModify = lastModify;
        this.date = date;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalLength() {
        return this.totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getCurrentLength() {
        return this.currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getSelect() {
        return this.select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public float getPercentage() {
        return this.percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }



    public int getChildTaskCount() {
        return this.childTaskCount;
    }

    public void setChildTaskCount(int childTaskCount) {
        this.childTaskCount = childTaskCount;
    }

    public String getLastModify() {
        return this.lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeInt(this.currentLength);
        dest.writeInt(this.totalLength);
        dest.writeFloat(this.percentage);
        dest.writeInt(this.status);
        dest.writeInt(this.childTaskCount);
        dest.writeLong(this.date);
        dest.writeString(this.lastModify);
    }

    public String getShowName() {
        return this.showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    protected DownloadData(Parcel in) {
        this.url = in.readString();
        this.path = in.readString();
        this.name = in.readString();
        this.currentLength = in.readInt();
        this.totalLength = in.readInt();
        this.percentage = in.readFloat();
        this.status = in.readInt();
        this.childTaskCount = in.readInt();
        this.date = in.readLong();
        this.lastModify = in.readString();
    }

    @Generated(hash = 77942469)
    public DownloadData(Long id, Long postId, String showName, String name, String imgUrl,
                        int status, int totalLength, int currentLength, String url, String path,
                        Boolean select, int taskId, float percentage, int childTaskCount, long date,
                        String lastModify) {
        this.id = id;
        this.postId = postId;
        this.showName = showName;
        this.name = name;
        this.imgUrl = imgUrl;
        this.status = status;
        this.totalLength = totalLength;
        this.currentLength = currentLength;
        this.url = url;
        this.path = path;
        this.select = select;
        this.taskId = taskId;
        this.percentage = percentage;
        this.childTaskCount = childTaskCount;
        this.date = date;
        this.lastModify = lastModify;
    }

    public static final Creator<DownloadData> CREATOR = new Creator<DownloadData>() {
        @Override
        public DownloadData createFromParcel(Parcel source) {
            return new DownloadData(source);
        }

        @Override
        public DownloadData[] newArray(int size) {
            return new DownloadData[size];
        }
    };


}
