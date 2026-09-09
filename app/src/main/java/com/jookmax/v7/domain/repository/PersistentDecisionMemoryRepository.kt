package com.jookmax.v7.domain.repository


import com.jookmax.v7.brain.intelligence.memory.DecisionPattern



interface PersistentDecisionMemoryRepository {


    suspend fun save(
        pattern: DecisionPattern
    )


    suspend fun saveAll(
        patterns: List<DecisionPattern>
    )


    suspend fun getAll():

            List<DecisionPattern>


    suspend fun getLatest():

            DecisionPattern?


    suspend fun count():

            Int


    suspend fun clear()

}
