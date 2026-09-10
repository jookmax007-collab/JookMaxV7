package com.jookmax.v7.data.backup


import androidx.room.Room

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.jookmax.v7.data.local.database.JookMaxDatabase
import com.jookmax.v7.data.local.entity.BackupRecordEntity

import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

import org.junit.Test

import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BackupRecordRepositoryIntegrationTest {



    @Test
    fun backupRecordRepository_shouldPersistAndManageRecords() =
        runBlocking {


            val context =
                InstrumentationRegistry
                    .getInstrumentation()
                    .targetContext



            val database =

                Room.databaseBuilder(

                    context,

                    JookMaxDatabase::class.java,

                    "backup_record_test_database"

                )
                .allowMainThreadQueries()
                .build()



            val dao =
                database.backupRecordDao()



            val repository =

                BackupRecordRepositoryImpl(
                    dao
                )



            val backup =

                BackupRecordEntity(

                    backupId = "backup_test_001",

                    fileName = "jookmax_backup.enc",

                    filePath = "/storage/test/jookmax_backup.enc",

                    fileSize = 2048,

                    createdAt = System.currentTimeMillis(),

                    appVersion = "7.x",

                    databaseVersion = 7,

                    brainVersion = "intelligence-core",

                    backupType = "LOCAL",

                    encrypted = true

                )



            repository.insertBackup(
                backup
            )



            assertEquals(

                1,

                repository.count()

            )



            val allBackups =

                repository.getAllBackups()



            assertEquals(

                1,

                allBackups.size

            )



            val latest =

                repository.getLatestBackup()



            assertNotNull(
                latest
            )



            assertEquals(

                "backup_test_001",

                latest!!.backupId

            )



            val found =

                repository.findBackup(
                    "backup_test_001"
                )



            assertNotNull(
                found
            )



            repository.deleteBackup(
                found!!
            )



            assertEquals(

                0,

                repository.count()

            )



            database.close()

        }


}
