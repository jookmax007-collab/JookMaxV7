package com.jookmax.v7.brain.intelligence.feedback


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Intelligence Feedback Analyzer
 *
 * Converts feedback history
 * into intelligence adjustment.
 *
 */
@Singleton
class IntelligenceFeedbackAnalyzer @Inject constructor(


    private val feedbackManager: IntelligenceFeedbackManager


) {



    fun calculatePerformance():

            Double {


        val reward =

            feedbackManager.averageReward()



        val successRate =

            feedbackManager.successRate()





        return (

                reward * 0.5 +

                successRate * 0.5

                )

            .coerceIn(

                0.0,

                1.0

            )

    }







    fun calculateAdjustment():

            Double {


        val performance =

            calculatePerformance()





        return when {


            performance > 0.75 ->

                1.05





            performance < 0.35 ->

                0.95





            else ->

                1.0

        }

    }


}
