package com.hua.download.core;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * DownUpload数据库
 * Created by hua on 2018/7/22.
 */
@Database(entities = {DownRecord.class}, version = 1)
public abstract class DUDatabase extends RoomDatabase {
    public abstract DownRecordDao getDownRecordDao();
}
