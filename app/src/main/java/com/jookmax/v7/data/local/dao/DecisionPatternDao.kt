package com.jookmax.v7.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

import com.jookmax.v7.data.local.entity.DecisionPatternEntity


@Dao
interface DecisionPatternDao {


    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        pattern: DecisionPatternEntity
    )


    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        patterns: List<DecisionPatternEntity>
    )


    @Update
    suspend fun update(
        pattern: DecisionPatternEntity
    )


    @Query(
        """
        SELECT *
        FROM decision_patterns
        ORDER BY timestamp DESC
        """
    )
    fun getAll():
        Flow<List<DecisionPatternEntity>>



    @Query(
        """
        SELECT *
        FROM decision_patterns
        WHERE marketRegime = :regime
        ORDER BY confidenceScore DESC
        """
    )
    fun getByMarketRegime(
        regime: String
    ): Flow<List<DecisionPatternEntity>>



    @Query(
        """
        SELECT *
        FROM decision_patterns
        WHERE successfulCount > failedCount
        ORDER BY confidenceScore DESC
        """
    )
    fun getSuccessfulPatterns():
        Flow<List<DecisionPatternEntity>>



    @Query(
        """
        SELECT COUNT(*)
        FROM decision_patterns
        """
    )
    suspend fun count(): Int



}
