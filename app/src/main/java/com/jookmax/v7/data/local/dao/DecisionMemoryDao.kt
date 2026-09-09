package com.jookmax.v7.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.jookmax.v7.data.local.entity.DecisionMemoryEntity


@Dao
interface DecisionMemoryDao {


    @Insert
    suspend fun insert(
        decision: DecisionMemoryEntity
    )


    @Insert
    suspend fun insertAll(
        decisions: List<DecisionMemoryEntity>
    )


    @Query(
        "SELECT * FROM decision_memory ORDER BY timestamp DESC"
    )
    suspend fun getAll():
            List<DecisionMemoryEntity>


    @Query(
        "SELECT * FROM decision_memory ORDER BY timestamp DESC LIMIT 1"
    )
    suspend fun getLatest():
            DecisionMemoryEntity?


    @Query(
        "SELECT COUNT(*) FROM decision_memory"
    )
    suspend fun count():
            Int


    @Query(
        "DELETE FROM decision_memory"
    )
    suspend fun clear()

}
