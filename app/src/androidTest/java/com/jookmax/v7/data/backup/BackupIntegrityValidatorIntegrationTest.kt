package com.jookmax.v7.data.backup


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

import org.junit.runner.RunWith

import java.io.File



@RunWith(AndroidJUnit4::class)
class BackupIntegrityValidatorIntegrationTest {



    @Test
    fun checksumValidation_shouldDetectFileChanges() {


        val context =

            InstrumentationRegistry
                .getInstrumentation()
                .targetContext



        val validator =

            BackupIntegrityValidator()



        val backupFile =

            File(

                context.cacheDir,

                "integrity_test_backup.enc"

            )



        backupFile.writeText(

            "original-backup-content"

        )



        try {


            val checksum =

                validator.calculateChecksum(
                    backupFile
                )



            val validResult =

                validator.validate(

                    backupFile,

                    checksum

                )



            assertTrue(

                validResult.valid

            )



            backupFile.writeText(

                "modified-backup-content"

            )



            val invalidResult =

                validator.validate(

                    backupFile,

                    checksum

                )



            assertFalse(

                invalidResult.valid

            )


        } finally {


            backupFile.delete()


        }

    }

}