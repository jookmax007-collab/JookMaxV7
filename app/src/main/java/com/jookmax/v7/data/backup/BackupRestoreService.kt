package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.dao.DecisionMemoryDao
import com.jookmax.v7.data.local.dao.DecisionPatternDao
import com.jookmax.v7.data.local.dao.LearningExperienceDao

import java.io.File

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupRestoreService @Inject constructor(


    private val encryption: BackupEncryption,


    private val deserializer: BackupDeserializer,


    private val integrityValidator: BackupIntegrityValidator,


    private val compatibilityChecker: BackupCompatibilityChecker,


    private val decisionMemoryDao: DecisionMemoryDao,


    private val decisionPatternDao: DecisionPatternDao,


    private val learningExperienceDao: LearningExperienceDao


) {



    suspend fun restoreBackup(

        file: File

    ): BackupRestoreResult {


        return try {


            if (!file.exists()) {

                return BackupRestoreResult(

                    success = false,

                    message = "Backup file does not exist"

                )

            }



            val encryptedContent =

                file.readText()



            val json =

                encryption.decrypt(

                    encryptedContent

                )



            val snapshot =

                deserializer.deserialize(

                    json

                )



            val metadata =

                BackupMetadataVersion(

                    backupSchemaVersion = 1,

                    appVersion = snapshot.metadata.appVersion,

                    databaseVersion = snapshot.metadata.databaseVersion,

                    brainVersion = snapshot.metadata.brainVersion,

                    createdAt = snapshot.metadata.createdAt

                )



            val compatibility =

                compatibilityChecker.validate(

                    metadata

                )



            if (!compatibility.compatible) {


                return BackupRestoreResult(

                    success = false,

                    message = compatibility.message

                )

            }




            if (snapshot.decisionMemory.isNotEmpty()) {


                decisionMemoryDao.insertAll(

                    snapshot.decisionMemory

                )

            }



            if (snapshot.decisionPatterns.isNotEmpty()) {


                decisionPatternDao.insertAll(

                    snapshot.decisionPatterns

                )

            }



            if (snapshot.learningExperiences.isNotEmpty()) {


                learningExperienceDao.insertAll(

                    snapshot.learningExperiences

                )

            }




            BackupRestoreResult(

                success = true,

                message = "Backup restored successfully",

                restoredDecisionMemoryCount =
                    snapshot.decisionMemory.size,

                restoredDecisionPatternsCount =
                    snapshot.decisionPatterns.size,

                restoredLearningExperiencesCount =
                    snapshot.learningExperiences.size

            )



        } catch (e: Exception) {


            BackupRestoreResult(

                success = false,

                message =
                    e.message
                        ?: "Restore failed"

            )

        }

    }

}