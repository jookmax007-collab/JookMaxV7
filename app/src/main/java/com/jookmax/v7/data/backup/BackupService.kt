package com.jookmax.v7.data.backup


import android.content.Context

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupService @Inject constructor(


    private val snapshotBuilder: BackupSnapshotBuilder,


    private val serializer: BackupSerializer,


    private val exporter: BackupExporter


) {



    suspend fun createBackup(

        context: Context,

        appVersion: String,

        databaseVersion: Int,

        brainVersion: String

    ): File {



        val snapshot =

            snapshotBuilder.build(

                appVersion = appVersion,

                databaseVersion = databaseVersion,

                brainVersion = brainVersion

            )



        val json =

            serializer.serialize(
                snapshot
            )



        val fileName =

            "jookmax_full_backup_${snapshot.metadata.backupId}.json"



        val directory =

            File(

                context.filesDir,

                "backups"

            )



        if (!directory.exists()) {

            directory.mkdirs()

        }



        val file =

            File(

                directory,

                fileName

            )



        file.writeText(
            json
        )



        return file

    }



}
