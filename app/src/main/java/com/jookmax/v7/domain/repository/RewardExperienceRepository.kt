package com.jookmax.v7.domain.repository


import com.jookmax.v7.brain.reward.model.RewardExperience


interface RewardExperienceRepository {


    suspend fun saveExperience(
        experience: RewardExperience
    )


    suspend fun getExperiences():
            List<RewardExperience>


    suspend fun getAverageReward():
            Double


    suspend fun clear()

}