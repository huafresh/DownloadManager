package com.hua.download.core;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 下载记录
 * Created by hua on 2018/7/22.
 */
@Entity
public class DownRecord {
    @PrimaryKey
    private int recordId;
    private String url;
    private String createTime;
    private String modifyTime;
    private String localPath;
    private long fileLength;
    private long downLength;
}
