package com.jookmax.v7.domain.repository

import com.jookmax.v7.brain.learning.LearningExperience


interface LearningRepository {


    suspend fun saveExperience(
        experience: LearningExperience
    )


    suspend fun getExperiences():
            List<LearningExperience>


    suspend fun getLatestExperience():
            LearningExperience?


    suspend fun getExperienceCount():
            Int


    suspend fun getAverageReward():
            Double


    suspend fun getSuccessRate():
            Double


    suspend fun clearMemory()

}
