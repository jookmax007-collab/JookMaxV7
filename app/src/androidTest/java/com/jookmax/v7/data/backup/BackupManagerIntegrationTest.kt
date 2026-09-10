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
class BackupManagerIntegrationTest {



    @Test
    fun backupManager_shouldCreateRecordAndRestoreBackup() =
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



            val backupDao =

                database.backupRecordDao()



            memoryDao.insert(

                DecisionMemoryEntity(

                    symbol = "XAUUSD",

                    trend = "UP",

                    rsi = 60.0,

                    volatility = 1.5,

                    action = "BUY",

                    confidence = 0.9,

                    approved = true,

                    reward = 30.0,

                    timestamp = System.currentTimeMillis()

                )

            )



            val backupManager =

                BackupManager(

                    BackupService(

                        BackupSnapshotBuilder(

                            memoryDao,

                            patternDao,

                            experienceDao

                        ),

                        BackupSerializer(),

                        BackupEncryption()

                    ),

                    BackupRestoreService(

                        BackupEncryption(),

                        BackupDeserializer(),

                        memoryDao,

                        patternDao,

                        experienceDao

                    ),

                    LocalBackupProvider(),

                    BackupRecordRepositoryImpl(

                        backupDao

                    )

                )



            val backupFile =

                backupManager.createBackup(

                    context,

                    "7.x",

                    7,

                    "intelligence-core"

                )



            assertTrue(

                backupFile.exists()

            )



            assertEquals(

                1,

                backupDao.count()

            )



            val history =

                backupManager.getBackupHistory()



            assertEquals(

                1,

                history.size

            )



            memoryDao.clear()



            assertEquals(

                0,

                memoryDao.count()

            )



            val restoreResult =

                backupManager.restoreBackup(

                    history.first().backupId

                )



            assertTrue(

                restoreResult.success

            )



            assertEquals(

                1,

                memoryDao.count()

            )



            database.close()

        }

}