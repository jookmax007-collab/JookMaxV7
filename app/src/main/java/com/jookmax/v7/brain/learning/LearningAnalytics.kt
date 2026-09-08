package com.jookmax.v7.brain.learning


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Learning Analytics
 *
 * Analyzes historical learning experiences.
 *
 * Flow:
 *
 * LearningExperienceManager
 *          |
 *          v
 * LearningAnalytics
 *          |
 *          v
 * Learning Performance Metrics
 *
 */
@Singleton
class LearningAnalytics @Inject constructor(

    private val experienceManager: LearningExperienceManager

) {



    fun calculateAverageReward(): Double {


        return experienceManager

            .getAverageReward()

    }





    fun calculateSuccessRate(): Double {


        return experienceManager

            .getSuccessRate()

    }





    fun calculateFailureRate(): Double {


        return 1.0 -

                calculateSuccessRate()

    }





    fun calculatePerformanceScore(): Double {


        val reward =

            calculateAverageReward()



        val successRate =

            calculateSuccessRate()



        return (

                reward * 0.6 +

                        successRate * 0.4

                )

            .coerceIn(

                0.0,

                1.0

            )

    }





    fun hasEnoughExperience(): Boolean {


        return experienceManager

            .getExperienceCount() >= 10

    }


}