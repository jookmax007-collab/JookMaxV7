package com.jookmax.v7.data.backup


import androidx.room.Room

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.jookmax.v7.data.local.database.JookMaxDatabase

import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import java.io.File



@RunWith(AndroidJUnit4::class)
class BackupManagerIntegrationTest {



    @Test
    fun backupManager_shouldCreateAndValidateBackupFlow() =
        runBlocking {


            val context =
                InstrumentationRegistry
                    .getInstrumentation()
                    .targetContext



            val database =

                Room.databaseBuilder(

                    context,

                    JookMaxDatabase::class.java,

                    "backup_manager_test_database"

                )
                .allowMainThreadQueries()
                .build()



            val memoryDao =
                database.decisionMemoryDao()



            val patternDao =
                database.decisionPatternDao()



            val experienceDao =
                database.learningExperienceDao()




            val backupService =

                BackupService(

                    BackupSnapshotBuilder(

                        memoryDao,

                        patternDao,

                        experienceDao

                    ),

                    BackupSerializer(),

                    BackupEncryption()

                )




            val restoreService =

                BackupRestoreService(

                    BackupEncryption(),

                    BackupDeserializer(),

                    BackupIntegrityValidator(),

                    BackupCompatibilityChecker(),

                    memoryDao,

                    patternDao,

                    experienceDao

                )




            val backupFile: File =

                backupService.createBackup(

                    context = context,

                    appVersion = "7.x",

                    databaseVersion = 7,

                    brainVersion = "intelligence-core"

                )




            val result =

                restoreService.restoreBackup(

                    backupFile

                )




            assert(

                result.success

            )




            backupFile.delete()

            database.close()

        }


}