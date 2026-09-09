package com.jookmax.v7.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import com.jookmax.v7.data.local.entity.LearningExperienceEntity

@Dao
interface LearningExperienceDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        experience: LearningExperienceEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        experiences: List<LearningExperienceEntity>
    )

    @Query(
        """
        SELECT *
        FROM learning_experiences
        ORDER BY timestamp DESC
        LIMIT :limit
        """
    )
    fun getLatest(
        limit: Int
    ): Flow<List<LearningExperienceEntity>>

    @Query(
        """
        SELECT *
        FROM learning_experiences
        WHERE symbol = :symbol
        ORDER BY timestamp DESC
        """
    )
    fun getBySymbol(
        symbol: String
    ): Flow<List<LearningExperienceEntity>>

    @Query(
        """
        SELECT *
        FROM learning_experiences
        WHERE marketRegime = :regime
        ORDER BY timestamp DESC
        """
    )
    fun getByMarketRegime(
        regime: String
    ): Flow<List<LearningExperienceEntity>>

    @Query(
        """
        SELECT COUNT(*)
        FROM learning_experiences
        """
    )
    suspend fun count(): Int

    @Query(
        """
        DELETE FROM learning_experiences
        """
    )
    suspend fun deleteAll()
}
