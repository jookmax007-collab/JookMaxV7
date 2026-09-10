package com.jookmax.v7.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.jookmax.v7.data.local.entity.BackupRecordEntity



@Dao
interface BackupRecordDao {



    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertBackup(
        backup: BackupRecordEntity
    )





    @Query(
        "SELECT * FROM backup_records ORDER BY createdAt DESC"
    )
    suspend fun getAllBackups():
            List<BackupRecordEntity>





    @Query(
        "SELECT * FROM backup_records ORDER BY createdAt DESC LIMIT 1"
    )
    suspend fun getLatestBackup():
            BackupRecordEntity?





    @Query(
        "SELECT * FROM backup_records WHERE backupId = :backupId LIMIT 1"
    )
    suspend fun findBackup(
        backupId: String
    ):
            BackupRecordEntity?





    @Delete
    suspend fun deleteBackup(
        backup: BackupRecordEntity
    )





    @Query(
        "DELETE FROM backup_records"
    )
    suspend fun clear()




    @Query(
        "SELECT COUNT(*) FROM backup_records"
    )
    suspend fun count():
            Int

}
