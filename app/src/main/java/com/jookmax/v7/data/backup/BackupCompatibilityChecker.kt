package com.jookmax.v7.data.backup


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupCompatibilityChecker @Inject constructor() {



    private val currentBackupSchemaVersion = 1



    fun isCompatible(

        metadata: BackupMetadataVersion

    ): Boolean {


        return metadata.backupSchemaVersion <= currentBackupSchemaVersion

    }





    fun validate(

        metadata: BackupMetadataVersion

    ): BackupCompatibilityResult {


        return if (isCompatible(metadata)) {


            BackupCompatibilityResult(

                compatible = true,

                message = "Backup version is compatible"

            )


        } else {


            BackupCompatibilityResult(

                compatible = false,

                message = "Backup version is not supported"

            )

        }

    }

}
