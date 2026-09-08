package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceAnalytics @Inject constructor() {



    fun calculateSuccessRate(

        history: ConfidenceHistory

    ): Double {



        if (history.feedbacks.isEmpty())

            return 0.0





        val successCount =

            history.feedbacks

                .count { it.success }





        return successCount.toDouble() /

                history.feedbacks.size.toDouble()

    }







    fun calculateAverageConfidence(

        history: ConfidenceHistory

    ): Double {



        if (history.feedbacks.isEmpty())

            return 0.0





        return history.feedbacks

            .map { it.initialConfidence }

            .average()

    }







    fun calculateFailureRate(

        history: ConfidenceHistory

    ): Double {



        return 1.0 -

                calculateSuccessRate(history)

    }







    fun detectOverConfidence(

        history: ConfidenceHistory

    ): Boolean {



        val avgConfidence =

            calculateAverageConfidence(history)



        val successRate =

            calculateSuccessRate(history)





        return avgConfidence > 0.75 &&

                successRate < 0.5

    }







    fun detectUnderConfidence(

        history: ConfidenceHistory

    ): Boolean {



        val avgConfidence =

            calculateAverageConfidence(history)



        val successRate =

            calculateSuccessRate(history)





        return avgConfidence < 0.4 &&

                successRate > 0.7

    }







    fun calculateAdjustment(

        history: ConfidenceHistory

    ): Double {



        return when {



            detectOverConfidence(history) ->

                0.90





            detectUnderConfidence(history) ->

                1.10





            else ->

                1.0

        }

    }


}