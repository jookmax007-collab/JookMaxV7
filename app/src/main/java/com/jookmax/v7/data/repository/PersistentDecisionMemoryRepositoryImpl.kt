package com.jookmax.v7.data.repository


import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.data.local.dao.DecisionMemoryDao
import com.jookmax.v7.data.mapper.DecisionMemoryMapper
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PersistentDecisionMemoryRepositoryImpl @Inject constructor(

    private val decisionMemoryDao: DecisionMemoryDao

) : PersistentDecisionMemoryRepository {



    override suspend fun save(
        pattern: DecisionPattern
    ) {

        decisionMemoryDao.insert(
            DecisionMemoryMapper.toEntity(pattern)
        )

    }



    override suspend fun saveAll(
        patterns: List<DecisionPattern>
    ) {

        decisionMemoryDao.insertAll(
            patterns.map(
                DecisionMemoryMapper::toEntity
            )
        )

    }



    override suspend fun getAll():

            List<DecisionPattern> {


        return decisionMemoryDao
            .getAll()
            .map(
                DecisionMemoryMapper::fromEntity
            )

    }



    override suspend fun getLatest():

            DecisionPattern? {


        return decisionMemoryDao
            .getLatest()
            ?.let(
                DecisionMemoryMapper::fromEntity
            )

    }



    override suspend fun count():

            Int {


        return decisionMemoryDao.count()

    }



    override suspend fun clear() {


        decisionMemoryDao.clear()

    }

}
