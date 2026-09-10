package com.jookmax.v7.data.backup


import android.content.Context

import com.jookmax.v7.data.local.entity.BackupRecordEntity

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupManager @Inject constructor(


    private val backupService: BackupService,


    private val restoreService: BackupRestoreService,


    private val provider: BackupProvider,


    private val repository: BackupRecordRepository


) {



    suspend fun createBackup(


        context: Context,


        appVersion: String,


        databaseVersion: Int,


        brainVersion: String


    ): File {


        val file =

            backupService.createBackup(

                context,

                appVersion,

                databaseVersion,

                brainVersion

            )



        val uploadResult =

            provider.uploadBackup(
                file
            )



        val record =

            BackupRecordEntity(


                backupId =
                    uploadResult.backupId,


                fileName =
                    file.name,


                filePath =
                    file.absolutePath,


                fileSize =
                    file.length(),


                createdAt =
                    System.currentTimeMillis(),


                appVersion =
                    appVersion,


                databaseVersion =
                    databaseVersion,


                brainVersion =
                    brainVersion,


                backupType =
                    "LOCAL",


                encrypted =
                    true

            )



        repository.insertBackup(
            record
        )



        return file

    }






    suspend fun restoreBackup(

        backupId: String

    ): BackupRestoreResult {


        val backup =

            repository.findBackup(
                backupId
            )
                ?: return BackupRestoreResult(

                    success = false,

                    message = "Backup record not found"

                )



        val file =

            File(
                backup.filePath
            )



        return restoreService.restoreBackup(
            file
        )

    }






    suspend fun getBackupHistory():

            List<BackupRecordEntity> {


        return repository.getAllBackups()

    }






    suspend fun deleteBackup(

        backup: BackupRecordEntity

    ): Boolean {


        val deleted =

            provider.deleteBackup(

                backup.backupId

            )



        if (deleted) {


            repository.deleteBackup(
                backup
            )

        }



        return deleted

    }



}