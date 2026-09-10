package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.entity.BackupRecordEntity


interface BackupRecordRepository {


    suspend fun insertBackup(
        backup: BackupRecordEntity
    )


    suspend fun getAllBackups():
            List<BackupRecordEntity>



    suspend fun getLatestBackup():
            BackupRecordEntity?



    suspend fun findBackup(
        backupId: String
    ):
            BackupRecordEntity?



    suspend fun deleteBackup(
        backup: BackupRecordEntity
    )



    suspend fun clear()



    suspend fun count():
            Int

}
