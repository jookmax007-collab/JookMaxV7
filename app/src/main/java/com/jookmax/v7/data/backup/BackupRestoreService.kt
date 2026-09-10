package com.jookmax.v7.data.backup


import android.content.Context

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


    private val decisionMemoryDao: DecisionMemoryDao,


    private val decisionPatternDao: DecisionPatternDao,


    private val learningExperienceDao: LearningExperienceDao


) {



    suspend fun restoreBackup(

        file: File

    ): BackupRestoreResult {


        return try {


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


                message =
                    "Backup restored successfully",


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