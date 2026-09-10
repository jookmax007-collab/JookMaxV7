package com.jookmax.v7.data.backup


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

import org.junit.runner.RunWith

import java.io.File


@RunWith(AndroidJUnit4::class)
class LocalBackupProviderIntegrationTest {


    @Test
    fun uploadDownloadDelete_shouldWork() {


        val context =

            InstrumentationRegistry
                .getInstrumentation()
                .targetContext


        val provider =

            LocalBackupProvider()


        val backupFile =

            File(
                context.cacheDir,
                "test_backup_123.enc"
            )


        backupFile.writeText(
            "encrypted-test-backup"
        )


        try {


            val uploadResult =

                kotlinx.coroutines.runBlocking {

                    provider.uploadBackup(
                        backupFile
                    )

                }


            assertTrue(
                uploadResult.success
            )


            assertEquals(
                "test_backup_123",
                uploadResult.backupId
            )


            assertEquals(
                backupFile.absolutePath,
                uploadResult.remotePath
            )


            val downloadedFile =

                kotlinx.coroutines.runBlocking {

                    provider.downloadBackup(
                        uploadResult.backupId
                    )

                }


            assertEquals(
                backupFile.absolutePath,
                downloadedFile.absolutePath
            )


            assertEquals(
                "encrypted-test-backup",
                downloadedFile.readText()
            )


            val deleted =

                kotlinx.coroutines.runBlocking {

                    provider.deleteBackup(
                        uploadResult.backupId
                    )

                }


            assertTrue(
                deleted
            )


        } finally {


            backupFile.delete()

        }

    }

}
