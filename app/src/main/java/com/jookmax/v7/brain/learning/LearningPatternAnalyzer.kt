package com.jookmax.v7.brain.learning

import com.jookmax.v7.domain.repository.LearningRepository

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Learning Pattern Analyzer
 *
 * Analyzes long term learning memory.
 *
 * Flow:
 *
 * LearningRepository
 *        |
 *        v
 * LearningPatternAnalyzer
 *        |
 *        v
 * Intelligence Layer
 *
 */
@Singleton
class LearningPatternAnalyzer @Inject constructor(

    private val learningRepository: LearningRepository

) {


    suspend fun calculatePatternScore(): Double {


        val experiences =

            learningRepository.getExperiences()



        if (experiences.isEmpty()) {

            return 0.0

        }



        val successRate =

            experiences

                .count {

                    it.success

                }

                .toDouble() /

                experiences.size.toDouble()



        val averageReward =

            experiences

                .map {

                    it.reward

                }

                .average()



        return (

                successRate * 0.6 +

                averageReward * 0.4

                )

            .coerceIn(

                0.0,

                1.0

            )

    }



    suspend fun calculateAdjustment(): Double {

        val score = calculatePatternScore()

        return when {

            score > 0.75 ->
                1.05

            score < 0.35 ->
                0.95

            else ->
                1.0
        }
    }



    suspend fun hasEnoughLearningData(): Boolean {


        return learningRepository

            .getExperienceCount() >= 20

    }

}

