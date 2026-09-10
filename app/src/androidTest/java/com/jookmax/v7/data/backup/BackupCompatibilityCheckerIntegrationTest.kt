package com.jookmax.v7.data.backup


import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

import org.junit.Test



class BackupCompatibilityCheckerIntegrationTest {



    @Test
    fun backupCompatibilityChecker_shouldValidateVersions() {


        val checker =
            BackupCompatibilityChecker()



        val supportedBackup =

            BackupMetadataVersion(

                backupSchemaVersion = 1,

                appVersion = "7.x",

                databaseVersion = 7,

                brainVersion = "intelligence-core",

                createdAt = System.currentTimeMillis()

            )



        val compatibleResult =

            checker.validate(
                supportedBackup
            )



        assertTrue(

            compatibleResult.compatible

        )



        val futureBackup =

            BackupMetadataVersion(

                backupSchemaVersion = 2,

                appVersion = "8.x",

                databaseVersion = 8,

                brainVersion = "future-core",

                createdAt = System.currentTimeMillis()

            )



        val incompatibleResult =

            checker.validate(
                futureBackup
            )



        assertFalse(

            incompatibleResult.compatible

        )

    }

}
