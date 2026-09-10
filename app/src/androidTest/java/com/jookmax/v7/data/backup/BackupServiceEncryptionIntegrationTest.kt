package com.jookmax.v7.data.backup


import android.content.Context

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BackupServiceEncryptionIntegrationTest {



    @Test
    fun encryptedBackupFile_shouldBeCreated() = runBlocking {



        val context: Context =

            InstrumentationRegistry
                .getInstrumentation()
                .targetContext





        val database =

            androidx.room.Room.databaseBuilder(

                context,

                com.jookmax.v7.data.local.database.JookMaxDatabase::class.java,

                "test_backup_database"

            )
            .allowMainThreadQueries()
            .build()





        val snapshotBuilder =

            BackupSnapshotBuilder(

                database.decisionMemoryDao(),

                database.decisionPatternDao(),

                database.learningExperienceDao()

            )





        val backupService =

            BackupService(

                snapshotBuilder,

                BackupSerializer(),

                BackupEncryption()

            )





        val backupFile =

            backupService.createBackup(

                context = context,

                appVersion = "7.x",

                databaseVersion = 6,

                brainVersion = "intelligence-core"

            )





        assertTrue(

            backupFile.exists()

        )





        val encryptedContent =

            backupFile.readText()





        assertTrue(

            encryptedContent.isNotEmpty()

        )





        assertNotEquals(

            "{",

            encryptedContent.first().toString()

        )





        database.close()

    }


}
