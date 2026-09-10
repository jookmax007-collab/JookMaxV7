package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.entity.DecisionMemoryEntity
import com.jookmax.v7.data.local.entity.DecisionPatternEntity
import com.jookmax.v7.data.local.entity.LearningExperienceEntity



data class BackupSnapshot(

    val metadata: BackupMetadata,


    val decisionMemory:

        List<DecisionMemoryEntity>,


    val decisionPatterns:

        List<DecisionPatternEntity>,


    val learningExperiences:

        List<LearningExperienceEntity>

)
