package com.jookmax.v7.brain.intelligence


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Intelligence Memory Analyzer
 *
 * Analyzes previous decisions
 *
 * Flow:
 *
 * DecisionMemory
 *       |
 *       v
 * IntelligenceMemoryAnalyzer
 *       |
 *       v
 * IntelligenceEngine
 *
 */
@Singleton
class IntelligenceMemoryAnalyzer @Inject constructor(

    private val decisionMemory: DecisionMemory

) {



    fun calculateMemoryScore(): Double {


        return decisionMemory

            .calculateIntelligenceScore()

    }







    fun detectWeakPerformance(): Boolean {


        return calculateMemoryScore() < 0.45

    }







    fun detectStrongPerformance(): Boolean {


        return calculateMemoryScore() > 0.75

    }







    fun calculateAdjustment(): Double {


        return when {


            detectStrongPerformance() ->

                1.05





            detectWeakPerformance() ->

                0.95





            else ->

                1.0

        }

    }


}