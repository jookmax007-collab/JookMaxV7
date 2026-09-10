package com.jookmax.v7.data.backup

import java.io.File

interface BackupProvider {


    suspend fun uploadBackup(
        backupFile: File
    ): BackupUploadResult



    suspend fun downloadBackup(
        backupId: String
    ): File



    suspend fun deleteBackup(
        backupId: String
    ): Boolean


}
