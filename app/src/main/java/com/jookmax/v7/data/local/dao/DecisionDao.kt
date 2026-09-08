package com.jookmax.v7.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jookmax.v7.data.local.entity.DecisionEntity



@Dao
interface DecisionDao {



    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertDecision(
        decision: DecisionEntity
    )



    @Query(
        "SELECT * FROM decision ORDER BY timestamp DESC"
    )
    suspend fun getDecisions(): List<DecisionEntity>



    @Query(
        "SELECT * FROM decision ORDER BY timestamp DESC LIMIT 1"
    )
    suspend fun getLatestDecision(): DecisionEntity?



    @Query(
        "DELETE FROM decision"
    )
    suspend fun clearDecisions()



}