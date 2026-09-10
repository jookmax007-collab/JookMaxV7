package com.jookmax.v7.data.backup


import android.content.Context

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.assertTrue
import org.junit.Test

import org.junit.runner.RunWith

import java.io.File



@RunWith(AndroidJUnit4::class)
class BackupIntegrationTest {



    @Test
    fun createAndValidateBackup_shouldWork() {


        val context: Context =
            InstrumentationRegistry
                .getInstrumentation()
                .targetContext



        val exporter =
            BackupExporter()



        val importer =
            BackupImporter()



        val validator =
            BackupValidator()



        val backupId =
            exporter.generateBackupId()



        val metadata =
            BackupMetadata(

                backupId = backupId,

                createdAt =
                    System.currentTimeMillis(),

                appVersion = "7.x",

                databaseVersion = 6,

                brainVersion = "intelligence-core"

            )



        val file: File =
            exporter.createBackupFile(
                context,
                metadata
            )



        assertTrue(
            file.exists()
        )



        val content =
            importer.readBackupFile(
                file
            )



        assertTrue(

            validator.isValid(
                content
            )

        )



        assertTrue(

            importer.backupExists(
                context
            )

        )

    }

}
