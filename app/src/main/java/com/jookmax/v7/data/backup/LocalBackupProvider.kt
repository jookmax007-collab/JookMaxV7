package com.jookmax.v7.data.backup

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalBackupProvider @Inject constructor() : BackupProvider {



    private val storage =
        mutableMapOf<String, File>()



    override suspend fun uploadBackup(
        backupFile: File
    ): BackupUploadResult {


        val backupId =
            backupFile.nameWithoutExtension


        storage[backupId] =
            backupFile


        return BackupUploadResult(

            success = true,

            backupId = backupId,

            remotePath = backupFile.absolutePath,

            message = "Local backup stored successfully"

        )

    }





    override suspend fun downloadBackup(
        backupId: String
    ): File {


        return storage[backupId]
            ?: throw IllegalStateException(
                "Backup not found: $backupId"
            )

    }





    override suspend fun deleteBackup(
        backupId: String
    ): Boolean {


        return storage.remove(
            backupId
        ) != null

    }


}
