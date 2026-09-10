package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.dao.BackupRecordDao
import com.jookmax.v7.data.local.entity.BackupRecordEntity

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupRecordRepositoryImpl @Inject constructor(

    private val backupRecordDao: BackupRecordDao

) : BackupRecordRepository {



    override suspend fun insertBackup(
        backup: BackupRecordEntity
    ) {

        backupRecordDao.insertBackup(
            backup
        )

    }





    override suspend fun getAllBackups():
            List<BackupRecordEntity> {

        return backupRecordDao.getAllBackups()

    }





    override suspend fun getLatestBackup():
            BackupRecordEntity? {

        return backupRecordDao.getLatestBackup()

    }





    override suspend fun findBackup(
        backupId: String
    ):
            BackupRecordEntity? {

        return backupRecordDao.findBackup(
            backupId
        )

    }





    override suspend fun deleteBackup(
        backup: BackupRecordEntity
    ) {

        backupRecordDao.deleteBackup(
            backup
        )

    }





    override suspend fun clear() {

        backupRecordDao.clear()

    }





    override suspend fun count():
            Int {

        return backupRecordDao.count()

    }


}
