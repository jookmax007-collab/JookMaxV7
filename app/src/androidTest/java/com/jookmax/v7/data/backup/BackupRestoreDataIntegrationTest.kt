package com.jookmax.v7.data.backup


import androidx.room.Room

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.jookmax.v7.data.local.database.JookMaxDatabase
import com.jookmax.v7.data.local.entity.DecisionMemoryEntity

import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import org.junit.Test

import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BackupRestoreDataIntegrationTest {



    @Test
    fun backupAndRestore_shouldRecoverDecisionMemory() =
        runBlocking {



            val context =

                InstrumentationRegistry
                    .getInstrumentation()
                    .targetContext





            val database =

                Room.databaseBuilder(

                    context,

                    JookMaxDatabase::class.java,

                    "restore_test_database"

                )
                .allowMainThreadQueries()
                .build()





            val memoryDao =

                database.decisionMemoryDao()



            val patternDao =

                database.decisionPatternDao()



            val experienceDao =

                database.learningExperienceDao()





            val testMemory =

                DecisionMemoryEntity(

                    symbol = "XAUUSD",

                    trend = "UP",

                    rsi = 55.5,

                    volatility = 1.2,

                    action = "BUY",

                    confidence = 0.85,

                    approved = true,

                    reward = 25.0,

                    timestamp = System.currentTimeMillis()

                )





            memoryDao.insert(

                testMemory

            )





            val snapshotBuilder =

                BackupSnapshotBuilder(

                    memoryDao,

                    patternDao,

                    experienceDao

                )





            val backupService =

                BackupService(

                    snapshotBuilder,

                    BackupSerializer(),

                    BackupEncryption()

                )





            val backupFile =

                backupService.createBackup(

                    context,

                    "7.x",

                    7,

                    "intelligence-core"

                )





            assertTrue(

                backupFile.exists()

            )





            memoryDao.clear()





            assertEquals(

                0,

                memoryDao.count()

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





            val result =

                restoreService.restoreBackup(

                    backupFile

                )





            assertTrue(

                result.success

            )





            assertEquals(

                1,

                memoryDao.count()

            )





            backupFile.delete()



            database.close()

        }


}