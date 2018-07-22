package com.hua.download.core;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * 下载记录表操作Dao
 * Created by hua on 2018/7/22.
 */
@Dao
public interface DownRecordDao {

    @Query("select * from DownRecord")
    List<DownRecord> getAllRecord();

    @Query("select * from DownRecord where recordId = :recordId")
    DownRecord getRecordById(int recordId);

    @Insert
    void addRecord(DownRecord downRecord);

    @Update
    void updateRecord(DownRecord downRecord);
}
