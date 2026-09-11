package com.jookmax.v7.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.jookmax.v7.data.local.entity.RewardExperienceEntity



@Dao
interface RewardExperienceDao {



    @Insert
    suspend fun insert(

        experience: RewardExperienceEntity

    )





    @Query(
        "SELECT * FROM reward_experiences ORDER BY timestamp DESC"
    )
    suspend fun getAll():

        List<RewardExperienceEntity>





    @Query(
        """
        SELECT * FROM reward_experiences
        WHERE success = 1
        ORDER BY timestamp DESC
        """
    )
    suspend fun getSuccessful():

        List<RewardExperienceEntity>





    @Query(
        """
        SELECT AVG(reward)
        FROM reward_experiences
        """
    )
    suspend fun getAverageReward():

        Double?





    @Query(
        "DELETE FROM reward_experiences"
    )
    suspend fun clear()

}