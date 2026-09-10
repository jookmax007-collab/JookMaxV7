package com.jookmax.v7.data.backup


import android.content.Context

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupImporter @Inject constructor() {



    fun readBackupFile(
        file: File
    ): String {


        return file.readText()

    }





    fun backupExists(
        context: Context
    ): Boolean {


        val directory =
            File(
                context.filesDir,
                "backups"
            )


        val files =
            directory.listFiles()


        return directory.exists()
                &&
                files != null
                &&
                files.isNotEmpty()

    }


}
