package com.jookmax.v7.data.backup


import android.content.Context
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupManager @Inject constructor(

    private val exporter: BackupExporter,

    private val importer: BackupImporter,

    private val validator: BackupValidator

) {



    fun createBackup(

        context: Context,

        appVersion: String,

        databaseVersion: Int,

        brainVersion: String

    ): File {


        val metadata = BackupMetadata(

            backupId =
                exporter.generateBackupId(),

            createdAt =
                System.currentTimeMillis(),

            appVersion =
                appVersion,

            databaseVersion =
                databaseVersion,

            brainVersion =
                brainVersion

        )


        return exporter.createBackupFile(
            context,
            metadata
        )

    }





    fun validateBackup(

        file: File

    ): Boolean {


        val content =
            importer.readBackupFile(file)


        return validator.isValid(
            content
        )

    }





    fun hasBackup(

        context: Context

    ): Boolean {


        return importer.backupExists(
            context
        )

    }

}
