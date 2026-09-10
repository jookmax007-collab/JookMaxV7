package com.jookmax.v7.data.backup


import android.content.Context
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupExporter @Inject constructor() {



    fun createBackupFile(
        context: Context,
        metadata: BackupMetadata
    ): File {


        val backupDirectory = File(
            context.filesDir,
            "backups"
        )


        if (!backupDirectory.exists()) {

            backupDirectory.mkdirs()

        }



        val fileName =
            "jookmax_backup_${metadata.backupId}.json"



        val backupFile =
            File(
                backupDirectory,
                fileName
            )



        backupFile.writeText(

            """
            {
                "backupId":"${metadata.backupId}",
                "createdAt":${metadata.createdAt},
                "appVersion":"${metadata.appVersion}",
                "databaseVersion":${metadata.databaseVersion},
                "brainVersion":"${metadata.brainVersion}",
                "backupType":"${metadata.backupType}"
            }
            """.trimIndent()

        )



        return backupFile

    }





    fun generateBackupId(): String {

        return UUID.randomUUID()
            .toString()

    }


}
