package com.jookmax.v7.data.backup


data class BackupRestoreResult(


    val success: Boolean,


    val message: String,


    val restoredDecisionMemoryCount: Int = 0,


    val restoredDecisionPatternsCount: Int = 0,


    val restoredLearningExperiencesCount: Int = 0

)
