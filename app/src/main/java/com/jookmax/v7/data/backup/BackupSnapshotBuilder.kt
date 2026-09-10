package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.dao.DecisionMemoryDao
import com.jookmax.v7.data.local.dao.DecisionPatternDao
import com.jookmax.v7.data.local.dao.LearningExperienceDao

import kotlinx.coroutines.flow.first

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupSnapshotBuilder @Inject constructor(


    private val decisionMemoryDao: DecisionMemoryDao,


    private val decisionPatternDao: DecisionPatternDao,


    private val learningExperienceDao: LearningExperienceDao


) {



    suspend fun build(


        appVersion: String,


        databaseVersion: Int,


        brainVersion: String


    ): BackupSnapshot {



        val now = System.currentTimeMillis()



        val metadata = BackupMetadata(


            backupId =

                "backup_$now",



            createdAt =

                now,



            appVersion =

                appVersion,



            databaseVersion =

                databaseVersion,



            brainVersion =

                brainVersion

        )





        val decisionMemory =

            decisionMemoryDao
                .getAll()





        val decisionPatterns =

            decisionPatternDao
                .getAll()
                .first()





        val learningExperiences =

            learningExperienceDao
                .getLatest(
                    limit = 1000
                )
                .first()





        return BackupSnapshot(


            metadata = metadata,


            decisionMemory = decisionMemory,


            decisionPatterns = decisionPatterns,


            learningExperiences = learningExperiences


        )

    }


}
