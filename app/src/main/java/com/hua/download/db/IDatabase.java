package com.hua.download.db;

import com.hua.download.core.DownRecord;

/**
 * 数据库存储接口
 * Created by hua on 2018/7/22.
 */

public interface IDatabase {

    /**
     * 保存下载记录
     *
     * @param downRecord 下载记录
     */
    void saveDownRecord(DownRecord downRecord);

    void updateDownRecord(DownRecord downRecord);

    DownRecord query(int recordId);

    void deleteDownRecord(int recordId);
}
