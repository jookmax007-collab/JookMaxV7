package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.intelligence.memory.CurrentMarketPattern
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.brain.intelligence.memory.PatternMatcher
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Memory Retrieval Engine
 *
 * Phase 12.7
 *
 * Responsible for retrieving relevant memories
 * based on current market pattern.
 *
 *
 * Current Market Pattern
 *
 *        |
 *        v
 *
 * MemoryRetrievalEngine
 *
 *        |
 *        v
 *
 * PersistentDecisionMemoryRepository
 *
 *        |
 *        v
 *
 * Historical Decision Patterns
 *
 */
@Singleton
class MemoryRetrievalEngine @Inject constructor(


    private val repository:
        PersistentDecisionMemoryRepository,


    private val patternMatcher:
        PatternMatcher


) {



    suspend fun retrieve(

        currentPattern: CurrentMarketPattern

    ): List<DecisionPattern> {



        val history =

            repository.getAll()



        if (history.isEmpty()) {

            return emptyList()

        }



        return patternMatcher.match(

            current = currentPattern,

            history = history

        )

    }







    suspend fun latest():

            DecisionPattern? {


        return repository.getLatest()

    }







    suspend fun memorySize():

            Int {


        return repository.count()

    }


}